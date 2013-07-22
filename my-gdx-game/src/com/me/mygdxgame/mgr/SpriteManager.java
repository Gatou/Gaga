package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.mygdxgame.sprite.SpriteBase;

public class SpriteManager {

	private static SpriteManager instance;
	private List<Sprite> cache;
	
	private SpriteManager(){
		cache = new ArrayList<Sprite>();
		
		//Sprite sprite = new Sprite(TextureManager.instance().get("tileset.png"));
		
		//for(int i=0; i<16; i++){
		//	Sprite sprite = new Sprite()
		//}
		//sprite.setOrigin(0, 96);
		//cache.add(sprite);
		/*
		SpriteBase sprite = new SpriteBase("wall_0.png");
		sprite.setOrigin(0, 96);
		cache.add(sprite);
		
		sprite = new SpriteBase("floor_0.png");
		sprite.setOrigin(0, 96);
		cache.add(sprite);*/
	}
	
	public static SpriteManager instance(){
		if(instance == null){
			instance = new SpriteManager();
		}
		return instance;
	}
	
	public Sprite get(int index){
		return cache.get(index);
	}
	
}
