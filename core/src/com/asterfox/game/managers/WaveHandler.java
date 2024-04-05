package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;

public class WaveHandler {
    GameScreen gs;
    public int wave = 1;
    public int score = 20;
    public WaveHandler(GameScreen gs){
        this.gs = gs;
    }

    public void updateWave(){
        if(score == 0){
            increaseWave();
            increaseScore();
        }
    }

    public void increaseWave(){
        wave++;
    }

    public void decreaseScore(){
        if(score > 0){
            score--;
        } else {
            updateWave();
        }
    }

    public void increaseScore(){
        score = (int) ((20 * wave) * 0.55);
     }

}
