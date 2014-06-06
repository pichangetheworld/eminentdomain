package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;

public class ProduceTrade extends Card {

	@Override
	public void doAction(Player active) {
		Planet target = (Planet) chooseTarget();
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
	public Object chooseTarget() {
		// TODO: let user choose a valid planet
		return null;
	}

}
