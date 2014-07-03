package com.dimsum.eminentdomain.player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dimsum.eminentdomain.cards.Card;
import com.dimsum.eminentdomain.cards.Card.Role;
import com.dimsum.eminentdomain.planets.Planet;
import com.dimsum.eminentdomain.states.GameState;
import com.dimsum.eminentdomain.util.PlayerDeck;

/*
 * Player class
 * 
 * Every player, whether AI or human, will have their player class
 * This keeps track of their deck (and discards), their hand,
 * 	maximum hand size, and number of fighters
 */
public class Player {
	
	public static final int DEFAULT_HANDSIZE = 5;
	
	protected static int _id;	// integer value, i.e. id 0, 1, 2, ...
	protected static String _name; // name, for UI purposes i.e. "Bob", "AI1"
	protected static int _handSize;

	protected PlayerDeck _deck;
	protected List<Card> _hand;
	protected List<Planet> _planets;
	
	protected static int _numFighters;
	
	private PlayerGraphics _graphics; 
	
	public Player(String name, int id) {
		_name = name;
		_id = id;
		_numFighters = 0;
		
		_deck = new PlayerDeck();
		_hand = new ArrayList<Card>();
		_planets = new ArrayList<Planet>();
		
		_handSize = DEFAULT_HANDSIZE;
		
		_graphics = new PlayerGraphics(this);
		
		drawUp();
	}
	
	public void addToStage(final Stage stage) {
		_graphics.addToStage(stage);
	}
	
	public void renderToScreen(final Stage stage) {
		_graphics.draw(stage);
	}
	
	public void toggleShowHand() {
		_graphics.toggleShowHand();
	}
	
	public int getId() { return _id; }
	
	public String getName() {
		return _name;
	}
	
	public List<Card> getHand() {
		return _hand;
	}
	
	public Card getTopDiscard() {
		return _deck.getDiscardPile().isEmpty() ? null : _deck.getDiscardPile().get(_deck.getDiscardPile().size()-1);
	}
	
	public boolean isDeckEmpty() {
		return _deck.getDeckEmpty();
	}
	
	protected void drawUp() {
		while (_hand.size() < _handSize) {
			Card card = _deck.draw();
			if (card == null) break;
			_hand.add(card);
		}
	}
	
	public void drawCard(int n) {
		for (int i = 0; i < n; ++i) {
			_hand.add(_deck.draw());
		}
	}

	// TODO let the player choose an object
	public Object chooseTarget(Class<?> validClass) {
		// ensure that the object is of the same class as validClass or DONE
		return null;
	}
	
	// TODO Choose any cards to match the role
	public List<Card> matchRole(Role role) {
		List<Card> collection = new ArrayList<Card>();
		// when matching, let the player choose any cards in their hand
		//  such that card.getSymbol(role) > 0
		
		// add the card to the collection and remove from hand
		
		return collection;
	}
	
	// Choose any cards to match the role, 
	//  consider any planets with the same symbol,
	//  and return the total number of symbols seen
	public int chooseSymbols(Role role) {
		int total = 0;
		
		List<Card> collection = matchRole(role);
		// when matching, let the player choose any cards in their hand
		//  such that card.getSymbol(role) > 0
		
		for (Card card : collection) {
			total += card.getSymbols(role);
		}
		
		for (Planet planet : _planets) {
			total += planet.getSymbols(role);
		}
		
		return total;
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
			if (card.isActive()) {
				card.doneAction();
				_hand.remove(card);
				_deck.discard(card);
			}
		}

		GameState.getInstance().endActionPhase();
	}
	
	// The leader will choose the role and then doRole(role)
	// The GameManager will then pass the role to every other player, who can Follow or Dissent
	public Role chooseRole() {
		return (Role) chooseTarget(Role.class);
	}
	
	// This assumes that the role has already been chosen by the leader
	//  this is called for each player who is not the leader
	public void rolePhase(Role role) {
		//Decision decision = (Decision) chooseTarget(Decision.class);
		//XXX
		String choice = new String();
		if (choice.compareTo("dissent") == 0) {
			drawCard(1);
		} else if (choice.compareTo("follow") == 0) {
			doRole(role);
		}
	}
	
	public void doRole(Role role) {
		// 2. Do the role
		// 3. (Optional) Add any matching roles from hand
		// 5. Remove any played cards from hand
		// 6. Add any played cards to the discard pile
		switch(role) {
		case SURVEY:
			// draw n-1 planet cards, +1 if this.equals(currentPlayer)
			int numToDraw = chooseSymbols(Role.SURVEY);
			if (this.equals(GameState.getInstance().getActivePlayer())) {
				++numToDraw;
			}
			// draw numToDraw planets from the planet deck, choose one
			// 0. draw planet cards and put cards to a temp arraylist
			// 1. show planet cards to player [ showPlanetsToPlayer(array) ]
			// 2. wait for player to select one
			// 3. when player selects one, add to player's planets
			// 4. return remaining cards to bottom of planet stack
			List<Planet> tempPlanetCollections = new ArrayList<Planet>(numToDraw);
			for (int i = 0; i < numToDraw; i++) {
				tempPlanetCollections.add(GameState.getInstance().getNextPlanet());
			}
			// XXX showPlanetsToPlayer(tempPlanetCollections);
			Planet selectedPlanet = (Planet) chooseTarget(Planet.class);
			addPlanet(selectedPlanet);
			tempPlanetCollections.remove(selectedPlanet);
			for (int i = 0; i < numToDraw; i++) {
				GameState.getInstance().recyclePlanets(tempPlanetCollections);
			}
			break;
		case WARFARE:
			if (this.equals(GameState.getInstance().getActivePlayer())) {
				Planet target = (Planet) chooseTarget(Planet.class);
				if (target.conquer(this)) {
					break;
				}
				// chance to conquer instead
			}
			addFighters(chooseSymbols(Role.WARFARE));
			break;
		case COLONISE:
			Planet target = (Planet) chooseTarget(Planet.class);
			if (this.equals(GameState.getInstance().getActivePlayer())
					&& target.colonise(this)) {
				// chance to conquer instead
			} else {
				target.addColony(matchRole(Role.COLONISE));
			}
			break;
		case PRODUCE:
			int prodSymbols = chooseSymbols(Role.PRODUCE);
			
			// produce on all planets
			for (Planet planet : _planets) {
				if (prodSymbols <= 0) break;
				prodSymbols -= planet.produce(prodSymbols);
			}
			
			break;
		case TRADE:
			int tradeSymbols = chooseSymbols(Role.TRADE);
			
			// trade on all planets
			for (Planet planet : _planets) {
				if (tradeSymbols <= 0) break;
				tradeSymbols -= planet.trade(tradeSymbols);
			}
			
			break;
		case RESEARCH:
			// do nothing, for now
			break;
		default:
		}
	}
	
	public void cleanupPhase() {
		// allow player to discard as many cards as he needs
		Object obj = chooseTarget(Card.class);
		while (obj instanceof Card && !_hand.isEmpty()) {
			_hand.remove(obj);
			_deck.discard((Card)obj);
		}
		drawUp();
		
		GameState.getInstance().endTurn();
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
	
	public void addPlanet(Planet planet) {
		_planets.add(planet);
	}
}
