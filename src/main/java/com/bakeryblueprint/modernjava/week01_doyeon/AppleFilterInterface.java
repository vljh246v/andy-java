package com.bakeryblueprint.modernjava.week01_doyeon;

public class AppleFilterInterface implements AppleFilter {

    @Override
    public boolean isMached(Apple apple) {
        return "도연".equals(apple.getOwner());
    }
}