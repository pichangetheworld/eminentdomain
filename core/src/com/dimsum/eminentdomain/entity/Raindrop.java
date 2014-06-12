package com.dimsum.eminentdomain.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Raindrop extends Rectangle implements Poolable {
	private static final long serialVersionUID = -8001882683633126099L;

	public Texture image;
	public boolean alive;

	public Raindrop(Texture image) {
		super(MathUtils.random(0, 800 - 64), 480, 64, 64);
		this.image = image;
		alive = false;
	}
	
	public Raindrop init() {
		this.setPosition(MathUtils.random(0, 800 - 64), 480);
		alive = true;
		return this;
	}

	@Override
	public void reset() {
		this.setPosition(0, 0);
		alive = false;
	}
	

    /**
     * Method called each frame, which updates the bullet.
     */
    public void update () {
		y -= 200 * Gdx.graphics.getDeltaTime();

        // if bullet is out of screen, set it to dead
        if (y + 64 < 0) alive = false;
    }
}
