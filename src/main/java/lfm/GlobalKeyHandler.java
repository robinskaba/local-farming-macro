package lfm;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.HashMap;

public class GlobalKeyHandler implements NativeKeyListener {
    private final HashMap<Integer, Runnable> functions = new HashMap<>();

    GlobalKeyHandler() {
        GlobalScreen.addNativeKeyListener(this);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("There was a problem registering the native hook.");
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        Runnable r = functions.get(e.getKeyCode());
        if (r != null) r.run();
    }

    @Override public void nativeKeyReleased(NativeKeyEvent e) {}
    @Override public void nativeKeyTyped(NativeKeyEvent e) {}

    public void addFunction(int key, Runnable func) {
        functions.put(key, func);
    }
}
