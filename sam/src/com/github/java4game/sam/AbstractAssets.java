/*
 *  Free license "AS IS", 20.10.2018
 *  Author: Andrey Svetashov (JAVA4GAME)
 *  Vk -> https://vk.com/java4game
 *  GitHub -> https://github.com/java4game
 */

package com.github.java4game.sam;

import com.badlogic.gdx.utils.ObjectMap;

import java.util.ArrayList;

public abstract class AbstractAssets {

    String folder;
    String[] extensions;
    ObjectMap<String, ?> data;
    ArrayList<String> names = new ArrayList<>();

    public void dispose() {
        folder = null;
        extensions = null;
        names = null;
        data = null;
    }
}
