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
	
	private int _RequiredToColonise;
	private int _RequiredToConquer;
	
	private int _coloniseCount;
	private int _militaryCount;
	
	public Planet() {
		_conquered = false;
	}
	
	public boolean Colonise() {
		if (!_conquered) {
			if (_coloniseCount >= _RequiredToColonise) {
				_coloniseCount = 0;
				// TODO: discard all the colonizes
				
				_conquered = true;
				return true;
			}
			return false;
		}
		return false;
	}
}
