package com.me.mygdxgame.game.map;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
import com.me.mygdxgame.data.DataTileset;
import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.mgr.PhysicsManager;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;

public class GameMap {

	int mapId;
	public DataMap mapData;
	private Map<String, List<GameMover>> tileEvents;
	private List<GameMover> events;
	public CollisionHandler collisionHandler;
	//public DataTileset tileset;
	
	public GameMap() {
		collisionHandler = new CollisionHandler();
	}
	
	public void setup(int mapId) {
		this.mapId = mapId;
		mapData = Data.maps.get(mapId);
		
		collisionHandler.setup(mapData);
		setupEvents();
		PhysicsManager.getInstance().setup();
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
