package by.bulaukin.news_portal.repository.specification;

import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.web.model.filter.CommentsFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public interface CommentsSpecification {

    static Specification<Comment> withFilter(CommentsFilter filter) {
        return Specification.where(byNewsId(filter.getNewsId()));
    }

    static Specification<Comment> byNewsId(Long newsId) {
        return ((root, query, criteriaBuilder) ->
        {
            Predicate equal = criteriaBuilder.equal(root.get("news").get("id"), newsId);
            return newsId == null ? null : equal;
        });


    }

}
