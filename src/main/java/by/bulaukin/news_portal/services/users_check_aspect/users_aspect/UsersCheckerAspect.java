package by.bulaukin.news_portal.services.users_check_aspect.users_aspect;

import by.bulaukin.news_portal.exception.EntityNotFoundException;
import by.bulaukin.news_portal.model.User;
import by.bulaukin.news_portal.services.UsersService;
import by.bulaukin.news_portal.services.users_check_aspect.UsersCheckerInterface;
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
public class UsersCheckerAspect implements UsersCheckerInterface {

    private final UsersService usersService;

//    @Override
    @Around("@annotation(UsersChecker)")
    public Object checkUsersId(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = getHttpServletRequest();
        Long userId = getUserId(request);
        if (userId != null) {
            User user = usersService.findById(userId);

            if (user == null) {
                throw new EntityNotFoundException(MessageFormat.
                        format("User with id: {0} can't change this content!", userId));
            }
        }


        return point.proceed();
    }
}
