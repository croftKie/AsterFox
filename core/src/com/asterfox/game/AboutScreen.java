package com.asterfox.game;

import com.asterfox.game.entities.Asteroid;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class AboutScreen implements Screen {

    final AsterFox game;
    final OrthographicCamera cam;
    final Rectangle menuRect;
    private Texture about, back;
    public AboutScreen(AsterFox game){
        this.game = game;

        menuRect = new Rectangle();
        menuRect.x = 800 / 2 - 150;
        menuRect.y = 480 / 2 - 150;
        menuRect.width = 300;
        menuRect.height = 300;

        generateMenuImages();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);
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

        game.batch.draw(about, 0, 0, 800, 480);
        game.batch.draw(back, 10, 380, 64, 64);
        game.batch.end();

        if (Gdx.input.isTouched()) {
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


    public void generateMenuImages(){
        about = new Texture(Gdx.files.internal("about_screen.png"));
        back = new Texture(Gdx.files.internal("back-button.png"));
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

