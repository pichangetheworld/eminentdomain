package com.pichangetheworld.eminentdomain;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.cards.Card;
import com.pichangetheworld.eminentdomain.cards.Survey;
import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;
import com.pichangetheworld.eminentdomain.states.GameState;

// Controller class
// This will be the Controller in MVC:
//	- whenever a player finishes their turn, this should
//			fire the event to tell the model to advance
//  - also: the controller determines AI turn progression

public class GameManager {
	private static final int NUM_PLAYERS = 2;

	private static GameManager _gameManager = null;

	private static List<Card> _SurveyDeck;
	
	private static List<Planet> _PlanetDeck;
	
	private static List<Player> _Players;
	
	private GameManager() {
		_SurveyDeck = new ArrayList<Card>();
		_PlanetDeck = new ArrayList<Planet>();
		_Players = new ArrayList<Player>();
	}
	
	public void init() {
		initSurveyDeck();
		
		initPlayers();
		
		startGame();
	}

	public static GameManager getInstance() {
		if (_gameManager == null) {
			_gameManager = new GameManager();
		}

		return _gameManager;
	}
	
	private void initSurveyDeck() {
		for (int i = 0; i < 10; ++i) {
			_SurveyDeck.add(new Survey());
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
