package com.search.images.model;

import com.search.images.service.network.SearchService;

public class MetaVO {
    int pageNum;
    boolean is_end;
    int pageable_count;
    int total_count;

    public MetaVO() {
        pageNum = SearchService.pageNumGenerator.incrementAndGet();
    }

    public int getPageNum() {
        return pageNum;
    }

    public boolean getIsEnd() {
        return is_end;
    }
}
