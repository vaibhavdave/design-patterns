package com.designpatterns.behavioral.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Backed by a plain resizable array that callers never see directly. {@link #iterator()} is the
 * only way to walk the songs, which is the entire point of Iterator: expose traversal without
 * exposing the internal representation (array, linked list, tree — callers can't tell and
 * shouldn't need to).
 */
public final class Playlist implements Iterable<Song> {

    private Song[] songs = new Song[4];
    private int size = 0;

    public void addSong(Song song) {
        if (size == songs.length) {
            songs = Arrays.copyOf(songs, songs.length * 2);
        }
        songs[size++] = song;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * Walks the backing array by index rather than delegating to {@code Arrays.asList(...).
     * iterator()} — the goal is to show what a for-each loop actually desugars to, not to wrap
     * another collection's iterator.
     */
    private final class PlaylistIterator implements Iterator<Song> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Song next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more songs in playlist");
            }
            return songs[cursor++];
        }
    }
}
