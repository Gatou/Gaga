package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.List;

import com.me.mygdxgame.sprite.SpriteBase;

public class SpriteManager {

	private static SpriteManager instance;
	private List<SpriteBase> cache;
	
	private SpriteManager(){
		cache = new ArrayList<SpriteBase>();
		
		SpriteBase sprite = new SpriteBase("wall_0.png");
		sprite.setOrigin(0, 96);
		cache.add(sprite);
		
		sprite = new SpriteBase("floor_0.png");
		sprite.setOrigin(0, 32);
		cache.add(sprite);
	}
	
	public static SpriteManager instance(){
		if(instance == null){
			instance = new SpriteManager();
		}
		return instance;
	}
	
	public SpriteBase get(int index){
		return cache.get(index);
	}
	
}
