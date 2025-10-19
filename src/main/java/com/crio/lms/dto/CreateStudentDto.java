package com.crio.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateStudentDto {

    @NotBlank(message = "Required name")
    private String name;
}
