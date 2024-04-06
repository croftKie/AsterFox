package com.asterfox.game;

import com.asterfox.game.entities.Bullet;
import com.asterfox.game.entities.Button;
import com.asterfox.game.entities.Player;
import com.asterfox.game.managers.AnimationHandler;
import com.asterfox.game.managers.AsteroidHandler;
import com.asterfox.game.managers.BulletHandler;
import com.asterfox.game.managers.SoundHandler;
import com.asterfox.game.managers.WaveHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    public AsterFox game;
    public OrthographicCamera cam;
    public float elapsedTime;
    public Player player;
    public Button leftbutton, rightButton, fireButton;
    public Input input;
    public AsteroidHandler asteroids;
    public BulletHandler bullets;
    public SoundHandler soundHandler;
    public WaveHandler waveHandler;
    public AnimationHandler aniHandler;
    public Animation<TextureRegion> ani;

    public Sprite hitAsteroid = null;

    public GameScreen(AsterFox game){
       this.game = game;
       this.cam = new OrthographicCamera();
       cam.setToOrtho(false, 800, 480);

       generateUI();
       generatePlayer();

       bullets = new BulletHandler(this);
       asteroids = new AsteroidHandler(this);
       soundHandler = new SoundHandler(this);
       waveHandler = new WaveHandler(this);
       aniHandler = new AnimationHandler(this);

       input = new Input(this);
       Gdx.input.setInputProcessor(input);


        TextureAtlas charSet = new TextureAtlas(Gdx.files.internal("explosion.atlas"));
        ani = new Animation<>(1/25f, charSet.findRegions("light"));
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
        elapsedTime += delta;

//        SPAWNERS
        asteroids.spawnAsteroid();

//        UPDATERS
        bullets.update();
        asteroids.update();
        aniHandler.update();


//        RENDER BATCH
        game.batch.begin();


        player.draw(game);
        leftbutton.draw(game);
        rightButton.draw(game);
        fireButton.draw(game);

        bullets.render();
        asteroids.render();
        aniHandler.render();


        String waveString = "Wave: " + waveHandler.wave;
        String scoreString = "Asteroids Left: " + waveHandler.score;
        game.font.getData().setScale(1.5f,1.5f);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, waveString, 10, 460);
        game.font.draw(game.batch, scoreString, 10, 425);

        game.batch.end();

        player.destroyPlayer(asteroids);


//        CHECK FOR INPUTS
        if (!player.destroyed){
            if (Gdx.input.isTouched()){
                input.touchDown(
                        Gdx.input.getX(),
                        Gdx.input.getY(),
                        Gdx.input.getMaxPointers(),
                        1
                );
            }
        }

        if(player.destroyed){
            System.out.println("firing");
            player.player.setY(player.player.getY() - 2);
            player.player.rotate(4);
            if(player.player.getY() < - 64){
                game.setScreen(new GameOver(game));
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
        this.dispose();
    }



//    UTILS
//

    public void generatePlayer(){
        player = new Player(
                this,
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
