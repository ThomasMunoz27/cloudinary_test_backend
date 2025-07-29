package com.cloudinary_test.demo.Entities.Enums;

public enum ReactionType {
    LIKE,
    DISKLIKE;

    public static ReactionType fromInt(int value){
        return switch (value){
            case 0 -> LIKE;
            case 1 -> DISKLIKE;
            default -> throw  new IllegalArgumentException("Valor invalido para Reaccionar");
        };
    }
}
