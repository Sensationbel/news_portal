package by.bulaukin.news_portal.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EntityFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityFilterValid {

    String message() default "Fields pagination mast be specify! If you specify minCost & maxCost than fields must be specify";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};
}
