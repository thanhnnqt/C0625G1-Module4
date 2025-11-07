package org.example.product_manage.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
