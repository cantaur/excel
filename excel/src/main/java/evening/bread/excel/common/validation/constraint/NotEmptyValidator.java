package evening.bread.excel.common.validation.constraint;

import evening.bread.excel.common.validation.NotEmptyPattern;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * NotEmptyValidator.java
 *
 * @author
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmptyPattern, Object> {

    String code;

    @Override
    public void initialize(NotEmptyPattern constraintAnnotation) {
        this.code = constraintAnnotation.code();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(ObjectUtils.isEmpty(code)) {
            return false;
        }

        if(ObjectUtils.isEmpty(value)) {
            return false;
        }

        return true;
    }
}