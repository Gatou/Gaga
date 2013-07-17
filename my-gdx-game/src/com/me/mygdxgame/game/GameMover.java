package com.me.mygdxgame.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.mygdxgame.sprite.SpriteBase;

public class GameMover extends GameEntity {

	private boolean hasChanged = false;
	public float x, y;
	public Sprite sprite;
	
	
	public GameMover(SpriteBase sprite, float x, float y) {
		//super(tilePosition);
		//Game.map.addEventToTile(tilePosition, this);
		hasChanged = false;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		sprite.flip(false, true);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight());
	}

	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
		//this.tilePosition.x = p.x;
		//this.tilePosition.y = p.y;
		sprite.setPosition(x, y);
		this.hasChanged = true;
	}

	public void update() {
        // We need to convert our angle from radians to degrees
        //sprite.setRotation(MathUtils.radiansToDegrees * b.getAngle());
        
		if(hasChanged) {
			hasChanged = false;
			//int i = tilePosition.x;
			//int j = tilePosition.y;
			
			
		}
	}
}
