package com.search.images.model.search;

import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("WeakerAccess")
@NoArgsConstructor
@Getter
public class Meta {
    int pageNum;
    boolean is_end;
    int pageable_count;
    int total_count;
}
