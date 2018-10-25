/*
 *  Free license "AS IS", 20.10.2018
 *  Author: Andrey Svetashov (JAVA4GAME)
 *  Vk -> https://vk.com/java4game
 *  GitHub -> https://github.com/java4game
 */

package com.github.java4game.sam;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.ObjectMap;

public class MusicAssets extends AbstractAssets {

    private boolean disabled = false;
    private float volume = 1.0f;
    private boolean looping = true;

    MusicAssets() {
        folder = "m/";
        extensions = new String[]{".ogg", ".mp3"};
        data = new ObjectMap<String, Music>();
    }

    void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    void setVolume(float volume) {
        this.volume = volume;
    }

    void setLooping(boolean looping) {
        this.looping = looping;
    }

    void play(MAsset asset) {
        if (disabled) return;
        Music music = (Music) data.get(asset.name());
        if (!music.isPlaying()) {
            music.setLooping(looping);
            music.setVolume(volume);
            music.play();
        }
    }

    void stop(MAsset asset) {
        Music music = (Music) data.get(asset.name());
        if (music.isPlaying()) music.stop();
    }

    void pause(MAsset asset) {
        Music music = (Music) data.get(asset.name());
        if (music.isPlaying()) music.pause();
    }

    @Override
    public void dispose() {
        for (ObjectMap.Entry<String, ?> d : data) ((Music) d.value).dispose();
        super.dispose();
    }
}
