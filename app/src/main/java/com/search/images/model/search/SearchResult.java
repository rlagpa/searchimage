package com.search.images.model.search;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class SearchResult {

    public static SearchResult INVALID = new SearchResult();

    @Setter
    boolean invalid = true;

    Meta meta;
    List<Document> documents;
}
