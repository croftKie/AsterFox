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
    private Texture title;
    private Texture tap_title;
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


        if (!intro){

            player.draw(game);
            large.asteroid.rotate(0.1f);
            large.render(game);
            small.asteroid.rotate(-0.1f);
            small.render(game);
            tiny.asteroid.rotate(-0.2f);
            tiny.render(game);

            game.batch.draw(title, 10, 320, 256, 128);
            game.batch.draw(tap_title, 10, 200, 256 + 128, 128);

//            game.font.getData().setScale(scale,scale);
//            game.font.setColor(new Color(0xFFFFFF));
//            game.font.draw(game.batch, text[0], 10, 440);
//            game.font.setColor(new Color(Color.WHITE));
//            game.font.draw(game.batch, text[1], 10, 360);
        }

        if (intro) {

            playerAlt.draw(game);



            game.font.getData().setScale(scale,scale);
            game.font.setColor(new Color(Color.WHITE));
            game.font.draw(game.batch, Data.UI.intro[0], 10, 400);
            game.font.draw(game.batch, Data.UI.intro[1], 10, 350);
            game.font.draw(game.batch, Data.UI.intro[2], 10, 300);
            game.font.draw(game.batch, Data.UI.intro[3], 10, 250);
            game.font.draw(game.batch, Data.UI.intro[4], 10, 200);
            game.font.draw(game.batch, Data.UI.intro[5], 10, 150);
        }


        game.batch.end();
        if (Gdx.input.isTouched()) {
            if(intro){
                clickButtonAlt(playerAlt);
            }
            clickButton(player);
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
                        400,
                        300,
                        64,
                        64
                },
                this,
                1,
                1
        );
    }
    public void clickButton(Player player){
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touchPos);
        if (touchPos.x > player.player.getX() && touchPos.x < player.player.getX() + player.player.getWidth()) {
            if (touchPos.y > player.player.getY() && touchPos.y < player.player.getY() + player.player.getHeight()) {
                player.player.setScale(1.5f);
                intro = true;

            }
        }
    };
    public void clickButtonAlt(Player player){
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touchPos);
        if (touchPos.x > player.player.getX() && touchPos.x < player.player.getX() + player.player.getWidth()) {
            if (touchPos.y > player.player.getY() && touchPos.y < player.player.getY() + player.player.getHeight()) {
                if(intro) {
                    game.setScreen(new GameScreen(game));
                    dispose();
                }
            }
        }
    };

    public void generateMenuImages(){
        title = new Texture(Gdx.files.internal("title.png"));
        tap_title = new Texture(Gdx.files.internal("tap_text.png"));
    }
}
