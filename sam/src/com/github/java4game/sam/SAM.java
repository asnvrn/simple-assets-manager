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
public class SAM {

    private static final String VER = "1.4";
    private static final String TAG = "SAM";
    private static final String OK = "ok!";
    private static final String NF = "not found!";
    private static final String FL = "failed!";

    private static AssetManager manager;
    private static GraphicsAssets gAssets;
    private static SoundsAssets sAssets;
    private static MusicAssets mAssets;
    private static EffectsAssets eAssets;
    private static FontsAssets fAssets;

    private static boolean loaded = false;
    private static boolean runOne = false;

    private static Logger logger = new Logger(TAG, Logger.DEBUG);
    private static int filesLoaded = 0;
    private static long millis = 0;

    private SAM() {}

    /** Initialization and start loading resources. */
    public static void load(boolean log) {
        millis = TimeUtils.millis();
        if (!log) logger.setLevel(Logger.NONE);
        logger.info("Simple Assets Manager " + VER + " loading...");
        manager = new AssetManager();
        gAssets = new GraphicsAssets();
        sAssets = new SoundsAssets();
        mAssets = new MusicAssets();
        eAssets = new EffectsAssets();
        fAssets = new FontsAssets();
        loader(gAssets, TextureAtlas.class);
        loader(sAssets, Sound.class);
        loader(mAssets, Music.class);
        loader(eAssets, ParticleEffect.class);
        loader(fAssets, BitmapFont.class);
    }

    /** Create inside SAM variables. */
     private static void build() {
        builder(gAssets);
        builder(sAssets);
        builder(mAssets);
        builder(eAssets);
        builder(fAssets);
        fAssets.setFontsFilter();
    }

    /** Method for loading process.
     * Insert it in render loop. */
    public static void update() {
        if (manager.getProgress() < .999f) {
            manager.update();
            return;
        }
        if (!loaded) {
            SAM.logger.info("Loading completed! [ " + filesLoaded + " files loaded ] [ " + TimeUtils.timeSinceMillis(millis) + " ms ]");
            build();
            loaded = true;
        }
    }

    /** Get float percents progress loading. */
    public static float getProgress() {
        return manager.getProgress();
    }

    /** Get string percent progress loading. */
    public static String getPercents() {
        return ((int) (manager.getProgress() * 100f)) + "%";
    }

    /** Single action condition. */
    public static boolean isLoadedRunOne() {
        if (loaded && !runOne) return runOne = true;
        return false;
    }

    /** True when loading completed. */
    public static boolean isLoaded() {
        return loaded;
    }

    /** Get LibGDX AssetManager. */
    public static AssetManager getManager() {
        return manager;
    }

    /** Create New NinePatch. */
    public static NinePatch createPath(GAsset asset) {
        return gAssets.createPath(asset);
    }

    /** Create New Sprite. */
    public static Sprite createSprite(GAsset asset) {
        return gAssets.createSprite(asset, -1);
    }

    /** Create New Sprite by index. */
    public static Sprite createSprite(GAsset asset, int index) {
        return gAssets.createSprite(asset, index);
    }

    /** Create New Array Sprites. */
    public static Array<Sprite> createSprites(GAsset asset) {
        return gAssets.createSprites(asset);
    }

    /** Create New Image. */
    public static Image createImage(GAsset asset) {
        return gAssets.createImage(asset);
    }

    /** Create New Image by index. */
    public static Image createImage(GAsset asset, int index) {
        return gAssets.createImage(asset, index);
    }

    /** Create new TextureRegion. */
    public static TextureRegion createTextureRegion(GAsset asset) {
        return gAssets.createTextureRegion(asset);
    }

    /** Create new TextureRegion by index. */
    public static TextureRegion createTextureRegion(GAsset asset, int index) {
        return gAssets.createTextureRegion(asset, index);
    }

    /** Create new StaticTiledMapTile. */
    public static StaticTiledMapTile createStaticTile(GAsset asset) {
        return gAssets.createStaticTile(asset);
    }

    /** Create new StaticTiledMapTile by index. */
    public static StaticTiledMapTile createStaticTile(GAsset asset, int index) {
        return gAssets.createStaticTile(asset, index);
    }

    /** Create new TextureRegionDrawable. */
    public static TextureRegionDrawable createDrawable(GAsset asset) {
        return gAssets.createDrawable(asset);
    }

    /** Create new TextureRegionDrawable by index. */
    public static TextureRegionDrawable createDrawable(GAsset asset, int index) {
        return gAssets.createDrawable(asset, index);
    }

    public static void playSound(SAsset asset) {
        sAssets.play(asset);
    }

    public static void stopSound(SAsset asset) {
        sAssets.stop(asset);
    }

    /** Volume for all sounds. */
    public static void setSoundsVolume(float volume) {
        sAssets.setVolume(volume);
    }

    /** Enable / disable all sounds. */
    public static void setSoundsDisabled(boolean disabled) {
        sAssets.setDisabled(disabled);
    }

    public static void playMusic(MAsset asset) {
        mAssets.play(asset);
    }

    public static void stopMusic(MAsset asset) {
        mAssets.stop(asset);
    }

    public static void pauseMusic(MAsset asset) {
        mAssets.pause(asset);
    }

    /** Volume for all music. */
    public static void setMusicVolume(float volume) {
        mAssets.setVolume(volume);
    }

    /** Enable / disable all music. */
    public static void setMusicDisabled(boolean disabled) {
        mAssets.setDisabled(disabled);
    }

    public static void setMusicLooping(boolean looping) {
        mAssets.setLooping(looping);
    }

    /** Font. */
    public static BitmapFont getFont(FAsset asset) {
        return fAssets.get(asset);
    }

    /** Effect. */
    public static ParticleEffect getEffect(EAsset asset) {
        return eAssets.get(asset);
    }

    private static void showLog(FileHandle[] files, AbstractAssets aAssets) {
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
        SAM.logger.info(s.toString());
    }

    private static <T> void loader(AbstractAssets aAssets, Class<T> type) {
        FileHandle[] files = Gdx.files.internal(aAssets.folder).list();
        showLog(files, aAssets);
        if (files == null) return;
        for (String ext : aAssets.extensions) {
            for (FileHandle file : files) {
                int index = file.name().lastIndexOf('.');
                if (index != -1) {
                    if (file.name().substring(index).equals(ext)) {
                        manager.load(aAssets.folder + file.name(), type);
                        aAssets.names.add(file.name().toUpperCase());
                        filesLoaded++;
                    }
                }
            }
        }
    }

    private static void builder(AbstractAssets aAssets) {
        for (String name : aAssets.names) {
            String str = name.substring(0, name.lastIndexOf('.'));
            aAssets.data.put(str, manager.get(aAssets.folder + name.toLowerCase()));
        }
    }

    /** Dispose all resources.
     * Insert it in main dispose method. */
    public static void dispose() {
        gAssets.dispose();
        sAssets.dispose();
        mAssets.dispose();
        fAssets.dispose();
        eAssets.dispose();
        manager.dispose();
        gAssets = null;
        sAssets = null;
        mAssets = null;
        fAssets = null;
        eAssets = null;
        manager = null;
        logger.info("Assets disposed!");
    }
}
