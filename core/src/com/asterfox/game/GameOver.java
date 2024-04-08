package com.asterfox.game;

import com.asterfox.game.entities.Asteroid;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOver implements Screen {

    final AsterFox game;
    final OrthographicCamera cam;
    final Texture menuBackground;
    final Rectangle menuRect;
    private Texture over;
    private Asteroid large;
    public GameOver(AsterFox game){
        this.game = game;

        menuBackground = new Texture(Gdx.files.internal("metalPanel.png"));
        menuRect = new Rectangle();
        menuRect.x = 800 / 2 - 150;
        menuRect.y = 480 / 2 - 150;
        menuRect.width = 300;
        menuRect.height = 300;

        generateMenuImages();
        generateAsteroids();

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

        large.render(game);
        game.batch.draw(over, 10, 90, 576, 460);

        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenu(game));
            dispose();
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
        over = new Texture(Gdx.files.internal("over_text.png"));
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
    }

}
