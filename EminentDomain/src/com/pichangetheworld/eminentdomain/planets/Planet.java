package com.pichangetheworld.eminentdomain.planets;

import com.pichangetheworld.eminentdomain.cards.Card.Role;

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
	
	private Role _symbol; // can have no symbol
	private int _maxProduce; // maximum number that can be produced
	private int _curGoods; // current number produced
	
	public Planet() {
		_conquered = false;
		_coloniseCount = 0;
	}
	
	public int addColony(int count) {
		_coloniseCount += count;
		return _coloniseCount;
	}
	
	// attempt to conquer a planet using colonise
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
	
	// attempt to conquer a planet using military power
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
	
	// function to produce toProduce # of goods on a planet
	// returns the number of goods produced
	public int produce(int toProduce) {
		int cur = _curGoods;
		if (_curGoods < _maxProduce) {
			_curGoods = Math.min(_curGoods + toProduce, _maxProduce);
		}
		return _curGoods - cur;
	}
	
	// function to produce toTrade # of goods on a planet (if existing)
	// returns the number of goods traded
	public int trade(int toTrade) {
		int cur = _curGoods;
		if (_curGoods > 0) {
			_curGoods = Math.max(_curGoods - toTrade, 0);
		}
		return cur - _curGoods;
	}
	
	public int requiredToColonise() {
		return _requiredToColonise;
	}
	
	public int requiredToConquer() {
		return _requiredToConquer;
	}

	public int getSymbols(Role role) {
		if (role == _symbol) return 1;
		return 0;
	}
}
