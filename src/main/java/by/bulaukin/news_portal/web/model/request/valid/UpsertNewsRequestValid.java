package by.bulaukin.news_portal.web.model.request.valid;

import by.bulaukin.news_portal.model.enums.NewsType;
import by.bulaukin.news_portal.validator.EntityFilterValid;
import by.bulaukin.news_portal.web.model.request.UpsertNewsRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;

public class UpsertNewsRequestValid implements ConstraintValidator<EntityFilterValid, UpsertNewsRequest> {

    @Override
    public boolean isValid(UpsertNewsRequest value, ConstraintValidatorContext context) {
        if (ObjectUtils.anyNull(value.getCodeNews())) {

            context.buildConstraintViolationWithTemplate("Code news must be not null!").addConstraintViolation();
        return false;
        }

        if(isCodeNews(value.getCodeNews())){
            context.buildConstraintViolationWithTemplate("Code news is not supported!").addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean isCodeNews(Character codeNews) {
        return Arrays.stream(NewsType.values()).noneMatch(v -> v.getCode().equals(codeNews));
    }
}
