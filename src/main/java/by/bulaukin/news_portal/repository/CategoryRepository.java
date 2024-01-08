package by.bulaukin.news_portal.repository;

import by.bulaukin.news_portal.model.Category;
import by.bulaukin.news_portal.model.enums.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByType(NewsType type);

}
