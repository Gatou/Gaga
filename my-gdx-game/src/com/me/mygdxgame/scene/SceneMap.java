package com.me.mygdxgame.scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.mgr.PhysicsManager;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.mgr.TextureMgr;
import com.me.mygdxgame.sprite.SpriteBase;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class SceneMap extends SceneBase implements InputProcessor{

	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	Texture tileset;
	SpriteBatch spriteBatch;
	InputMultiplexer plex;
	public static ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	private boolean lockCamera = false;
	
	static GameMover player  = new GameMover(new SpriteBase(TextureMgr.get("character.png")), 100, 100);

	public static Map<String, Boolean> commands = new HashMap<String, Boolean>(){{
		put("Up", false);
		put("Down", false);
		put("Left", false);
		put("Right", false);
	}};
	
	public SceneMap(){
		//StageMgr.startStageLater(new StageMap());
		Game.map.setup(0);
		plex = new InputMultiplexer();
		
		plex.addProcessor(StageMgr.stage);
		plex.addProcessor(this);
		spriteBatch = new SpriteBatch();
		tileset = TextureMgr.get("tileset.png");
		Gdx.input.setInputProcessor(plex);
	}

	public void updatePre(){
		super.updatePre();
	}

	public void updateMain(){
		super.updateMain();
		Game.camera.update();
		Game.map.update();
		
		updateCommands();
		player.update();
		PhysicsManager.getInstance().update();
		
		
		spriteBatch.setProjectionMatrix(Game.camera.combined);
		
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        
        //Sprite sprite = new Sprite(tileset, 0, 0, 32, 32);
		//sprite.setPosition(32, 32);
		//sprite.draw(spriteBatch);
		
        
		for(int i=0; i<Game.map.mapData.tilemap.length; i++){
			for(int j=0; j<Game.map.mapData.tilemap[i].length; j++){
				int tileIndex = Game.map.mapData.tilemap[i][j];
				int tilex = (tileIndex % 8) * 32;
				int tiley = (tileIndex / 8) * 32;
				Sprite sprite = new Sprite(tileset, tilex, tiley, 32, 32);
				sprite.setPosition(i*32, j*32);
				sprite.draw(spriteBatch);
			}
		}
		player.sprite.draw(spriteBatch);
		spriteBatch.end();
		/*
		shapeRenderer.setProjectionMatrix(Game.camera.combined);
		 
		shapeRenderer.begin(ShapeType.FilledRectangle);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.filledRect(player.x-player.boundsWidth/2, player.y-player.boundsHeight/2, player.boundsWidth, player.boundsHeight);
		shapeRenderer.end();
		*/
		//PhysicsManager.getInstance().debugRenderer.render(PhysicsManager.getInstance().world, Game.camera.combined);
		
	}

	public void updateCommands(){
		Vector2 velocity = new Vector2(0, 0);
		
		/*
		if(commands.get("Up")){
			velocity.y = -1;
		}
		else if(commands.get("Down")){
			velocity.y = 1;
		}
		if(commands.get("Left")){
			velocity.x = -1;
		}
		else if(commands.get("Right")){
			velocity.x = 1;
		}*/

		
		
		velocity.set(StageMgr.touchpad.getKnobPercentX(), StageMgr.touchpad.getKnobPercentY()*-1);
		
		velocity.nor();
		
		Vector2 speed = velocity.mul(Gdx.graphics.getDeltaTime());
		
		//speed.set(round(speed.x, 2), round(speed.y, 2));
		speed.mul(150 * 100);
		
		player.body.setLinearVelocity(speed);
		//Game.map.collisionHandler.adjustSpeed(player, speed);
		//player.x += velocity.x;
		//player.y += velocity.y;
		//player.setPosition(player.x, player.y);
			 
	}

	
	public void terminate() {
		super.terminate();
		//removeInputListener(this);
	}

	public static List<Body> bodies = new ArrayList<Body>();
	@Override
	public boolean keyDown(int keycode) {
		Random rand = new Random();
		System.out.println("la");
		
		int offset = 10;
		for(int i=5; i<16+offset; i++){
			for(int j=5; j<12+offset; j++){
				Body body = PhysicsManager.getInstance().createDynamicBody(i, j);
				body.setLinearVelocity(rand.nextInt(20), rand.nextInt(20));
				bodies.add(body);
				//body.setActive(false);
				//PhysicsManager.getInstance().world.destroyBody(body);
			}
		}
		for(int i=0; i<20+offset; i++){
			for(int j=0; j<15+offset; j++){
				if(i != 0 && j != 0 && i!=19+offset && j !=14+offset){
					continue;
				}
				Body body = PhysicsManager.getInstance().createStaticBody(i, j);
				//body.setLinearVelocity(rand.nextInt(20), rand.nextInt(20));
				bodies.add(body);
				//body.setActive(false);
				//PhysicsManager.getInstance().world.destroyBody(body);
			}
		}
		System.out.println("lou");
		
		if(keycode == Keys.Z){
			commands.put("Up", true);
		}
		else if(keycode == Keys.S){
			commands.put("Down", true);
		}
		else if(keycode == Keys.Q){
			commands.put("Left", true);
		}
		else if(keycode == Keys.D){
			commands.put("Right", true);
		}
		//TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.Z){
			commands.put("Up", false);
		}
		else if(keycode == Keys.S){
			commands.put("Down", false);
		}
		else if(keycode == Keys.Q){
			commands.put("Left", false);
		}
		else if(keycode == Keys.D){
			commands.put("Right", false);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		last.set(-1, -1, -1);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {/*
		if(!lockCamera && (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) || Gdx.input.isTouched(1))){

			Ray pickRay = Game.camera.getPickRay(Gdx.input.getX(), Gdx.input.getY());
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, curr);
			if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
				pickRay = Game.camera.getPickRay(last.x, last.y);
				Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, delta);			
				delta.sub(curr);
				Game.camera.position.add(delta.x, delta.y, delta.z);
			}
			last.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		}*/
		return true;
	}

	//@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		switch(amount) {
		case 1:
			Game.camera.zoom += 0.1;
			break;
		case -1:
			Game.camera.zoom -= 0.1;
			break;
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return true;
	}
	
}
