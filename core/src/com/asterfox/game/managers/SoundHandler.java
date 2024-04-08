package com.asterfox.game.managers;

import com.asterfox.game.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundHandler {
    public Music bgMusic;
    public Music engine;
    public Sound explosion_crunch;
    public Sound explosion_low;
    public Sound laser;

    public SoundHandler(GameScreen gs){
        this.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("bgmusic.wav"));
        this.engine = Gdx.audio.newMusic(Gdx.files.internal("engine.ogg"));
        this.laser = Gdx.audio.newSound(Gdx.files.internal("laser.ogg"));
        this.explosion_crunch = Gdx.audio.newSound(Gdx.files.internal("explosion_crunch.ogg"));
        this.explosion_low = Gdx.audio.newSound(Gdx.files.internal("explosion_low.ogg"));



        bgMusic.setVolume(0.5f);
        bgMusic.setLooping(true);
        engine.setLooping(true);
    }

    public void playMusic(){
        bgMusic.play();
    }
    public void playlaser(){
        laser.play();
    }
    public void playExplosion(){
        explosion_crunch.play();
        explosion_low.play();
    }
    public void playEngine(){
        engine.play();
    }

}
