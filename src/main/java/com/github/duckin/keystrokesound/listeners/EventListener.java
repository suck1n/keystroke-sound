package com.github.duckin.keystrokesound.listeners;


import com.github.duckin.keystrokesound.Sound;
import com.intellij.ide.IdeEventQueue;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EventListener implements IdeEventQueue.EventDispatcher {

    @Override
    public boolean dispatch(@NotNull AWTEvent e) {
        if(e instanceof KeyEvent) {
            KeyEvent event = (KeyEvent) e;
            if(event.getID() == KeyEvent.KEY_PRESSED) {
                Sound.playKeyTyped(event.getKeyChar());
            }
        }
        return false;
    }
}
