package ru.practicum.explorewithme.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explorewithme.EventState;
import ru.practicum.explorewithme.ParticipationStatus;
import ru.practicum.explorewithme.dto.participationrequest.ParticipationRequestDto;
import ru.practicum.explorewithme.exceptions.BadRequestException;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.NotFoundException;
import ru.practicum.explorewithme.mappers.ParticipationRequestMapper;
import ru.practicum.explorewithme.models.Event;
import ru.practicum.explorewithme.models.ParticipationRequest;
import ru.practicum.explorewithme.models.User;
import ru.practicum.explorewithme.services.EventService;
import ru.practicum.explorewithme.services.ParticipationRequestService;
import ru.practicum.explorewithme.storages.ParticipationRequestRepository;
import ru.practicum.explorewithme.storages.UserRepository;
import ru.practicum.explorewithme.storages.event.EventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ParticipationRequestServiceImpl implements ParticipationRequestService {

    private final ParticipationRequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final EventService eventService;
    private final UserRepository userRepository;
    private final ParticipationRequestMapper participationRequestMapper;

    public ParticipationRequestServiceImpl(ParticipationRequestRepository requestRepository,
                                           EventRepository eventRepository,
                                           EventService eventService,
                                           ParticipationRequestMapper participationRequestMapper,
                                           UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
        this.userRepository = userRepository;
        this.participationRequestMapper = participationRequestMapper;
    }

    @Override
    public List<ParticipationRequestDto> getMyEventParticipantRequests(Long userId, Long eventId) {
        Event event = getEventFromDB(eventId);
        if (!Objects.equals(event.getInitiator().getId(), userId))
            throw new ForbiddenException("User is not initiator of event");

        if (!event.getRequestModeration())
            return new ArrayList<>();


        List<ParticipationRequest> requestList = requestRepository.findAllByEvent_Id(eventId);

        return requestList.stream()
                .map(participationRequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationRequestDto moderateParticipantRequest(Long userId, Long eventId, Long reqId, Boolean confirm) {
        Event event = getEventFromDB(eventId);
        if (!Objects.equals(event.getInitiator().getId(), userId))
            throw new ForbiddenException("User is not initiator of event");

        ParticipationRequest request = getRequestFromDB(reqId);
        if (!Objects.equals(request.getEvent().getId(), eventId))
            throw new ForbiddenException("This is a request for another event");

        if (!event.getRequestModeration())
            throw new ForbiddenException("Requests for this event do not require moderation");

        if (request.getStatus() != ParticipationStatus.PENDING)
            throw new ForbiddenException("Request status is not PENDING");

        //???????? ???????????? ?????????? ??????????????????
        if (!confirm) {
            request.setStatus(ParticipationStatus.REJECTED);
            ParticipationRequest patchedRequest = requestRepository.save(request);
            return participationRequestMapper.toParticipationRequestDto(patchedRequest);
        }

        if (event.getParticipantLimit() != 0
                && event.getParticipantLimit() - event.getConfirmedRequests() == 0)
            throw new ForbiddenException("Requests limit reached");

        request.setStatus(ParticipationStatus.CONFIRMED);
        ParticipationRequest patchedRequest = requestRepository.save(request);

        eventService.changeConfirmRequests(true, eventId);
        //???????? ?????? ???????? ?????????????????? ?????????????????? ????????????
        if (event.getParticipantLimit() - event.getConfirmedRequests() == 1)
            requestRepository.changeStatusForEventRequests(ParticipationStatus.REJECTED,
                    eventId,
                    ParticipationStatus.PENDING);

        return participationRequestMapper.toParticipationRequestDto(patchedRequest);
    }

    @Override
    public List<ParticipationRequestDto> getMyRequests(Long userId) {
        List<ParticipationRequest> requestList = requestRepository.findAllByRequester_Id(userId);
        return requestList.stream()
                .map(participationRequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto createRequest(Long userId, Long eventId) {

        if (requestRepository.findByEvent_IdAndRequester_Id(eventId, userId).isPresent())
            throw new ForbiddenException("Repeated request");

        Event event = getEventFromDB(eventId);
        if (Objects.equals(event.getInitiator().getId(), eventId))
            throw new BadRequestException("User is initiator of event");
        if (event.getState() != EventState.PUBLISHED)
            throw new ForbiddenException("Event is not PUBLISHED");
        if (event.getParticipantLimit() != 0
                && event.getParticipantLimit() - event.getConfirmedRequests() == 0)
            throw new ForbiddenException("Requests limit reached");

        User user = getUserFromDB(userId);

        ParticipationRequest newRequest = new ParticipationRequest(null,
                LocalDateTime.now(),
                event,
                user,
                event.getRequestModeration() ? ParticipationStatus.PENDING : ParticipationStatus.CONFIRMED);
        ParticipationRequest savedRequest = requestRepository.save(newRequest);
        return participationRequestMapper.toParticipationRequestDto(savedRequest);
    }

    @Override
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        ParticipationRequest request = getRequestFromDB(requestId);
        if (!Objects.equals(request.getRequester().getId(), userId))
            throw new ForbiddenException("userId and requesterId are different");

        switch (request.getStatus()) {
            case PENDING:
            case REJECTED:
                request.setStatus(ParticipationStatus.CANCELED);
                break;
            case CANCELED:
                break;
            case CONFIRMED:
                request.setStatus(ParticipationStatus.CANCELED);
                eventService.changeConfirmRequests(false, request.getEvent().getId());
                break;
            default:
        }

        ParticipationRequest cancelRequest = requestRepository.save(request);
        return participationRequestMapper.toParticipationRequestDto(cancelRequest);
    }

    private ParticipationRequest getRequestFromDB(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("ParticipationRequest with id="
                        + requestId
                        + " was not found."));
    }

    private Event getEventFromDB(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event with id=" + eventId + " was not found."));
    }

    private User getUserFromDB(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id=" + userId + " was not found."));
    }
}
