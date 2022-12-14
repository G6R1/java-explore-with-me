package ru.practicum.explorewithme.services.impl;

import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.dto.categories.CategoryDto;
import ru.practicum.explorewithme.dto.categories.NewCategoryDto;
import ru.practicum.explorewithme.exceptions.ForbiddenException;
import ru.practicum.explorewithme.exceptions.NotFoundException;
import ru.practicum.explorewithme.mappers.CategoriesMapper;
import ru.practicum.explorewithme.models.Category;
import ru.practicum.explorewithme.models.Event;
import ru.practicum.explorewithme.services.CategoryService;
import ru.practicum.explorewithme.storages.CategoryRepository;
import ru.practicum.explorewithme.storages.event.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final CategoriesMapper categoriesMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               EventRepository eventRepository,
                               CategoriesMapper categoriesMapper) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
        this.categoriesMapper = categoriesMapper;
    }

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        List<Category> categoryList = categoryRepository.searchCategoriesPage(from, size);
        return categoryList.stream().map(categoriesMapper::toCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategory(Long catId) {
        Category category = getCategoryFromDB(catId);
        return categoriesMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto createCategory(NewCategoryDto categoryDto) {
        Category category = new Category(null, categoryDto.getName());
        Category newCategory = categoryRepository.save(category);
        return categoriesMapper.toCategoryDto(newCategory);
    }

    @Override
    public CategoryDto patchCategory(CategoryDto categoryDto) {
        Category oldCategory = getCategoryFromDB(categoryDto.getId());
        oldCategory.setName(categoryDto.getName());
        Category patchCategory = categoryRepository.save(oldCategory);
        return categoriesMapper.toCategoryDto(patchCategory);
    }

    @Override
    public void deleteCategory(Long catId) {
        Category category = getCategoryFromDB(catId);
        List<Event> eventList = eventRepository.findAllByCategory_id(catId);
        if (!eventList.isEmpty())
            throw new ForbiddenException("Find event with this category");
        categoryRepository.deleteById(catId);
    }

    private Category getCategoryFromDB(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category with id=" + categoryId + " was not found."));
    }
}
