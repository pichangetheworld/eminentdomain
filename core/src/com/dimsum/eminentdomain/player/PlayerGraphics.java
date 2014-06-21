package com.dimsum.eminentdomain.player;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dimsum.eminentdomain.cards.Card;

public class PlayerGraphics {

	private static final float cw = 192; // card width
	private static final float ch = 256; // card height

	private Player _player;
	private Group _hand;

	PlayerGraphics(final Player player) {
		this._player = player;

		_hand = new Group();
	}
	
	public void draw(final Stage stage) {
		_hand.setPosition(0, -0.87f * ch);

		int n = _player.getHand().size();
		int i = 0;
		for (Card card : _player.getHand()) {
			card.setSize(cw, ch);
			_hand.addActor(card);
			if (n < stage.getWidth() / cw) {
				card.setPosition(stage.getWidth() / 2 - ((float) n / 2 - i) * cw, 0);
			} else {
				card.setPosition(i * (stage.getWidth() - cw) / (n - 1), 0);
			}
			++i;
			
		}
		
		stage.addActor(_hand);
	}

}
