package com.me.mygdxgame.mgr;

import java.util.Hashtable;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
	
	private static TextureManager instance;
	private Map<String, Texture> cache = new Hashtable<String, Texture>();
	
	private TextureManager() {
	}
	
	public static TextureManager instance(){
		if(instance == null){
			instance = new TextureManager();
		}
		return instance;
	}
	
	public void add(String assetName){
		if(!cache.containsKey(assetName)){
			cache.put(assetName, new Texture(Gdx.files.internal(assetName)));
		}
	}
	
	public Texture get(String assetName){
		add(assetName);
		return cache.get(assetName);
	}
	
}
