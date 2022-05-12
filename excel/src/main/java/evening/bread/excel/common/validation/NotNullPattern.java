package evening.bread.excel.common.validation;

import evening.bread.excel.common.validation.constraint.NotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Constraint(validatedBy = NotNullValidator.class)
public @interface NotNullPattern {

    String message() default "{custom.validation.invalid.code}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String code() default "";
}