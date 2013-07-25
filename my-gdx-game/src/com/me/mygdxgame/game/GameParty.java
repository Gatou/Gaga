package com.me.mygdxgame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.me.mygdxgame.game.action.GameAction;
import com.me.mygdxgame.game.unit.GameUnit;
import com.me.mygdxgame.sprite.WallSpriteState;
import com.me.mygdxgame.utils.Point2HashMap;
import com.me.mygdxgame.utils.Point2i;

public class GameParty {

	public List<GameUnit> units;
	public List<GameAction> actionQueue;
	public List<GameAction> completedActionQueue;
	public Point2HashMap<Integer, Integer, WallSpriteState> wallStates;
	
	public GameParty() {
		units = new ArrayList<GameUnit>();
		actionQueue = new ArrayList<GameAction>();
		completedActionQueue = new ArrayList<GameAction>();
		wallStates = new Point2HashMap<Integer, Integer, WallSpriteState>();
	}
	
	public void update(){
		for(GameAction action : completedActionQueue){
			actionQueue.remove(action);
		}
		
		for(GameAction action : actionQueue){
			action.update();
		}
		for(GameUnit unit : units){
			unit.update();
		}
	}
	
}
