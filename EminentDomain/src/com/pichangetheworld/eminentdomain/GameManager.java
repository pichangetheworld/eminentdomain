package com.pichangetheworld.eminentdomain;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.cards.Card;
import com.pichangetheworld.eminentdomain.cards.Colonise;
import com.pichangetheworld.eminentdomain.cards.ProduceTrade;
import com.pichangetheworld.eminentdomain.cards.Research;
import com.pichangetheworld.eminentdomain.cards.Survey;
import com.pichangetheworld.eminentdomain.cards.Warfare;
import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;
import com.pichangetheworld.eminentdomain.states.GameState;

/*
 * Manager class
 * 
 * Creates the players, initializes the decks, passes the turn
 */

public class GameManager {
	private static final int NUM_PLAYERS = 2;

	private static GameManager _gameManager = null;

	private static List<Card> _SurveyDeck;
	private static List<Card> _WarfareDeck;
	private static List<Card> _ColoniseDeck;
	private static List<Card> _ProduceTradeDeck;
	private static List<Card> _ResearchDeck;
	
	private static List<Planet> _PlanetDeck;
	
	private static List<Player> _Players;
	
	private GameManager() {
		_SurveyDeck = new ArrayList<Card>();
		_WarfareDeck = new ArrayList<Card>();
		_ColoniseDeck = new ArrayList<Card>();
		_ProduceTradeDeck = new ArrayList<Card>();
		_ResearchDeck = new ArrayList<Card>();
		_PlanetDeck = new ArrayList<Planet>();
		_Players = new ArrayList<Player>();
	}
	
	public void init() {
		initDecks();
		
		initPlayers();
		
		startGame();
	}

	public static GameManager getInstance() {
		if (_gameManager == null) {
			_gameManager = new GameManager();
		}

		return _gameManager;
	}
	
	private void initDecks() {
		for (int i = 0; i < 20 - 2 * NUM_PLAYERS; ++i) {
			_SurveyDeck.add(new Survey());
			_ColoniseDeck.add(new Colonise());
			_ProduceTradeDeck.add(new ProduceTrade());
			_ResearchDeck.add(new Research());
		}
		for (int i = 0; i < 16 - NUM_PLAYERS; ++i) {
			_WarfareDeck.add(new Warfare());
		}
	}
	
	private void initPlayers() {
		_Players.add(new Player("Bob", 0));
		for (int i = 1; i < NUM_PLAYERS; ++i) {
			_Players.add(new Player("AI" + i, i));
		}
	}
	
	private void startGame() {
		GameState.getInstance().init(_Players.get(0));
	}
	
	public Player getNextPlayer() {
		return _Players.get(GameState.getInstance().getActivePlayer().getId()+1);
	}
	
	public int getNextPlanetColoniseCost() {
		return _PlanetDeck.get(0).requiredToColonise();
	}
	
	public int getNextPlanetConquerCost() {
		return _PlanetDeck.get(0).requiredToConquer();
	}
}
