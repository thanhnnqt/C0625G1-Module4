package org.example.song_manage.validate;

import org.example.song_manage.dto.SongDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SongValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SongDto songDto = (SongDto) target;

        String name = songDto.getName();
        if ("".equals(name)) {
            errors.rejectValue("name", "notEmpty", "Yêu cầu nhập");
        } else if (name.length() > 800) {
            errors.rejectValue("name", null, "Không được quá 800 ký tự");
        }

        String singer = songDto.getSinger();
        if ("".equals(singer)) {
            errors.rejectValue("singer", "notEmpty", "Yêu cầu nhập");
        } else if (!singer.matches("^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$")) {
            errors.rejectValue("singer", null, "Không đúng định dạng");
        } else if (singer.length() > 300) {
            errors.rejectValue("singer", null, "Không được quá 300 ký tự");
        }

        String category = songDto.getCategory();
        if ("".equals(category)) {
            errors.rejectValue("category", "notEmpty", "Yêu cầu nhập");
        } else if (!category.matches("^[0-9A-Za-z\\s,]+$")) {
            errors.rejectValue("category", null, "Không đúng định dạng");
        } else if (category.length() > 300) {
            errors.rejectValue("category", null, "Không được quá 300 ký tự");
        }
    }
}
