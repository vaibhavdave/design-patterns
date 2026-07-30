package com.designpatterns.behavioral.mediator;

/** Three users in one room, communicating only through the mediator — never directly. */
public final class MediatorDemo {

    public static void main(String[] args) {
        ChatRoom room = new ChatRoom();

        ChatUser alice = new ChatUser(room, "Alice");
        ChatUser bob = new ChatUser(room, "Bob");
        ChatUser carol = new ChatUser(room, "Carol");

        room.addUser(alice);
        room.addUser(bob);
        room.addUser(carol);

        alice.send("Hello everyone!");
        bob.send("Hey Alice!");

        System.out.println("Alice received: " + alice.getReceivedMessages());
        System.out.println("Bob received:   " + bob.getReceivedMessages());
        System.out.println("Carol received: " + carol.getReceivedMessages());
    }
}
