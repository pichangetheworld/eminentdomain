package com.dimsum.eminentdomain.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bucket extends Rectangle {
	private static final long serialVersionUID = -3693692381520129109L;
	
	public Texture image;

	public Bucket() {
		super(800 / 2 - 64 / 2, 20, 64, 64);
		image = new Texture(Gdx.files.internal("bucket.png"));
	}
}
