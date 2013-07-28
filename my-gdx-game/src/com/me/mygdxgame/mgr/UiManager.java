package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class UiManager {

	private static UiManager instance;
	
	public Stage stage = null;
	public Skin skin;
	//public static Touchpad touchpad;
	public Label fpsLabel;

	private UiManager() {
		skin = new Skin(Gdx.files.internal("Graphics/Window/uiskin.json"));
		
		/*
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);*/
		
		stage = new Stage();
		
		fpsLabel = new Label("", skin);
		fpsLabel.setFillParent(true);
		
		
		//stage.addActor(fpsLabel);
		
	}
	
	public static UiManager instance(){
		if(instance == null){
			instance = new UiManager();
		}
		return instance;
	}
	
	public void resize (int width, int height) {
		stage.setViewport(width, height, false);
		//stage.getCamera()
	}

	public void dispose() {
		stage.dispose();
	}

	public void update() {
		fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond() + "          ");
		if(stage != null) {
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
		}
		//emptyTable.drawDebug(stage);
	}
	
	public void clear(){
		stage.clear();
		fpsLabel.remove();
		//System.out.println(fpsLabel.getParent());
	}
	
	public void setRoot(Actor actor){
		//clear();
		stage.addActor(actor);
	}
	
	public void setFpsVisible(boolean visible){
		if(visible){
			//System.out.println(fpsLabel.getParent());
			if(fpsLabel.getParent() == null){
				stage.addActor(fpsLabel);
			}
		}
		else{
			fpsLabel.remove();
		}
	}
	
}
