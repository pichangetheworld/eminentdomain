package com.dimsum.eminentdomain.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.dimsum.eminentdomain.EminentDomainGame;

public class GameScreen implements Screen {
	private final EminentDomainGame _game;
	
	private List<String> hand;

	private static final String[] _IMAGE_NAMES = {
		"colonize",
		"planet",
		"research",
		"survey",
		"tradeproduce",
		"warfare"
	};

	// TextureAtlas (for drawing)
	private TextureAtlas _textureAtlas;

	private Sprite _coloniseImage;
	private Sprite _planetImage;
	private Sprite _warfareImage;
	private Sprite _surveyImage;
	private Sprite _produceTradeImage;
	private Sprite _researchImage;

	private OrthographicCamera _camera;

	private Vector3 _touchPos;
	private int _score;
	
	private long lastDropTime = 0;

	public GameScreen(final EminentDomainGame game) {
		this._game = game;

		// Initialise Textures
		_textureAtlas = new TextureAtlas(Gdx.files.internal("cards.atlas"));
		_coloniseImage = new Sprite(_textureAtlas.findRegion(_IMAGE_NAMES[0]));
		_coloniseImage.setScale(0.5f);

		// Create camera
		_camera = new OrthographicCamera();
		_camera.setToOrtho(false, 800, 480);

		_touchPos = new Vector3();
		
		hand = new ArrayList<String>();
		hand.add("colonize");

		_score = 0;
	}

	@Override
	public void render(float delta) {
		// WIPE THE FIELD
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// allow user to move bucket using touch
		if (Gdx.input.isTouched() && TimeUtils.millis() - lastDropTime > 500) {
			_touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			_camera.unproject(_touchPos);
			hand.add("colonize");
			lastDropTime = TimeUtils.millis();
		}

		_camera.update();

		_game.batch.setProjectionMatrix(_camera.combined);
		_game.batch.begin();
		int n = hand.size();
		if (n < 800 / 96) {
			for (int i = 0; i < n; ++i) {
				_game.batch.draw(_coloniseImage, 400 - ((float)n/2 - i) * 96, 20, 96, 128);
			}
		} else {
			for (int i = 0; i < n; ++i) {
				_game.batch.draw(_coloniseImage, i * (800 - 96)/(n-1), 20, 96, 128);
			}
		}
		
		_game.font.draw(_game.batch, "Score:" + (_score < 100 ? " " : "")
				+ (_score < 10 ? " " : "") + _score, 800 - 280, 480 - 10);
		_game.batch.end();
	}

	@Override
	public void dispose() {
		_textureAtlas.dispose();
		_game.batch.dispose();

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
}
