package com.asterfox.game;

import static com.asterfox.game.constants.Data.GameWindow.vp_height;
import static com.asterfox.game.constants.Data.GameWindow.vp_width;

import com.asterfox.game.entities.Planet;
import com.asterfox.game.managers.PlanetHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Arrays;

public class MapScreen implements Screen {
    public AsterFox game;
    final OrthographicCamera cam;
    private PlanetHandler planetHandler;
    public float[] locsX = {80, 280, 90, 120, 290};
    public float[] locsY = {110, 270, 390, 530, 650};
    private Texture back, bg;
    private boolean leaving = false;
    private Long timer;
    private Planet clickedPlanet;
    private Array<Planet> otherPlanets;
    public MapScreen(AsterFox game){
        this.game = game;
        this.planetHandler = new PlanetHandler(this);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, vp_width, vp_height);
        back = new Texture(Gdx.files.internal("back-button.png"));
        bg = new Texture(Gdx.files.internal("star_bg.png"));
    }

    @Override
    public void show() {
        for (int i = 0; i < locsX.length; i++) {
            planetHandler.spawnPlanet(i, locsX[i], locsY[i]);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        cam.update(); // LOOK UP WHAT THIS IS DOING
        game.batch.setProjectionMatrix(cam.combined); // LOOK UP WHAT THIS IS DOING


        planetHandler.updateMap();

        game.batch.begin();
        game.batch.draw(bg, 0, 0, bg.getWidth(), bg.getHeight());
        planetHandler.renderMap();
            game.batch.draw(back, 30, 720, 64, 64);
        game.batch.end();


        if (Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            for (int i = 0; i < locsX.length; i++) {
                if (
                        (touchPos.x > locsX[i] && touchPos.x < locsX[i] + 64) &&
                        (touchPos.y > locsY[i] && touchPos.y < locsY[i] + 64)
                ) {
                    if (game.waveHandler.planetsUnlocked.contains(game.waveHandler.planetOptions[i], true)){
                        leaving = true;
                        timer = TimeUtils.millis();
                        clickedPlanet = planetHandler.planets.get(i);
                        otherPlanets = new Array<Planet>();
                        for (Planet planet : planetHandler.planets) {
                            otherPlanets.add(planet);
                        }
                        otherPlanets.removeIndex(i);
                    }
                }
            }

            clickButton(30, 720, 64, 64, new MainMenu(game));
        }

        if (leaving){
            if (TimeUtils.timeSinceMillis(timer) <= 1800){
                for (int i = 0; i < otherPlanets.size; i++) {
                    otherPlanets.get(i).reducePlanet();
                    otherPlanets.get(i).planet.setColor(
                            255,255,255,otherPlanets.get(i).planet.getColor().a - 0.2F
                    );
                }
                clickedPlanet.movePlanetFast();
            } else {
                leaving = false;
                game.setScreen(new GameScreen(game));
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


    public void clickButton(int x, int y, int width, int height, Screen screen){
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touchPos);
        if (touchPos.x > x && touchPos.x < x + width) {
            if (touchPos.y > y && touchPos.y < y + height) {
                game.setScreen(screen);
                dispose();
            }
        }
    };
}
