package com.search.images.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DocumentVO {
    public String collection;
    public String datetime;
    public String display_sitename;
    public String doc_url;
    public int width;
    public int height;
    public String image_url;
    public String thumbnail_url;
}