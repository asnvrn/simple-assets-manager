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
        implementation 'com.github.java4game:simple-assets-manager:1.0'
    }
}
```

## Setup
### Step 1
Create resources folders in ..android/assets/

Folder       | Assets
------------ | -------------
**g/** | **Graphics**: TextureAtlas files, extensions: ***.atlas***, ***.pack***
**s/** | **Sounds**: Sound files, extensions: ***.ogg***, ***.mp3***
**m/** | **Music**: Music files, extensions: ***.ogg***, ***.mp3***
**e/** | **Effects**: ParticleEffect files, extension: ***.eff***
**f/** | **Fonts**: BitmapFont files, extension: ***.fnt***

### Step 2
Create packedge *assets* in core project and create enums in *assets* packedge:
```
public enum G implements GAsset {
    // Insert here AtlasRegion names
}
```
```
public enum S implements SAsset {
    // Insert here Sound files names, without extension 
}
```
```
public enum M implements MAsset {
    // Insert here Music files names, without extension 
}
```
```
public enum E implements EAsset {
    // Insert here ParticleEffect files names, without extension 
}
```
```
public enum F implements FAsset {
    // Insert here BitmapFont files names, without extension 
}
```
Note:
> All enums in upper case with divide _

### Step 3
Run method SAM.load() for initialization and start loading all resources.

Insert method SAM.update() in render loop for loading.

Create code for action when loading completed:
```
render() {
   ...
   if (SAM.isLoadedRunOne) {
      // Loading completed. Run one any action...
   }
   ...
}
```
## Use
### Graphics Assets
```
SAM.createImage(G.IMAGE_NAME);
```
### Sounds Assets
```
SAM.playSound(S.SOUND_NAME);
```
### Music Assets
```
SAM.playMusic(M.MUSIC_NAME);
```
### Effects Assets
```
SAM.getEffect(E.EFFECT_NAME);
```
### Fonts Assets
```
SAM.getFont(F.FONT_NAME);
```
### Assets Dispose
```
SAM.dispose();
```
