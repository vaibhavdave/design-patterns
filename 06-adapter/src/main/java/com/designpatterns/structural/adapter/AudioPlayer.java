package com.designpatterns.structural.adapter;

/**
 * The concrete client-facing player. Plays {@code mp3} natively and delegates everything else to
 * a {@link MediaAdapter}, so callers only ever depend on {@link MediaPlayer#play(String, String)}
 * — they never see {@link AdvancedMediaPlayer}, {@link VlcPlayer}, or {@link Mp4Player} directly.
 */
public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        if ("mp3".equalsIgnoreCase(audioType)) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if ("vlc".equalsIgnoreCase(audioType) || "mp4".equalsIgnoreCase(audioType)) {
            MediaPlayer adapter = new MediaAdapter(audioType);
            adapter.play(audioType, fileName);
        } else {
            throw new IllegalArgumentException("Unsupported audio type: " + audioType);
        }
    }
}
