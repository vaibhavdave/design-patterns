package com.designpatterns.structural.adapter;

/**
 * The target interface every client codes against. Only {@code mp3} is native; every other
 * format has to arrive through an adapter without the client ever knowing that.
 */
public interface MediaPlayer {

    void play(String audioType, String fileName);
}
