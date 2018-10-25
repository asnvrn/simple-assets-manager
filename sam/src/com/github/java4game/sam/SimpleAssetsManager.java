/*
 *  Free license "AS IS", 20.10.2018
 *  Author: Andrey Svetashov (JAVA4GAME)
 *  Vk -> https://vk.com/java4game
 *  GitHub -> https://github.com/java4game
 */

package com.github.java4game.sam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;

/** Simple Assets Manager for LibGDX.
 * Provides simple methods for uses graphics, sounds, music, fonts and effects resources in the game. */
public class SimpleAssetsManager {

    private static final String VER = "1.4.2";
    private static final String TAG = "SAM";
    private static final String OK = "ok!";
    private static final String NF = "not found!";
    private static final String FL = "failed!";

    private AssetManager manager;
    private GraphicsAssets gAssets;
    private SoundsAssets sAssets;
    private MusicAssets mAssets;
    private EffectsAssets eAssets;
    private FontsAssets fAssets;

    private static Logger logger = new Logger(TAG, Logger.DEBUG);
    private static int filesLoaded = 0;
    private static long millis = 0;

    private static SimpleAssetsManager sam;

    private static boolean loaded = false;
    private static boolean built = false;
    private static boolean completed = false;
    private static boolean runOne = false;

    protected SimpleAssetsManager() {}

    /** Initialization and start loading resources. */
    public static boolean load() {
        if (!loaded) {
            logger.info("Simple Assets Manager " + VER + " loading...");
            millis = TimeUtils.millis();
            sam = new SimpleAssetsManager();
            sam.manager = new AssetManager();
            sam.gAssets = new GraphicsAssets();
            sam.sAssets = new SoundsAssets();
            sam.mAssets = new MusicAssets();
            sam.eAssets = new EffectsAssets();
            sam.fAssets = new FontsAssets();
            sam.loader(sam.gAssets, TextureAtlas.class);
            sam.loader(sam.sAssets, Sound.class);
            sam.loader(sam.mAssets, Music.class);
            sam.loader(sam.eAssets, ParticleEffect.class);
            sam.loader(sam.fAssets, BitmapFont.class);
            loaded = true;
        }
        if (!built && sam.manager.getProgress() > 0.999f) {
            sam.builder(sam.gAssets);
            sam.builder(sam.sAssets);
            sam.builder(sam.mAssets);
            sam.builder(sam.eAssets);
            sam.builder(sam.fAssets);
            sam.fAssets.setFontsFilter();
            built = true;
            completed = true;
            logger.info("Loading completed! [ " + filesLoaded + " files loaded ] [ " + TimeUtils.timeSinceMillis(millis) + "ms ]");
        }
        if (!completed) sam.manager.update();
        return completed;
    }

    /** Condition for performing a one-time action. */ 
    public static boolean isLoadedRunOne() {
        if (!runOne && completed) return runOne = true;
        return false;
    }

    /** Get flag loading completed. */
    public static boolean isLoaded() {
        return completed;
    }

    /** Get string percent progress loading. */
    public static String getPercents() {
        return ((int) (sam.manager.getProgress() * 100f)) + "%";
    }

    /** Create New NinePatch. */
    public static NinePatch newNinePath(GAsset asset) {
        return sam.gAssets.createPath(asset);
    }

    /** Create New Sprite. */
    private Sprite newSprite(GAsset asset) {
        return gAssets.createSprite(asset, -1);
    }

    /** Create New Sprite by index. */
    public static Sprite newSprite(GAsset asset, int index) {
        return sam.gAssets.createSprite(asset, index);
    }

    /** Create New Array Sprites. */
    public static Array<Sprite> newSprites(GAsset asset) {
        return sam.gAssets.createSprites(asset);
    }

    /** Create New Image. */
    public static Image newImage(GAsset asset) {
        return sam.gAssets.createImage(asset);
    }

    /** Create New Image by index. */
    public static Image newImage(GAsset asset, int index) {
        return sam.gAssets.createImage(asset, index);
    }

    /** Create new TextureRegion. */
    public static TextureRegion newTextureRegion(GAsset asset) {
        return sam.gAssets.createTextureRegion(asset);
    }

    /** Create new TextureRegion by index. */
    public static TextureRegion newTextureRegion(GAsset asset, int index) {
        return sam.gAssets.createTextureRegion(asset, index);
    }

