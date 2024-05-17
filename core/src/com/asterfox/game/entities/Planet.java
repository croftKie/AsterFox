package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.asterfox.game.MainMenu;
import com.asterfox.game.MapScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Planet extends Entity{
    private GameScreen gs;
    private MapScreen ms;
    private MainMenu mm;
    public Sprite planet;

    public Planet(GameScreen gs, String asset, float[] dimens){
        this.gs = gs;
        this.planet = createAsset(asset, dimens);
    }
    public Planet(MapScreen ms, String asset, float[] dimens){
        this.ms = ms;
        this.planet = createAsset(asset, dimens);
    }
    public Planet(MainMenu mm, String asset, float[] dimens){
        this.mm = mm;
        this.planet = createAsset(asset, dimens);
    }

    public void render(AsterFox game){
        draw(game, planet);
    }

    public void update(){
        movePlanet();
    }
    public void swingPlanet(){
        planet.setX(planet.getX() - 0.02F);
        planet.setY(planet.getY() + 0.01F);
    }

    public void movePlanet(){
        planet.setSize(planet.getWidth() + 0.01F, planet.getHeight() + 0.01F);
    }
    public void movePlanetFast(){
        planet.setSize(planet.getWidth() + 10F, planet.getHeight() + 10F);
    }
    public void reducePlanet(){
        if(planet.getWidth() >= 0) {
            planet.setSize(planet.getWidth() - 2F, planet.getHeight() - 2F);
        }
    }
}
