/*
 *  Free license "AS IS", 20.10.2018
 *  Author: Andrey Svetashov (JAVA4GAME)
 *  Vk -> https://vk.com/java4game
 *  GitHub -> https://github.com/java4game
 */

package com.github.java4game.sam;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;

public class SoundsAssets extends AbstractAssets {

    private boolean disabled = false;
    private float volume = 1.0f;

    SoundsAssets() {
        folder = "s/";
        extensions = new String[]{".ogg", ".mp3"};
        data = new ObjectMap<String, Sound>();
    }

    void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    void setVolume(float volume) {
        this.volume = volume;
    }

    void play(SAsset asset) {
        if (disabled) return;
        Sound sound = (Sound) data.get(asset.name());
        sound.play(volume);
    }

    void stop(SAsset asset) {
        Sound sound = (Sound) data.get(asset.name());
        sound.stop();
    }

    @Override
    public void dispose() {
        for (ObjectMap.Entry<String, ?> d : data) ((Sound) d.value).dispose();
        super.dispose();
    }
}
