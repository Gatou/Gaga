package com.me.mygdxgame.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.mgr.SpriteManager;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.utils.Cst;

public class SceneMap extends SceneBase implements InputProcessor{

	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	Texture tileset;
	SpriteBatch spriteBatch;
	InputMultiplexer plex;
	
	//private boolean lockCamera = false;
	
	
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
	}

	public void updateMain(){
		super.updateMain();
		Game.camera.update();
		Game.map.update();
		
		
		spriteBatch.setProjectionMatrix(Game.camera.combined);
		
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        
        OrthographicCamera cam = Game.camera;
        
        Vector3 tmp = new Vector3();
        tmp.set(0, 0, 0);
        cam.unproject(tmp);
        
        int startX = (int) (tmp.x/Cst.TILE_W);
        int startY = (int) (tmp.y/Cst.TILE_H);
        startX = Math.max(startX, 0);
        startY = Math.max(startY, 0);
        
        int endX =  startX + (int)((cam.viewportWidth * cam.zoom)/Cst.TILE_W)+1;
        int endY =  startY + (int)((cam.viewportHeight * cam.zoom)/Cst.TILE_H)+10;
        endX = Math.min(endX, Game.map.mapData.width);
        endY = Math.min(endY, Game.map.mapData.height);
        
		for(int i=startX; i<endX; i++){
			for(int j=startY; j<endY; j++){
				int spriteIndex = Game.map.mapData.tilemap[i][j];
				Sprite sprite = SpriteManager.instance().get(spriteIndex);
				sprite.setPosition(i*Cst.TILE_W, j*Cst.TILE_H);
				sprite.draw(spriteBatch);
				/*
				int tilex = (tileIndex % 8) * 32;
				int tiley = (tileIndex / 8) * 32;
				Sprite sprite = new Sprite(tileset, tilex, tiley, 32, 32);
				sprite.setPosition(i*32, j*32);
				sprite.draw(spriteBatch);*/
			}
		}
        
		spriteBatch.end();

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
	public boolean touchDragged(int x, int y, int pointer) {
		if((Gdx.input.isButtonPressed(Input.Buttons.RIGHT) || Gdx.input.isTouched(1))){

			Ray pickRay = Game.camera.getPickRay(Gdx.input.getX(), Gdx.input.getY());
			Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, curr);
			if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
				pickRay = Game.camera.getPickRay(last.x, last.y);
				Intersector.intersectRayPlane(pickRay, Cst.XY_PLANE, delta);			
				delta.sub(curr);
				Game.camera.position.add(delta.x, delta.y, delta.z);
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
	
}
