package com.me.mygdxgame.mgr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class StageMgr {

	public static Stage stage = null;
	public static Stage nextStage = null;
	public static Skin skin;
	//public static Touchpad touchpad;
	public static Label fpsLabel;
	public static Image image1;
	static float rotValue = 0;
	public static Group actorGroup;
	
	public static void init() {
		skin = new Skin(Gdx.files.internal("Graphics/Window/uiskin.json"));
		//nextStage = new StageMainMenu();
		nextStage = new Stage();

		fpsLabel = new Label("", skin);
		fpsLabel.setFillParent(true);
		nextStage.addActor(fpsLabel);
		
		//touchpad = new Touchpad(0, skin);
		//touchpad.setBounds(0, 0, 200, 200);
		//nextStage.addActor(touchpad);

		actorGroup = new Group();
		nextStage.addActor(actorGroup);
		//emptyTable.setFillParent(true);

		/*
		image1 = new Image(new Texture(Gdx.files.internal("character.png")));
		image1.setPosition(50, 50);
		image1.setOrigin(image1.getWidth()/2, image1.getHeight()/2);
		*/
		actorGroup.setPosition(0, 0);
		
		actorGroup.setTransform(true);
		

		
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
		rotValue += 0.01f;
		actorGroup.rotate(rotValue);
		//actorGroup.scale(rotValue/1000);
		//image1.rotate(-rotValue);
		if(stage != null) {
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
		}
		if(isStageChanging()){
			launchStage();
		}
		//emptyTable.drawDebug(stage);
	}
}
