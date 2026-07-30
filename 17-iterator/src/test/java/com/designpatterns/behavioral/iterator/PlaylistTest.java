package com.designpatterns.behavioral.iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlaylistTest {

    @Test
    void enhancedForLoopVisitsEverySongInAdditionOrder() {
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("A", "Artist A"));
        playlist.addSong(new Song("B", "Artist B"));
        playlist.addSong(new Song("C", "Artist C"));

        List<String> titles = new ArrayList<>();
        for (Song song : playlist) {
            titles.add(song.title());
        }

        assertThat(titles).containsExactly("A", "B", "C");
    }

    @Test
    void hasNextIsFalseOnAnEmptyPlaylist() {
        Playlist playlist = new Playlist();

        assertThat(playlist.iterator().hasNext()).isFalse();
    }

    @Test
    void nextThrowsNoSuchElementExceptionOnceExhausted() {
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Only Song", "Solo Artist"));

        Iterator<Song> iterator = playlist.iterator();
        iterator.next();

        assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void growsPastItsInitialBackingCapacityWithoutLosingSongs() {
        Playlist playlist = new Playlist();
        for (int i = 0; i < 10; i++) {
            playlist.addSong(new Song("Song " + i, "Artist"));
        }

        assertThat(playlist.size()).isEqualTo(10);
        int count = 0;
        for (Song ignored : playlist) {
            count++;
        }
        assertThat(count).isEqualTo(10);
    }

    @Test
    void eachCallToIteratorReturnsAnIndependentCursor() {
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("A", "Artist A"));
        playlist.addSong(new Song("B", "Artist B"));

        Iterator<Song> first = playlist.iterator();
        first.next();

        Iterator<Song> second = playlist.iterator();

        assertThat(second.next().title()).isEqualTo("A");
    }
}
