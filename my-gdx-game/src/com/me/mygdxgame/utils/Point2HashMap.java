package com.me.mygdxgame.utils;

import java.util.HashMap;
import java.util.Map;


public class Point2HashMap<Ti, Tj, Telem> extends HashMap<Ti, Map<Tj, Telem>>{

	public Telem get(Ti i, Tj j){
		Map<Tj, Telem> hash = get(i);
		if(hash == null){
			return null;
		}
		return hash.get(j);
	}
	
	public void put(Ti i, Tj j, Telem elem){
		Map<Tj, Telem> hash = get(i);
		if(hash == null){
			hash = new HashMap<Tj, Telem>();
			put(i, hash);
		}
		hash.put(j, elem);
	}
	
	public void remove(Ti i, Tj j){
		Map<Tj, Telem> hash = get(i);
		//if(hash == null){
		//	return;
		//}
		hash.remove(j);
		if(hash.isEmpty()){
			remove(i);
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Ti i : keySet()){
			Map<Tj, Telem> hash = get(i);
			for (Tj j : hash.keySet()){
				result += i + " " + j + "\n";
			}
		}
		return result;
	}
	
}
