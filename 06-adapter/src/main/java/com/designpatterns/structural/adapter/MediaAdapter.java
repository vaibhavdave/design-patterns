package com.designpatterns.structural.adapter;

/**
 * Translates the {@link MediaPlayer#play(String, String)} call into whichever
 * {@link AdvancedMediaPlayer} method matches the requested format. This is the adapter: it
 * implements the interface the client expects and internally holds/drives the interface the
 * client does not (and should not have to) know about.
 */
public class MediaAdapter implements MediaPlayer {

    private final AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            this.advancedMediaPlayer = new VlcPlayer();
        } else if ("mp4".equalsIgnoreCase(audioType)) {
            this.advancedMediaPlayer = new Mp4Player();
        } else {
            throw new IllegalArgumentException("MediaAdapter does not support: " + audioType);
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer.playVlc(fileName);
        } else if ("mp4".equalsIgnoreCase(audioType)) {
            advancedMediaPlayer.playMp4(fileName);
        } else {
            throw new IllegalArgumentException("MediaAdapter does not support: " + audioType);
        }
    }
}
