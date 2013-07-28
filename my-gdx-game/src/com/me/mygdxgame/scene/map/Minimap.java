package com.me.mygdxgame.scene.map;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.utils.Cst;

public class Minimap {

	public int baseWidth;
	public int baseHeight;
	public float zoom;
	
	public Image image;
	private Texture dynamicTexture;
	private Pixmap minimapPixmap;
	
	int lastWidthI, lastHeightJ;
	//private int currentStartI;
	//private int currentHeightJ;
	
	public Minimap() {
		zoom = 1;
		baseWidth = 200;
		baseHeight = 200;
		
		image = new Image();
		
	}
	
	public void update(SpriteBatch spriteBatch, int startI, int startJ, int endI, int endJ){
		
		int widthI = endI - startI;
		int heightJ = endJ - startJ;
		
		int w = (int) (baseWidth / zoom);
		int h = (int) (baseHeight / zoom);
		w = Math.min(w, Game.map.mapData.width);
		h = Math.min(h, Game.map.mapData.height);
		
		int middleI = startI + widthI/2;
		int middleJ = startJ + heightJ/2;
		
		int baseMinimapStartI = Math.max(middleI - w/2, 0);
		int baseMinimapStartJ = Math.max(middleJ - h/2, 0);

		final int minimapEndI = Math.min(baseMinimapStartI + w, Game.map.mapData.width);
		final int minimapEndJ = Math.min(baseMinimapStartJ + h, Game.map.mapData.height);
		
		int surplusI = Math.max(0, baseMinimapStartI + w - Game.map.mapData.width);
		int surplusJ = Math.max(0, baseMinimapStartJ + h - Game.map.mapData.height);
		//System.out.println(surplusI + " " + surplusJ);
		
		final int minimapStartI = Math.max(0, baseMinimapStartI - surplusI);// - ((baseMinimapStartI + w) - Game.map.mapData.width);
		final int minimapStartJ = Math.max(0, baseMinimapStartJ - surplusJ);// - ((baseMinimapStartJ + h) - Game.map.mapData.height);
		
		//System.out.println(minimapStartI + " " + minimapStartJ);
		//int testI = ;
		//minimapStartI -= testI;
		
		//System.out.println(minimapEndI + " " + minimapEndJ);
        Matrix4 uiMatrix = new Matrix4();
        uiMatrix.setToOrtho2D(0, 0, Cst.VIEWPORT_WIDTH, Cst.VIEWPORT_HEIGHT);
        spriteBatch.setProjectionMatrix(uiMatrix);
        
        
        minimapPixmap = new Pixmap(w, h, Pixmap.Format.RGB565);
        minimapPixmap.setColor(0.2f, 0.2f, 0.2f, 1);
        minimapPixmap.fill();
        
        
        minimapPixmap.setColor(1, 0, 0, 1);
        
        //System.out.println(minimapStartI + " " + minimapStartJ + " " + minimapEndI + " " + minimapEndJ);
        
        lastWidthI = minimapEndI - minimapStartI;
        lastHeightJ = minimapEndJ - minimapStartJ;
        
        for(int i=minimapStartI; i<minimapEndI; i++){
        	for(int j=minimapStartJ; j<minimapEndJ; j++){
        		int minimapI = i - minimapStartI;
        		int minimapJ = j - minimapStartJ;
        		
        		if(Game.map.mapData.tilemap[i][j] == 0){
        			minimapPixmap.drawPixel(minimapI, minimapJ);
        		}
        		//else{
        		//	minimapPixmap.drawPixel(minimapI, minimapJ, 1);
        		//}
        	}
        }
        
        //System.out.println(startJ + " " + endJ);
        minimapPixmap.setColor(1, 1, 1, 1);
        for(int i=startI; i<endI; i++){
        	for(int j=startJ; j<endJ; j++){
        		if(i==startI || j==startJ || i==endI-1 || j==endJ-1){
            		int minimapI = i - minimapStartI;
            		int minimapJ = j - minimapStartJ;
            		//System.out.println(minimapJ);
        			minimapPixmap.drawPixel(minimapI, minimapJ);
        		}
        	}
        }
        
        dynamicTexture = new Texture(minimapPixmap);
        //minimapPixmap.dispose();
        /*
        Sprite sprite2 = new Sprite(dynamicTexture);
        sprite2.setOrigin(0, 0);
        sprite2.setScale(zoom);
        //sprite2.flip(false, true);
        //sprite2.setPosition(tmp.x + sprite.getWidth()/2, tmp.y + sprite.getHeight()/2);
        
        sprite2.draw(spriteBatch);
        */
        //image = new Image(dynamicTexture);
        image.setDrawable(new TextureRegionDrawable(new TextureRegion(dynamicTexture)));
        image.setWidth(baseWidth);
        image.setHeight(baseHeight);
        image.setOrigin(0, 0);
        //image.setScale(zoom);

        image.clearListeners();
		image.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y){
            	y = baseHeight - y;
            	//System.out.println(x + " " + y);
            	
            	int widthI = minimapEndI - minimapStartI;
            	int heightJ = minimapEndJ - minimapStartJ;
            	
            	int i = (int) ((widthI*x) / baseWidth);
            	int j = (int) ((heightJ*y) / baseHeight);
            	i += minimapStartI;
            	j += minimapStartJ;
            	
            	Game.camera.moveToTile(i, j);
            	//System.out.println(i + " " + j);
            }
        });
		
	}

	public void zoomIn(){
		if(lastWidthI > 40){
			zoom *= 2;
		}
		else if(lastHeightJ > 40){
			zoom *= 2;
		}
		System.out.println(zoom);
	}
	
	public void zoomOut(){
		if(lastWidthI < Game.map.mapData.width){
			zoom /= 2;
		}
		else if(lastHeightJ < Game.map.mapData.height){
			zoom /= 2;
		}
		
		System.out.println(lastWidthI);
		System.out.println(zoom);
	}
	
}
