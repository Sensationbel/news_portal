package by.bulaukin.news_portal.services.users_check_aspect.comment_aspect;

import by.bulaukin.news_portal.exception.EntityNotRootException;
import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.services.CommentsService;
import by.bulaukin.news_portal.services.users_check_aspect.UsersCheckerInterface;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class UsersCheckerCommentAspect implements UsersCheckerInterface {

    private final CommentsService commentsService;

//    @Override
    @Around("@annotation(UsersCheckerComment)")
    public Object checkUsersId(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = getHttpServletRequest();
        Long userId = getUserId(request);
        Long contentId = getContentId(request);
        Comment comment = commentsService.findById(contentId);
        if (!Objects.equals(comment.getUser().getId(), userId)) {
            throw new EntityNotRootException(MessageFormat.
                    format("User with id: {0} can't change this content!", userId));
        }

        return point.proceed();
    }
}
