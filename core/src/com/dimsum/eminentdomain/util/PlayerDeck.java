package com.dimsum.eminentdomain.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dimsum.eminentdomain.cards.Card;
import com.dimsum.eminentdomain.cards.Colonize;
import com.dimsum.eminentdomain.cards.ProduceTrade;
import com.dimsum.eminentdomain.cards.Research;
import com.dimsum.eminentdomain.cards.Survey;
import com.dimsum.eminentdomain.cards.Warfare;

/*
 * Contains the deck and the discards
 * 
 * Helper class to abstract drawing from the deck
 *  and shuffling the discards when the deck is empty
 */
public class PlayerDeck {
	private List<Card> _deck;
	private List<Card> _discards;
	
	// Constructor
	public PlayerDeck() {
		_deck = new ArrayList<Card>();
		
		_discards = new ArrayList<Card>();

		_deck.add(new Survey());
		_deck.add(new Survey());
		_deck.add(new Warfare());
		_deck.add(new Colonize());
		_deck.add(new Colonize());
		_deck.add(new ProduceTrade());
		_deck.add(new ProduceTrade());
		_deck.add(new Research());
		_deck.add(new Research());
		
		// add Politics
		
		shuffle();
	}
	
	public void discard(Card discard) {
		_discards.add(discard);
	}
	
	public List<Card> getDiscardPile() {
		return _discards;
	}
	
	public boolean getDeckEmpty() {
		return _deck.isEmpty();
	}
	
	// Shuffles the deck
	public void shuffle() {
		Random r = new Random();
		for (int i = 0; i < 200; ++i) {
			int r1 = r.nextInt(_deck.size()),
			    r2 = r.nextInt(_deck.size());
			Card temp = _deck.get(r1);
			_deck.set(r1, _deck.get(r2));
			_deck.set(r2, temp);
		}
	}
	
	// Returns the top card of the deck, or null if the deck is empty
	public Card draw() {
		if (_deck.isEmpty()) {
			// shuffle the discards back into the deck
			_deck.addAll(_discards);
			_discards.clear();
			
			shuffle();
		}
		
		if (_deck.isEmpty()) return null; // discard pile was empty too
		return _deck.remove(0);
	}
}
