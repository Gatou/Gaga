package com.me.mygdxgame.ai.unit;

import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.unit.GameUnit;

public class MoverAi extends Ai{

	public static enum AiMoverState{
		WAIT,
		MOVE,
	}
	
	public AiMoverState moverState;
	
	public MoverAi(GameUnit unit) {
		super(unit);
	}

	public void moveNextTo(int i, int j) {
		if(moverState == AiMoverState.MOVE){
			return;
		}
		/*
		if(isNextTo(i, j)){
			return;
		}*/
		
		int[][] positions = {{i+1, j}, {i-1, j}, {i, j+1}, {i, j-1}};
		
		for(int[] position : positions){
			int ti = position[0];
			int tj = position[1];
			if(Game.map.blocked(unit, ti, tj)){
				continue;
			}
				
			unit.findPath(ti, tj);
			if(unit.path != null){
				moverState = AiMoverState.MOVE;
				return;
			}
		}

	}
	
	public void update(){
		super.update();
		
		if(moverState == AiMoverState.MOVE){
			if(unit.path == null){
				moverState = AiMoverState.WAIT;
			}
		}
	}
	
	public boolean isNextTo(int i, int j){
		int[][] positions = {{i+1, j}, {i-1, j}, {i, j+1}, {i, j-1}};
		for(int[] position : positions){
			int ti = position[0];
			int tj = position[1];
			if(unit.getTileI() == ti && unit.getTileJ() == tj){
				return true;
			}
		}
		return false;
	}
}
