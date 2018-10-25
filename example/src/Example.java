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
import com.github.java4game.sam.SimpleAssetsManager;

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

        // Start SimpleAssetsManager loading resource
        // with logger enabled


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (A.load()) {
            if (A.isLoadedRunOne()) {
                 sprites1 = A.newSprites(G.LOGO);
                sprites2 = A.newSprites(G.LOGO);


                img = A.newImage(G.LOGO, 17);
                img.setPosition(Gdx.graphics.getWidth() / 2f - img.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - img.getHeight() / 2f);

                label = new Label("Simple Assets Manager", new Label.LabelStyle(A.font(F.FONT_30), Color.DARK_GRAY));
                label.setPosition(Gdx.graphics.getWidth() / 2f - label.getWidth() / 2f, 70f);

                // Start play music
                A.playMusic(M.MUSIC);

                // Start play sound
                A.playSound(S.PLAY);

                // Start ParticleEffect
                A.effect(E.STAR).setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
                A.effect(E.STAR).start();
            }
            acc++;
            acc2++;
            batch.begin();
            img.draw(batch, 1);
            label.draw(batch, 1);
            A.effect(E.STAR).update(Gdx.graphics.getDeltaTime());
            A.effect(E.STAR).draw(batch);
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
                A.playSound(S.STAR30);
                A.effect(E.STAR).reset();
                A.effect(E.STAR).start();
                acc = 0;
            }
        } else {
            // This percent progress
            batch.begin();
            fontForShowProgress.draw(batch, "Loading... " + SimpleAssetsManager.getPercents(), 0, 18f);
            batch.end();
        }
    }

    @Override
    public void dispose() {
        // Dispose all resources */
        A.dispose();
    }
}
