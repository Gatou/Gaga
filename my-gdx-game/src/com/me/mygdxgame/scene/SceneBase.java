package com.me.mygdxgame.scene;

import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.mgr.UiManager;

public abstract class SceneBase{

	public float updateTime;
	
	
	public void start() {
		
	}

	public void update() {
		updateTime = Gdx.graphics.getDeltaTime();
		updatePre();
		updateMain();
		updatePost();
	}
	
	public void updatePre(){
		
		//IntervalMgr.update();
	}
	
	public void updateMain(){
		
	}
	
	public void updatePost(){
		//WindowMgr.update();
		UiManager.instance().update();
	}
	
	public void terminate() {
	}


}
