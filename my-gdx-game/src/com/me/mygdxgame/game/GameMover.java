package com.me.mygdxgame.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.mygdxgame.mgr.PhysicsManager;
import com.me.mygdxgame.sprite.SpriteBase;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class GameMover extends GameEntity {

	private boolean hasChanged = false;
	public float x, y;
	public Sprite sprite;
	
	public Body body;
	public int boundsWidth, boundsHeight;
	
	public GameMover(SpriteBase sprite, float x, float y) {
		//super(tilePosition);
		//Game.map.addEventToTile(tilePosition, this);
		hasChanged = false;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		sprite.flip(false, true);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight());
		boundsWidth = 32;
		boundsHeight = 20;
		body = PhysicsManager.getInstance().createDynamicBody(2, 2);
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
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        // We need to convert our angle from radians to degrees
        //sprite.setRotation(MathUtils.radiansToDegrees * b.getAngle());
        
		if(hasChanged) {
			hasChanged = false;
			//int i = tilePosition.x;
			//int j = tilePosition.y;
			
			
		}
	}
}
