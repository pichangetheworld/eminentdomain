package com.dimsum.eminentdomain.cards;

import com.dimsum.eminentdomain.player.Player;

public class Research extends Card {

	@Override
	public void action(Player currentPlayer) {
		for (int i = 0; i < 2; ++i) {
			Object target = currentPlayer.chooseTarget(Card.class);
			if (target instanceof Card) {
				// remove from the game
				currentPlayer.removeCard((Card)target);
				
				if (target.equals(this))
					_active = false;
				
//			} else if (target instanceof Decision) {
//				if (((Decision) target).done) 
//					break;
			}
		}
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.RESEARCH) return 1;
		return 0;
	}
}
