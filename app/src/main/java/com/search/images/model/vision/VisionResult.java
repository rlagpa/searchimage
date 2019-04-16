package com.search.images.model.vision;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("WeakerAccess")
@NoArgsConstructor
@Getter
public class VisionResult {
    int width;
    int height;
    List<Face> faces;
}
