package com.designpatterns.structural.proxy.spring;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpringProxyTest {

    @Test
    void proxyImplementsTheSameInterfaceAsTheTarget() {
        AccountService target = new DefaultAccountService();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new LoggingMethodInterceptor());

        AccountService proxy = (AccountService) factory.getProxy();

        assertThat(proxy).isInstanceOf(AccountService.class);
        assertThat(proxy).isNotSameAs(target);
    }

    @Test
    void interceptorRunsBeforeAndAfterTheRealMethodInvocation() {
        List<String> callOrder = new ArrayList<>();
        AccountService target = new AccountService() {
            @Override
            public void withdraw(String accountId, double amount) {
                callOrder.add("target.withdraw");
            }
        };

        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice((org.aopalliance.intercept.MethodInterceptor) invocation -> {
            callOrder.add("before");
            Object result = invocation.proceed();
            callOrder.add("after");
            return result;
        });
        AccountService proxy = (AccountService) factory.getProxy();

        proxy.withdraw("ACC-1", 25.0);

        assertThat(callOrder).containsExactly("before", "target.withdraw", "after");
    }

    @Test
    void proxyFactoryUsesJdkDynamicProxyBecauseTargetIsAnInterface() {
        AccountService target = new DefaultAccountService();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new LoggingMethodInterceptor());

        AccountService proxy = (AccountService) factory.getProxy();

        assertThat(java.lang.reflect.Proxy.isProxyClass(proxy.getClass())).isTrue();
    }
}
