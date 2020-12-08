package com.bakeryblueprint.modernjava.week01_jaehyun;

public interface A {
    default void hello(){
        System.out.println("A : hello()");
    }
}
