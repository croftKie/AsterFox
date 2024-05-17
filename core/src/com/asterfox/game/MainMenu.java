package com.asterfox.game;

import com.asterfox.game.constants.Data;
import com.asterfox.game.entities.Asteroid;
import com.asterfox.game.entities.Planet;
import com.asterfox.game.entities.Player;
import com.asterfox.game.managers.WaveHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import static com.asterfox.game.constants.Data.UI.*;
import static com.asterfox.game.constants.Data.GameWindow.*;

public class MainMenu implements Screen {

    final AsterFox game;
    final OrthographicCamera cam;
    private Sprite title;
    private Sprite play, about, quit, intro_texts;
    private Planet planet;
    private Texture bg;
    private boolean leaving = false;
    private Long timer;
    private Screen destination;
    public MainMenu(AsterFox game){
        this.game = game;

        generateMenuImages();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, vp_width, vp_height);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        cam.update(); // LOOK UP WHAT THIS IS DOING
        planet.swingPlanet();
        game.batch.setProjectionMatrix(cam.combined); // LOOK UP WHAT THIS IS DOING

        game.batch.begin();

        game.batch.draw(bg, 0, 0, bg.getWidth(), bg.getHeight());
        planet.render(game);
        game.batch.draw(title, title.getX(), title.getY(), 512, 100);
        game.batch.draw(play, play.getX(), play.getY(), 512 * 0.8F, 128 * 0.8F);
        game.batch.draw(about, about.getX(), about.getY(), 512 * 0.3F, 128 * 0.4F);
        game.batch.draw(quit, quit.getX(), quit.getY(), 512 * 0.8F, 128 * 0.8F);

        game.batch.end();


        if (Gdx.input.isTouched()) {
            clickButtonAlt(
                    play.getX(),
                    play.getY(),
                    512 * 0.8F,
                    128 * 0.8F,
                    new MapScreen(game)
            );
            clickButtonAlt(
                    about.getX(),
                    about.getY(),
                    512 * 0.8F,
                    128 * 0.8F,
                    new AboutScreen(game)
            );
            clickQuit(
                    quit.getX(),
                    quit.getY(),
                    512 * 0.8F,
                    128 * 0.8F
            );
        }

        if (leaving){
            if (TimeUtils.timeSinceMillis(timer) <= 1800){
                title.setX(title.getX() - 700 * delta);
                play.setX(play.getX() - 560 * delta);
                about.setX(about.getX() - 445 * delta);
                quit.setX(quit.getX() - 330 * delta);
            } else {
                leaving = false;
                game.setScreen(destination);
                dispose();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


//    UTILS

    public void clickButtonAlt(float x, float y, float width, float height, Screen screen){
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touchPos);
        if (touchPos.x > x && touchPos.x < x + width) {
            if (touchPos.y > y && touchPos.y < y + height) {
                destination = screen;
                leaving = true;
                timer = TimeUtils.millis();
            }
        }
    };
    public void clickQuit(float x, float y, float width, float height){
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touchPos);
        if (touchPos.x > x && touchPos.x < x + width) {
            if (touchPos.y > y && touchPos.y < y + height) {
                Gdx.app.exit();
                System.exit(0);
            }
        }
    }
    public void generateMenuImages(){
        title = new Sprite(
                new Texture(Gdx.files.internal("title.png")),
                512,
                128);
        title.setX(-15);
        title.setY(600);

        play = new Sprite(
                new Texture(Gdx.files.internal("play.png")),
                512,
                128);
        play.setX(80);
        play.setY(450);

        about = new Sprite(
                new Texture(Gdx.files.internal("about.png")),
                512,
                256);
        about.setX(30);
        about.setY(20);

        quit = new Sprite(new Texture(
                Gdx.files.internal("quit.png")),
                512,
                128);
        quit.setX(80);
        quit.setY(300);

        intro_texts = new Sprite(
                new Texture(Gdx.files.internal("intro_text.png")),
                645,
                387);
        intro_texts.setX(10);
        intro_texts.setY(350);

        bg = new Texture(Gdx.files.internal("star_bg.png"));

        planet = new Planet(
                this,
                "planet01.png",
                new float[]{
                    200,
                    100,
                    400,
                    400
                }
            );
    }
}
