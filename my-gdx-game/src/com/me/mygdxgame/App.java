package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.mgr.DataMgr;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.scene.SceneBase;
import com.me.mygdxgame.scene.SceneMap;
import com.me.mygdxgame.utils.Cst;


public class App implements ApplicationListener{
	
		
	SceneBase scene;
	
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
		StageMgr.init();
		//TweenMgr.init();
		//ScriptMgr.init();
		//Gdx.graphics.setDisplayMode(800, 480, false);
		//Gdx.graphics.setVSync(true);
		//resize(0, 0);
		scene = new SceneMap();
	}
	
	@Override
	public void render() {
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
		StageMgr.resize(width, height);
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}

}


				