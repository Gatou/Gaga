package com.me.mygdxgame.mgr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataTileset;
import com.me.mygdxgame.data.DataTileset.Passability;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.utils.Cst;

public class PhysicsManager {

	private static PhysicsManager instance;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100f;
	
	public World world; 
	public Box2DDebugRenderer debugRenderer;
	
	private PhysicsManager() {
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();
	}
	
	public static PhysicsManager getInstance(){
		if(instance == null){
			instance = new PhysicsManager();
		}
		return instance;
	}
	
	public void setup(){
		DataTileset tileset = Data.tilesets.get(Game.map.mapData.tilesetId);
		
		for(int i=0; i<Game.map.mapData.tilemap.length; i++){
			for(int j=0; j<Game.map.mapData.tilemap[i].length; j++){
				int tileIndex = Game.map.mapData.tilemap[i][j];
				if(tileset.collisions.get(tileIndex) == Passability.NON_PASSABLE){
					createStaticBody(i, j);
				}
			}
		}
	}
	
	public Body createStaticBody(int i, int j){
		BodyDef groundBodyDef = new BodyDef();  
		// Set its world position
		groundBodyDef.position.set(new Vector2(i*Cst.TILE_W+Cst.TILE_W/2, j*Cst.TILE_H+Cst.TILE_H/2));  

		// Create a body from the defintion and add it to the world
		Body groundBody = world.createBody(groundBodyDef);  

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(16, 16);
		// Create a fixture from our polygon shape and add it to our ground body  
		groundBody.createFixture(groundBox, 0.0f); 
		// Clean up after ourselves
		groundBox.dispose();
		
		return groundBody;
	}
	
	public Body createDynamicBody(int i, int j){
		BodyDef groundBodyDef = new BodyDef();  
		groundBodyDef.type = BodyDef.BodyType.DynamicBody;;
		// Set its world position
		groundBodyDef.position.set(new Vector2(i*Cst.TILE_W+Cst.TILE_W/2, j*Cst.TILE_H+Cst.TILE_H/2));  
		
		// Create a body from the defintion and add it to the world
		Body groundBody = world.createBody(groundBodyDef);  

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(16, 16);
		// Create a fixture from our polygon shape and add it to our ground body  
		groundBody.createFixture(groundBox, 0.0f); 
		// Clean up after ourselves
		groundBox.dispose();
		
		return groundBody;
	}
	
	
	
	
	public void update(){
		world.step(1/60f, 6, 2);
	}
	
}
