package com.dimsum.eminentdomain.player;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dimsum.eminentdomain.cards.Card;

public class PlayerGraphics {
	private Player _player;
	private Group _hand;
	
	private Card _deckSprite;
	private Card _discardSprite;

	protected boolean _showHand;

	PlayerGraphics(final Player player) {
		this._player = player;
		this._showHand = false;

		_hand = new Group();
		_discardSprite = new Card();
		_deckSprite = new Card();
	}
	
	public void toggleShowHand() {
		_showHand = !_showHand;
	}
	
	public void addToStage(final Stage stage) {
		stage.addActor(_discardSprite);
		stage.addActor(_deckSprite);
		
		stage.addActor(_hand);
	}
	
	public void draw(final Stage stage) {
		_hand.setPosition(0, -0.87f * Card.ch);

		int n = _player.getHand().size();
		int i = 0;
		
		if (_hand.getChildren().size != _player.getHand().size()) {
			_hand.clear();
			for (Card card : _player.getHand()) {
				_hand.addActor(card);
				if (n < stage.getWidth() / Card.cw) {
					card.setPosition(stage.getWidth() / 2 - ((float) n / 2 - i) * Card.cw, 0);
				} else {
					card.setPosition(i * (stage.getWidth() - Card.cw) / (n - 1), 0);
				}
				++i;
			}
		}

		if (_showHand) {
			_hand.setPosition(0, (stage.getHeight() - Card.ch) / 2);
		} else {
			_hand.setPosition(0, -0.87f * Card.ch);
		}
		
		if (_player.getTopDiscard() != null) {
			_discardSprite.setTexture(_player.getTopDiscard().getTexture());
		}
		_discardSprite.setPosition(stage.getWidth() - 300, stage.getHeight()/2);
		
		if (!_player.isDeckEmpty()) {
			_deckSprite.setTexture(Card._texture_back);
		}
		_deckSprite.setPosition(stage.getWidth() - 300, stage.getHeight()/2 - Card.ch);
	}

}
