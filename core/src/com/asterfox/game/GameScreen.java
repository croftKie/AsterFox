package com.asterfox.game;

import com.asterfox.game.entities.Asteroid;
import com.asterfox.game.entities.Bullets;
import com.asterfox.game.entities.Button;
import com.asterfox.game.entities.Player;
import com.asterfox.game.managers.AsteroidHandler;
import com.asterfox.game.managers.BulletHandler;
import com.asterfox.game.managers.SoundHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    final AsterFox game;
    final OrthographicCamera cam;
    public Player player;
    public Button leftbutton, rightButton, fireButton;
    public Input input;
    public long lastBulletSpawned;
    public AsteroidHandler asteroids;
    public BulletHandler bullets;
    public SoundHandler soundHandler;
    public int score = 20;
    public Music bgMusic = Gdx.audio.newMusic(Gdx.files.internal("bgmusic.wav"));

    public GameScreen(AsterFox game){
       this.game = game;
       this.cam = new OrthographicCamera();
       cam.setToOrtho(false, 800, 480);

       generateUI();
       generatePlayer();
       asteroids = new AsteroidHandler(this);
       bullets = new BulletHandler(this);
       soundHandler = new SoundHandler(this);

       input = new Input(this);
       Gdx.input.setInputProcessor(input);
    }


//    OVERRIDES

    @Override
    public void show() {

        soundHandler.playMusic();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);


//        RENDER BATCH
        game.batch.begin();
        player.draw(game);
        leftbutton.draw(game);
        rightButton.draw(game);
        fireButton.draw(game);

        for (Bullets bullet: bullets.bullets){
            bullet.draw(game);
        }

        for (Asteroid asteroid: asteroids.asteroids){
            asteroid.draw(game);
        }

        String scoreString = "Asteroids Left: " + score;
        game.font.getData().setScale(2,2);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, scoreString, 10, 440);

        game.batch.end();



//        CHECK FOR INPUTS

        if (Gdx.input.isTouched()){
            input.touchDown(
                    Gdx.input.getX(),
                    Gdx.input.getY(),
                    Gdx.input.getMaxPointers(),
                    1
            );
        }



        bullets.moveDestroyBullets(asteroids);
        asteroids.moveDestroyAsteroids(bullets.bullets);
        asteroids.spawnAsteroid();

        if (score == 0) {
            game.setScreen(new GameOver(game));
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
//

    public void generatePlayer(){
        player = new Player(
                "ship.png",
                new float[]{
                        800 / 2 - 64 / 2,
                        80,
                        64,
                        64
                },
                cam
        );
    }

    public void generateUI(){
        leftbutton = new Button(
                "left_button.png",
                new float[]{
                        100 - 64 / 2,
                        40,
                        64,
                        64
                });
        rightButton = new Button(
                "right_button.png",
                new float[]{
                        200 - 64 / 2,
                        40,
                        64,
                        64
                }
        );
        fireButton = new Button(
                "fire_button.png",
                new float[]{
                        650 - 64 / 2,
                        40,
                        64,
                        64
                }
        );

    }

}
