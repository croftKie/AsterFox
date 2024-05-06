package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.MapScreen;
import com.asterfox.game.entities.Planet;
import com.badlogic.gdx.utils.Array;

public class PlanetHandler {
    public Array<Planet> planets;

    private GameScreen gs;
    private MapScreen ms;

    public PlanetHandler(GameScreen gs){
        this.gs = gs;
        planets = new Array<Planet>(){};
    }
    public PlanetHandler(MapScreen ms){
        this.ms = ms;
        planets = new Array<Planet>(){};
    }

    public void render(){
        for (Planet planet : planets) {
            planet.render(gs.game);
        }
    }
    public void renderMap(){
        for (Planet planet : planets) {
            planet.render(ms.game);
        }
    }
    public void update(){
        for (int i = 0; i < planets.size; i++) {
            planets.get(i).update();
        }
    }
    public void updateMap(){
        for (int i = 0; i < planets.size; i++) {

            if (!ms.game.waveHandler.planetsUnlocked.contains(ms.game.waveHandler.planetOptions[i], true)){
                planets.get(i).planet.setColor(255,255,255,0.25f);
            } else if (ms.game.waveHandler.planetsComplete.contains(ms.game.waveHandler.planetOptions[i],true)){
                planets.get(i).planet.setColor(0, 122, 0, 1);
            } else {
                planets.get(i).planet.setColor(255,255,255,1);
            }
        }
    }

    public void spawnPlanet(int index, float x, float y){
        Planet p = new Planet(
                ms,
                ms.game.waveHandler.planetOptions[index],
                new float[]{
                    x,
                    y,
                    128,
                    128
                }
                );
        planets.add(p);
    };
    public void spawnPlanet(int index){
        Planet p = new Planet(
                gs,
                gs.game.waveHandler.planetOptions[index],
                new float[]{
                        200,
                        200,
                        1280,
                        1280
                }
        );
        planets.add(p);
    };
}
