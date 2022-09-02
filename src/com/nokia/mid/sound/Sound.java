/*
 * Decompiled with CFR 0.152.
 */
package com.nokia.mid.sound;

import com.nokia.mid.sound.SoundListener;

public class Sound {
    public static final int SOUND_PLAYING = 0;
    public static final int SOUND_STOPPED = 1;
    public static final int SOUND_UNINITIALIZED = 3;
    public static final int FORMAT_TONE = 1;
    public static final int FORMAT_WAV = 5;

    public Sound(byte[] data, int type) {
    }

    public Sound(int freq, long duration) {
    }

    public static int getConcurrentSoundCount(int type) {
        return 0;
    }

    public int getGain() {
        return 0;
    }

    public void setGain(int gain) {
    }

    public int getState() {
        return 1;
    }

    public static int[] getSupportedFormats() {
        return new int[0];
    }

    public void init(byte[] data, int type) {
    }

    public void init(int freq, long duration) {
    }

    public synchronized void play(int loop) {
    }

    public void stop() {
    }

    public void release() {
    }

    public void resume() {
    }

    public void setSoundListener(SoundListener listener) {
    }
}

