package by.bulaukin.news_portal.validator;

import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class EntityFilterValidValidator implements ConstraintValidator<EntityFilterValid, EntityFilter> {
    @Override
    public boolean isValid(EntityFilter value, ConstraintValidatorContext context) {
        return !ObjectUtils.anyNull(value.getPageNum(), value.getPageSize());
    }
}
