package com.pichangetheworld.eminentdomain.cards;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;

public class Colonise extends Card {

	@Override
	public void doAction(Player active) {
		Planet target = (Planet) active.chooseTarget(Planet.class);
		// if you can conquer it, conquer it
		if (!target.colonise(active)) {
			List<Card> colonies = new ArrayList<Card>();
			colonies.add(this);
			target.addColony(colonies);
		}
	}

	@Override
	public void doRole(Player active, boolean isLeader) {

	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.COLONISE) return 1;
		return 0;
	}
}
