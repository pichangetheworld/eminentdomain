package com.dimsum.eminentdomain;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dimsum.eminentdomain.screens.MainMenuScreen;

public class EminentDomainGame extends Game {
	private Stage _stage;
	
	@Override
	public void create () {
		_stage = new Stage();
		Gdx.input.setInputProcessor(_stage);
        
        this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		_stage.dispose();
	}
	
	public Stage getStage() {
		return _stage;
	}
}
