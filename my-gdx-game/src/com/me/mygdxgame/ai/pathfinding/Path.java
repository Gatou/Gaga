package com.me.mygdxgame.ai.pathfinding;


import java.util.ArrayList;

import com.me.mygdxgame.utils.Point2i;


public class Path {

	public ArrayList<Point2i> steps = new ArrayList<Point2i>();


	public Path() {
		
	}
	
	public void appendStep(int x, int y) {
		steps.add(new Point2i(x,y));
	}

	public void prependStep(int x, int y) {
		steps.add(0, new Point2i(x, y));
	}
	
}
