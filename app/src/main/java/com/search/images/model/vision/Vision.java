package com.search.images.model.vision;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class Vision {
    public static Vision INVALID = new Vision();

    @Setter
    boolean isInValid = true;
    VisionResult result;
}
