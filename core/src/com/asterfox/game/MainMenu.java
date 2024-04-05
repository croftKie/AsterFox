package com.asterfox.game;

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
    private Asteroid large;
    private Asteroid small;
    private Asteroid tiny;

    public MainMenu(AsterFox game){
        this.game = game;

        generatePlayer();
//        generateAsteroids();

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
//        large.asteroid.rotate(0.1f);
//        large.draw(game);
//        small.asteroid.rotate(-0.1f);
//        small.draw(game);
//        tiny.asteroid.rotate(-0.2f);
//        tiny.draw(game);


        game.font.getData().setScale(scale,scale);
        game.font.setColor(new Color(0xFFFFFF));
        game.font.draw(game.batch, text[0], 10, 440);
        game.font.setColor(new Color(Color.WHITE));
        game.font.draw(game.batch, text[1], 10, 360);

        game.batch.end();
        if (Gdx.input.isTouched()) {
            clickButton();
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
    }
//    public void generateAsteroids(){
//        large = new Asteroid(
//                "meteor_large.png",
//                new float[]{
//                        700,
//                        400,
//                        64,
//                        64
//                }
//        );
//        large.asteroid.setScale(4);
//
//        small = new Asteroid(
//                "meteor_small.png",
//                new float[]{
//                        300,
//                        10,
//                        64,
//                        64
//                }
//        );
//        small.asteroid.setScale(4);
//
//        tiny = new Asteroid(
//                "meteor_small.png",
//                new float[]{
//                        400,
//                        300,
//                        64,
//                        64
//                }
//        );
//    }
    public void clickButton(){
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touchPos);
        if (touchPos.x > player.player.getX() && touchPos.x < player.player.getX() + player.player.getWidth()) {
            if (touchPos.y > player.player.getY() && touchPos.y < player.player.getY() + player.player.getHeight()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        }
    }
}
