package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;

public class Warfare extends Card {

	@Override
	public void doAction(Player active) {
		Planet target = (Planet) active.chooseTarget(Planet.class);
		// if you can conquer it, conquer it
		if (!target.conquer(active)) {
			active.addFighters(1);
		}
	}

	@Override
	public void doRole(Player active, boolean isLeader) {

	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.WARFARE) return 1;
		return 0;
	}

}
