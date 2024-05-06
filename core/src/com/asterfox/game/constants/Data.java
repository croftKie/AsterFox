package com.asterfox.game.constants;

import com.badlogic.gdx.graphics.Color;

public class Data {
    public static class GameWindow {
        public static final int vp_width = 480;
        public static final int vp_height = 800;
    }
    public static class UI {
        public static final int scale = 3;
        public static final String[] text = new String[]{"AsterFox", "Tap Ship to Play"};
        public static final String[] intro = new String[]{
                "You're not a hero...",
                "You're a prisoner.",
                "Trapped aboard a creaking spaceship,",
                "With a life sentence.",
                "If you want to end your sentence early.",
                "Clear the asteroid fields."
        };
    }
}
