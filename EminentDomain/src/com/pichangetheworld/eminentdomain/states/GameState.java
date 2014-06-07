package com.pichangetheworld.eminentdomain.states;

import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.GameManager;
import com.pichangetheworld.eminentdomain.cards.Colonise;
import com.pichangetheworld.eminentdomain.cards.ProduceTrade;
import com.pichangetheworld.eminentdomain.cards.Research;
import com.pichangetheworld.eminentdomain.cards.Survey;
import com.pichangetheworld.eminentdomain.cards.Warfare;
import com.pichangetheworld.eminentdomain.planets.Planet;
import com.pichangetheworld.eminentdomain.player.Player;
import com.pichangetheworld.eminentdomain.util.RoleStack;

/*
 * State class
 * Keeps track of the current state of the finite state machine
 */
public class GameState {
	
	public enum Phase {
		ACTION,
		ROLE,
		DISCARD_DRAW {
			@Override
			public Phase next() {
				return ACTION;
			}
		};
		
		public Phase next() {
			return values()[ordinal() + 1];
		}
	}
	
	public enum Role {
		SURVEY(0),
		WARFARE(1),
		COLONISE(2),
		PRODUCETRADE(3),
		RESEARCH(4);
		
		private final int id;
	    Role(int id) { this.id = id; }
	    public int getValue() { return id; }
	}
	
	private Player _activePlayer;
	private Phase _phase;
	
	private static List<RoleStack> _roleStacks;
	
	private static List<Planet> _PlanetDeck;
	
	private static GameState _gameState = null;
	
	private GameState() {
		_PlanetDeck = new ArrayList<Planet>();
		_roleStacks = new ArrayList<RoleStack>();

		_activePlayer = null;
		_phase = Phase.ACTION;
	}
	
	public static GameState getInstance() {
		if (_gameState == null) {
			_gameState = new GameState();
		}
		return _gameState;
	}
	
	public Player getActivePlayer() {
		return _activePlayer;
	}
	
	public void init(Player activePlayer, int numPlayers) {
		_activePlayer = activePlayer;

		_roleStacks.add(new RoleStack(Survey.class, 20 - 2 * numPlayers));
		_roleStacks.add(new RoleStack(Warfare.class, 16 - numPlayers));
		_roleStacks.add(new RoleStack(Colonise.class, 20 - 2 * numPlayers));
		_roleStacks.add(new RoleStack(ProduceTrade.class, 20 - 2 * numPlayers));
		_roleStacks.add(new RoleStack(Research.class, 20 - 2 * numPlayers));
	}

	public int getNextPlanetColoniseCost() {
		if (_PlanetDeck.isEmpty()) { return -1; }
		return _PlanetDeck.get(0).requiredToColonise();
	}
	
	public int getNextPlanetConquerCost() {
		if (_PlanetDeck.isEmpty()) { return -1; }
		return _PlanetDeck.get(0).requiredToConquer();
	}
	
	public void endActionPhase() {
		if (_phase == Phase.ACTION) {
			_phase = _phase.next();
		}
	}
	
	public void endRolePhase() {
		if (_phase == Phase.ROLE) {
			_phase = _phase.next();
		}
	}
	
	public void endTurn() {
		if (_phase == Phase.DISCARD_DRAW) {
			_activePlayer = GameManager.getInstance().getNextPlayer();
			_phase = _phase.next();
		}
	}
}
