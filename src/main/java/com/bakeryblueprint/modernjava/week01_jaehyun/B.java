package com.bakeryblueprint.modernjava.week01_jaehyun;

public interface B extends A{
    default void hello(){
        System.out.println("B : hello()");
    }
}
