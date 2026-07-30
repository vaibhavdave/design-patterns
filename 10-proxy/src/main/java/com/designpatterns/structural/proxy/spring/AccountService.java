package com.designpatterns.structural.proxy.spring;

/**
 * Deliberately just an interface — Spring's {@code ProxyFactory} builds a JDK dynamic proxy for
 * it (no CGLIB subclassing needed) because it has an interface to proxy.
 */
public interface AccountService {

    void withdraw(String accountId, double amount);
}
