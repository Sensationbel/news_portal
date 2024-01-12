package by.bulaukin.news_portal.services.impl;

import by.bulaukin.news_portal.exception.EntityNotFoundException;
import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.repository.CommentsRepository;
import by.bulaukin.news_portal.repository.specification.CommentsSpecification;
import by.bulaukin.news_portal.services.CommentsService;
import by.bulaukin.news_portal.services.users_check.UsersCheckerComment;
import by.bulaukin.news_portal.web.model.filter.CommentsFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    @Override
    public List<Comment> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public List<Comment> findAllByNewsId(CommentsFilter filter) {
        return commentsRepository.findAll(CommentsSpecification.withFilter(filter));
    }

    @Override
    public Comment findById(Long id) {
        return commentsRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("Comment with id: {0} wasn't found", id)));
    }

    @Override
    public Comment save(Comment comment) {
        return commentsRepository.save(comment);
    }

    @SneakyThrows
    @Override
    @UsersCheckerComment
    public Comment update(Comment comment) {
        Comment exsistComment = findById(comment.getId());

        BeanUtils.copyProperties(exsistComment, comment);
        return commentsRepository.save(comment);
    }

    @Override
    @UsersCheckerComment
    public void deleteById(Long id) {
        commentsRepository.deleteById(id);
    }
}

