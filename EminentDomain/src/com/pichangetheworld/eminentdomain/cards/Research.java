package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.player.Player;

public class Research extends Card {

	@Override
	public void doAction(Player active) {
		for (int i = 0; i < 2; ++i) {
			Object target = active.chooseTarget(Card.class);
			if (target instanceof Card) {
				// remove from the game
				active.removeCard((Card)target);
				
			//} else if (target instanceof Done) {
			//	break;
			}
		}
	}

	@Override
	public void doRole() {
		// for now, do nothing
		return;
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.RESEARCH) return 1;
		return 0;
	}
}
