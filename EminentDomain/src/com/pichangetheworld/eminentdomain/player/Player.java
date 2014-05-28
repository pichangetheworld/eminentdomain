package com.pichangetheworld.eminentdomain.player;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.cards.Card;
import com.pichangetheworld.eminentdomain.cards.PlayerDeck;

public class Player {
	
	public static final int DEFAULT_HANDSIZE = 5;
	
	private static String _name;
	private static int _id;

	private PlayerDeck _deck;
	private List<Card> _hand;
	
	private static int _handSize;
	
	public Player(String name, int id) {
		_name = name;
		_id = id;
		
		_deck = new PlayerDeck();
		_hand = new ArrayList<Card>();
		
		_handSize = DEFAULT_HANDSIZE;
		drawUp();
	}
	
	public int Id() { return _id; }
	
	public String getName() {
		return _name;
	}
	
	protected void drawUp() {
		while (_hand.size() < _handSize) {
			_hand.add(_deck.draw());
		}
	}
	
	public void draw(int n) {
		for (int i = 0; i < n; ++i) {
			_hand.add(_deck.draw());
		}
	}
	
	public void PlayAction(Card card) {
		card.DoAction(this);
	}
}
