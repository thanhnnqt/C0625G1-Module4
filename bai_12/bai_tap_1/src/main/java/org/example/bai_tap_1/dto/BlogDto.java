package org.example.bai_tap_1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.bai_tap_1.entity.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private String name;
    private String tittle;
    private String content;
    private Category category;
}
