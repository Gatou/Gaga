package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class StageMgr {

	public static Stage stage = null;
	public static Stage nextStage = null;
	public static Skin skin;
	public static Touchpad touchpad;
	public static Label fpsLabel;
	
	public static void init() {
		skin = new Skin(Gdx.files.internal("Graphics/Window/uiskin.json"));
		//nextStage = new StageMainMenu();
		nextStage = new Stage();
		fpsLabel = new Label("", skin);
		fpsLabel.setFillParent(true);
		nextStage.addActor(fpsLabel);
		
		touchpad = new Touchpad(0, skin);
		touchpad.setBounds(0, 0, 200, 200);
		nextStage.addActor(touchpad);

		
		launchStage();
		

		
	}

	public static void startStageLater(Stage newStage) {
		nextStage = newStage;
	}

	public static boolean isStageChanging() {
		return nextStage != null;
	}

	private static void launchStage() {
		if(stage != null){
			stage.dispose();
		}
		stage = nextStage;
		nextStage = null;
	}

	public static void resize (int width, int height) {
		stage.setViewport(width, height, true);
	}

	public static void dispose() {
		stage.dispose();
	}

	public static void update() {
		fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond() + "          ");
		
		if(stage != null) {
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
		}
		if(isStageChanging()){
			launchStage();
		}
	}
}
