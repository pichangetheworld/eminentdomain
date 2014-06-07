package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;

public class ProduceTrade extends Card {

	@Override
	public void doAction(Player active) {
		Planet target = (Planet) active.chooseTarget(Planet.class);
		// target must already be conquered
		if (!target._conquered) {
			// ERROR
		}
		
		// target.produce() or target.trade()
	}

	@Override
	public void doRole() {

	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.PRODUCE) return 1;
		else if (role == Role.TRADE) return 1;
		return 0;
	}
}
