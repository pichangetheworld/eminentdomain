package com.pichangetheworld.eminentdomain.planets;

public class Planet {

	public enum Type {
		FERTILE,
		METALLIC,
		ADVANCED,
		PRESTIGE
	}
	
	public boolean _conquered;
	public Type _type;
	
	private int _requiredToColonise;
	private int _requiredToConquer;
	
	private int _coloniseCount;
	private int _militaryCount;
	
	public Planet() {
		_conquered = false;
	}
	
	public boolean Colonise() {
		if (!_conquered) {
			if (_coloniseCount >= _requiredToColonise) {
				_coloniseCount = 0;
				// TODO: discard all the colonizes
				
				_conquered = true;
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean Conquer() {
		if (!_conquered) {
			if (_militaryCount >= _requiredToConquer) {
				_militaryCount = 0;
				// discard all the ships?
				
				_conquered = true;
				return true;
			}
			return false;
		}
		return false;
	}
	
	public int requiredToColonise() {
		return _requiredToColonise;
	}
	
	public int requiredToConquer() {
		return _requiredToConquer;
	}
}
