package com.dimsum.eminentdomain.planets;

import java.util.ArrayList;
import java.util.List;

import com.dimsum.eminentdomain.cards.Card;
import com.dimsum.eminentdomain.cards.Card.Role;
import com.dimsum.eminentdomain.player.Player;

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

	private List<Card> _colonies; // number of colonise symbols under it

	private Role _symbol; // can have no symbol
	private int _maxProduce; // maximum number that can be produced
	private int _curGoods; // current number produced
	
	public Planet() {
		_conquered = false;
		_colonies = new ArrayList<Card>();
	}
	
	public int addColony(List<Card> colonies) {
		_colonies.addAll(colonies);
		int count = 0;
		for (Card card : _colonies) {
			count += card.getSymbols(Role.COLONISE);
		}
		return count;
	}
	
	// attempt to conquer a planet using colonise
	public boolean colonise(Player active) {
		if (!_conquered) {
			int count = 0;
			for (Card card : _colonies) {
				count += card.getSymbols(Role.COLONISE);
			}
			if (count >= _requiredToColonise) {
				for (Card card : _colonies) {
					active.discardCard(card);
				}
				
				_conquered = true;
				return true;
			}
			return false;
		}
		return false;
	}
	
	// attempt to conquer a planet using military power
	public boolean conquer(Player active) {
		if (!_conquered) {
			if (active.getFighterCount() >= _requiredToConquer) {
				// discard any ships used
				active.addFighters(-_requiredToConquer);
				
				// if any colonies were under this planet discard them
				for (Card card : _colonies) {
					active.discardCard(card);
				}
				
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
