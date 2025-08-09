package com.example.fetchtest.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCreateDto {

    @NotBlank(message = "Назва є обов'язковою")
    @Size(min = 2, max = 100, message = "Назва повинна містити від 2 до 100 символів")
    private String name;

    @Size(max = 500, message = "Опис не повинен перевищувати 500 символів")
    private String description;

    @NotNull(message = "Ціна є обов'язковою")
    @DecimalMin(value = "0.0", inclusive = false, message = "Ціна повинна бути більше 0")
    @Digits(integer = 10, fraction = 2, message = "Ціна повинна містити не більше 10 цілих цифр і 2 десяткових")
    private BigDecimal price;

    @Size(max = 150, message = "Короткий опис не повинен перевищувати 150 символів")
    private String shortDescription;

    @NotBlank(message = "Категорія є обов'язковою")
    @Size(min = 2, max = 50, message = "Категорія повинна містити від 2 до 50 символів")
    private String category;

    @NotNull(message = "Кількість є обов'язковою")
    @Min(value = 0, message = "Кількість не може бути від'ємною")
    private Integer quantity;

    @Size(max = 50, message = "Бренд не повинен перевищувати 50 символів")
    private String brand;
}