package ru.practicum.explorewithme.mappers;

import org.mapstruct.Mapper;
import ru.practicum.explorewithme.dto.categories.CategoryDto;
import ru.practicum.explorewithme.models.Category;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {

    CategoryDto toCategoryDto(Category category);
}
