package com.github.duckin.keystrokesound;

import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class Sound {

    public static void playKeyTyped(char c) {
        KeyType.get(c).startNext();
    }

    private enum KeyType {
        ENTER("enter", 1),
        ESCAPE("escape", 3),
        OTHER("other", 3),
        SPACE("space", 1),
        TAB("tab", 3);

        private final String directory;
        private final AudioInputStream[] streams;
        private final AtomicInteger index = new AtomicInteger(1);

        KeyType(String dir, int total) {
            this.directory = dir;
            this.streams = new AudioInputStream[total];
        }

        public static KeyType get(char c) {
            switch (c) {
                case KeyEvent.VK_SPACE: return KeyType.SPACE;
                case KeyEvent.VK_ENTER: return KeyType.ENTER;
                case KeyEvent.VK_TAB: return KeyType.TAB;
                case KeyEvent.VK_ESCAPE: return KeyType.ESCAPE;
                default: return KeyType.OTHER;
            }
        }

        public void startNext() {
            resetStream(index.get() % streams.length);
            new Thread(() -> {
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(streams[index.getAndIncrement() % streams.length]);
                    clip.start();
                } catch (LineUnavailableException | IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }).start();
        }

        private void resetStream(int index) {
            try {
                URL url = getClass().getClassLoader().getResource("sounds/" + directory + "/" + (index + 1) + ".wav");
                if(url != null) {
                    streams[index] = AudioSystem.getAudioInputStream(url);
                } else {
                    System.out.println("URL-NULL: sound/" + directory + "/" + (index + 1) + ".mp3");
                }
            } catch (UnsupportedAudioFileException | IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
