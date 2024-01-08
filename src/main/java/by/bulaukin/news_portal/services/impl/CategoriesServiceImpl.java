package by.bulaukin.news_portal.services.impl;

import by.bulaukin.news_portal.model.Category;
import by.bulaukin.news_portal.model.enums.NewsType;
import by.bulaukin.news_portal.repository.CategoryRepository;
import by.bulaukin.news_portal.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByCode(Character code) {
        return categoryRepository.findCategoryByType(NewsType.fromCode(code));
    }
}
