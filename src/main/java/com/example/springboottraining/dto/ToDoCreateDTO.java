package com.example.springboottraining.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoCreateDTO {
    @NotBlank
    @Size(min = 5, max = 255)
    private String taskname;

    @NotBlank
    @Size(min = 5, max = 255)
    private String description;
}