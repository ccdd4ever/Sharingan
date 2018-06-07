package JDK.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yuanhai on 2016/3/30.
 */

interface AbstracClass {
    void show();
}


class ClassA implements AbstracClass {
    public void show() {
        System.out.println("This is classA");
    }
}


class ClassB implements AbstracClass {
    public void show() {
        System.out.println("This is classB");
    }
}


class Invoker implements InvocationHandler {
    Object ac;

    public Invoker(Object ac) {
        this.ac = ac;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(ac, args);
        return null;
    }
}


class JDKDynamicProxtTest {
    public static void main(String[] args) {
        Invoker invoker = new Invoker(new ClassA());
        AbstracClass acl =
                (AbstracClass) Proxy.newProxyInstance(AbstracClass.class.getClassLoader(), new Class[] {AbstracClass.class}, invoker);
        acl.show();
    }
}

