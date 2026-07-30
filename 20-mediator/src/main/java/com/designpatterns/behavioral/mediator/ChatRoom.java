package com.designpatterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete mediator. Knows every {@link User} in the room; each user knows only this object. That
 * asymmetry is the whole pattern: N users need N references total (one each, to the mediator)
 * instead of N*(N-1) references to each other.
 */
public final class ChatRoom implements ChatMediator {

    private final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }
}
