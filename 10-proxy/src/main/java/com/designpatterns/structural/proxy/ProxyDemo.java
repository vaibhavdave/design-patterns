package com.designpatterns.structural.proxy;

import com.designpatterns.structural.proxy.spring.AccountService;
import com.designpatterns.structural.proxy.spring.DefaultAccountService;
import com.designpatterns.structural.proxy.spring.LoggingMethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;

public final class ProxyDemo {

    private ProxyDemo() {
    }

    public static void main(String[] args) {
        System.out.println("--- Virtual proxy (lazy init) ---");
        Image image = new ProxyImage("vacation.jpg");
        System.out.println("ProxyImage created; RealImage NOT loaded yet.");
        image.display(); // triggers the real load
        image.display(); // reuses the cached RealImage, no reload

        System.out.println();
        System.out.println("--- Spring AOP dynamic proxy (logging) ---");
        AccountService target = new DefaultAccountService();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new LoggingMethodInterceptor());
        AccountService proxy = (AccountService) factory.getProxy();

        proxy.withdraw("ACC-1", 50.0);
    }
}
