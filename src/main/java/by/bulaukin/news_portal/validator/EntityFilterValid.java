package by.bulaukin.news_portal.validator;

import by.bulaukin.news_portal.web.model.request.valid.UpsertNewsRequestValid;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {EntityFilterValidValidator.class, UpsertNewsRequestValid.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityFilterValid {

    String message() default "EntityFilterValid error: ";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};
}
