package com.me.mygdxgame.scene;

import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.App;
import com.me.mygdxgame.mgr.StageMgr;

public abstract class SceneBase{

	
	
	
	public void start() {
		
	}

	public void update() {
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
		StageMgr.update();
	}
	
	public void terminate() {
	}


}
