package com.me.mygdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.me.mygdxgame.utils.Cst;

public class GameCamera extends OrthographicCamera{
	
	public GameCamera(){
		setToOrtho(true, Cst.VIEWPORT_WIDTH, Cst.VIEWPORT_HEIGHT);
	}
	
	public void resize(int width, int height) {
		setToOrtho(true, Cst.VIEWPORT_WIDTH, Cst.VIEWPORT_HEIGHT);
	}
}
