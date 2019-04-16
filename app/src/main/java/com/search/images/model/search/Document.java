package com.search.images.model.search;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Document {
    public String collection;
    public String datetime;
    public String display_sitename;
    public String doc_url;
    public int width;
    public int height;
    public String image_url;
    public String thumbnail_url;
}