package com.search.images.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FaceVO {
    FaceAttributeVO facial_attributes;
    float score;
}
