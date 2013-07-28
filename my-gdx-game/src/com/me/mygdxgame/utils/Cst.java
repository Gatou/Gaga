package com.me.mygdxgame.utils;

import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;

public class Cst {
	
	public static final Plane XY_PLANE = new Plane(new Vector3(0, 0, 1), 0);
	
	public static final int TILE_W = 96;
	public static final int TILE_H = 96;
	
	public static final int TILE_HW = TILE_W/2;
	public static final int TILE_HH = TILE_H/2;
	
	public static final int VIEWPORT_WIDTH = 800;
	public static final int VIEWPORT_HEIGHT = 480;
	
	public static final int WALL_HEIGHT = 32;
	public static final int TILE_PLUS_WALL_HEIGHT = TILE_H + WALL_HEIGHT;
	//public static final Point2i MAP_SIZE = new Point2i(10, 10);

	//public static final int TILE_HW = TILE_W / 2;
	//public static final int TILE_HH = TILE_H / 2;
	
	//public static final int TILE_QW = TILE_HW / 2;
	//public static final int TILE_QH = TILE_HH / 2;

	//public static final byte FLOOR = 0;
	//public static final byte WALL = 1;
	
	//public static final int NB_TEXTURES = 2;
	
}
