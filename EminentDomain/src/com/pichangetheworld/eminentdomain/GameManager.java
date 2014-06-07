package com.pichangetheworld.eminentdomain;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.player.Player;
import com.pichangetheworld.eminentdomain.states.GameState;

/*
 * Manager class
 * 
 * Creates the players, initializes the decks, passes the turn
 */

public class GameManager {
	private static int _numPlayers = 0;

	private static GameManager _gameManager = null;
	
	private static List<Player> _Players;
	
	private GameManager() {
		_Players = new ArrayList<Player>();
	}
	
	public void init(int numPlayers) {
		_numPlayers = numPlayers;
		
		initPlayers();
		
		startGame();
	}

	public static GameManager getInstance() {
		if (_gameManager == null) {
			_gameManager = new GameManager();
		}

		return _gameManager;
	}
	
	private void initPlayers() {
		_Players.add(new Player("Bob", 0));
		for (int i = 1; i < _numPlayers; ++i) {
			_Players.add(new Player("AI" + i, i));
		}
	}
	
	private void startGame() {
		GameState.getInstance().init(_Players.get(0), _Players.size());
	}
	
	public Player getNextPlayer() {
		return _Players.get((GameState.getInstance().getActivePlayer().getId()+1)%_numPlayers);
	}
}
