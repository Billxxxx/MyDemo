package com.bill.billdemo;


import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Parent parent = new Parent();
        parent.method();
        parent.staticMethod();

        Child child = new Child();
        child.method();
        child.staticMethod();
    }
}


class Parent {

    public static void staticMethod() {
        System.out.println("Parent Static Method");
    }

    public void method() {
        System.out.println("Parent Method");
    }
}

class Child extends Parent {

    public static void staticMethod() {
        System.out.println("Child Static Method");
    }

    public void method() {
        System.out.println("Child Method");
    }
}
