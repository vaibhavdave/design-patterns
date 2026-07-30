package com.designpatterns.behavioral.iterator;

/** Runnable walkthrough: {@code ./gradlew :17-iterator:run} (or run this class from your IDE). */
public final class PlaylistDemo {

    private PlaylistDemo() {
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Clair de Lune", "Debussy"));
        playlist.addSong(new Song("Take Five", "Dave Brubeck"));
        playlist.addSong(new Song("So What", "Miles Davis"));

        System.out.println("-- Enhanced for-loop over a custom Iterable --");
        for (Song song : playlist) {
            System.out.println(song.title() + " — " + song.artist());
        }

        System.out.println();
        System.out.println("-- What the for-each above desugars to --");
        java.util.Iterator<Song> it = playlist.iterator();
        while (it.hasNext()) {
            Song song = it.next();
            System.out.println(song.title() + " — " + song.artist());
        }
    }
}
