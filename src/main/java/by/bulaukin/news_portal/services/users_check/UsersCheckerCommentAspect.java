package by.bulaukin.news_portal.services.users_check;

import by.bulaukin.news_portal.exception.EntityNotRootException;
import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.services.CommentsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Aspect
@Component
@RequiredArgsConstructor
public class UsersCheckerCommentAspect implements UsersCheckerInterface {

    private final CommentsService commentsService;

    @Override
    @Around("@annotation(UsersCheckerComment)")
    public Object checkUsersId(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = getHttpServletRequest();
        Long userId = getUserId(request);
        Long contentId = getContentId(request);
        Comment comment = commentsService.findById(contentId);
        if (comment.getUser().getId() != userId) {
            throw new EntityNotRootException(MessageFormat.
                    format("User with id: {0} can't change this content!", userId));
        }

        Object result = point.proceed();

        return result;
    }
}
