package com.xgaslan.blog.domain.models.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagViewModel {
    private UUID id;

    private String name;

    private Integer postCount;
}
