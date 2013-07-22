package com.me.mygdxgame.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.data.MapData;
import com.me.mygdxgame.utils.Cst;

public class GameCamera extends OrthographicCamera{
	
	public GameCamera(){
		setToOrtho(true, Cst.VIEWPORT_WIDTH, Cst.VIEWPORT_HEIGHT);
	}
	
	public void resize(int width, int height) {
		setToOrtho(true, width, height);
	}
	
	public void moveToStartPosition(){
		MapData mapData = Game.map.mapData;
		int i = mapData.startI;
		int j = mapData.startJ;
		//System.out.println(i*Cst.TILE_W);
		
		//position.set(-60, 0, 0);
		setPosition(i*Cst.TILE_W + Cst.TILE_W_HALF, j*Cst.TILE_H + Cst.TILE_H_HALF);
	}
	
	public void setPosition(float x, float y){
		x = Math.max(0, x);
		y = Math.max(0, y);
		x = Math.min(Game.map.mapData.width*Cst.TILE_W, x);
		y = Math.min(Game.map.mapData.width*Cst.TILE_H, y);
		position.set(x, y, position.z);
	}
	
	public float width(){
		return viewportWidth * zoom;
	}
	
	public float height(){
		return viewportHeight * zoom;
	}
	
}

