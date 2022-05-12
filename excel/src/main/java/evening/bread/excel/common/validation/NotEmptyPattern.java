package evening.bread.excel.common.validation;

import evening.bread.excel.common.validation.constraint.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * NotEmptyPattern.java
 *
 * @author
 */
@Retention(RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmptyPattern {

    String message() default "{custom.validation.invalid.code}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String code() default "";
}
