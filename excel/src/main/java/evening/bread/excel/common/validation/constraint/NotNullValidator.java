package evening.bread.excel.common.validation.constraint;

import evening.bread.excel.common.validation.NotNullPattern;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;


public class NotNullValidator implements ConstraintValidator<NotNullPattern, Object> {

    String code;

    @Override
    public void initialize(NotNullPattern constraintAnnotation) {
        this.code = constraintAnnotation.code();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(ObjectUtils.isEmpty(code)) {
            return false;
        }

        if(Objects.isNull(value)) {
            return false;
        }

        if(value instanceof List) {
            if(((List) value).size() == 0){
                return false;
            }

        }

        return true;
    }
}
