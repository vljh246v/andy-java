package com.bakeryblueprint.modernjava.week08.jaehyun;

public class TestGenericObj<A, B, C> {

  private A a;

  public void init(A a){
    this.a = a;
  }

  B parse(A a){
    return (B)a;
  }
  C to(B b) {
    return (C)b;
  }

}
