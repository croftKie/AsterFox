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
        asteroid.setSize(asteroid.getWidth() + 0.5F, asteroid.getHeight() + 0.5F);
    }

    public boolean isDestroyable(int mode){
        if(asteroid.getBoundingRectangle().getWidth() > 320){
            return true;
        }
        if(mode == 1 && asteroid.getBoundingRectangle().contains(gs.uiHandler.cursor.cursor.getBoundingRectangle())){
            return true;
        }
        return false;
    }
}
