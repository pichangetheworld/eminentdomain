package com.pichangetheworld.eminentdomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pichangetheworld.eminentdomain.cards.Card.Role;
import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;
import com.pichangetheworld.eminentdomain.states.GameState;

/*
 * Manager class
 * 
 * Creates the players, initializes the decks, passes the turn
 */

public class GameManager {
	
	private static final Planet [] STARTING_PLANETS = {};
	
	private static int _numPlayers = 0;

	private static GameManager _gameManager = null;
	
	private static List<Player> _Players;
	
	private GameManager() {
		_Players = new ArrayList<Player>();
	}
	
	public void init(int numPlayers) {
		_numPlayers = numPlayers;
		
		initPlayers();
		
		// 1. create new list of starter planets 
		// 2a. for each player, select a planet at random
		// 2b. add to planer's planet list
		// 3. remove the selected planet from starter planet list
		allocateStartingPlanets();
		
		startGame();
		
		Player cur = GameState.getInstance().getActivePlayer();
		Role role;
		while (!(GameState.getInstance().isFinished() &&
				cur.equals(_Players.get(0)))) {
			// play the game!
			// 1. do action
			// 2. then do role
			// 3. then cleanup
			// 4. then pass the turn on
			cur.actionPhase();
			
			role = cur.chooseRole();
			cur.doRole(role);
			// XXX we should do them in order
			for (Player p : _Players) {
				if (!p.equals(cur))
					p.rolePhase(role);
			}
			
			cur.cleanupPhase();
			
			// cleanupPhase() will end the current player's turn
			cur = GameState.getInstance().getActivePlayer();
		}
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
	
	private void allocateStartingPlanets() {
		List<Planet> startingPlanet = new ArrayList<Planet>();
		for (Planet planet : STARTING_PLANETS) {
			startingPlanet.add(planet);
		}
		
		Random r = new Random();
		for (Player player : _Players) {
			 player.addPlanet(startingPlanet.remove(r.nextInt(startingPlanet.size())));
		}
	}
	
	private void startGame() {
		GameState.getInstance().init(_Players.get(0), GameState.Variant.TWO_PLAYERS);
	}
	
	public Player getNextPlayer() {
		return _Players.get((GameState.getInstance().getActivePlayer().getId()+1)%_numPlayers);
	}
}
