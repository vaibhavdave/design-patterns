package com.designpatterns.creational.factorymethod;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationFactoryTest {

    @Test
    void emailFactoryProducesAnEmailNotification() {
        // createNotification() is protected, not public: the test lives in the same package,
        // mirroring how a concrete creator subclass would see it, without widening the API.
        NotificationFactory factory = new EmailNotificationFactory();
        assertThat(factory.createNotification()).isInstanceOf(EmailNotification.class);
    }

    @Test
    void smsFactorySendRoutesThroughSmsRendering() {
        NotificationFactory factory = new SmsNotificationFactory();
        String result = factory.send("Hello");
        assertThat(result).contains("[SMS]").contains("Hello");
    }

    @Test
    void pushFactorySendRoutesThroughPushRendering() {
        NotificationFactory factory = new PushNotificationFactory();
        String result = factory.send("Wake up");
        assertThat(result).contains("[Push]").contains("Wake up");
    }

    @Test
    void smsNotificationTruncatesMessagesLongerThan160Characters() {
        String longMessage = "x".repeat(200);
        String rendered = new SmsNotification().render(longMessage);
        assertThat(rendered).hasSize("[SMS] ".length() + 160);
    }

    @Test
    void channelEnumSelectsTheMatchingConcreteFactory() {
        assertThat(NotificationChannel.EMAIL.factory()).isInstanceOf(EmailNotificationFactory.class);
        assertThat(NotificationChannel.SMS.factory()).isInstanceOf(SmsNotificationFactory.class);
        assertThat(NotificationChannel.PUSH.factory()).isInstanceOf(PushNotificationFactory.class);
    }
}
