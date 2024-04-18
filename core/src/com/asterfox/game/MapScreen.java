package com.asterfox.game;

import static com.asterfox.game.constants.Data.GameWindow.vp_height;
import static com.asterfox.game.constants.Data.GameWindow.vp_width;

import com.asterfox.game.managers.PlanetHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class MapScreen implements Screen {
    public AsterFox game;
    final OrthographicCamera cam;
    private PlanetHandler planetHandler;
    public float[] locsX = {50, 140, 220, 330, 395, 470, 540, 610, 700};
    public float[] locsY = {120, 210, 85, 305, 155, 390, 65, 265, 50};
    private Texture back;

    public MapScreen(AsterFox game){
        this.game = game;
        this.planetHandler = new PlanetHandler(this);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, vp_width, vp_height);
        back = new Texture(Gdx.files.internal("back-button.png"));
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
            game.batch.draw(back, 10, 380, 64, 64);
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

            clickButton(10, 380, 64, 64, new MainMenu(game));
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
