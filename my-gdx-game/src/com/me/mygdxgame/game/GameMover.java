package com.me.mygdxgame.game;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Linear;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.mygdxgame.App;
import com.me.mygdxgame.ai.pathfinding.AStarPathFinder;
import com.me.mygdxgame.ai.pathfinding.Mover;
import com.me.mygdxgame.ai.pathfinding.Path;
import com.me.mygdxgame.sprite.SpriteBase;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.TweenXYAccessor;

public class GameMover extends GameEntity implements Mover{

	private boolean hasChanged = false;
	//public float x, y;
	public Sprite sprite;
	
	public Path path;
	private int pathIndex;
	private boolean moving;
	
	public float tx, ty;
	int moveSpeed;
	
	public GameMover(SpriteBase sprite, float x, float y) {
		//super(tilePosition);
		//Game.map.addEventToTile(tilePosition, this);
		hasChanged = false;
		this.sprite = sprite;
		//this.x = x;
		//this.y = y;
		sprite.flip(false, true);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight());
		setPosition(x, y);
		moveSpeed = 200;
	}

	public int getTileI(){
		return (int)sprite.getX()/Cst.TILE_W;
	}
	
	public int getTileJ(){
		return (int)sprite.getY()/Cst.TILE_H;
	}
	
	public void setTilePosition(int i, int j){
		Point2f position = determineDestinationInTile(i, j);
		setPosition(position.x, position.y);
	}
	
	public void setPosition(float x, float y){
		Game.map.removeEventFromTile(new Point2i((int)this.sprite.getX()/Cst.TILE_W, (int)this.sprite.getY()/Cst.TILE_H), this);
		//this.x = x;
		//this.y = y;
		//this.tilePosition.x = p.x;
		//this.tilePosition.y = p.y;
		sprite.setPosition(x, y);
		Game.map.addEventToTile(new Point2i((int)this.sprite.getX()/Cst.TILE_W, (int)this.sprite.getY()/Cst.TILE_H), this);
		this.hasChanged = true;
	}

	public void update() {
		//App.instance().tweenManager.killTarget(sprite);
		//System.out.println(sprite.getX() + " " + tx);
		if( moving && (int)sprite.getX()/Cst.TILE_W == (int)tx/Cst.TILE_W && (int)sprite.getY()/Cst.TILE_H == (int)ty/Cst.TILE_H){
			//System.out.println(path);
			moving = false;
			//App.instance().tweenManager.killAll();
			//System.out.println(moving);
		}
        // We need to convert our angle from radians to degrees
        //sprite.setRotation(MathUtils.radiansToDegrees * b.getAngle());
        
		if(hasChanged) {
			hasChanged = false;
			//int i = tilePosition.x;
			//int j = tilePosition.y;
			//Game.map.removeEventFromTile(new Point2i((int)x/Cst.TILE_W, (int)y/Cst.TILE_H), this);
			//sprite.setPosition(x, y);
			//Game.map.removeEventFromTile(new Point2i((int)x/Cst.TILE_W, (int)y/Cst.TILE_H), this);
		}
		
		updatePath();

	}
	
	public void updatePath(){
		if(path == null){
			return;
		}
		
		if(pathIndex  >= path.steps.size()){
			path = null;
			
			moving = false;
			//System.out.println(moving);
			return;
		}
		if(moving){
			return;
		}
		
		
		Point2i step = path.steps.get(pathIndex);
		Point2f dest = determineDestinationInTile(step.x, step.y);
		tx = dest.x;
		ty = dest.y;
		
		float distance = (float) Math.sqrt(Math.pow(tx-sprite.getX(), 2) + Math.pow(ty-sprite.getY(), 2));
		float duration = distance/moveSpeed;
		
		moving = true;
		Tween.to(this, TweenXYAccessor.XY, duration)
			.target(dest.x, dest.y)
			.ease(Linear.INOUT)
			//.setCallback(stepCompletedCallback)
			.start(App.instance().tweenManager);
		pathIndex += 1;
	}
	
	public Point2f determineDestinationInTile(int i, int j){
		float x = i*Cst.TILE_W;
		float y = j*Cst.TILE_H;
		//int tileWidthOffset = Cst.TILE_W/6;
		//int tileHeightOffset = Cst.TILE_H/6;
		//Random rand = new Random();
	
		//int xOffset = tileWidthOffset + rand.nextInt( Cst.TILE_W - tileWidthOffset*2 );
		//int yOffset = tileHeightOffset + rand.nextInt( Cst.TILE_H - tileHeightOffset*2 );
		
		int xOffset = 32;
		int yOffset = 32;
		return new Point2f(x + xOffset, y + yOffset);
	}
	
	public void findPath(int destI, int destJ){
		if(path != null){
			return;
		}
		//System.out.println("lala");
		//System.out.println("Path finder:" + mover.getTileI() + " " + mover.getTileJ() +" " + i +" " + j);
		AStarPathFinder pathFinder = new AStarPathFinder(Game.map, 5000, false);
		
		path = pathFinder.findPath(this, getTileI(), getTileJ(), destI, destJ);
		moving = false;
		pathIndex = 0;
	}
	
	/*
	private final TweenCallback stepCompletedCallback = new TweenCallback() {
		    @Override
		    public void onEvent(int type, BaseTween<?> source) {
		    	moving = false;
		    	updatePath();
		    }
		};
	*/
}
