package jdk.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yuanhai on 2016/3/30.
 */
class ClassC {

        public void show() {
                System.out.println("This is class C");
        }
}

class CGLIBProxyTest implements MethodInterceptor {
        private Object o;

        public Object getInstance(Object o) {
                this.o = o;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(o.getClass());
                enhancer.setCallback(this);
                return enhancer.create();
        }

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                throws Throwable {
                System.out.println("开始代理");
                methodProxy.invokeSuper(o, objects);
                return null;
        }

        public static void main(String[] args) {
                CGLIBProxyTest cglibProxyTest = new CGLIBProxyTest();
                ClassC c = (ClassC) cglibProxyTest.getInstance(new ClassC());
                c.show();
        }
}
