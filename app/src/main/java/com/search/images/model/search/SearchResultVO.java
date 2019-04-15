package com.search.images.model.search;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * query를 통해 search 된 결과 list
 */
@NoArgsConstructor
@Getter
public class SearchResultVO {
    MetaVO meta;
    List<DocumentVO> documents;
}
