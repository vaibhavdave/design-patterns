package com.designpatterns.structural.adapter;

/**
 * Stand-in for a third-party library's own hierarchy: a perfectly good API, just not the one
 * {@link MediaPlayer} clients speak. Adapter's job is to bridge the two without changing either.
 */
public interface AdvancedMediaPlayer {

    void playVlc(String fileName);

    void playMp4(String fileName);
}
