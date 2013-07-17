package com.me.mygdxgame.game.map;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.MapData;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.utils.Point2i;

public class GameMap {

	int mapId;
	public MapData mapData;
	private Map<String, List<GameMover>> tileEvents;
	private List<GameMover> events;

	
	public GameMap() {
	}
	
	public void setup(int mapId) {
		this.mapId = mapId;
		
		if(mapId == -1){
			mapData = new MapData(-1, "");
			mapData.generateRandomMap();
		}
		else{
			mapData = Data.maps.get(mapId);
			
		}
		
		setupEvents();
	}


	
	public void setupEvents(){
		tileEvents = new Hashtable<String, List<GameMover>>();
		events = new ArrayList<GameMover>();
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

	public List<GameMover> eventsAt(Point2i tilePosition){
		return tileEvents.get(tilePosition.getHashCode());
	}

	public void update(){
		updateEvents();
	}

	public void updateEvents(){
		//if(events != null)
		for(GameMover event : events){
			event.update();
		}
	}

}
