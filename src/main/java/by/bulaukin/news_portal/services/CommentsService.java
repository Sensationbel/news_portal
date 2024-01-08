package by.bulaukin.news_portal.services;

import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.web.model.filter.CommentsFilter;

import java.util.List;

public interface CommentsService {

    List<Comment> findAll();
    List<Comment> findAllByNewsId(CommentsFilter filter);
    Comment findById(Long id);
    Comment save(Comment comment);
    Comment update(Comment comment);
    void deleteById(Long id);

}
