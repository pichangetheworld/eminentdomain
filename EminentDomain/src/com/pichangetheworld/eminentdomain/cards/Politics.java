package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.player.Player;
import com.pichangetheworld.eminentdomain.util.RoleStack;

public class Politics extends Card {

	@Override
	public void action(Player currentPlayer) {
		RoleStack obj = (RoleStack) currentPlayer.chooseTarget(RoleStack.class);
		
		// ensure that the role stack is not empty
		Card card = obj.chooseRole();
		while (card == null) {
			// choose another stack
			obj = (RoleStack) currentPlayer.chooseTarget(RoleStack.class);
			card = obj.chooseRole();
		}
		
		currentPlayer.addCard(card);
		currentPlayer.removeCard(this);
		_active = false;
	}

	@Override
	public int getSymbols(Role role) {
		return 0;
	}
}
