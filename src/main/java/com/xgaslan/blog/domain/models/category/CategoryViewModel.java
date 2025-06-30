package com.xgaslan.blog.domain.models.category;

import com.xgaslan.blog.domain.models.base.BaseModel;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryViewModel extends BaseModel {
    private UUID id;

    private String name;

    private long postCount;
}
