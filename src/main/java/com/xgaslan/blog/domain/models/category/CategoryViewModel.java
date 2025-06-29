package com.xgaslan.blog.domain.models.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryViewModel {
    private UUID id;

    private String name;

    private long postCount;
}
