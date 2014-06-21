package com.dimsum.eminentdomain.player;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dimsum.eminentdomain.cards.Card;

public class PlayerGraphics {


	private Player _player;
	private Group _hand;

	PlayerGraphics(final Player player) {
		this._player = player;

		_hand = new Group();
		
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
		Card topDiscard = _player.getTopDiscard();
		if (topDiscard != null) {
			topDiscard.setPosition(stage.getWidth()- 300, stage.getHeight()/2);
			stage.addActor(topDiscard);
		} // TODO: add placeholder for empty discard pile
		
		
		stage.addActor(_hand);
		
	}

}
