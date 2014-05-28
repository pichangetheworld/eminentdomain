package com.pichangetheworld.eminentdomain.states;

// Model class
// This will be the Model in MVC:
//	- provide methods for advancing the active player to the next
//	- runs through the 'turn process' (rolling dice, moving tokens, etc. relevant here?)
//	- method calls should be made from event listeners
public class GameState {
	
	public int _activePlayerId = -1;
	
	private static GameState _gameState = null;
	
	private GameState() {}
	
	public static GameState getInstance() {
		if (_gameState == null) {
			_gameState = new GameState();
		}
		return _gameState;
	}
	
	public void init(int activePlayerId) {
		_activePlayerId = activePlayerId;
	}
}
