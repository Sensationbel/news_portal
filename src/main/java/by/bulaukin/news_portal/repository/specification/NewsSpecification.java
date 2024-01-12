package by.bulaukin.news_portal.repository.specification;

import by.bulaukin.news_portal.model.Category;
import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.model.enums.NewsType;
import by.bulaukin.news_portal.web.model.filter.NewsFilter;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter filter) {
        return Specification.where(getByUserId(filter.getUserId()))
                .and(byCategory(filter.getNewsType()));
    }

    private static Specification<News> getByUserId(Long userId) {
        return (((root, query, criteriaBuilder) ->
            userId == null ? null : criteriaBuilder.equal(root.get("user").get("id"), userId)
        ));
    }

    private static Specification<News> byCategory(Character newsType) {
        return (((root, query, criteriaBuilder) -> {
//                Join<News, Category > categoryJoin = root.join("news");
               return newsType == null ? null :
                       criteriaBuilder.equal(root.get("category").get("type"), NewsType.fromCode(newsType));
        }
        ));

    }

}
