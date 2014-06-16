package com.dimsum.eminentdomain.states;

import java.util.ArrayList;
import java.util.List;

import com.dimsum.eminentdomain.GameManager;
import com.dimsum.eminentdomain.cards.Colonize;
import com.dimsum.eminentdomain.cards.ProduceTrade;
import com.dimsum.eminentdomain.cards.Research;
import com.dimsum.eminentdomain.cards.Survey;
import com.dimsum.eminentdomain.cards.Warfare;
import com.dimsum.eminentdomain.planets.Planet;
import com.dimsum.eminentdomain.player.Player;
import com.dimsum.eminentdomain.util.RoleStack;

/*
 * State class
 * Keeps track of the current state of the finite state machine
 */
public class GameState {
	
	public enum Variant {
		TWO_PLAYERS, // 1
		THREE_PLAYERS, // 1
		THREE_PLAYERS_LONG, // 2
		FOUR_PLAYERS; // 2
		
		public int numPlayers() {
			if (this == TWO_PLAYERS)
				return 2;
			if (this == FOUR_PLAYERS)
				return 4;
			return 3;
		}
		
		public int decksToEnd() {
			if (this == TWO_PLAYERS || this == THREE_PLAYERS)
				return 1;
			return 2;
		}
	};
	
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
	
	private Player _activePlayer;
	private Variant _variant;
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
	
	public void init(Player activePlayer, Variant variant) {
		_activePlayer = activePlayer;
		_variant = variant;
		int numPlayers = _variant.numPlayers();
		
		_roleStacks.add(new RoleStack(Survey.class, 20 - 2 * numPlayers));
		_roleStacks.add(new RoleStack(Warfare.class, 16 - numPlayers));
		_roleStacks.add(new RoleStack(Colonize.class, 20 - 2 * numPlayers));
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
	
	public Planet getNextPlanet(){
		return _PlanetDeck.remove(0);
	}
	
	public void recyclePlanets(List<Planet> planets){
		_PlanetDeck.addAll(planets);
	}
	
	public boolean isFinished() {
		int emptyStacks = 0;
		for (RoleStack stack : _roleStacks) {
			if (stack.isEmpty()) {
				++emptyStacks;
			}
		}
		return (emptyStacks >= _variant.decksToEnd());
	}
}
