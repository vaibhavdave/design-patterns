package com.designpatterns.creational.factorymethod;

/**
 * Maps a channel name to its concrete creator. This is the only place in the module that knows
 * every concrete {@link NotificationFactory} subtype exists — everything else (see
 * {@link NotificationFactory#send}) works purely through the abstract creator and product types.
 */
public enum NotificationChannel {

    EMAIL {
        @Override
        public NotificationFactory factory() {
            return new EmailNotificationFactory();
        }
    },
    SMS {
        @Override
        public NotificationFactory factory() {
            return new SmsNotificationFactory();
        }
    },
    PUSH {
        @Override
        public NotificationFactory factory() {
            return new PushNotificationFactory();
        }
    };

    public abstract NotificationFactory factory();
}
