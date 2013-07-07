package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.Random;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
import com.me.mygdxgame.data.DataTileset;
import com.me.mygdxgame.data.DataTileset.Passability;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameCamera;
import com.me.mygdxgame.game.map.GameMap;
import com.me.mygdxgame.utils.Cst;

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
		Data.maps = new ArrayList<DataMap>();
		//Map 0

		DataMap data = new DataMap(0, "Map000");
		data.tilemap = new int[6][5];
		data.tilemap[0][0] = 0; data.tilemap[1][0] = 0; data.tilemap[2][0] = 0; data.tilemap[3][0] = 0; data.tilemap[4][0] = 0; data.tilemap[5][0] = 0;
		data.tilemap[0][1] = 0; data.tilemap[1][1] = 1; data.tilemap[2][1] = 1; data.tilemap[3][1] = 1; data.tilemap[4][1] = 1; data.tilemap[5][1] = 0;
		data.tilemap[0][2] = 0; data.tilemap[1][2] = 1; data.tilemap[2][2] = 1; data.tilemap[3][2] = 1; data.tilemap[4][2] = 1; data.tilemap[5][2] = 0;
		data.tilemap[0][3] = 0; data.tilemap[1][3] = 1; data.tilemap[2][3] = 1; data.tilemap[3][3] = 1; data.tilemap[4][3] = 1; data.tilemap[5][3] = 0;
		data.tilemap[0][4] = 0; data.tilemap[1][4] = 0; data.tilemap[2][4] = 0; data.tilemap[3][4] = 0; data.tilemap[4][4] = 0; data.tilemap[5][4] = 0;
		
		//for(int j=0; j<14; j++)
		//Random rand = new Random();

		Data.maps.add(data);
	}

	public static void createGameObjects(){
		Game.camera = new GameCamera();
		Game.map = new GameMap();
	}

}
