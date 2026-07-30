package com.designpatterns.structural.adapter;

public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        // This player only knows how to play mp4; ignoring vlc is intentional for the demo.
        throw new UnsupportedOperationException("Mp4Player cannot play vlc files");
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}
