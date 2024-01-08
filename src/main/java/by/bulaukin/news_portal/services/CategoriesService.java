package by.bulaukin.news_portal.services;

import by.bulaukin.news_portal.model.Category;

public interface CategoriesService {

    Category findCategoryByCode(Character code);

}
