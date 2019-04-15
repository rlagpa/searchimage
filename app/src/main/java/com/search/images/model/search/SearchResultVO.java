package com.search.images.model.search;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * query를 통해 search 된 결과 list
 */
@NoArgsConstructor
@Getter
public class SearchResultVO {

    public static SearchResultVO INVALID = new SearchResultVO();

    @Setter
    boolean invalid = true;

    MetaVO meta;
    List<DocumentVO> documents;
}
