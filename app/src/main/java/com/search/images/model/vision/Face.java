package com.search.images.model.vision;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Face {
    FaceAttribute facial_attributes;
    float score;
    final String FEMALE = "여성";
    final String MALE = "남성";

    public String toString(String age, String gender, String accurancy) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(age, age()));
        builder.append("   ");
        builder.append(String.format(gender, isFemale()? FEMALE : MALE));
        builder.append("   ");
        builder.append(String.format(accurancy, accuracy()));

        return builder.toString();
    }

    private boolean isFemale() {
        return facial_attributes.getGender().getFemale() > facial_attributes.getGender().getMale();
    }

    private int age() {
        return Math.round(facial_attributes.getAge());
    }

    private int accuracy() {
        return Math.round(score * 100);
    }

}
