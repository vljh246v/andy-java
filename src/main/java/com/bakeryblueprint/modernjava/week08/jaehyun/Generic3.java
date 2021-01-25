package com.bakeryblueprint.modernjava.week08.jaehyun;

public class Generic3<T> {



  public void method3(){
    try {

    }catch (T e){
      throw new T();
    }
  }

  public static void main(String[] args) {
  }

  private static T t;

  public static T method(){
    return t;
  }

}
