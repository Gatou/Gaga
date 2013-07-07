package com.me.mygdxgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.mgr.DataMgr;
import com.me.mygdxgame.mgr.PhysicsManager;
import com.me.mygdxgame.mgr.SceneMgr;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.mgr.TweenMgr;
import com.me.mygdxgame.mgr.WindowMgr;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class App implements ApplicationListener{
	

	private static TweenManager manager;
	

		

	
	@Override 
	public void create() {
		/*
		long t = System.currentTimeMillis();
		
		String path = (new File(System.getProperty("user.dir"), "monfile.json")).getAbsolutePath();
	    Gson gson = new Gson();
	    Person person = null;
	    try {
	    	person = gson.fromJson(new FileReader(path), Person.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    System.out.println(System.currentTimeMillis() - t);
	    */
		
	    //System.out.println(person.tilemap[1][4]);	
		//Debug.setDebugMode(true);
		DataMgr.init();
		WindowMgr.init();
		StageMgr.init();
		TweenMgr.init();
		//ScriptMgr.init();
		SceneMgr.init();
		PhysicsManager.getInstance();
		//Gdx.graphics.setDisplayMode(800, 480, false);
		Gdx.graphics.setVSync(true);
	}
	
	@Override
	public void render() {
		TweenMgr.update();
		SceneMgr.update();
	}
		
	@Override
	public void dispose(){
	}
	
	@Override
	public void resize(int width, int height) {
		Game.camera.resize(width,height);
		WindowMgr.resize(width, height);
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}

}


				