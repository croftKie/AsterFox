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
                        60,
                        40,
                        64,
                        64
                });
        rightButton = new Button(
                "right_button.png",
                new float[]{
                        150,
                        40,
                        64,
                        64
                }
        );
        fireButton = new Button(
                "fire_button.png",
                new float[]{
                        800 - 124,
                        40,
                        64,
                        64
                }
        );
        speedButton = new Button(
                "boost.png",
                new float[]{
                        800 - 124,
                        120,
                        64,
                        64
                }
        );
        reloadButton = new Button(
                "reload.png",
                new float[]{
                        800 - 218,
                        40,
                        64,
                        64
                }
        );

    }

    public void generateHUD(){
        bulletIcons = new Array<Icon>();
        for (int i = 1; i <= gs.bullets.bulletsLoaded; i++) {
            Icon tempIcon = new Icon(
                    "bullet.png",
                    new float[]{
                            32 * i,
                            350,
                            32,
                            32
                    }
            );
            bulletIcons.add(tempIcon);
        }
    }
}
