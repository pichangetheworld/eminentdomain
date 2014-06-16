package com.dimsum.eminentdomain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.dimsum.eminentdomain.EminentDomainGame;
import com.dimsum.eminentdomain.cards.Card;
import com.dimsum.eminentdomain.cards.Colonize;

public class GameScreen implements Screen {
	private static final float cw = 192; // card width
	private static final float ch = 256; // card height
	private static float sw_offset; // stage width minus offset for button
	private static float sh_offset; // stage height

	private final EminentDomainGame _game;

	private Group _hand;
	private boolean _showHand;
	private Stage _stage;

	// TextureAtlas (for drawing)
	private TextureAtlas textureAtlas; // ** image of buttons **//
	private Skin buttonSkin; // ** images are used as skins of the button **//

	public GameScreen(final EminentDomainGame game) {
		_stage = new Stage();
		Gdx.input.setInputProcessor(_stage);

		sw_offset = _stage.getWidth();
		sh_offset = (_stage.getHeight() - ch) / 2;

		this._game = game;

		// Initialise Textures
		textureAtlas = new TextureAtlas(Gdx.files.internal("assets.atlas")); // **
																				// button
																				// atlas
																				// image
																				// **//

		buttonSkin = new Skin();
		buttonSkin.addRegions(textureAtlas); // ** skins for on and off **//

		_showHand = false;
		addButtons();

		_hand = new Group();
		_hand.setPosition(0, -5 * cw / 4);
		drawCard();
		drawCard();

		_stage.addActor(_hand);
	}

	@Override
	public void render(float delta) {
		// WIPE THE FIELD
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_stage.act(Gdx.graphics.getDeltaTime());
		_stage.draw();
	}

	private void drawCard() {
		Card a = new Colonize();
		a.setSize(cw, ch);
		_hand.addActor(a);

		int n = _hand.getChildren().size;
		int i = 0;
		for (Actor actor : _hand.getChildren()) {
			if (n < sw_offset / cw) {
				actor.setPosition(sw_offset / 2 - ((float) n / 2 - i) * cw, 0);
			} else {
				actor.setPosition(i * (sw_offset - cw) / (n - 1), 0);
			}
			++i;
		}
	}

	private void addButtons() {
		TextButtonStyle style = new TextButtonStyle(); // ** Button properties
														// **//
		style.up = buttonSkin.getDrawable("button_up");
		style.down = buttonSkin.getDrawable("button_down");
		style.font = new BitmapFont();

		TextButton showHandButton = new TextButton("Show Hand", style); // **
																		// Button
																		// text
																		// and
																		// style
																		// **//
		showHandButton.setPosition(10, 10); // ** Button location **//
		showHandButton.setHeight(200); // ** Button Height **//
		showHandButton.setWidth(100); // ** Button Width **//
		showHandButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("show hand", "Pressed");
				_showHand = !_showHand;
				if (_showHand) {
					_hand.setPosition(0, sh_offset);
				} else {
					_hand.setPosition(0, -5 * cw / 4);
				}
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("show hand", "Released");
			}
		});

		TextButton drawCardButton = new TextButton("Draw Card", style); // **
																		// Button
																		// text
																		// and
																		// style
																		// **//
		drawCardButton.setPosition(_stage.getWidth() - 120, 10); // ** Button
																	// location
																	// **//
		drawCardButton.setHeight(200); // ** Button Height **//
		drawCardButton.setWidth(100); // ** Button Width **//
		drawCardButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("draw card", "Pressed");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("draw card", "Released");
				drawCard();
			}
		});

		_stage.addActor(showHandButton);
		_stage.addActor(drawCardButton);
	}

	@Override
	public void dispose() {
		// _textureAtlas.dispose();
		_stage.dispose();
		textureAtlas.dispose();
		buttonSkin.dispose();
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
