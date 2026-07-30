package com.designpatterns.structural.adapter;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AudioPlayerTest {

    @Test
    void playsMp3Natively() {
        MediaPlayer player = new AudioPlayer();
        String output = captureStdOut(() -> player.play("mp3", "song.mp3"));

        assertThat(output).contains("Playing mp3 file: song.mp3");
    }

    @Test
    void playsVlcThroughTheSameMediaPlayerInterfaceViaTheAdapter() {
        // The caller only ever holds a MediaPlayer reference — it never touches
        // AdvancedMediaPlayer, VlcPlayer, or MediaAdapter directly.
        MediaPlayer player = new AudioPlayer();
        String output = captureStdOut(() -> player.play("vlc", "movie.vlc"));

        assertThat(output).contains("Playing vlc file: movie.vlc");
    }

    @Test
    void playsMp4ThroughTheSameMediaPlayerInterfaceViaTheAdapter() {
        MediaPlayer player = new AudioPlayer();
        String output = captureStdOut(() -> player.play("mp4", "clip.mp4"));

        assertThat(output).contains("Playing mp4 file: clip.mp4");
    }

    @Test
    void rejectsUnsupportedFormats() {
        MediaPlayer player = new AudioPlayer();

        assertThatThrownBy(() -> player.play("avi", "video.avi"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void mediaAdapterDirectlyTranslatesVlcAndMp4Calls() {
        MediaPlayer vlcAdapter = new MediaAdapter("vlc");
        String output = captureStdOut(() -> vlcAdapter.play("vlc", "another.vlc"));

        assertThat(output).contains("Playing vlc file: another.vlc");
    }

    private static String captureStdOut(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream capture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capture));
        try {
            action.run();
        } finally {
            System.setOut(originalOut);
        }
        return capture.toString();
    }
}
