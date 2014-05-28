package com.pichangetheworld.eminentdomain;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.cards.Card;
import com.pichangetheworld.eminentdomain.cards.Survey;
import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;
import com.pichangetheworld.eminentdomain.states.GameState;

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
		
		initSurveyDeck();
		
		initPlayers();
		
		StartGame();
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
	
	private void StartGame() {
		GameState.getInstance().init(0);
	}
}
