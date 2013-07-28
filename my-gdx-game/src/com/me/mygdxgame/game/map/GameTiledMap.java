package com.me.mygdxgame.game.map;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.me.mygdxgame.ai.pathfinding.TileBasedMap;
import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.MapData;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.game.GameParty;
import com.me.mygdxgame.utils.Point2i;

public abstract class GameTiledMap implements TileBasedMap{

	int mapId;
	public MapData mapData;
	private Map<String, List<GameMover>> tileEvents;
	public List<GameParty> parties;
	
	public GameTiledMap() {
	}
	
	public void setup(int mapId) {
		this.mapId = mapId;
		
		parties = new ArrayList<GameParty>();
		tileEvents = new Hashtable<String, List<GameMover>>();
		
		if(mapId == -1){
			mapData = new MapData(-1, "");
			mapData.generateRandomMap();
		}
		else{
			mapData = Data.maps.get(mapId);
			
		}
		
		setupEvents();
		Game.camera.moveToStartPosition();
	}


	
	public void setupEvents(){
		
		
	}

	public void removeEventFromTile(Point2i tile, GameMover ev) {
		List<GameMover> evs = eventsAt(tile);
		if(evs != null){
			evs.remove(ev);
			if(evs.isEmpty()) {
				tileEvents.remove(tile.getHashCode());
			}
		}
	}

	public void addEventToTile(Point2i tile, GameMover ev) {
		List<GameMover> evs = eventsAt(tile);
		if(evs == null) {
			evs = new ArrayList<GameMover>();
			tileEvents.put(tile.getHashCode(), evs);
		}
		evs.add(ev);
	}

	public List<GameMover> eventsAt(int tileX, int tileY){
		Point2i tilePosition = new Point2i(tileX, tileY);
		return eventsAt(tilePosition);
	}

	public List<GameMover> eventsAt(Point2i tile){
		return tileEvents.get(tile.getHashCode());
	}

	public void update(){
		updateEvents();
	}

	public void updateEvents(){
		for(GameParty party : parties){
			party.update();
		}
	}

	@Override
	public void pathFinderVisited(int x, int y) {
	}

	@Override
	public boolean blocked(GameMover mover, int i, int j) {
		boolean mapCollision = mapData.tilemap[i][j] != 0; 
		if(mapCollision){
			return true;
		}
		return false;
	}

	@Override
	public float getCost(GameMover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}

	@Override
	public Point2i getMapSize() {
		return new Point2i(Game.map.mapData.width, Game.map.mapData.height);
	}
}
