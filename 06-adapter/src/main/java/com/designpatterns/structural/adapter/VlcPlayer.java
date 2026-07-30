package com.designpatterns.structural.adapter;

public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // This player only knows how to play vlc; ignoring mp4 is intentional for the demo.
        throw new UnsupportedOperationException("VlcPlayer cannot play mp4 files");
    }
}
