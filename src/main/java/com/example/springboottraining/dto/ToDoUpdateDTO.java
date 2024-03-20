package com.example.springboottraining.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoUpdateDTO {

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    @Size(min = 5, max = 255)
    private String taskname;

    @NotBlank
    @Size(min = 5, max = 255)
    private String description;

    @NotNull
    private Boolean completed;

}