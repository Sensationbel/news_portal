package by.bulaukin.news_portal.repository.specification;

import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.model.enums.NewsType;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {

    static Specification<News> withFilter(EntityFilter filter) {
        return Specification.where(getByUserId(filter.getUserId()))
                .and(byCategory(filter.getNewsType()));
    }

    private static Specification<News> getByUserId(Long userId) {
        return (((root, query, criteriaBuilder) ->
            userId == null ? null : criteriaBuilder.equal(root.get("user").get("id"), userId)
        ));
    }

    private static Specification<News> byCategory(Character newsType) {
        return (((root, query, criteriaBuilder) ->  newsType == null ? null :
                       criteriaBuilder.equal(root.get("category").get("type"), NewsType.fromCode(newsType))

        ));

    }

}
