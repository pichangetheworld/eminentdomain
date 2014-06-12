package com.dimsum.eminentdomain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dimsum.eminentdomain.EminentDomainGame;

public class MainMenuScreen implements Screen {

    private final EminentDomainGame _game;

    private OrthographicCamera _camera;

    public MainMenuScreen(final EminentDomainGame game) {
        _game = game;

        _camera = new OrthographicCamera();
        _camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _camera.update();
        _game.batch.setProjectionMatrix(_camera.combined);

        _game.batch.begin();
        _game.font.draw(_game.batch, "Welcome to Drop!!! ", 100, 300);
        _game.font.draw(_game.batch, "Tap anywhere to begin!", 100, 200);
        _game.batch.end();

        if (Gdx.input.isTouched()) {
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
