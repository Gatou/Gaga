package com.me.mygdxgame.game.action;

import java.util.ArrayList;
import java.util.List;

import com.me.mygdxgame.ai.unit.Ai.AiActionState;
import com.me.mygdxgame.ai.unit.Ai.AiTimedActionState;
import com.me.mygdxgame.ai.unit.MoverAi;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameParty;
import com.me.mygdxgame.game.unit.GameUnit;
import com.me.mygdxgame.game.unit.GameUnit.UnitType;
import com.me.mygdxgame.sprite.WallSpriteState;
import com.me.mygdxgame.utils.Point2HashMap;

public class DestroyWallAction extends GameAction{

	public static Point2HashMap<Integer, Integer, DestroyWallAction> cache = new Point2HashMap<Integer, Integer, DestroyWallAction>();
	
	public List<GameUnit> units;
	public GameParty party;
	public int maxUnits;
	int i, j;
	int hp;
	boolean reachable;
	boolean interrupted;
	
	public DestroyWallAction() {
		units = new ArrayList<GameUnit>();
	}
	
	public void setup(GameParty party, int i, int j){
		this.party = party;
		maxUnits = 2;
		this.i = i;
		this.j = j;
		this.hp = 30;
		reachable = false;
		
		WallSpriteState wallState = new WallSpriteState();
		wallState.selected = true;
		party.wallStates.put(i, j, wallState);
		
		cache.put(i, j, this);
	}
	
	public void update(){
		
		super.update();
		
		if(interrupted){
			clear();
			return;
		}
		
		if(!isPositionReachable()){
			return;
		}
		
		if(needUnits()){
			searchUnits();
		}
		
		for(GameUnit unit : units){
			execute(unit);
		}
		//System.out.println("Hp:" + hp);
		
		if(isCompleted()){
			completed();
		}
	}
	
	public boolean isCompleted(){
		return hp <= 0;
	}
	
	public void completed(){
		Game.map.mapData.setTile(i, j);
		clear();
	}
	
	public void interrupt(){
		interrupted = true;
		clear();
	}
	
	public void clear(){
		DestroyWallAction.cache.remove(i, j);
		for(GameUnit unit : units){
			unit.actionCompleted();
		}
		units.clear();
		
		party.completedActionQueue.add(this);
		party.wallStates.remove(i, j);
	}
	
	public boolean isPositionReachable(){
		if(reachable){
			return true;
		}
		if(!Game.map.blocked(null, i+1, j) || !Game.map.blocked(null, i-1, j) || !Game.map.blocked(null, i, j+1) || !Game.map.blocked(null, i, j-1)){
			reachable = true;
			return true;
		}
		return false;
	}
	
	public void searchUnits(){
		for(GameUnit unit : party.units){
			if(unit.type == UnitType.PIONEER && unit.ai.actionState == AiActionState.WAIT){
				addUnit(unit);
				if(!needUnits()){
					return;
				}
			}
		}
	}
	
	public void addUnit(GameUnit unit){
		
		//System.out.println("unit added");
		unit.ai.actionState = AiActionState.BUSY;
		units.add(unit);
		//System.out.println(unit.ai.actionState);
	}
	
	public boolean needUnits(){
		//System.out.println(units.size() + " " + maxUnits);
		if(units.size() >= maxUnits){
			return false;
		}
		return true;
	}
	
	public void execute(GameUnit unit){		
		MoverAi unitAi = unit.ai;
		
		if(!unitAi.isNextTo(i, j)){
			unitAi.moveNextTo(i, j);
			return;
		}
		
		//System.out.println(unitAi.timedActionState);
		
		if(unitAi.timedActionState == AiTimedActionState.WAIT){
			unitAi.startAction(0.5f);
		}
		else if(unitAi.timedActionState == AiTimedActionState.COMPLETED){
			hp -= 10;
			unitAi.clearTimedAction();
		}
	}
	

	
}
