package by.bulaukin.news_portal.web.model.request.valid;

import by.bulaukin.news_portal.validator.EntityFilterValid;
import by.bulaukin.news_portal.web.model.request.UpsertCommentRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class UpsertCommentRequestValid implements ConstraintValidator<EntityFilterValid, UpsertCommentRequest> {
    @Override
    public boolean isValid(UpsertCommentRequest value, ConstraintValidatorContext context) {
        if (ObjectUtils.anyNull(value.getNewsId(), value.getText())) {
            context.buildConstraintViolationWithTemplate("Fields userId & text must be specify!")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
