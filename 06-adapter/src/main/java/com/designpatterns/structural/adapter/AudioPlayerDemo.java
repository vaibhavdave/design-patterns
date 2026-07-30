package com.designpatterns.structural.adapter;

public final class AudioPlayerDemo {

    private AudioPlayerDemo() {
    }

    public static void main(String[] args) {
        MediaPlayer player = new AudioPlayer();

        player.play("mp3", "song.mp3");
        player.play("vlc", "movie.vlc");
        player.play("mp4", "clip.mp4");

        try {
            player.play("avi", "video.avi");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected as expected: " + e.getMessage());
        }
    }
}
