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
			return;
		}
		
		// Let the user decide whether to produce or trade
		Object obj = active.chooseTarget(Role.class);
		if (obj == Role.PRODUCE) {
			target.produce(1);
		} else if (obj == Role.TRADE) {
			target.trade(1);
		}
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.PRODUCE) return 1;
		else if (role == Role.TRADE) return 1;
		return 0;
	}
}
