package lfm;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Main {
    private static final Macro macro = new Macro();
    private static final GlobalKeyHandler globalKeyHandler = new GlobalKeyHandler();

    private static boolean running = true;

    public static void main(String[] args) {
        bindKeys();

        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void bindKeys() {
        globalKeyHandler.addFunction(NativeKeyEvent.VC_K, macro::startWartMacro);
        globalKeyHandler.addFunction(NativeKeyEvent.VC_P, macro::switchPause);
        globalKeyHandler.addFunction(NativeKeyEvent.VC_L, macro::stop);
        globalKeyHandler.addFunction(NativeKeyEvent.VC_R, () -> running = false);
    }
}