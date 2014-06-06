package com.pichangetheworld.eminentdomain.cards;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.player.Player;

public class Research extends Card {

	@Override
	public void doAction(Player active) {
		List<Card> targets = new ArrayList<Card>();
		for (int i = 0; i < 2; ++i) {
			Object target = chooseTarget();
			if (target instanceof Card) {
				targets.add((Card)target);
			}
		}
		// choose 0, 1, or 2 targets (possibly including self)
		// remove from the game
		active.removeCards(targets);
	}

	@Override
	public void doRole() {
		// for now, do nothing
		return;
	}

	@Override
	public Object chooseTarget() {
		// TODO: let user choose a valid planet
		return null;
	}

}
