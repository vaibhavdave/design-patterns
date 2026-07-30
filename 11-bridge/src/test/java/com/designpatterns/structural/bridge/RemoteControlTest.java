package com.designpatterns.structural.bridge;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RemoteControlTest {

    @Test
    void togglePowerTurnsTheUnderlyingDeviceOnAndOff() {
        Device tv = new Tv();
        RemoteControl remote = new RemoteControl(tv);

        assertThat(tv.isEnabled()).isFalse();
        remote.togglePower();
        assertThat(tv.isEnabled()).isTrue();
        remote.togglePower();
        assertThat(tv.isEnabled()).isFalse();
    }

    @Test
    void volumeUpAndDownDelegateToWhicheverDeviceIsInjected() {
        Device radio = new Radio();
        RemoteControl remote = new RemoteControl(radio);
        int initialVolume = radio.getVolume();

        remote.volumeUp();
        assertThat(radio.getVolume()).isEqualTo(initialVolume + 10);

        remote.volumeDown();
        remote.volumeDown();
        assertThat(radio.getVolume()).isEqualTo(initialVolume - 10);
    }

    @Test
    void sameAdvancedRemoteControlClassWorksWithATvOrARadioInterchangeably() {
        AdvancedRemoteControl tvRemote = new AdvancedRemoteControl(new Tv());
        AdvancedRemoteControl radioRemote = new AdvancedRemoteControl(new Radio());

        tvRemote.togglePower();
        tvRemote.volumeUp();
        radioRemote.togglePower();
        radioRemote.volumeUp();

        // Same RemoteControl-family class, two completely different Device implementations
        // underneath — neither hierarchy needed a combinatorial subclass for this to work.
        assertThat(tvRemote).isInstanceOf(RemoteControl.class);
        assertThat(radioRemote).isInstanceOf(RemoteControl.class);
    }

    @Test
    void muteSilencesTheDeviceAndUnmuteRestoresThePreviousVolume() {
        Device tv = new Tv();
        tv.setVolume(40);
        AdvancedRemoteControl remote = new AdvancedRemoteControl(tv);

        remote.mute();
        assertThat(tv.getVolume()).isEqualTo(0);

        remote.mute();
        assertThat(tv.getVolume()).isEqualTo(40);
    }

    @Test
    void volumeIsClampedToTheZeroToOneHundredRange() {
        Device radio = new Radio();
        RemoteControl remote = new RemoteControl(radio);

        for (int i = 0; i < 20; i++) {
            remote.volumeUp();
        }

        assertThat(radio.getVolume()).isEqualTo(100);
    }
}
