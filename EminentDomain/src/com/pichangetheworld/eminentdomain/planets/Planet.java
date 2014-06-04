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
	
	public Planet() {
		_conquered = false;
		_coloniseCount = 0;
	}
	
	public int addColony(int count) {
		_coloniseCount += count;
		return _coloniseCount;
	}
	
	public boolean colonise() {
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
	
	public boolean conquer(int militaryCount) {
		if (!_conquered) {
			if (militaryCount >= _requiredToConquer) {
				// discard all the ships
				
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
