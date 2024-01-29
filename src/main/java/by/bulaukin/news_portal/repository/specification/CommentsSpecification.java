package by.bulaukin.news_portal.repository.specification;

import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.model.enums.NewsType;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public interface CommentsSpecification {

    static Specification<Comment> withFilter(EntityFilter filter) {
        return Specification.where(byNewsId(filter.getNewsId()))
                .and(byUserId(filter.getUserId()))
                .and(byNewsType(filter.getNewsType()));
    }

    static Specification<Comment> byNewsType(Character newsType) {
        return ((root, query, criteriaBuilder) -> {
            if(newsType == null) {
                return null;
            }
            NewsType type = NewsType.fromCode(newsType);
            return criteriaBuilder.equal(root.get("news")
                    .get("category").get("type"), type);
        });
    }

    static Specification<Comment> byUserId(Long userId) {
        return ((root, query, criteriaBuilder) -> {
            Predicate equal = criteriaBuilder.equal(root.get("user").get("id"), userId);
            return userId == null || userId == 0 ? null : equal;
        });
    }

    static Specification<Comment> byNewsId(Long newsId) {
        return ((root, query, criteriaBuilder) ->
        {
            Predicate equal = criteriaBuilder.equal(root.get("news").get("id"), newsId);
            return newsId == null || newsId == 0 ? null : equal;
        });
    }

}
