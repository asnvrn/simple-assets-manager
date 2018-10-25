# Simple Asset Manager for LibGDX [![](https://jitpack.io/v/java4game/simple-assets-manager.svg)](https://jitpack.io/#java4game/simple-assets-manager)

Simple Asset Manager (SAM) - Менеджер ресурсов с автопоиском и автозагрузкой всех ассетов.

## Подключение

Добавить зависимость в корневой build.gradle проекта:
```
allprojects {
    repositories {
        ...
	maven { url 'https://jitpack.io' }
    }
}
```
Добавить зависимость в core проект сам менеджер:
```
project(":core") {
    apply plugin: "java"

    dependencies {
        ...
        implementation 'com.github.java4game:simple-assets-manager:1.4.2'
    }
}
```

## Настройка
### Шаг 1
Создать директории для ассетов каждого вида в ..android/assets/

Директория       | Ресурсы	     | Класс	     | Расширения файлов
------------ | ------------- | ------------- | -------------
**g/** | **Графика** | TextureAtlas   | ***.atlas***, ***.pack***
**s/** | **Звуки**   | Sound          | ***.ogg***, ***.mp3***
**m/** | **Музыка**  | Music          | ***.ogg***, ***.mp3***
**e/** | **Эффекты** | ParticleEffect | ***.eff***
**f/** | **Шрифты**  | BitmapFont     | ***.fnt***

### Щаг 2
Создать пакет *assets* в core проекте и создать в нем enums для каждого вида ресурса:
```
public enum G implements GAsset {
    // Поместить сюда имена регионов из TextureAtlases
}
```
```
public enum S implements SAsset {
    // Поместить сюда имена звуков
}
```
```
public enum M implements MAsset {
    // Поместить сюда имена музыки 
}
```
```
public enum E implements EAsset {
    // Поместить сюда имена ParticleEffects 
}
```
```
public enum F implements FAsset {
    // Поместить сюда имена BitmapFont шрифтов 
}
```
Note:
> Все имена enums должны быть в верхнем регистре, разделитель _

Создать пустой класс **A** в пакете *assets*, расширяющий класс **SimpleAssetManager**
```
public class A extents SimpleAssetManager {
    // Сюда можно помещать пользовательский код управления ресурсами 
}
```

### Шаг 3
Создать код для загрузки ресурсов в render цикле:
```
render() {
   ...
   if (A.load()) {
      // Поместить сюда код, который выполнится после завершения загрузки ресурсов
   }
   ...
}
```
## Использование
### Графика
```
A.newDrawable(G.REGION_NAME);
A.newImage(G.REGION_NAME);
A.newNinePath(G.REGION_NAME);
A.newSprite(G.REGION_NAME);
A.newSprites(G.REGION_NAME);
A.newStaticTile(G.REGION_NAME);
A.newTextureRegion(G.REGION_NAME);
```
### Звуки
```
A.playSound(S.SOUND_NAME);
A.stopSound(S.SOUND_NAME);
A.setSoundsVolune(float volume);
A.setSoundsDisabled(boolean disabled);
```
### Музыка
```
A.playMusic(M.MUSIC_NAME);
A.stopMusic(M.MUSIC_NAME);
A.setMusicVolune(float volume);
A.setMusicDisabled(boolean disabled);
A.setMusicLooping(boolean looping);
```
### Эффекты
```
A.effect(E.EFFECT_NAME);
```
### Шрифты
```
A.font(F.FONT_NAME);
```
### Очистка ресурсов
```
A.dispose();
```
