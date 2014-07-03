package com.dimsum.eminentdomain.player;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dimsum.eminentdomain.cards.Card;

public class PlayerGraphics {

	private Player _player;
	private Group _hand;

	protected boolean _showHand;

	PlayerGraphics(final Player player) {
		this._player = player;
		this._showHand = false;

		_hand = new Group();
	}
	
	public void toggleShowHand() {
		_showHand = !_showHand;
	}
	
	public void draw(final Stage stage) {
		_hand.setPosition(0, -0.87f * Card.ch);

		int n = _player.getHand().size();
		int i = 0;
		
		for (Card card : _player.getHand()) {
			_hand.addActor(card);
			if (n < stage.getWidth() / Card.cw) {
				card.setPosition(stage.getWidth() / 2 - ((float) n / 2 - i) * Card.cw, 0);
			} else {
				card.setPosition(i * (stage.getWidth() - Card.cw) / (n - 1), 0);
			}
			++i;
		}

		if (_showHand) {
			_hand.setPosition(0, (stage.getHeight() - Card.ch) / 2);
		} else {
			_hand.setPosition(0, -0.87f * Card.ch);
		}
		
		Card topDiscard = _player.getTopDiscard();
		if (topDiscard != null) {
			topDiscard.setPosition(stage.getWidth()- 300, stage.getHeight()/2);
			stage.addActor(topDiscard);
		} // TODO: add placeholder for empty discard pile
		
		
		stage.addActor(_hand);
		
	}

}
