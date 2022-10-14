package ru.practicum.explorewithme.mappers;

import ru.practicum.explorewithme.dto.сategory.CategoryDto;
import ru.practicum.explorewithme.models.Category;

public class CategoriesMapper {
    public static CategoryDto toCategoryDto(Category category) {
        if (category == null)
            return null;

        return new CategoryDto(category.getId(), category.getName());
    }


}
