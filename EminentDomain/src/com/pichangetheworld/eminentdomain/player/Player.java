package com.pichangetheworld.eminentdomain.player;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.cards.Card;
import com.pichangetheworld.eminentdomain.states.GameState;
import com.pichangetheworld.eminentdomain.util.PlayerDeck;

public class Player {
	
	public static final int DEFAULT_HANDSIZE = 5;
	
	private static String _name;
	private static int _id;

	private PlayerDeck _deck;
	private List<Card> _discard;
	protected List<Card> _hand;
	
	private static int _handSize;
	
	private static int _numFighters;
	
	public Player(String name, int id) {
		_name = name;
		_id = id;
		_numFighters = 0;
		
		_deck = new PlayerDeck();
		_discard = new ArrayList<Card>();
		_deck.setDiscards(_discard);
		
		_hand = new ArrayList<Card>();
		
		_handSize = DEFAULT_HANDSIZE;
		drawUp();
	}
	
	public int getId() { return _id; }
	
	public String getName() {
		return _name;
	}
	
	public List<Card> getHand() {
		return _hand;
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

	public Object chooseTarget(Class<?> validClass) {
		// let the player choose an object
		// ensure that the object is of the same class as validClass or DONE
		return null;
	}
	
	public void actionPhase() {
		// 1. Choose a card in hand (optional)
		// 2. if (card is Card) // else card is skipPhase()
		// 3. set GameState.NEXT_PHASE()
		Object obj = chooseTarget(Card.class);
		if (obj instanceof Card) {
			// 	a. Do the action
			// 	b. Discard the action card from the hand
			// 	c. Add the action card to the discard pile
			Card card = (Card)obj;
			card.doAction(this);
			_hand.remove(card);
			_discard.add(card);
		}

		GameState.getInstance().endActionPhase();
	}
	
	public void rolePhase() {
		// TODO
		// 1. Choose a deck in play
		// 2. Do the role
		// 3. (Optional) Add any matching roles from hand
		// 4. Remove any played cards from hand
		// 5. Add any played cards to the discard pile
		// 6. set GameState.NEXT_PHASE()
	}
	
	public void cleanupPhase() {
		// allow player to discard as many cards as he needs
		Object obj = chooseTarget(Card.class);
		while (obj instanceof Card && !_hand.isEmpty()) {
			_hand.remove(obj);
			_discard.add((Card)obj);
		}
		drawUp();
	}

	public int getFighterCount() {
		return _numFighters;
	}
	
	public void addFighters(int count) {
		_numFighters += count;
	}
	
	public void removeCards(List<Card> cards) {
		_hand.removeAll(cards); // do not discard them, they are gone from the game
	}
}
