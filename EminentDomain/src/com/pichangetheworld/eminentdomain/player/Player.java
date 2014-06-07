package com.pichangetheworld.eminentdomain.player;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.cards.Card;
import com.pichangetheworld.eminentdomain.states.GameState;
import com.pichangetheworld.eminentdomain.util.PlayerDeck;

/*
 * Player class
 * 
 * Every player, whether AI or human, will have their player class
 * This keeps track of their deck (and discards), their hand,
 * 	maximum hand size, and number of fighters
 */
public class Player {
	
	public static final int DEFAULT_HANDSIZE = 5;
	
	private static int _id;	// integer value, i.e. id 0, 1, 2, ...
	private static String _name; // name, for UI purposes i.e. "Bob", "AI1"

	private PlayerDeck _deck;
	protected List<Card> _hand;
	
	private static int _handSize;
	
	private static int _numFighters;
	
	public Player(String name, int id) {
		_name = name;
		_id = id;
		_numFighters = 0;
		
		_deck = new PlayerDeck();
		
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
			Card card = _deck.draw();
			if (card == null) break;
			_hand.add(card);
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
			// it is possible that the card doesn't exist any more
			//	e.g. Politics, Research
			if (_hand.remove(card)) {
				_deck.discard(card);
			}
		}

		GameState.getInstance().endActionPhase();
	}
	
	public void rolePhase() {
		// TODO
		// 1. Choose a deck in play
		// 2. Do the role
		// 3. (Optional) Add any matching roles from hand
		// 4 (GameManager) - pass the role to every other player, who can Follow or Dissent
		// 5. Remove any played cards from hand
		// 6. Add any played cards to the discard pile
		
		// 7. set GameState.NEXT_PHASE()

		GameState.getInstance().endRolePhase();
	}
	
	public void cleanupPhase() {
		// allow player to discard as many cards as he needs
		Object obj = chooseTarget(Card.class);
		while (obj instanceof Card && !_hand.isEmpty()) {
			_hand.remove(obj);
			_deck.discard((Card)obj);
		}
		drawUp();
	}

	public int getFighterCount() {
		return _numFighters;
	}
	
	public void addFighters(int count) {
		_numFighters += count;
	}
	
	// function to remove cards from the game
	public void removeCard(Card card) {
		_hand.remove(card); // do not discard them, they are gone from the game
	}

	// add cards to the player's hand
	public void addCard(Card card) {
		_hand.add(card);
	}
	
	// add cards to the player's discard pile
	public void discardCard(Card card) {
		_deck.discard(card);
	}
}
