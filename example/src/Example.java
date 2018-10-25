import assets.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.github.java4game.sam.SAM;

public class Example extends ApplicationAdapter {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 840;
        config.height = 480;
        new LwjglApplication(new Example(), config);
    }

    private SpriteBatch batch;
    private Image img;
    private Label label;
    private int acc = 0;
    private int acc2 = 0;
    private int sc = 0;

    private BitmapFont fontForShowProgress;
    private Array<Sprite> sprites1;
    private Array<Sprite> sprites2;

    @Override
    public void create() {
        batch = new SpriteBatch();
        fontForShowProgress = new BitmapFont(Gdx.files.internal("f/font_18.fnt"));
        fontForShowProgress.setColor(Color.DARK_GRAY);

        // Start SAM loading resource
        // with logger enabled
        SAM.load(true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Run one this block, when loading completed
        if (SAM.isLoadedRunOne()) {
            sprites1 = SAM.createSprites(G.LOGO);
            sprites2 = SAM.createSprites(G.LOGO);

            img = SAM.createImage(G.LOGO, 17);
            img.setPosition(Gdx.graphics.getWidth() / 2f - img.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - img.getHeight() / 2f);

            label = new Label("Simple Assets Manager", new Label.LabelStyle(SAM.getFont(F.FONT_30), Color.DARK_GRAY));
            label.setPosition(Gdx.graphics.getWidth() / 2f - label.getWidth() / 2f, 70f);

            // Start play music
            SAM.playMusic(M.MUSIC);

            // Start play sound
            SAM.playSound(S.PLAY);

            // Start ParticleEffect
            SAM.getEffect(E.STAR).setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
            SAM.getEffect(E.STAR).start();
        }

        // Run loop after loading completed
        if (SAM.isLoaded()) {
            acc++;
            acc2++;
            batch.begin();
            img.draw(batch, 1);
            label.draw(batch, 1);
            SAM.getEffect(E.STAR).update(Gdx.graphics.getDeltaTime());
            SAM.getEffect(E.STAR).draw(batch);
            if (acc2 > 5) {
                if (sc > 16) sc = 0;
                sc++;
                acc2 = 0;
            }
            sprites1.get(sc).setPosition(img.getX() - 150f, img.getY());
            sprites1.get(sc).draw(batch, 1);
            sprites2.get(sc).setPosition(img.getX() + 150f, img.getY());
            sprites2.get(sc).draw(batch, 1);
            batch.end();
            if (acc > 200) {
                SAM.playSound(S.STAR30);
                SAM.getEffect(E.STAR).reset();
                SAM.getEffect(E.STAR).start();
                acc = 0;
            }
        } else {
            // Updating SAM loading resources
            SAM.update();

            // This percent progress
            batch.begin();
            fontForShowProgress.draw(batch, "Loading... " + SAM.getPercents(), 0, 18f);
            batch.end();
        }
    }

    @Override
    public void dispose() {
        // Dispose all resources */
        SAM.dispose();
    }
}