    /** Create new StaticTiledMapTile. */
    public static StaticTiledMapTile newStaticTile(GAsset asset) {
        return sam.gAssets.createStaticTile(asset);
    }

    /** Create new StaticTiledMapTile by index. */
    public static StaticTiledMapTile newStaticTile(GAsset asset, int index) {
        return sam.gAssets.createStaticTile(asset, index);
    }

    /** Create new TextureRegionDrawable. */
    public static TextureRegionDrawable newDrawable(GAsset asset) {
        return sam.gAssets.createDrawable(asset);
    }

    /** Create new TextureRegionDrawable by index. */
    public static TextureRegionDrawable newDrawable(GAsset asset, int index) {
        return sam.gAssets.createDrawable(asset, index);
    }

    public static void playSound(SAsset asset) {
        sam.sAssets.play(asset);
    }

    public static void stopSound(SAsset asset) {
        sam.sAssets.stop(asset);
    }

    /** Volume for all sounds. */
    public static void setSoundsVolume(float volume) {
        sam.sAssets.setVolume(volume);
    }

    /** Enable / disable all sounds. */
    public static void setSoundsDisabled(boolean disabled) {
        sam.sAssets.setDisabled(disabled);
    }

    public static void playMusic(MAsset asset) {
        sam.mAssets.play(asset);
    }

    public static void stopMusic(MAsset asset) {
        sam.mAssets.stop(asset);
    }

    public static void pauseMusic(MAsset asset) {
        sam.mAssets.pause(asset);
    }

    /** Volume for all music. */
    public static void setMusicVolume(float volume) {
        sam.mAssets.setVolume(volume);
    }

    /** Enable / disable all music. */
    public static void setMusicDisabled(boolean disabled) {
        sam.mAssets.setDisabled(disabled);
    }

    public static void setMusicLooping(boolean looping) {
        sam.mAssets.setLooping(looping);
    }

    /** Font. */
    public static BitmapFont font(FAsset asset) {
        return sam.fAssets.get(asset);
    }

    /** Effect. */
    public static ParticleEffect effect(EAsset asset) {
        return sam.eAssets.get(asset);
    }

    private void showLog(FileHandle[] files, AbstractAssets aAssets) {
        if (logger.getLevel() == Logger.NONE) return;
        int fc = 0;
        StringBuilder list = new StringBuilder();
        for (FileHandle f: files) {
            list.append("[ ").append(f.name()).append(" ] ");
            fc++;
        }
        StringBuilder s = new StringBuilder();
        s.append("Folder \'");
        s.append(aAssets.folder);
        s.append("\' ");
        boolean ex = Gdx.files.internal(aAssets.folder).exists();
        s.append(ex ? OK : NF);
        if (ex) {
            s.append(" [ ");
            s.append(fc);
            s.append(" files");
            s.append(" ] ");
            s.append(list.toString());
        }
        logger.info(s.toString());
    }

    private <T> void loader(AbstractAssets aAssets, Class<T> type) {
        FileHandle[] files = Gdx.files.internal(aAssets.folder).list();
        showLog(files, aAssets);
        if (files == null) return;
        for (String ext : aAssets.extensions) {
            for (FileHandle file : files) {
                int index = file.name().lastIndexOf('.');
                if (index != -1) {
                    if (file.name().substring(index).equals(ext)) {
                        sam.manager.load(aAssets.folder + file.name(), type);
                        aAssets.names.add(file.name().toUpperCase());
                        filesLoaded++;
                    }
                }
            }
        }
    }

    private void builder(AbstractAssets aAssets) {
        for (String name : aAssets.names) {
            String str = name.substring(0, name.lastIndexOf('.'));
            aAssets.data.put(str, sam.manager.get(aAssets.folder + name.toLowerCase()));
        }
    }

    /** Dispose all resources.
     * Insert it in main dispose method. */
    public static void dispose() {
        sam.gAssets.dispose();
        sam.sAssets.dispose();
        sam.mAssets.dispose();
        sam.fAssets.dispose();
        sam.eAssets.dispose();
        sam.manager.dispose();
        sam.gAssets = null;
        sam.sAssets = null;
        sam.mAssets = null;
        sam.fAssets = null;
        sam.eAssets = null;
        sam.manager = null;
        sam = null;
        logger.info("Assets disposed!");
    }
}
