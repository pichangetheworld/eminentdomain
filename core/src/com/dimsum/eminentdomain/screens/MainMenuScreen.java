package com.dimsum.eminentdomain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.dimsum.eminentdomain.EminentDomainGame;

public class MainMenuScreen implements Screen {

	private final EminentDomainGame _game;

	private Stage _stage;

	public MainMenuScreen(final EminentDomainGame game) {
		this._game = game;

		this._stage = _game.getStage();

		LabelStyle labelStyle = new LabelStyle(); // ** Button properties **//
		labelStyle.font = new BitmapFont();

		Label label = new Label("Eminent Domain", labelStyle);
		label.setFontScale(4f);
		label.setPosition((_stage.getWidth() - 4 * label.getWidth()) / 2,
				_stage.getHeight() / 2);
		this._stage.addActor(label);

		Label label2 = new Label("Tap anywhere to begin!", labelStyle);
		label2.setFontScale(4f);
		label2.setPosition((_stage.getWidth() - 4 * label2.getWidth()) / 2,
				_stage.getHeight() / 2 - 100);
		this._stage.addActor(label2);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_stage.act(Gdx.graphics.getDeltaTime());
		_stage.draw();

		if (Gdx.input.isTouched()) {
			_stage.clear();
			_game.setScreen(new GameScreen(_game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
