package com.pichangetheworld.eminentdomain.states;

import com.pichangetheworld.eminentdomain.GameManager;
import com.pichangetheworld.eminentdomain.player.Player;

// Model class
// This will be the Model in MVC:
//	- provide methods for advancing the active player to the next
//	- runs through the 'turn process' (rolling dice, moving tokens, etc. relevant here?)
//	- method calls should be made from event listeners
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
	
	private Player _activePlayer = null;
	public Phase _phase = Phase.ACTION;
	
	private static GameState _gameState = null;
	
	private GameState() {}
	
	public static GameState getInstance() {
		if (_gameState == null) {
			_gameState = new GameState();
		}
		return _gameState;
	}
	
	public Player getActivePlayer() {
		return _activePlayer;
	}
	
	public void init(Player activePlayer) {
		_activePlayer = activePlayer;
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
