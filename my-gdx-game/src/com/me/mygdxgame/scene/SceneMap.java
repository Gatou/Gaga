package com.me.mygdxgame.scene;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.App;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameCamera;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.game.action.DestroyWallAction;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.sprite.SpriteBase;
import com.me.mygdxgame.sprite.WallSpriteState;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Grid;
import com.me.mygdxgame.utils.Point2i;

public class SceneMap extends SceneBase implements InputProcessor, GestureListener{

	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	Texture tileset;
	SpriteBatch spriteBatch;
	InputMultiplexer plex;
	public static Grid grid = new Grid();
	
	public SceneMap(){
		//StageMgr.startStageLater(new StageMap());
		Game.map.setup(-1);
		plex = new InputMultiplexer();
		
		plex.addProcessor(StageMgr.stage);
		plex.addProcessor(this);
		spriteBatch = new SpriteBatch();
		//tileset = TextureManager.get("tileset.png");
		Gdx.input.setInputProcessor(plex);
		
		
		
	}

	public void updatePre(){
		super.updatePre();
		//updateTime = ...
	}

	public void updateMain(){
		super.updateMain();
		//Game.camera.moveToStartPosition();
		//System.out.println(Game.camera.position);

		Game.camera.update();
		Game.map.update();
		App.instance().tweenManager.update(updateTime);
		
		spriteBatch.setProjectionMatrix(Game.camera.combined);
		
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        
        GameCamera cam = Game.camera;
        
        Vector3 tmp = new Vector3();
        tmp.set(0, 0, 0);
        cam.unproject(tmp);
        
        int startI = (int) (tmp.x/Cst.TILE_W);
        int startJ = (int) (tmp.y/Cst.TILE_H);
        startI = Math.max(startI, 0);
        startJ = Math.max(startJ, 0);
        
        int endI =  startI + (int)((cam.width())/Cst.TILE_W)+2;
        int endJ =  startJ + (int)((cam.height())/Cst.TILE_H)+10;
        endI = Math.min(endI, Game.map.mapData.width);
        endJ = Math.min(endJ, Game.map.mapData.height);
        
        //System.out.println(endI);
        
        for(int i=startI; i<endI; i++){
        	for(int j=startJ; j<endJ; j++){
        		if(i<=0 || j <=0 || i>=Game.map.mapData.width-1 || j >= Game.map.mapData.height-1){
        			continue;
        			//Game.map.mapData.tilemap[i][j] = -1;
        		}
				int spriteIndex = Game.map.mapData.tilemap[i][j];
				if(spriteIndex == 0){
					if(Game.map.mapData.tilemap[i-1][j] != 15 && Game.map.mapData.tilemap[i+1][j] != 15 && Game.map.mapData.tilemap[i][j+1] != 15 && Game.map.mapData.tilemap[i][j-1] != 15){
						Game.map.mapData.tilemap[i][j] = -1;
					}
					/*
					SpriteBase sprite = new SpriteBase("tileset4.png");
					sprite.setRegion((spriteIndex%16)*Cst.TILE_W, 64, Cst.TILE_W, 64);
					sprite.setSize(Cst.TILE_W, 128);
					sprite.flip(false, true);
					sprite.setPosition(i*Cst.TILE_W, j*Cst.TILE_H);
					sprite.draw(spriteBatch);*/
				}
        	}
        }
        
        for(int i=startI; i<endI; i++){
        	for(int j=startJ; j<endJ; j++){
				int spriteIndex = Game.map.mapData.tilemap[i][j];
				if(spriteIndex == 15){
					SpriteBase sprite = new SpriteBase("tileset4.png");
					sprite.setRegion((spriteIndex%16)*Cst.TILE_W, (spriteIndex/16)*128, Cst.TILE_W, 128);
					sprite.setSize(Cst.TILE_W, 128);
					sprite.flip(false, true);
					sprite.setPosition(i*Cst.TILE_W, j*Cst.TILE_H);
					sprite.draw(spriteBatch);
				}
        	}
        }
        
        for(int j=startJ; j<endJ; j++){
        	for(int i=startI; i<endI; i++){
        	
				int spriteIndex = Game.map.mapData.tilemap[i][j];
				if(spriteIndex != 15){
					//if(spriteIndex==15){
					//	continue;
					//}
					SpriteBase sprite = new SpriteBase("tileset4.png");
					
					if(spriteIndex == -1){
						sprite.setRegion((15%16)*Cst.TILE_W, 64, Cst.TILE_W, 64);
						sprite.setSize(Cst.TILE_W, 64);
						//sprite.setColor(0.5f, 0.5f, 0.5f, 0.5f);
						sprite.setOrigin(0, 64);
					}
					else{
						sprite.setRegion((spriteIndex%16)*Cst.TILE_W, (spriteIndex/16)*128, Cst.TILE_W, 128);
						sprite.setSize(Cst.TILE_W, 128);
						sprite.setOrigin(0, 64);
					}
					
					
					//Sprite sprite = SpriteManager.instance().get(0);
					//sprite.setSize(32, 96);
					//sprite.setBounds(0, -96, 32, -96);
					//sprite.setRegion((spriteIndex%16)*Cst.TILE_W, 0, 32, 96);
					
					//if(spriteIndex==0){
						//sprite.setOrigin(0, 0);
						//continue;
					//}
					//else{
						//sprite.setOrigin(0, 64);
						//sprite.setColor(1f, 1f, 1f, 0.5f);
						// normalement sprite.setOrigin(0, 96); voir si pb lors du render order
						//continue;
					//}
					//sprite.setOrigin(0, 0);
					sprite.flip(false, true);
					sprite.setPosition(i*Cst.TILE_W, j*Cst.TILE_H);
					
					WallSpriteState wallState = Game.map.parties.get(0).wallStates.get(i, j);
					if(wallState != null){
						//System.out.println(i + " " + j);
						if(wallState.selected){
							sprite.setColor(1, 0, 0, 1);
						}
					}
					
					sprite.draw(spriteBatch);
				}
				List<GameMover> events =  Game.map.eventsAt(i, j);
				if(events != null){
					for(GameMover event : events){
						if(event.sprite != null){
							event.sprite.draw(spriteBatch);
						}
					}
				}
				
			}
		}
        
		
		spriteBatch.end();
		//grid.update(startI, startJ, endI, endJ);

        
	}


	
	public void terminate() {
		super.terminate();
		//removeInputListener(this);
	}


	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		switch(character) {
		case '-':
			Game.camera.zoom *= 1.2;
			break;
		case '+':
			Game.camera.zoom /= 1.2;
			break;
		case '5':
			//System.out.println("lala");
			GameMover mover = Game.map.parties.get(0).units.get(0);
			//mover.findPath(0,0);
			//System.out.println("lol");
			//Game.map.parties.get(0).members.get(0).setTilePosition(Game.map.mapData.startI, Game.map.mapData.startJ);
			break;
		}
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if(button == Input.Buttons.LEFT){
			Vector3 v = new Vector3();
			Ray pickRay = Game.camera.getPickRay(x, y);
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, v);
			int i = (int)v.x/Cst.TILE_W;
			int j = (int)v.y/Cst.TILE_H;
			
			if(i<=0 || j <=0 || i>=Game.map.mapData.width-1 || j >= Game.map.mapData.height-1){
				return false;
			}
			
			if(Game.map.mapData.tilemap[i][j+1] != 15){
				j += 1;
			}
			
			
			if(Game.map.mapData.tilemap[i][j] != 15){
				DestroyWallAction action = new DestroyWallAction();
				action.setup(Game.map.parties.get(0), i, j);
				Game.map.parties.get(0).actionQueue.add(action);
				
			}
			//GameMover mover = Game.map.parties.get(0).units.get(0);
			//mover.findPath(i, j);
		}

		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		last.set(-1, -1, -1);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if((Gdx.input.isButtonPressed(Input.Buttons.RIGHT) || Gdx.input.isTouched(1))){

			Ray pickRay = Game.camera.getPickRay(Gdx.input.getX(), Gdx.input.getY());
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, curr);
			if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
				pickRay = Game.camera.getPickRay(last.x, last.y);
				Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, delta);			
				delta.sub(curr);
				//Game.camera.position.add(delta.x, delta.y, delta.z);
				Game.camera.setPosition(Game.camera.position.x+delta.x, Game.camera.position.y+delta.y);
			}
			last.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		}
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

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
