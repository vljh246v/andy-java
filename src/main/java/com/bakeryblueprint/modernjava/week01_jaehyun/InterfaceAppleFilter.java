package com.bakeryblueprint.modernjava.week01_jaehyun;

public class InterfaceAppleFilter implements AppleFilter{
    @Override
    public boolean isMatched(Apple apple) {
        return "김현수".equals(apple.getOwner())
                && "red".equals(apple.getColor())
                && apple.getWeight() > 10;
    }
}
