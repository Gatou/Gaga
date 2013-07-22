package com.me.mygdxgame.game;

import java.util.ArrayList;
import java.util.List;

public class GameParty {

	public List<GameMover> members;
	
	public GameParty() {
		members = new ArrayList<GameMover>();
	}
	
	public void update(){
		for(GameMover mover : members){
			mover.update();
		}
	}
	
}
