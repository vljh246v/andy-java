package com.bakeryblueprint.modernjava.week04;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicExample {

  private final AtomicBoolean locked = new AtomicBoolean(false);

  // get(), set(), getAndSet(), compareAndSet() 등을 제공
  public boolean lock() {
    return this.locked.compareAndSet(false, true);
  }
}