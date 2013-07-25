package com.me.mygdxgame.game.unit;

import com.me.mygdxgame.ai.unit.MoverAi;
import com.me.mygdxgame.ai.unit.Ai.AiActionState;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.sprite.SpriteBase;

public class GameUnit extends GameMover{

	public static enum UnitType{
		PIONEER;
	}
	
	public UnitType type;
	public MoverAi ai;
	
	public GameUnit(SpriteBase sprite, float x, float y, UnitType type) {
		super(sprite, x, y);
		this.type = type;
		this.ai = new MoverAi(this);
	}
	
	public void update(){
		super.update();
		ai.update();
	}

	public void actionCompleted() {
		ai.actionState = AiActionState.WAIT;
		path = null;
	}


}
