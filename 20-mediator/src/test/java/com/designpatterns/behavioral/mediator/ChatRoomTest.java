package com.designpatterns.behavioral.mediator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChatRoomTest {

    @Test
    void allOtherUsersReceiveTheMessage() {
        ChatRoom room = new ChatRoom();
        ChatUser alice = new ChatUser(room, "Alice");
        ChatUser bob = new ChatUser(room, "Bob");
        ChatUser carol = new ChatUser(room, "Carol");
        room.addUser(alice);
        room.addUser(bob);
        room.addUser(carol);

        alice.send("Hello everyone!");

        assertThat(bob.getReceivedMessages()).containsExactly("Alice: Hello everyone!");
        assertThat(carol.getReceivedMessages()).containsExactly("Alice: Hello everyone!");
    }

    @Test
    void senderDoesNotReceiveItsOwnMessage() {
        ChatRoom room = new ChatRoom();
        ChatUser alice = new ChatUser(room, "Alice");
        ChatUser bob = new ChatUser(room, "Bob");
        room.addUser(alice);
        room.addUser(bob);

        alice.send("Hello Bob!");

        assertThat(alice.getReceivedMessages()).isEmpty();
    }

    @Test
    void multipleMessagesAreRecordedInOrder() {
        ChatRoom room = new ChatRoom();
        ChatUser alice = new ChatUser(room, "Alice");
        ChatUser bob = new ChatUser(room, "Bob");
        room.addUser(alice);
        room.addUser(bob);

        alice.send("First");
        bob.send("Second");
        alice.send("Third");

        assertThat(bob.getReceivedMessages()).containsExactly("Alice: First", "Alice: Third");
        assertThat(alice.getReceivedMessages()).containsExactly("Bob: Second");
    }

    @Test
    void userAddedMidConversationOnlyReceivesLaterMessages() {
        ChatRoom room = new ChatRoom();
        ChatUser alice = new ChatUser(room, "Alice");
        room.addUser(alice);
        alice.send("Before Carol joins");

        ChatUser carol = new ChatUser(room, "Carol");
        room.addUser(carol);
        alice.send("After Carol joins");

        assertThat(carol.getReceivedMessages()).containsExactly("Alice: After Carol joins");
    }
}
