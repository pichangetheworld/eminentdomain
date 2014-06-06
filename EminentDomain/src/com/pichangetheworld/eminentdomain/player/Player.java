package com.pichangetheworld.eminentdomain.player;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.cards.Card;
import com.pichangetheworld.eminentdomain.cards.PlayerDeck;
import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.states.GameState;

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
	
	public Planet chooseTargetPlanet() {
		return null;
	}
	
	public Card chooseTargetCard() {
		return null;
	}
	
	public void actionPhase() {
		// TODO
		// 1. Choose a card in hand (optional)
		// 2. if (card is Card) // else card is skipPhase()
		// 3. set GameState.NEXT_PHASE()
		Object obj = chooseTarget(Card.class);
		if (obj instanceof Card) {
			playAction((Card)obj);
		}

		GameState.getInstance().endActionPhase();
	}
	
	public void playAction(Card card) {
		// 	1. Do the action
		// 	2. Discard the action card from the hand
		// 	3. Add the action card to the discard pile
		
		// play the action
		card.doAction(this);
		_hand.remove(card);
		_discard.add(card);
	}
	
	public void playRole() {
		// TODO
		// 1. Choose a deck in play
		// 2. Do the role
		// 3. (Optional) Add any matching roles from hand
		// 4. Remove any played cards from hand
		// 5. Add any played cards to the discard pile
		// 6. set GameState.NEXT_PHASE()
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
