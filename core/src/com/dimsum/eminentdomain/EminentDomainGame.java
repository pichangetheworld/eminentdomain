package com.dimsum.eminentdomain;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dimsum.eminentdomain.screens.MainMenuScreen;

public class EminentDomainGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont();
        font.setScale(4);
        
        this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
