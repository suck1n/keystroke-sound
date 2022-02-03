package com.github.duckin.keystrokesound.listeners;

import com.intellij.ide.IdeEventQueue;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.annotations.NotNull;

public class AppStarterListener implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        IdeEventQueue.getInstance().addDispatcher(new EventListener(), Disposer.newDisposable());
    }
}
