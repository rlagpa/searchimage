package com.search.images.model.vision;

import com.search.images.R;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FaceVO {
    FaceAttributeVO facial_attributes;
    float score;

    public boolean isFemail() {
        return facial_attributes.getGender().getFemale() > facial_attributes.getGender().getMale();
    }

    public int age() {
        return Math.round(facial_attributes.getAge());
    }

    public int accuracy() {
        return Math.round(score * 100);
    }
}
