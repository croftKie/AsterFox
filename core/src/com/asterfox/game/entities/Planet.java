package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.asterfox.game.MapScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Planet extends Entity{
    private GameScreen gs;
    private MapScreen ms;
    public Sprite planet;

    public Planet(GameScreen gs, String asset, float[] dimens){
        this.gs = gs;
        this.planet = createAsset(asset, dimens);
    }
    public Planet(MapScreen ms, String asset, float[] dimens){
        this.ms = ms;
        this.planet = createAsset(asset, dimens);
    }

    public void render(AsterFox game){
        draw(game, planet);
    }

    public void update(){
        movePlanet();
    }

    public void movePlanet(){
        float y = planet.getY() - 0.1f;
        planet.setY(y);
    }
}
