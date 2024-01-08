package by.bulaukin.news_portal.repository;

import by.bulaukin.news_portal.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CommentsRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

}
