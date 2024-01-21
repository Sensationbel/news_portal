package by.bulaukin.news_portal.validator;

import by.bulaukin.news_portal.model.enums.NewsType;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;

public class EntityFilterValidValidator implements ConstraintValidator<EntityFilterValid, EntityFilter> {
    @Override
    public boolean isValid(EntityFilter value, ConstraintValidatorContext context) {
        if (ObjectUtils.anyNull(value.getPageNum(), value.getPageSize())) {
            context
                    .buildConstraintViolationWithTemplate("Fields pagination mast be specify! If you specify minCost & maxCost than fields must be specify")
                    .addConstraintViolation();
            return false;
        }

        if (value.getNewsType() != null && isNewsType(value.getNewsType())) {
            context
                    .buildConstraintViolationWithTemplate("The newsType parameter must be conform to specification!")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean isNewsType(Character value) {
        return Arrays.stream(NewsType.values()).noneMatch(v -> v.getCode().equals(value));
    }


}
