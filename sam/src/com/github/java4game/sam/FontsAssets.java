/*
 *  Free license "AS IS", 20.10.2018
 *  Author: Andrey Svetashov (JAVA4GAME)
 *  Vk -> https://vk.com/java4game
 *  GitHub -> https://github.com/java4game
 */

package com.github.java4game.sam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ObjectMap;

public class FontsAssets extends AbstractAssets {

    FontsAssets() {
        folder = "f/";
        extensions = new String[]{".fnt"};
        data = new ObjectMap<String, BitmapFont>();
    }

    BitmapFont get(FAsset asset) {
        return (BitmapFont) data.get(asset.name());
    }

    @Override
    public void dispose() {
        for (ObjectMap.Entry<String, ?> d : data) ((BitmapFont) d.value).dispose();
        super.dispose();
    }

    void setFontsFilter() {
        for (String name : names) {
            String str = name.substring(0, name.lastIndexOf('.'));
            BitmapFont font = (BitmapFont) data.get(str);
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
}
