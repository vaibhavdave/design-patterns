package com.designpatterns.structural.proxy.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * The advice a Spring {@code ProxyFactory} weaves around every call to the target. This is the
 * "add behavior around a real object" half of a proxy — conceptually identical to what {@link
 * com.designpatterns.structural.proxy.ProxyImage} does by hand, except Spring generates the
 * proxy class at runtime instead of us writing it out ourselves.
 */
public class LoggingMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before: " + invocation.getMethod().getName());
        try {
            return invocation.proceed();
        } finally {
            System.out.println("After: " + invocation.getMethod().getName());
        }
    }
}
