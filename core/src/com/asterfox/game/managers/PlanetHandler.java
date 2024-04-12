package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.MapScreen;
import com.asterfox.game.entities.Planet;
import com.badlogic.gdx.utils.Array;

public class PlanetHandler {
    public Array<Planet> planets;
    public String[] planetOptions = new String[]{
            "planet01.png",
            "planet02.png",
            "planet03.png",
            "planet04.png",
            "planet05.png",
            "planet06.png",
            "planet07.png",
            "planet08.png",
            "planet09.png",

    };
    public Array<String> planetsUnlocked = new Array<String>(){};
    public Array<String> planetsComplete = new Array<String>(){};
    public String currentLevel;
    private GameScreen gs;
    private MapScreen ms;

    public PlanetHandler(GameScreen gs){
        this.gs = gs;
        planets = new Array<Planet>(){};
    }
    public PlanetHandler(MapScreen ms){
        this.ms = ms;
        planets = new Array<Planet>(){};
        planetsUnlocked.add("planet08.png");
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

            if (planetsComplete.contains(planetOptions[i],true)){
                planets.get(i).planet.setColor(0, 122, 0, 1);
            }
        }
    }

    public void spawnPlanet(int index, float x, float y){
        Planet p = new Planet(
                gs,
                planetOptions[index],
                new float[]{
                    x,
                    y,
                    64,
                    64
                }
                );

        if (!planetsUnlocked.contains(planetOptions[index], true)){
            p.planet.setColor(255,255,255,0.25f);
        }

        planets.add(p);
    };
    public void spawnPlanet(int index){
        Planet p = new Planet(
                gs,
                planetOptions[index],
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
