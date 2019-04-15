package com.search.images.model.search;

import com.search.images.service.network.SearchService;

import lombok.Getter;

@Getter
public class MetaVO {
    int pageNum;
    boolean is_end;
    int pageable_count;
    int total_count;

    //FIXME need pageNum?
    public MetaVO() {
        pageNum = SearchService.pageNumGenerator.incrementAndGet();
    }

}
