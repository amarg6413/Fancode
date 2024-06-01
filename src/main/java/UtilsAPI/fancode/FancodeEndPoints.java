package UtilsAPI.fancode;

import lombok.Getter;

@Getter
public enum FancodeEndPoints {
    TODOs("/todos"),
    POSTs("/posts"),
    COMMENTS("/comments"),
    ALBUMS("/albums"),
    PHOTOS("/photos"),
    USERS("/users");
    private String endPoint;

    FancodeEndPoints(String endPoint) {
        this.endPoint = endPoint;
    }
}
