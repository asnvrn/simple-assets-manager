# Simple Asset Manager for LibGDX [![](https://jitpack.io/v/java4game/simple-assets-manager.svg)](https://jitpack.io/#java4game/simple-assets-manager)

Simple Asset Manager (SAM) - Resources manager with autofinding and autoloading all assets.

## Installation

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
	maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency in core project:
```
project(":core") {
    apply plugin: "java"

    dependencies {
        ...
        implementation 'com.github.java4game:simple-assets-manager:1.4.2'
    }
}
```

## Setup
### Step 1
Create resources folders in ..android/assets/

Folder       | Assets	     | Class	     | Extensions
------------ | ------------- | ------------- | -------------
**g/** | **Graphics** | TextureAtlas   | ***.atlas***, ***.pack***
**s/** | **Sounds**   | Sound          | ***.ogg***, ***.mp3***
**m/** | **Music**    | Music          | ***.ogg***, ***.mp3***
**e/** | **Effects**  | ParticleEffect | ***.eff***
**f/** | **Fonts**    | BitmapFont     | ***.fnt***

### Step 2
Create package *assets* in core project and create enums in *assets* package:
```
public enum G implements GAsset {
    // Insert here AtlasRegion names
}
```
```
public enum S implements SAsset {
    // Insert here Sound names 
}
```
```
public enum M implements MAsset {
    // Insert here Music names 
}
```
```
public enum E implements EAsset {
    // Insert here ParticleEffect names 
}
```
```
public enum F implements FAsset {
    // Insert here BitmapFont names 
}
```
**Note**:
> All enums names должны in upper case with divide _ and equals file names in lower case without extension.
> Exclusion: G enum, names equals regions names in texture atlases.

> Example:

> G.IMG_BG equals region with name img_bg

> S.BTN_CLICK equals file name btn_click.mp3 or btn_click.ogg in directory s/

Create empty class **A** in *assets* package, extends **SimpleAssetManager**
```
public class A extents SimpleAssetManager {
    // User code here...
}
```

### Step 3
Create code for loading resources:
```
render() {
   ...
   if (A.load()) {
      // Insert here action, when loading completed
   }
   ...
}
```
## Uses
### Graphics Assets
```
A.newDrawable(G.REGION_NAME);
A.newImage(G.REGION_NAME);
A.newNinePath(G.REGION_NAME);
A.newSprite(G.REGION_NAME);
A.newSprites(G.REGION_NAME);
A.newStaticTile(G.REGION_NAME);
A.newTextureRegion(G.REGION_NAME);
```
### Sounds Assets
```
A.playSound(S.SOUND_NAME);
A.stopSound(S.SOUND_NAME);
A.setSoundsVolume(float volume);
A.setSoundsDisabled(boolean disabled);
```
### Music Assets
```
A.playMusic(M.MUSIC_NAME);
A.stopMusic(M.MUSIC_NAME);
A.setMusicVolume(float volume);
A.setMusicDisabled(boolean disabled);
A.setMusicLooping(boolean looping);
```
### Effects Assets
```
A.effect(E.EFFECT_NAME);
```
### Fonts Assets
```
A.font(F.FONT_NAME);
```
### Assets Dispose
```
A.dispose();
```
