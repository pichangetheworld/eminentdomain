package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.player.Player;

public class Survey extends Card {

	@Override
	public void DoAction(Player active) {
		active.draw(2);
	}

	@Override
	public void DoRole() {

	}

}
