package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.player.Player;
import com.pichangetheworld.eminentdomain.util.RoleStack;

public class Politics extends Card {

	@Override
	public void doAction(Player active) {
		RoleStack obj = (RoleStack) active.chooseTarget(RoleStack.class);
		
		// ensure that the role stack is not empty
		Card card = obj.chooseRole();
		while (card == null) {
			// choose another stack
			obj = (RoleStack) active.chooseTarget(RoleStack.class);
			card = obj.chooseRole();
		}
		
		active.addCard(card);
		active.removeCard(this);
	}

	@Override
	public int getSymbols(Role role) {
		return 0;
	}
}
