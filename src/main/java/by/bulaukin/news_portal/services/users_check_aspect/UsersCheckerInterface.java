package by.bulaukin.news_portal.services.users_check_aspect;

import by.bulaukin.news_portal.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public interface UsersCheckerInterface {

    default HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) Objects.requireNonNull(requestAttributes)).getRequest();
    }

    default Long getUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");

        return userId != null ? Long.valueOf(userId) : null;
    }

    default Long getContentId(HttpServletRequest request) {
        return Long.valueOf(request.getParameter("contentId"));
    }

}
