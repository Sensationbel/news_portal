package by.bulaukin.news_portal.services.users_check;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public interface UsersCheckerInterface {

    Object checkUsersId(ProceedingJoinPoint point) throws Throwable;

    default HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    default Long getUserId(HttpServletRequest request) {
        return Long.valueOf(request.getParameter("userId"));
    }

    default Long getContentId(HttpServletRequest request) {
        return Long.valueOf(request.getParameter("contentId"));
    }

}
