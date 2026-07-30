package com.designpatterns.behavioral.mediator;

/**
 * Holds a reference to the {@link ChatMediator} only — never to other users. Every outgoing
 * message is delegated to the mediator, which is the only object that knows the full set of
 * participants.
 */
public abstract class User {

    protected final ChatMediator mediator;
    protected final String name;

    protected User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void send(String message);

    public abstract void receive(String message, String senderName);
}
