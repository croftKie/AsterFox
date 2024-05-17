package com.asterfox.game;

import static com.asterfox.game.constants.Data.GameWindow.vp_height;
import static com.asterfox.game.constants.Data.GameWindow.vp_width;

import com.asterfox.game.constants.Data;
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
    public Input input;
    public AsteroidHandler asteroids;
    public SoundHandler soundHandler;
    public AnimationHandler aniHandler;
    public UiHandler uiHandler;
    public PlanetHandler planetHandler;
    public Texture stars;
    public Texture bg, stats_bar;
    public Sprite hitAsteroid = null;

    public float deltaTime;

    public GameScreen(AsterFox game){
       this.game = game;
       this.cam = new OrthographicCamera();
       cam.setToOrtho(false, vp_width, vp_height);

        asteroids = new AsteroidHandler(this);
        soundHandler = new SoundHandler(this);
        aniHandler = new AnimationHandler(this);
        generatePlayer();
        uiHandler = new UiHandler(this);
        planetHandler = new PlanetHandler(this);

        stars = new Texture(Gdx.files.internal("stars.png"));
        bg = new Texture(Gdx.files.internal("controller.png"));
        stats_bar = new Texture(Gdx.files.internal("Stats_Bar.png"));


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
        cam.position.set(player.player.getX() + 30, player.player.getY() + 280, 0);
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        elapsedTime += delta;

//        SPAWNERS
        asteroids.spawnAsteroid();

//        UPDATERS
        asteroids.update();
        aniHandler.update();
        planetHandler.update();
        uiHandler.update();
        game.waveHandler.updateWave();

//        RENDER BATCH
        game.batch.begin();
        game.batch.draw(stars, -1000, -200, stars.getWidth(), stars.getHeight());
        planetHandler.render();
        asteroids.render();
        aniHandler.render();

        game.batch.draw(
                bg,
                player.player.getX() - 210,
                player.player.getY() - 120,
                bg.getWidth() - 40,
                bg.getHeight() / 2F
        );
        game.batch.draw(
                stats_bar,
                player.player.getX() - 210,
                player.player.getY() + 90,
                stats_bar.getWidth() - 600,
                stats_bar.getHeight() - 50
        );




        uiHandler.render();

        game.batch.end();


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
                        480 / 2,
                        160,
                        64,
                        64
                },
                cam
        );
    }

}
