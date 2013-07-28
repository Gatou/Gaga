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
		//collisionMap = new short[][]{
		
	}

	public void generateRandomMap(){
		Random rand = new Random();
		width = 100;
		height = 100;
		tilemap = new short[width][height];
		
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				tilemap[i][j] = 0xF00;
			}
		}
		
		//startI = 1+rand.nextInt(width-2);
		//startJ = 1+rand.nextInt(width-2);
		startI = 50;
		startJ = 50;
		
		GameParty party = new GameParty();
		Game.map.parties.add(party);
		
		//setTile(5,5, (short) 15);
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
        
		generateCross(startI, startJ, 4, 0);
		
		


		
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
	

	private void generateCross(int i, int j, int maxDepth, int currentDepth){
		if(currentDepth >= maxDepth){
			return;
		}
		
		if(i<=0 || j<=0 || i>=width-1 || j>=height-1){
			return;
		}
		boolean tileValid = setTile(i, j);
		
		if(tileValid){
		
			generateCross(i+1, j, maxDepth, currentDepth+1);
			generateCross(i-1, j, maxDepth, currentDepth+1);
			generateCross(i, j+1, maxDepth, currentDepth+1);
			generateCross(i, j-1, maxDepth, currentDepth+1);
		}
	}	
	
	public boolean setTile(int i, int j){
		if(!posValid(i, j)){
			return false;
		}
		//System.out.println(i + " " + j + " " + tileIndex);
		tilemap[i][j] = 0;
		applyAutoTile(i, j, (short) 0xFF);
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
	
	public short adjustBits(short bits){
		//if W border then no NW-SW corner
		if((bits & 0x01) == 0x01){ 
			bits = (short) (bits & 0x6F);
		}
		//if N border then no NW-NE corner
		if((bits & 0x02) == 0x02){ 
			bits = (short) (bits & 0xCF);
		}
		//if E border then no NE-SE corner
		if((bits & 0x04) == 0x04){
			bits = (short) (bits & 0x9F);
		}
		//if S border then no SE-SW corner
		if((bits & 0x08) == 0x08){
			bits = (short) (bits & 0x3F);
		}
		return bits;
	}
	
	public void applyAutoTile(int i, int j, short newTileBits){
		//boolean[] bits = indexToBits(newTileIndex);
		//boolean[] bits2;
		int ii, jj;
		
		//6
		ii = i+1;
		jj = j;
		if(posValid(ii, jj)){
			short bits = tilemap[ii][jj];
			if(bits != 0){
				bits = (short) (bits | (short)0x01);
				bits = adjustBits(bits);
	
				tilemap[ii][jj] = bits;
			}
			
		}
		
		//4
		ii = i-1;
		jj = j;
		if(posValid(ii, jj)){
			short bits = tilemap[ii][jj];
			if(bits != 0){
				bits = (short) (bits | (short)0x04);
				bits = adjustBits(bits);
				tilemap[ii][jj] = bits;
			}
		}
		
		//8
		ii = i;
		jj = j-1;
		if(posValid(ii, jj)){
			short bits = tilemap[ii][jj];
			if(bits != 0){
				bits = (short) (bits | (short)0x08);
				bits = adjustBits(bits);
				tilemap[ii][jj] = bits;
			}
		}
		

		
		//2
		ii = i;
		jj = j+1;
		if(posValid(ii, jj)){
			short bits = tilemap[ii][jj];
			if(bits != 0){
				bits = (short) (bits | (short)0x02);
				bits = adjustBits(bits);
				tilemap[ii][jj] = bits;
			}
		}
		
		
		//7
		ii = i-1;
		jj = j-1;
		if(posValid(ii, jj)){
			short bits = tilemap[ii][jj];
			
			if(bits != 0){
				bits = (short) (bits | (short)0x40);
				bits = adjustBits(bits);
				tilemap[ii][jj] = bits;
			}
		}
		
		//9
		ii = i+1;
		jj = j-1;
		if(posValid(ii, jj)){
			short bits = tilemap[ii][jj];
			if(bits != 0){
				bits = (short) (bits | (short)0x80);
				bits = adjustBits(bits);
				tilemap[ii][jj] = bits;
			}
		}
		
		//1
		ii = i-1;
		jj = j+1;
		if(posValid(ii, jj)){
			short bits = tilemap[ii][jj];
			if(bits != 0){
				bits = (short) (bits | (short)0x20);
				bits = adjustBits(bits);
				tilemap[ii][jj] = bits;
			}
		}
		
		//3
		ii = i+1;
		jj = j+1;
		if(posValid(ii, jj)){
			short bits = tilemap[ii][jj];
			if(bits != 0){
				bits = (short) (bits | (short)0x10);
				bits = adjustBits(bits);
				tilemap[ii][jj] = bits;
			}
		}

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
