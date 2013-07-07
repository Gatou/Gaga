package com.me.mygdxgame.data;

import java.util.ArrayDeque;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class DataMap extends DataBase{

	public int tilesetId;
	public int[][] tilemap;
	//public int[][] collisionMap;

	public DataMap(int id, String name) {
		super(id, name);
		tilesetId = 0;
		//collisionMap = new byte[][]{
		
	}

}
