package com.asterfox.game;

import static com.asterfox.game.constants.Data.GameWindow.vp_height;
import static com.asterfox.game.constants.Data.GameWindow.vp_width;

import com.asterfox.game.managers.PlanetHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class MapScreen implements Screen {
    public AsterFox game;
    final OrthographicCamera cam;
    private PlanetHandler planetHandler;
    public float[] locsX = {50, 140, 220, 330, 395, 470, 540, 610, 700};
    public float[] locsY = {320, 210, 85, 305, 155, 390, 65, 265, 50};

    public MapScreen(AsterFox game){
        this.game = game;
        this.planetHandler = new PlanetHandler(this);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, vp_width, vp_height);
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
            planetHandler.renderMap();
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
                        game.setScreen(new GameScreen(game));
                        dispose();
                    }
                }
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
}
