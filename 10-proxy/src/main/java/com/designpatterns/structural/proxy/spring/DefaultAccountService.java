package com.designpatterns.structural.proxy.spring;

public class DefaultAccountService implements AccountService {

    @Override
    public void withdraw(String accountId, double amount) {
        System.out.println("Withdrawing " + amount + " from account " + accountId);
    }
}
