package com.pichangetheworld.eminentdomain.states;

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
