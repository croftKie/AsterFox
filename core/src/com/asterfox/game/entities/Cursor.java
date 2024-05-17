package com.asterfox.game.entities;

import com.asterfox.game.AsterFox;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Cursor extends Entity {
    public Sprite cursor;
    private int speed = 10;
    public Cursor(String assetFile, float[] dimens){
        cursor = createAsset(assetFile, dimens);
    }

    public void draw(AsterFox game){
        draw(game, cursor);
    }

    public void move(int direc){
        switch (direc){
            case 0:
                cursor.setY(cursor.getY() + speed);
                break;
            case 1:
                cursor.setX(cursor.getX() + speed);
                break;
            case 2:
                cursor.setY(cursor.getY() - speed);
                break;
            case 3:
                cursor.setX(cursor.getX() - speed);
                break;
            default:
                break;
        }
    }

}
