package com.me.mygdxgame.data;

import java.util.ArrayList;
import java.util.List;

public class DataTileset extends DataBase{

	public enum Passability{
		PASSABLE,
		NON_PASSABLE,
	}
	
	public List<Passability> collisions;
	
	public DataTileset(int id, String name) {
		super(id, name);
		collisions = new ArrayList<Passability>();
	}

}
