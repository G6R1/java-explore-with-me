package ru.practicum.explorewithme.services;

import ru.practicum.explorewithme.dto.сategory.CategoryDto;
import ru.practicum.explorewithme.dto.сategory.NewCategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategory(Long catId);

    CategoryDto createCategory(NewCategoryDto categoryDto);

    CategoryDto patchCategory(CategoryDto categoryDto);

    void deleteCategory(Long catId);
}