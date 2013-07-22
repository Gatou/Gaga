package com.me.mygdxgame.mgr;

import java.util.ArrayList;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataTileset;
import com.me.mygdxgame.data.DataTileset.Passability;
import com.me.mygdxgame.data.MapData;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameCamera;
import com.me.mygdxgame.game.map.GameMap;

public class DataMgr {

	public static void init(){
		loadDatabase();
		createGameObjects();
	}

	public static void loadDatabase(){

		Data.tilesets = new ArrayList<DataTileset>();
		
		DataTileset dataTileset = new DataTileset(0, "Tilesdfsd");
		dataTileset.collisions.add(Passability.NON_PASSABLE);
		dataTileset.collisions.add(Passability.PASSABLE);
		Data.tilesets.add(dataTileset);
		
		//Load maps database
		Data.maps = new ArrayList<MapData>();
		//Map 0

		MapData data = new MapData(0, "Map000");
		//for(int j=0; j<14; j++)
		//Random rand = new Random();

		Data.maps.add(data);
	}

	public static void createGameObjects(){
		Game.camera = new GameCamera();
		Game.map = new GameMap();
	}

}
