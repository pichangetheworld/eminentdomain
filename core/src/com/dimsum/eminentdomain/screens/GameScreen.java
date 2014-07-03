package com.dimsum.eminentdomain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.dimsum.eminentdomain.EminentDomainGame;
import com.dimsum.eminentdomain.GameManager;
import com.dimsum.eminentdomain.player.Player;
import com.dimsum.eminentdomain.states.GameState;

public class GameScreen implements Screen {

	private final EminentDomainGame _game;
	private Stage _stage;

	// TextureAtlas (for drawing)
	private TextureAtlas textureAtlas; // ** image of buttons **//
	private Skin buttonSkin; // ** images are used as skins of the button **//

	public GameScreen(final EminentDomainGame game) {
		this._game = game;

		this._stage = this._game.getStage();
		
		// Initialise Textures
		// ** button atlas image ** //
		textureAtlas = new TextureAtlas(Gdx.files.internal("assets.atlas"));

		buttonSkin = new Skin();
		buttonSkin.addRegions(textureAtlas); // ** skins for on and off **//

		addButtons();

		GameManager.getInstance().init(2);
	}

	@Override
	public void render(float delta) {
		// WIPE THE FIELD
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_stage.act(Gdx.graphics.getDeltaTime());

		GameState.getInstance().getActivePlayer().renderToScreen(_stage);
		_stage.draw();
	}

	private void addButtons() {
		TextButtonStyle style = new TextButtonStyle(); // ** Button properties **//
		style.up = buttonSkin.getDrawable("button_up");
		style.down = buttonSkin.getDrawable("button_down");
		style.font = new BitmapFont();
		
		// ** Button text and style **//
		TextButton showHandButton = new TextButton("Show Hand", style);
		showHandButton.setPosition(10, 10); // ** Button location **//
		showHandButton.setHeight(200); // ** Button Height **//
		showHandButton.setWidth(100); // ** Button Width **//
		showHandButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("show hand", "Pressed");
				GameState.getInstance().getActivePlayer().toggleShowHand();
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("show hand", "Released");
			}
		});

		TextButton drawCardButton = new TextButton("Discard Card", style);
		drawCardButton.setPosition(_stage.getWidth() - 120, 10);
		drawCardButton.setHeight(200); // ** Button Height **//
		drawCardButton.setWidth(100); // ** Button Width **//
		drawCardButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("draw card", "Pressed");
				Player p = GameState.getInstance().getActivePlayer();
				if (!p.getHand().isEmpty())
					p.discardCard(p.getHand().remove(0));
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("draw card", "Released");
//				drawCard();
			}
		});

		_stage.addActor(showHandButton);
		_stage.addActor(drawCardButton);
	}

	@Override
	public void dispose() {
		// _textureAtlas.dispose();
		textureAtlas.dispose();
		buttonSkin.dispose();
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
