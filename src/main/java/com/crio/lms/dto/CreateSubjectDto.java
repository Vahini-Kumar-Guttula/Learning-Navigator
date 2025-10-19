package com.crio.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSubjectDto {

    @NotBlank(message = "Required name")
    private String subjectName;
}

