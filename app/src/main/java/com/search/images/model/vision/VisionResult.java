package com.search.images.model.vision;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VisionResult {
    int width;
    int height;
    List<Face> faces;
}
