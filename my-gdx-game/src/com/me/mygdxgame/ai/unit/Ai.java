package com.me.mygdxgame.ai.unit;

import com.me.mygdxgame.App;
import com.me.mygdxgame.game.unit.GameUnit;

public class Ai {

	public static enum AiActionState{
		WAIT,
		BUSY,
	}
	
	public static enum AiTimedActionState{
		WAIT,
		DOING,
		COMPLETED,
	}
	
	public GameUnit unit;
	public AiActionState actionState;
	public AiTimedActionState timedActionState;
	public float actionDuration;
	
	public Ai(GameUnit unit) {
		this.unit = unit;
		clear();
	}
	
	public void clear(){
		actionState = AiActionState.WAIT;
		clearTimedAction();
	}
	
	public void clearTimedAction(){
		timedActionState = AiTimedActionState.WAIT;
		actionDuration = 0;
	}
	
	public void startAction(float duraction){
		actionDuration = duraction;
		timedActionState = AiTimedActionState.DOING;
	}
	
	public void update(){
		if(timedActionState == AiTimedActionState.DOING){
			actionDuration -= App.instance().scene.updateTime;
			if(actionDuration <= 0){
				actionDuration = 0;
				timedActionState = AiTimedActionState.COMPLETED;
			}
		}
	}
	



	
}
