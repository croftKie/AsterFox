package com.asterfox.game;

import com.asterfox.game.constants.Data;
import com.asterfox.game.entities.Asteroid;
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
import static com.asterfox.game.constants.Data.UI.*;
import static com.asterfox.game.constants.Data.GameWindow.*;

public class MainMenu implements Screen {

    final AsterFox game;
    final OrthographicCamera cam;
    private Player player;
    private Player playerAlt;
    private Asteroid large;
    private Asteroid small;
    private Asteroid tiny;
    private boolean intro = false;
    private Sprite title;
    private Sprite play, about, quit, intro_texts;
    private boolean leaving = false;
    private float limit = 3;
    private float timer = 0;
    private Screen destination;
    public MainMenu(AsterFox game){
        this.game = game;

        generatePlayer();
        generateAsteroids();
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
        game.batch.setProjectionMatrix(cam.combined); // LOOK UP WHAT THIS IS DOING

        game.batch.begin();


        player.draw(game);
        large.asteroid.rotate(0.1f);
        large.render(game);
        small.asteroid.rotate(-0.1f);
        small.render(game);
        tiny.asteroid.rotate(-0.2f);
        tiny.render(game);


        game.batch.draw(title, title.getX(), title.getY(), 256, 64);
        game.batch.draw(play, play.getX(), play.getY(), 128, 64);
        game.batch.draw(about, about.getX(), about.getY(), 128, 84);
        game.batch.draw(quit, quit.getX(), quit.getY(), 128, 64);


        game.batch.end();
        if (Gdx.input.isTouched()) {
            clickButtonAlt(140, 270, 128, 84, new MapScreen(game));
            clickButtonAlt(140, 200, 128, 64, new AboutScreen(game));
            clickQuit(140, 150, 128, 64);
        }

        if (leaving){
            if (timer <= limit){
                title.setX(title.getX() - 500 * delta);
                play.setX(play.getX() - 410 * delta);
                about.setX(about.getX() - 325 * delta);
                quit.setX(quit.getX() - 260 * delta);
                player.player.translate(-200 * delta, 200 * delta);


                if(timer > 0.5){
                    small.asteroid.translate(200 * delta, 200 * delta);
                    tiny.asteroid.translate(50 * delta, 200 * delta);
                }

                if (timer > 1){
                    large.asteroid.translate(0 * delta, -280 * delta);
                }


                System.out.println(timer += delta);
                System.out.println(limit);
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

    public void generatePlayer(){
        player = new Player(
                this,
                "ship.png",
                new float[]{
                        600 - 64 / 2,
                        140,
                        64,
                        64
                },
                cam
        );
        player.player.setRotation(40);
        player.player.setScale(2);

        playerAlt = new Player(
                this,
                "ship.png",
                new float[]{
                        600 - 64 / 2,
                        370,
                        64,
                        64
                },
                cam
        );

        playerAlt.player.setRotation(-40);
        playerAlt.player.setScale(2);
    }
    public void generateAsteroids(){
        large = new Asteroid(
                "meteor_large.png",
                new float[]{
                        700,
                        400,
                        64,
                        64,
                },
                this,
                1,
                1
        );
        large.asteroid.setScale(4);

        small = new Asteroid(
                "meteor_small.png",
                new float[]{
                        300,
                        10,
                        64,
                        64
                },
                this,
                1,
                1
        );
        small.asteroid.setScale(4);

        tiny = new Asteroid(
                "meteor_small.png",
                new float[]{
                        320,
                        300,
                        64,
                        64
                },
                this,
                1,
                1
        );
    }
    public void clickButtonAlt(int x, int y, int width, int height, Screen screen){
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touchPos);

        if (touchPos.x > x && touchPos.x < x + width) {
            if (touchPos.y > y && touchPos.y < y + height) {
                destination = screen;
                leaving = true;
            }
        }
    };
    public void clickQuit(int x, int y, int width, int height){
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
        title = new Sprite(new Texture(Gdx.files.internal("title.png")), 512, 128);
        title.setX(10);
        title.setY(350);

        play = new Sprite(new Texture(Gdx.files.internal("play.png")), 256, 128);
        play.setX(140);
        play.setY(270);

        about = new Sprite(new Texture(Gdx.files.internal("about.png")), 256, 128);
        about.setX(140);
        about.setY(200);

        quit = new Sprite(new Texture(Gdx.files.internal("quit.png")), 256, 128);
        quit.setX(140);
        quit.setY(150);

        intro_texts = new Sprite(new Texture(Gdx.files.internal("intro_text.png")), 645, 387);
        intro_texts.setX(10);
        intro_texts.setY(350);
    }
}
