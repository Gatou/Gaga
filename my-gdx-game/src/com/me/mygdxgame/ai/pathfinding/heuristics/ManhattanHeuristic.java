package com.me.mygdxgame.ai.pathfinding.heuristics;

import com.me.mygdxgame.ai.pathfinding.AStarHeuristic;
import com.me.mygdxgame.ai.pathfinding.TileBasedMap;
import com.me.mygdxgame.game.GameMover;

public class ManhattanHeuristic implements AStarHeuristic {

	private int minimumCost;
	
	public ManhattanHeuristic(int minimumCost) {
		this.minimumCost = minimumCost;
	}

	public float getCost(TileBasedMap map, GameMover mover, int x, int y, int tx, int ty) {
		return minimumCost * (Math.abs(x-tx) + Math.abs(y-ty));
	}
}
