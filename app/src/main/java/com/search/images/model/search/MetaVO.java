package com.search.images.model.search;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MetaVO {
    int pageNum;
    boolean is_end;
    int pageable_count;
    int total_count;
}
