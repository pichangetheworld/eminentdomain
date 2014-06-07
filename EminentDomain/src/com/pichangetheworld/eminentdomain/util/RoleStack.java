package com.pichangetheworld.eminentdomain.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.pichangetheworld.eminentdomain.cards.Card;

/*
 * Helper class to abstract choosing a role
 */
public class RoleStack {
	private List<Card> _deck;

	private Class<?> _type;

	// Constructor
	public RoleStack(Class<?> type, int numInDeck) {
		_deck = new ArrayList<Card>();
		_type = type;
		
		Constructor<?> ctor;
		try {
			ctor = _type.getConstructor();
			for (int i = 0; i < numInDeck; ++i) {
				_deck.add((Card)ctor.newInstance(new Object[] {}));
			}
		} catch (NoSuchMethodException e) {
			// No such constructor
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public Class<?> getType() {
		return _type;
	}

	// Returns the top card of the deck, or null if the deck is empty
	public Card chooseRole() {
		if (_deck.isEmpty())
			return null;
		return _deck.remove(0);
	}
}
