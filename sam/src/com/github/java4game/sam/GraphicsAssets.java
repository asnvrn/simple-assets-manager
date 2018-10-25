/*
 *  Free license "AS IS", 20.10.2018
 *  Author: Andrey Svetashov (JAVA4GAME)
 *  Vk -> https://vk.com/java4game
 *  GitHub -> https://github.com/java4game
 */

package com.github.java4game.sam;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class GraphicsAssets extends AbstractAssets {

    private TextureAtlas atlas = null;
    private AtlasRegion region = null;

    GraphicsAssets() {
        folder = "g/";
        extensions = new String[]{".atlas", ".pack"};
        data = new ObjectMap<String, TextureAtlas>();
    }

    private AtlasRegion findRegion(GAsset asset, int index) {
        for (ObjectMap.Entry<String, ?> d : data) {
            atlas = (TextureAtlas) d.value;
            if (index == -1) {
                if ((region = atlas.findRegion(asset.name().toLowerCase())) != null) return region;
            } else {
                if ((region = atlas.findRegion(asset.name().toLowerCase(), index)) != null) return region;
            }
        }
        throw new RuntimeException("Region \'" + asset.name() + "\' with index [" + index + "] is not found!");
    }

    boolean containsEnum(Enum e) {
        for (ObjectMap.Entry<String, ?> d : data) {
            TextureAtlas a = (TextureAtlas) d.value;
            if (a.findRegion(e.name().toLowerCase()) == null) return false;
        }
        return true;
    }

    NinePatch createPath(GAsset asset) {
        findRegion(asset, -1);
        return atlas.createPatch(region.name);
    }

    Sprite createSprite(GAsset asset) {
        findRegion(asset, -1);
        return atlas.createSprite(region.name);
    }

    Sprite createSprite(GAsset asset, int index) {
        findRegion(asset, index);
        return atlas.createSprite(region.name);
    }

    Array<Sprite> createSprites(GAsset asset) {
        findRegion(asset, -1);
        return atlas.createSprites(region.name);
    }

    Image createImage(GAsset asset) {
        return new Image(findRegion(asset, -1));
    }

    Image createImage(GAsset asset, int index) {
        return new Image(findRegion(asset, index));
    }

    TextureRegion createTextureRegion(GAsset asset) {
        return new TextureRegion(findRegion(asset, -1));
    }

    TextureRegion createTextureRegion(GAsset asset, int index) {
        return new TextureRegion(findRegion(asset, index));
    }

    StaticTiledMapTile createStaticTile(GAsset asset) {
        return new StaticTiledMapTile(findRegion(asset, -1));
    }

    StaticTiledMapTile createStaticTile(GAsset asset, int index) {
        return new StaticTiledMapTile(findRegion(asset, index));
    }

    TextureRegionDrawable createDrawable(GAsset asset) {
        return new TextureRegionDrawable(findRegion(asset, -1));
    }

    TextureRegionDrawable createDrawable(GAsset asset, int index) {
        return new TextureRegionDrawable(findRegion(asset, index));
    }

    @Override
    public void dispose() {
        for (ObjectMap.Entry<String, ?> d : data) ((TextureAtlas) d.value).dispose();
        super.dispose();
    }
}
