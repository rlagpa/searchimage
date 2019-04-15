package com.search.images.model.vision;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FaceVO {
    FaceAttributeVO facial_attributes;
    float score;
}
