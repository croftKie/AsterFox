package com.asterfox.game;

import com.asterfox.game.entities.Button;
import com.asterfox.game.entities.Player;
import com.asterfox.game.managers.AnimationHandler;
import com.asterfox.game.managers.AsteroidHandler;
import com.asterfox.game.managers.BulletHandler;
import com.asterfox.game.managers.PlanetHandler;
import com.asterfox.game.managers.SoundHandler;
import com.asterfox.game.managers.UiHandler;
import com.asterfox.game.managers.WaveHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    public AnimationHandler aniHandler;
    public UiHandler uiHandler;
    public PlanetHandler planetHandler;

    public Texture planet;
    public Sprite hitAsteroid = null;

    public float deltaTime;

    public GameScreen(AsterFox game){
       this.game = game;
       this.cam = new OrthographicCamera();
       cam.setToOrtho(false, 800, 480);

        bullets = new BulletHandler(this);
        asteroids = new AsteroidHandler(this);
        soundHandler = new SoundHandler(this);
        aniHandler = new AnimationHandler(this);
        uiHandler = new UiHandler(this);
        planetHandler = new PlanetHandler(this);

       generatePlayer();

       planet = new Texture(Gdx.files.internal("planet09.png"));


       input = new Input(this);
       Gdx.input.setInputProcessor(input);

    }


//    OVERRIDES

    @Override
    public void show() {
        soundHandler.playMusic();
        planetHandler.spawnPlanet(game.selectedLevel);
    }

    @Override
    public void render(float delta) {
        deltaTime = delta;
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
        planetHandler.update();
        player.update();

//        RENDER BATCH
        game.batch.begin();
        planetHandler.render();
        player.draw(game);
        bullets.render();
        asteroids.render();
        aniHandler.render();
        uiHandler.render();



        String waveString = "Wave: " + game.waveHandler.wave;
        String scoreString = "Asteroids Left: " + game.waveHandler.score;
        game.font.getData().setScale(1.5f,1.5f);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, waveString, 10, 460);
        game.font.draw(game.batch, scoreString, 10, 425);

        game.batch.end();


        System.out.println(player.player.getWidth());
        System.out.println(player.player.getHeight());
        System.out.println(player.player.getBoundingRectangle().getWidth());
        System.out.println(player.player.getBoundingRectangle().getHeight());
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

}
