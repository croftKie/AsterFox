package com.asterfox.game;

import com.asterfox.game.managers.WaveHandler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AsterFox extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public int selectedLevel = 0;
	public WaveHandler waveHandler;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(); // uses default font currently
		this.setScreen(new MainMenu(this));
		waveHandler = new WaveHandler(this);
	}

	@Override
	public void render () {
		super.render(); // REQUIRED
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
