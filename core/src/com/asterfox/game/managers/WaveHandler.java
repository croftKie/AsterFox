package com.asterfox.game.managers;

import com.asterfox.game.AsterFox;
import com.asterfox.game.GameScreen;
import com.asterfox.game.MapScreen;

public class WaveHandler {
    AsterFox game;
    public int wave = 1;
    public int score = 20;
    public WaveHandler(AsterFox game){
        this.game = game;
    }

    public void updateWave(){
        if(score < 1){
            if (wave % 2 == 0){
                game.setScreen(new MapScreen(game));
                wave = 1;
                increaseScore();
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
        score = (int) ((20 * wave) * 0.55);
     }

}
