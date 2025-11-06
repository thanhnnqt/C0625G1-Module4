package org.example.form_dang_ky.validate;


import org.example.form_dang_ky.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class UserValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        if ("".equals(userDto.getName())){
            errors.rejectValue("name","notEmpty","Yêu cầu nhập");
        }else if (!userDto.getName().matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$")){
            errors.rejectValue("name",null,"Không đúng định dạng");
        }
        String phone = userDto.getPhoneNumber();
        if (phone == null || phone.trim().isEmpty()) {
            errors.rejectValue("phoneNumber", "phone.empty", "Số điện thoại không được để trống");
        } else if (!phone.matches("^0\\d{9}$")) {
            errors.rejectValue("phoneNumber", "phone.format", "Số điện thoại phải bắt đầu bằng 0 và đủ 10 số");
        }
        Integer age = userDto.getAge();
        if (age == null) {
            errors.rejectValue("age", "age.empty", "Tuổi không được để trống");
        } else if (age < 18 || age > 120) {
            errors.rejectValue("age", "age.invalid", "Tuổi phải từ 18 đến 120");
        }
    }

}
