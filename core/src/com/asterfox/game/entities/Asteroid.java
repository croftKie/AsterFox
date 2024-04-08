package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameOver;
import com.asterfox.game.GameScreen;
import com.asterfox.game.MainMenu;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Asteroid extends Entity{
    public int rotationOption;
    public float xOption;
    public Sprite asteroid;
    private GameScreen gs;
    private MainMenu mm;
    private GameOver gm;

    public Asteroid(String asset, float[] dimens, GameScreen gs, int rotationOption, float xOption){
        asteroid = createAsset(asset, dimens);
        this.gs = gs;
        this.rotationOption = rotationOption;
        this.xOption = xOption;
    }

    public Asteroid(String asset, float[] dimens, MainMenu mm, int rotationOption, float xOption){
        asteroid = createAsset(asset, dimens);
        this.mm = mm;
        this.rotationOption = rotationOption;
        this.xOption = xOption;
    }
    public Asteroid(String asset, float[] dimens, GameOver gm, int rotationOption, float xOption){
        asteroid = createAsset(asset, dimens);
        this.gm = gm;
        this.rotationOption = rotationOption;
        this.xOption = xOption;
    }

    public void render(AsterFox game){
        draw(game, asteroid);
    }

    public void update(){
        moveAsteroid();
    }

    private void moveAsteroid(){
        float y = asteroid.getY() - (3 * (gs.waveHandler.wave * 0.55f));
        asteroid.setY(y);
        asteroid.setX(asteroid.getX() + xOption);
        asteroid.rotate(rotationOption);
    }

    public boolean isDestroyable(){
        if(asteroid.getBoundingRectangle().getY() < 0 - 64){
            return true;
        }
        for (int i = 0; i < gs.bullets.bullets.size; i++) {
            if (asteroid.getBoundingRectangle().overlaps(gs.bullets.bullets.get(i).bullet.getBoundingRectangle())) {

                gs.bullets.bullets.removeIndex(i);
                gs.waveHandler.decreaseScore();
                break;
            }
        }
        return false;
    }
}
