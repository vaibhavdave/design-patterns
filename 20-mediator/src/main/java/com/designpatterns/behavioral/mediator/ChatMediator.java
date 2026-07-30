package com.designpatterns.behavioral.mediator;

/** The single point every user talks through — no user ever calls another user directly. */
public interface ChatMediator {

    void sendMessage(String message, User sender);

    void addUser(User user);
}
