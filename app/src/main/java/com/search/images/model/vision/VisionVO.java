package com.search.images.model.vision;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class VisionVO {
    public static VisionVO INVALID = new VisionVO();

    @Setter
    boolean isInValid = true;
    ResultVO result;
}
