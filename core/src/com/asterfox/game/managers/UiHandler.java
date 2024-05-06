package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.asterfox.game.entities.Button;
import com.asterfox.game.entities.Icon;
import com.badlogic.gdx.utils.Array;

public class UiHandler {
    private GameScreen gs;
    public Button leftbutton, rightButton, fireButton, speedButton, reloadButton;
    public Array<Icon> bulletIcons;
    public UiHandler(GameScreen gs){
        this.gs = gs;
        generateUI();
        generateHUD();
    }

    public void update(){
        leftbutton.button.setX(gs.player.player.getX() - 220);
        rightButton.button.setX(gs.player.player.getX() - 110);
        fireButton.button.setX(gs.player.player.getX() + 100);
        speedButton.button.setX(gs.player.player.getX() + 180);
        reloadButton.button.setX(gs.player.player.getX() + 180);

    }

    public void render(){
        leftbutton.draw(gs.game);
        rightButton.draw(gs.game);
        fireButton.draw(gs.game);
        speedButton.draw(gs.game);
        reloadButton.draw(gs.game);

        for (Icon bulletIcon : bulletIcons) {
            bulletIcon.draw(gs.game);
        }
    }


    public void generateUI(){
        leftbutton = new Button(
                "left_button.png",
                new float[]{
                        gs.player.player.getX() - 220,
                        60,
                        64,
                        64
                });
        leftbutton.button.setColor(255, 255,255,0.5f);
        rightButton = new Button(
                "right_button.png",
                new float[]{
                        gs.player.player.getX() - 110,
                        60,
                        64,
                        64
                }
        );
        rightButton.button.setColor(255, 255,255,0.5f);
        fireButton = new Button(
                "fire_button.png",
                new float[]{
                        gs.player.player.getX() + 100,
                        40,
                        64,
                        64
                }
        );
        fireButton.button.setColor(255, 255,255,0.5f);
        speedButton = new Button(
                "boost.png",
                new float[]{
                        gs.player.player.getX() + 180,
                        120,
                        64,
                        64
                }
        );
        speedButton.button.setColor(255, 255,255,0.5f);
        reloadButton = new Button(
                "reload.png",
                new float[]{
                        gs.player.player.getX() + 180,
                        40,
                        64,
                        64
                }
        );
        reloadButton.button.setColor(255, 255,255,0.5f);
    }

    public void generateHUD(){
        bulletIcons = new Array<Icon>();
        for (int i = 0; i <= gs.bullets.bulletsLoaded; i++) {
            Icon tempIcon = new Icon(
                    "bullet.png",
                    new float[]{
                            (32 * i),
                            680,
                            16,
                            16
                    }
            );
            bulletIcons.add(tempIcon);
        }
    }
}
