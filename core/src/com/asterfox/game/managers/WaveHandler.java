package com.asterfox.game.managers;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.asterfox.game.MainMenu;
import com.asterfox.game.MapScreen;
import com.badlogic.gdx.utils.Array;

public class WaveHandler {
    AsterFox game;
    public int wave = 1;
    public int score = 1;
    public Array<String> planetsUnlocked = new Array<String>(){};
    public Array<String> planetsComplete = new Array<String>(){};
    public String[] planetOptions = new String[]{
            "planet09.png",
            "planet08.png",
            "planet07.png",
            "planet06.png",
            "planet05.png",
            "planet04.png",
            "planet03.png",
            "planet02.png",
            "planet01.png",
    };
    public WaveHandler(AsterFox game){
        this.game = game;
        planetsUnlocked.add("planet09.png");
    }

    public void updateWave(){
        if (score < 1 && wave % 2 == 0 && game.selectedLevel + 1 >= planetOptions.length){
            game.setScreen(new MainMenu(game));
            return;
        }

        if(score < 1){
            if (wave % 2 == 0){
                game.setScreen(new MapScreen(game));
                wave = 1;
                increaseScore();
                planetsComplete.add(game.waveHandler.planetOptions[game.selectedLevel]);
                planetsUnlocked.add(game.waveHandler.planetOptions[game.selectedLevel + 1]);
                game.selectedLevel++;
            } else {
                increaseWave();
                increaseScore();
            }
        }

    }

    public void increaseWave(){
        wave++;
    }

    public void decreaseScore(){
        if(score >= 1){
            score--;
        } else {
            updateWave();
        }
    }

    public void increaseScore(){
        score = (int) ((1 * wave) * 0.55);
     }

}
