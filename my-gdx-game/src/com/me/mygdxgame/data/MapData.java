package com.me.mygdxgame.data;

import java.util.Random;

import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameParty;
import com.me.mygdxgame.game.unit.GameUnit;
import com.me.mygdxgame.game.unit.GameUnit.UnitType;
import com.me.mygdxgame.sprite.SpriteBase;

public class MapData extends DataBase{

	final static int FLOOR_INDEX = 15;
	
	//public int tilesetId;
	public int width;
	public int height;
	public short[][] tilemap;
	//public int[][] collisionMap;

	public int startI;
	public int startJ;
	
	public MapData(int id, String name) {
		super(id, name);
		//tilesetId = 0;
		//collisionMap = new byte[][]{
		
	}

	public void generateRandomMap(){
		Random rand = new Random();
		width = 16;
		height = 16;
		tilemap = new short[width][height];
		
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				tilemap[i][j] = 0;
			}
		}
		
		startI = 1+rand.nextInt(width-2);
		//startJ = 1+rand.nextInt(width-2);
		startJ = 14;
		
		GameParty party = new GameParty();
		Game.map.parties.add(party);
		
		//setTile(5,5,(short) 15);
		//setTile(5,5+1,(short) 15);
		/*
        for(int i=0; i<width; i++){
        	for(int j=0; j<height; j++){
        		if(i<=0 || j <=0 || i>=Game.map.mapData.width-1 || j >= Game.map.mapData.height-1){
        			//continue;
        			Game.map.mapData.tilemap[i][j] = -1;
        		}
        	}
        }*/
        
		generateCross(startI, startJ, (short) 15, 4, 0);
		
		


		
		GameUnit mover = new GameUnit(new SpriteBase("character.png"), 0, 0, UnitType.PIONEER);
		mover.setTilePosition(startI, startJ);
		party.units.add(mover);
		
		mover = new GameUnit(new SpriteBase("character.png"), 0, 0, UnitType.PIONEER);
		mover.setTilePosition(startI-1, startJ);
		party.units.add(mover);
		
		mover = new GameUnit(new SpriteBase("character.png"), 0, 0, UnitType.PIONEER);
		mover.setTilePosition(startI+1, startJ);
		party.units.add(mover);
		
		//setTile(5+1,5+1,(short) 0);
		//setTile(5,5+2,(short) 0);
		/*
		setTile(5-1,5-1,(short) 0);
		setTile(5+1,5+1,(short) 0);
		setTile(5-1,5+1,(short) 0);
		setTile(5+1,5-1,(short) 0);
		setTile(5+1,5,(short) 0);
		setTile(5-1,5,(short) 0);
		setTile(5,5+1,(short) 0);
		setTile(5,5-1,(short) 0);
		setTile(5,5,(short) 15);*/
		//setTile(5+2,5+2,(short) 0);
		//setTile(5,5+1,(short) 0);
		//setTile(5+1,5,(short) 0);
		//setTile(5,5-1,(short) 0);
		//setTile(5,5+1,(short) 0);
	}
	

	private void generateCross(int i, int j, short tileIndex, int maxDepth, int currentDepth){
		if(currentDepth >= maxDepth){
			return;
		}
		
		if(i<=0 || j<=0 || i>=width-1 || j>=height-1){
			return;
		}
		boolean tileValid = setTile(i, j, tileIndex);
		
		if(tileValid){
		
			generateCross(i+1, j, tileIndex, maxDepth, currentDepth+1);
			generateCross(i-1, j, tileIndex, maxDepth, currentDepth+1);
			generateCross(i, j+1, tileIndex, maxDepth, currentDepth+1);
			generateCross(i, j-1, tileIndex, maxDepth, currentDepth+1);
		}
	}	
	
	public boolean setTile(int i, int j, short tileIndex){
		if(!posValid(i, j)){
			return false;
		}
		tilemap[i][j] = tileIndex;
		applyAutoTile(i, j, tileIndex);
		return true;
	}
	
	public boolean posValid(int i, int j){
		if(i<0){
			return false;
		}
		else if(j<0){
			return false;
		}
		else if(i>=width){
			return false;
		}
		else if(j>=height){
			return false;
		}
		return true;
	}
	
	public void applyAutoTile(int i, int j, int newTileIndex){
		boolean[] bits = indexToBits(newTileIndex);
		boolean[] bits2;
		int ii, jj;
		
		
		//8
		ii = i;
		jj = j-1;
		if(posValid(ii, jj)){
			if(tilemap[ii][jj] >= 16){
				tilemap[ii][jj] = 0;
			}
			bits2 = indexToBits(tilemap[ii][jj]);
			short index = bitsToIndex(new boolean[]{bits2[0], bits2[1], bits2[2], bits[3]});
			if(tilemap[ii][jj] != 15 && index == 15){ index = 0; }
			tilemap[ii][jj] = index;
		}
		
		//2
		ii = i;
		jj = j+1;
		if(posValid(ii, jj)){
			if(tilemap[ii][jj] >= 16){
				tilemap[ii][jj] = 0;
			}
			bits2 = indexToBits(tilemap[ii][jj]);
			short index = bitsToIndex(new boolean[]{bits2[0], bits[1], bits2[2], bits2[3]});
			if(tilemap[ii][jj] != 15 && index == 15){ index = 0; }
			tilemap[ii][jj] = index;
		}
		
		//4
		ii = i-1;
		jj = j;
		if(posValid(ii, jj)){
			if(tilemap[ii][jj] >= 16){
				tilemap[ii][jj] = 0;
			}
			bits2 = indexToBits(tilemap[ii][jj]);
			short index = bitsToIndex(new boolean[]{bits2[0], bits2[1], bits[2], bits2[3]});
			if(tilemap[ii][jj] != 15 && index == 15){ index = 0; }
			tilemap[ii][jj] = index;
		}

		//6
		ii = i+1;
		jj = j;
		if(posValid(ii, jj)){
			if(tilemap[ii][jj] >= 16){
				tilemap[ii][jj] = 0;
			}
			bits2 = indexToBits(tilemap[ii][jj]);
			short index = bitsToIndex(new boolean[]{bits[0], bits2[1], bits2[2], bits2[3]});
			if(tilemap[ii][jj] != 15 && index == 15){ index = 0; }
			tilemap[ii][jj] = index;
		}
		
		/*
		//7
		ii = i-1;
		jj = j-1;
		if(posValid(ii, jj) && (tilemap[ii][jj] <= 0 || tilemap[ii][jj] >= 16)){
			bits2 = indexToBits(tilemap[ii][jj]);
			short index = (short) (16 + bitsToIndex(new boolean[]{bits2[0], bits2[1], bits[2], bits2[3]}));
			//if(tilemap[ii][jj] != 15 && index == 15){ index = 0; }
			tilemap[ii][jj] = index;
			
		}
		
		//9
		ii = i+1;
		jj = j-1;
		if(posValid(ii, jj) && (tilemap[ii][jj] <= 0 || tilemap[ii][jj] >= 16)){
			bits2 = indexToBits(tilemap[ii][jj]);
			short index = (short) (16 + bitsToIndex(new boolean[]{bits2[0], bits2[1], bits2[2], bits[3]}));
			//if(tilemap[ii][jj] != 15 && index == 15){ index = 0; }
			tilemap[ii][jj] = index;
		}
		
		//1
		ii = i-1;
		jj = j+1;
		if(posValid(ii, jj) && (tilemap[ii][jj] <= 0 || tilemap[ii][jj] >= 16)){
			bits2 = indexToBits(tilemap[ii][jj]);
			short index = (short) (16 + bitsToIndex(new boolean[]{bits2[0], bits[1], bits2[2], bits2[3]}));
			//if(tilemap[ii][jj] != 15 && index == 15){ index = 0; }
			tilemap[ii][jj] = index;
		}
		
		//3
		ii = i+1;
		jj = j+1;
		if(posValid(ii, jj) && (tilemap[ii][jj] <= 0 || tilemap[ii][jj] >= 16)){
			bits2 = indexToBits(tilemap[ii][jj]);
			short index = (short) (16 + bitsToIndex(new boolean[]{bits[0], bits2[1], bits2[2], bits2[3]}));
			//if(tilemap[ii][jj] != 15 && index == 15){ index = 0; }
			tilemap[ii][jj] = index;
		}*/

	}
	
	public boolean[] indexToBits(int index){
		if(index == -1){
			return new boolean[]{false, false, false, false};
		}
	    boolean[] bits = new boolean[4];
	    for (int ii = 3; ii >= 0; ii--) {
	        bits[ii] = (index & (1 << ii)) != 0;
	    }
	    return bits;
	}
	
	public short bitsToIndex(boolean[] bits){
		short result = 0;
	    for (int i = 0; i < bits.length; i++) {
	    	if(bits[i]){
	    		result += Math.pow(2, i);
	    	}
	    }
	    return result;
	}
	
	
	
}
