package com.pichangetheworld.eminentdomain.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerDeck {
	private List<Card> _deck;
	private List<Card> _discards;
	
	public PlayerDeck() {
		_deck = new ArrayList<Card>();
		
		initDefaultDeck();
	}
	
	private void initDefaultDeck() {
		_deck.add(new Survey());
		_deck.add(new Survey());
		_deck.add(new Survey());
		_deck.add(new Survey());
		_deck.add(new Survey());
		_deck.add(new Survey());
		
		shuffle();
	}
	
	public void shuffle() {
		Random r = new Random();
		for (int i = 0; i < 200; ++i) {
			int r1 = r.nextInt() % _deck.size(),
			    r2 = r.nextInt() % _deck.size();
			Card temp = _deck.get(r1);
			_deck.set(r1, _deck.get(r2));
			_deck.set(r2, temp);
		}
	}
	
	public Card draw() {
		if (_deck.isEmpty()) {
			// shuffle the discards back into the deck
			_deck.addAll(_discards);
			_discards.clear();
			
			shuffle();
		}
		
		return _deck.remove(0);
	}
}
