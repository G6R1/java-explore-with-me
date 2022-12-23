# Explore-with-me
Cервис – афиша мероприятий. Проект реализован в рамках курса "Java-Разработчик" Яндекс Практикум.

## ﻿﻿Функциональность

Cервис позволяет пользователям создавать анонсы проводимых мероприятий и записываться на участие в них. Поиск информации о мероприятиях с возможностью фильтрации доступен так же незарегистрированным пользователям. Администраторы афиши могут создавать подборки событий, облегчающие пользователям поиск схожих мероприятий. Дополнительно встроен сервис сбора статистики для отслеживания наиболее популярных запросов.

## Стэк
Java 11+, Spring Boot, Apache Maven, PostgreSQL, Hibernate, Docker.

## API

Основной сервис URL: http://localhost:8080.  
Сервис сбора статистики URL: http://localhost:9090.  
Спецификация основного сервиса: https://github.com/G6R1/java-explore-with-me/blob/main/ewm-main-service-spec.json.  
Спецификация сервис сбора статистики: https://github.com/G6R1/java-explore-with-me/blob/main/ewm-stat-service-spec.json.  

### Public endpoints
* GET /events - получение событий с возможностью фильтрации.
* GET /events/{id} - получение информации об опубликованном событии по его id.
###
* GET /compilations - получение списка подборок событий.
* GET /compilations/{compId} - получение информации о подборке событии по её id. 
###
* GET /categories - получение списка категорий событий.
* GET /categories/{catId} - получение информации о категории по её id. 
### Private endpoints
* GET /users/{userId}/events - получить событий, добавленных текущим пользователем. 
* PATCH /users/{userId}/events - изменение события, добавленного текущим пользователем. 
* POST /users/{userId}/events - добавление нового события.
* GET /users/{userId}/events/{eventId} - получение информации о событии по его id.
* PATCH /users/{userId}/events/{eventId} - отмена события, добавленного текущим пользователем. 
* GET /users/{userId}/events/{eventId}/requests - получение информации о запросах на участие в событии текущего пользователя.
* PATCH /users/{userId}/events/{eventId}/requests/{reqId}/comfirm - подтверждение чужой заявки на участие в событии текущего пользователя.
* PATCH /users/{userId}/events/{eventId}/requests/{reqId}/reject - отклонение чужой заявки на участие в событии текущего пользователя
###
* GET /users/{userId}/requests - получение информации о заявках текущего пользователя на участие в чужих событиях.
* POST /users/{userId}/requests - добавление запроса от текущего пользователя на участие в событии.
* PATCH /users/{userId}/requests/{reqId}/cancel - отмена своего запроса на участие в событии.
### Admin endpoints
### Private endpoints
* GET /admin/events - поиск событий. 
* PUT /admin/events/{eventId} - редактирование события. 
* PATCH /admin/events/{userId}/events - публикация события.
* PATCH /admin/events/{userId}/events/{eventId} - отклонение события.
###
* POST /admin/categories - добавление новой категории событий.
* PATCH /admin/categories  - изменение категории.
* DELETE /admin/categories/{catId} - удаление категории.
###
* POST /admin/users - создание пользователя.
* GET /admin/users  - получение информации о пользователе.
* DELETE /admin/users/{userId} - удаление пользователя.
###
* POST /admin/compilations - добавление новой подборки событий.
* DELETE /admin/compilations/{compId}  - удаление подборки.
* DELETE /admin/compilations/{compId}/events/{eventId} - удалить событие из подборки. 
* PATCH /admin/compilations/{compId}/events/{eventId} - добавить событие в подборку.
* DELETE /admin/compilations/{compId}/pin  - открепить подборку на главной странице
* PATCH /admin/compilations/{compId}/pin - закрепить подборку на главной странице.
###
### Statistics service endpoints
* POST /hit - cохранение информации о том, что к эндпоинту был запрос.
* GET /stats - получение статистики по посещениям.


## Схема базы данных
### Main service
![BD_scheme](https://github.com/G6R1/java-explore-with-me/raw/main/schemaBD_main.png)
### Statistics service
![BD_scheme](https://github.com/G6R1/java-explore-with-me/raw/main/schemaBD_stat.png)

