package com.dimsum.eminentdomain.cards;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.dimsum.eminentdomain.planets.Planet;
import com.dimsum.eminentdomain.player.Player;

public class Colonize extends Card {
	private Texture texture = new Texture(Gdx.files.internal("colonize.png"));

	@Override
	public void action(Player currentPlayer) {
		Planet target = (Planet) currentPlayer.chooseTarget(Planet.class);
		// if you can conquer it, conquer it
		if (!target.colonise(currentPlayer)) {
			List<Card> colonies = new ArrayList<Card>();
			colonies.add(this);
			target.addColony(colonies);
		}
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, this.getX(), getY(), this.getOriginX(),
				this.getOriginY(), this.getWidth(), this.getHeight(),
				this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
				texture.getWidth(), texture.getHeight(), false, false);
	}

	@Override
	public int getSymbols(Role role) {
		if (role == Role.COLONISE) return 1;
		return 0;
	}
}
