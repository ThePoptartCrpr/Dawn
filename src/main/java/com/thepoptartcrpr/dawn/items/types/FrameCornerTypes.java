package com.thepoptartcrpr.dawn.items.types;

import lombok.Getter;

public enum FrameCornerTypes {

    TYPE_TOPRIGHT(0),
    TYPE_TOPLEFT(1),
    TYPE_BOTTOMLEFT(2),
    TYPE_BOTTOMRIGHT(3);

    @Getter
    private int id;

    FrameCornerTypes(int id) {
        this.id = id;
    }

}
