package com.xgaslan.blog.domain.models.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateModel {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between {min} and {max} characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Name can only contain alphanumeric characters and spaces")
    private String name;
}
