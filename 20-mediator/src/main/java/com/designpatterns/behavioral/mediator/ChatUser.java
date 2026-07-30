package com.designpatterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/** Records every message it receives so tests can verify routing without inspecting the room. */
public final class ChatUser extends User {

    private final List<String> receivedMessages = new ArrayList<>();

    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, String senderName) {
        receivedMessages.add(senderName + ": " + message);
    }

    public List<String> getReceivedMessages() {
        return List.copyOf(receivedMessages);
    }
}
