package com.me.mygdxgame.data;

import java.util.ArrayDeque;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class MapData extends DataBase{

	//public int tilesetId;
	public int width;
	public int height;
	public short[][] tilemap;
	//public int[][] collisionMap;

	int startI;
	int startJ;
	
	public MapData(int id, String name) {
		super(id, name);
		//tilesetId = 0;
		//collisionMap = new byte[][]{
		
	}

	public void generateRandomMap(){
		Random rand = new Random();
		width = 30;
		height = 30;
		tilemap = new short[width][height];
		
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				tilemap[i][j] = 0;
			}
		}
		
		startI = rand.nextInt(width);
		startJ = rand.nextInt(width);
		generateCross(startI, startJ, (short) 1, 4, 0);
	}
	
	private void generateCross(int i, int j, short spriteIndex, int maxDepth, int currentDepth){
		if(currentDepth >= maxDepth){
			return;
		}
		if(i<0 || j<0 || i>=width || j>=height){
			return;
		}
		tilemap[i][j] = spriteIndex;
		
		generateCross(i+1, j, spriteIndex, maxDepth, currentDepth+1);
		generateCross(i-1, j, spriteIndex, maxDepth, currentDepth+1);
		generateCross(i, j+1, spriteIndex, maxDepth, currentDepth+1);
		generateCross(i, j-1, spriteIndex, maxDepth, currentDepth+1);
	}
	
}
