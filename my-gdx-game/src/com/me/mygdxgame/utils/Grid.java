package com.me.mygdxgame.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.mygdxgame.game.Game;

public class Grid {
    
	private ShapeRenderer renderer;
	public boolean visible;

    public Grid(){
    	visible = true;
    	renderer = new ShapeRenderer();
    }
   
    public void update(int startI, int startJ, int endI, int endJ){
    	if(!visible){
    		return;
    	}
    	
		renderer.setProjectionMatrix(Game.camera.combined);
		renderer.begin(ShapeType.Line);
		renderer.setColor(0.8f, 1f, 0.8f, 1f);
		
		int cellWidth = Cst.TILE_W;
		int cellHeight = Cst.TILE_H;
		int lineWidth = (endI-startI)*cellWidth;
		int lineHeight = (endJ-startJ)*cellHeight;
		
		for(int i=startI; i<endI+1; i++){
			renderer.line(i*cellWidth, startJ*cellHeight - Cst.WALL_HEIGHT, i*cellWidth, startJ*cellHeight + lineHeight - Cst.WALL_HEIGHT);
		}
		
		for(int j=startJ; j<endJ+1; j++){
			renderer.line(startI*cellWidth, j*cellHeight - Cst.WALL_HEIGHT,  startI*cellWidth + lineWidth, j*cellHeight - Cst.WALL_HEIGHT);
		}
		
		renderer.end();
    }
    

}