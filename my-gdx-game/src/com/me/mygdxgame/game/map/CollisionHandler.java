package com.me.mygdxgame.game.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
import com.me.mygdxgame.data.DataTileset;
import com.me.mygdxgame.data.DataTileset.Passability;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.utils.Cst;

public class CollisionHandler {

	DataMap mapData;
	DataTileset tileset;
	
	public CollisionHandler() {
	}
	
	public void setup(DataMap mapData){
		this.mapData = mapData;
		tileset = Data.tilesets.get(mapData.tilesetId);
	}
	

	
	public void adjustSpeed(GameMover mover, Vector2 speed){
		/*
		//int i = (int) (mover.x/Cst.TILE_W);
		//int j = (int) (mover.y/Cst.TILE_H);
		//System.out.println(i + " " + j);
		//int tileIndex = mapData.tilemap[i][j];
		//System.out.println(tileset.collisions.get(tileIndex));
		
		int iTileBegin, iTileEnd, jTileBegin, jTileEnd;
		float boundX, boundY;
		float newX = mover.x + speed.x;
		float newY = mover.y + speed.y;
		
		boundX = newX - mover.boundsWidth/2;
		iTileBegin = (int) (boundX/Cst.TILE_W);
		boundX = newX + mover.boundsWidth/2;
		iTileEnd = (int) (boundX/Cst.TILE_W);
		
		boundY = newY - mover.boundsHeight/2;
		jTileBegin = (int) (boundY/Cst.TILE_H);
		boundY = newY + mover.boundsHeight/2;
		jTileEnd = (int) (boundY/Cst.TILE_H);
		
		System.out.println(iTileBegin + " " + iTileEnd + "     " + jTileBegin + " " + jTileEnd);
		int tileIndex;
		
		List<String> normals = new ArrayList<String>();
		
		for(int i = iTileBegin; i <= iTileEnd; i++){
			for(int j = jTileBegin; j <= jTileEnd; j++){
				tileIndex = mapData.tilemap[i][j];
				System.out.println(tileset.collisions.get(tileIndex));
				if(tileset.collisions.get(tileIndex) == Passability.NON_PASSABLE){

				    if (speed.x > 0) // object came from the left
				    	normals.add("left");
				    else if (speed.x < 0) // object came from the right
				    	normals.add("right");
				    if (speed.y > 0) // object came from the top
				    	normals.add("up");
				    else if (speed.y < 0) // object came from the bottom
				    	normals.add("down");
				    
				    
				}
			}
		}*/
	}
	
}
