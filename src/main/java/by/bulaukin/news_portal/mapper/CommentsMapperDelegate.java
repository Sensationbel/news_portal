package by.bulaukin.news_portal.mapper;

import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.services.NewsService;
import by.bulaukin.news_portal.services.UsersService;
import by.bulaukin.news_portal.web.model.request.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentsMapperDelegate implements CommentsMapper {

    @Autowired
    private UsersService usersService;
    @Autowired
    private NewsService newsService;

    @Override
    public Comment requestToComment(Long userId, UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setUser(usersService.findById(userId));
        comment.setNews(newsService.findById(request.getNewsId()));
        comment.setText(request.getText());

        return comment;
    }

    @Override
    public Comment requestToComment(Long contentId, Long userId, UpsertCommentRequest request) {
        Comment existedComment = requestToComment(userId, request);
        existedComment.setId(contentId);
        return existedComment;
    }
}
