package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.entities.Button;
import com.asterfox.game.entities.Cursor;
import com.asterfox.game.entities.Icon;
import com.badlogic.gdx.utils.Array;

public class UiHandler {
    private GameScreen gs;
    public Button leftbutton, rightButton, fireButton, up, down, pause;
    public Cursor cursor;
    public UiHandler(GameScreen gs){
        this.gs = gs;
        generateUI();
    }

    public void update(){
        leftbutton.button.setX(gs.player.player.getX() + 80);
        rightButton.button.setX(gs.player.player.getX() + 170);
        fireButton.button.setX(gs.player.player.getX() + 135);
        up.button.setX(gs.player.player.getX() - 140);
        down.button.setX(gs.player.player.getX() - 140);
        cursor.cursor.setX(gs.player.player.getX());
        pause.button.setX(gs.player.player.getX() - 190);
    }

    public void render(){
        leftbutton.draw(gs.game);
        rightButton.draw(gs.game);
        fireButton.draw(gs.game);
        up.draw(gs.game);
        down.draw(gs.game);
        cursor.draw(gs.game);
        pause.draw(gs.game);
    }


    public void generateUI(){
        leftbutton = new Button(
                "arrow-left.png",
                new float[]{
                        gs.player.player.getX() + 80,
                        80,
                        64,
                        64
                });
        rightButton = new Button(
                "arrow-right.png",
                new float[]{
                        gs.player.player.getX() + 170,
                        80,
                        64,
                        64
                }
        );
        fireButton = new Button(
                "explosive.png",
                new float[]{
                        gs.player.player.getX() + 155,
                        160,
                        64,
                        64
                }
        );
        up = new Button(
                "arrow-up.png",
                new float[]{
                        gs.player.player.getX() - 215,
                        140,
                        64,
                        64
                }
        );
        down = new Button(
                "arrow-down.png",
                new float[]{
                        gs.player.player.getX() - 215,
                        60,
                        64,
                        64
                }
        );
        cursor = new Cursor(
                "target.png",
                new float[]{
                        220,
                        600,
                        32,
                        32
                }
        );
        pause = new Button(
                "pause.png",
                new float[]{
                        gs.player.player.getX() - 215,
                        760,
                        64,
                        64
                }
        );
    }
}
