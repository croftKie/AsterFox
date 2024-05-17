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
        if(score % 3000 == 0){
            increaseWave();
            increaseScore();
        } else {
            increaseScore();
        }
    }

    public void resetScore(){
        score = 1;
    }

    public void increaseWave(){
        wave++;
    }

    public void increaseScore(){
        score++;
     }

}
