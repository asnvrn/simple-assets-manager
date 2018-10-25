/*
 *  Free license "AS IS", 20.10.2018
 *  Author: Andrey Svetashov (JAVA4GAME)
 *  Vk -> https://vk.com/java4game
 *  GitHub -> https://github.com/java4game
 */

package com.github.java4game.sam;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.ObjectMap;

public class EffectsAssets extends AbstractAssets {

    EffectsAssets() {
        folder = "e/";
        extensions = new String[]{".eff"};
        data = new ObjectMap<String, ParticleEffect>();
    }

    ParticleEffect get(EAsset asset) {
        return (ParticleEffect) data.get(asset.name());
    }

    @Override
    public void dispose() {
        for (ObjectMap.Entry<String, ?> d : data) ((ParticleEffect) d.value).dispose();
        super.dispose();
    }
}
