package com.pichangetheworld.eminentdomain.cards;

import com.pichangetheworld.eminentdomain.player.Player;

public abstract class Card {
	public abstract void DoAction(Player active);
	public abstract void DoRole();
}
