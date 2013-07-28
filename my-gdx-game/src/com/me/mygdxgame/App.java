package com.me.mygdxgame;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.ApplicationListener;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.mgr.DataMgr;
import com.me.mygdxgame.mgr.UiManager;
import com.me.mygdxgame.scene.SceneBase;
import com.me.mygdxgame.scene.map.SceneMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.TweenXYAccessor;


public class App implements ApplicationListener{
	
	public TweenManager tweenManager;
	private static App instance;
	
	public static App instance(){
		if(instance == null){
			instance = new App();
		}
		return instance;
	}
	
	public SceneBase scene;
	
	private App() {
		
	}
	
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
		UiManager.instance();
		//TweenMgr.init();
		//ScriptMgr.init();
		//Gdx.graphics.setDisplayMode(800, 480, false);
		//Gdx.graphics.setVSync(true);
		//resize(0, 0);
		tweenManager = new TweenManager();
		Tween.registerAccessor(GameMover.class, new TweenXYAccessor());
	}
	
	@Override
	public void render() {
		if(scene == null){
			scene = new SceneMap();
		}
		scene.update();
		//TweenMgr.update();
		//SceneMgr.update();
	}
		
	@Override
	public void dispose(){
	}
	
	@Override
	public void resize(int width, int height) {
		width = Cst.VIEWPORT_WIDTH;
		height = Cst.VIEWPORT_HEIGHT;
		Game.camera.resize(width,height);
		UiManager.instance().resize(width, height);
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}

}


				