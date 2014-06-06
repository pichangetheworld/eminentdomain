package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;

public class Colonise extends Card {

	@Override
	public void doAction(Player active) {
		Planet target = (Planet) active.chooseTarget(Planet.class);
		// if you can conquer it, conquer it
		if (!target.colonise()) {
			target.addColony(1);
		}
	}

	@Override
	public void doRole() {

	}
}