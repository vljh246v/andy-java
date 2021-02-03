package com.bakeryblueprint.modernjava.week09;

class AutoCloseableImplA implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println(this.getClass().getName() + "는 종료되었습니다. ");
    }
}

class AutoCloseableImplB implements AutoCloseable{
    private AutoCloseableImplA a;

    public AutoCloseableImplB(AutoCloseableImplA a) {
        this.a = a;
    }

    @Override
    public void close() throws Exception {
        System.out.println(this.getClass().getName() + "는 종료되었습니다.");
        // AutoCloseableImplA 도 종료 처리함
        a.close();
    }
}

public class AutoCloseableImpl {
    public static void main(String[] args) {
//        try(AutoCloseableImplB b = new AutoCloseableImplB(new AutoCloseableImplA()) ) {
        try(AutoCloseableImplA a = new AutoCloseableImplA();
            AutoCloseableImplB b = new AutoCloseableImplB(a);) {
            System.out.println("try with resource Test Completed.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
