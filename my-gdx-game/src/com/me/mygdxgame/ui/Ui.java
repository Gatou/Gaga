package com.me.mygdxgame.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.mygdxgame.mgr.TextureManager;
import com.me.mygdxgame.mgr.UiManager;

public class Ui extends Group{

	public static ImageButton createButton(int width, int height, String iconFilename){
		ImageButtonStyle style = new ImageButtonStyle(UiManager.instance().skin.get(ButtonStyle.class));
		style.imageUp = new TextureRegionDrawable(new TextureRegion(TextureManager.instance().get(iconFilename + "_up.png")));
		style.imageDown = new TextureRegionDrawable(new TextureRegion(TextureManager.instance().get(iconFilename + "_down.png")));
		
		ImageButton button = new ImageButton(style);
		button.setSize(width, height);
		return button;
	}
	
}
