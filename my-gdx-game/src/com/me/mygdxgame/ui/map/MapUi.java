package com.me.mygdxgame.ui.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.mgr.TextureManager;
import com.me.mygdxgame.mgr.UiManager;
import com.me.mygdxgame.scene.map.Minimap;
import com.me.mygdxgame.ui.Ui;
import com.me.mygdxgame.utils.Cst;

public class MapUi extends Ui{

	int buttonWidth = 32;
	int buttonHeight = 32;
	int buttonSpacing = 12;
	
	List<ImageButton> mainMenuButtons;
	List<ImageButton> minimapButtons;
	List<ImageButton> mapZoomButtons;
	
	public Minimap minimap;
	
	public MapUi() {
		createMinimapUi();
		createMainMenuUi();
		createMapZoomButtons();
		
		BuildingPanel panel = new BuildingPanel();
		panel.setPosition(50, 50);
		addActor(panel);
	}
	
	public void createMainMenuUi(){
		mainMenuButtons = new ArrayList<ImageButton>();
		
		ImageButton b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/building");
		mainMenuButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				//minimap.zoomIn();
			}
		});

		b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/building");
		mainMenuButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
			}
		});

		b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/building");
		mainMenuButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
			}
		});
		
		b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/building");
		mainMenuButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
			}
		});
		
		
		int index = 0;
		int spacing = (minimap.baseWidth - mainMenuButtons.size()*buttonWidth) / (mainMenuButtons.size()+1);
		for(ImageButton button : mainMenuButtons){
			addActor(button);
			
			button.setPosition(minimap.image.getX() + spacing, minimap.image.getY() - buttonHeight - buttonSpacing);
			button.setX(button.getX() + buttonWidth*index + spacing*index);
			index += 1;
		}

	}
	
	public void createMinimapUi(){
		minimapButtons = new ArrayList<ImageButton>();
		
		minimap = new Minimap();

		ImageButton b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/zoomIn");
		minimapButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				minimap.zoomIn();
			}
		});
		
		b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/zoomOut");
		minimapButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				minimap.zoomOut();
			}
		});
		
		minimap.image.setPosition(0, Cst.VIEWPORT_HEIGHT-minimap.baseHeight);
		
		int index = 0;
		for(ImageButton button : minimapButtons){
			addActor(button);
			
			button.setPosition(minimap.image.getX()+minimap.baseWidth-4, minimap.image.getY()+minimap.baseHeight-buttonHeight-buttonSpacing);
			button.setY(button.getY() - buttonHeight*index - buttonSpacing*index);
			
			index += 1;
		}
		
		addActor(minimap.image);
	}
	

	
	public void createMapZoomButtons(){
		/*
		mapZoomButtons = new ArrayList<ImageButton>();
		
		ImageButton b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/zoomIn");
		mapZoomButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				if(Game.camera.zoom <= 0.25){
					return;
				}
				Game.camera.zoom /= 2;
			}
		});
		
		b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/zoomResize");
		mapZoomButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				Game.camera.zoom = 1;
			}
		});
		
		b = createButton(buttonWidth, buttonHeight, "Graphics/Icon/zoomOut");
		mapZoomButtons.add(b);
		b.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				if(Game.camera.zoom >= 8){
					return;
				}
				Game.camera.zoom *= 2;
				System.out.println(Game.camera.zoom);
			}
		});
		
		int index = 0;
		for(ImageButton button : mapZoomButtons){
			addActor(button);
			
			button.setPosition(0, Cst.VIEWPORT_HEIGHT-buttonHeight - buttonSpacing);
			button.setY(button.getY() - buttonHeight*index - buttonSpacing*index);
			index += 1;
		}
		*/
	}
	
}
