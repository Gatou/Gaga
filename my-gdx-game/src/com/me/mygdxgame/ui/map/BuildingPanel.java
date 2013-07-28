package com.me.mygdxgame.ui.map;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.mygdxgame.mgr.UiManager;
import com.me.mygdxgame.ui.Ui;

public class BuildingPanel extends Table{

	public BuildingPanel() {
		//setSize(300, 300);
		
		addListener(new ClickListener(){
			
		});
		//stage = new Stage(0, 0, false);
		//UiManager.instance().skin UiManager.instance().skin = new UiManager.instance().skin(Gdx.files.internal("data/uiUiManager.instance().skin.json"));
		//Gdx.input.setInputProcessor(stage);

		// Gdx.graphics.setVSync(false);

		//stage.addActor(this);
		//this.setFillParent(true);

		Table table = new Table();
		
		table.defaults().pad(12);
		
		//table.row().fill().expandY();
		int buttonSize = 60;
		table.add(Ui.createButton(200, 200, "Graphics/Icon/building")).size(buttonSize, buttonSize);
		table.add(Ui.createButton(200, 200, "Graphics/Icon/building")).size(buttonSize, buttonSize).row();
		table.add(Ui.createButton(200, 200, "Graphics/Icon/building")).size(buttonSize, buttonSize);
		table.add(Ui.createButton(200, 200, "Graphics/Icon/building")).size(buttonSize, buttonSize).row();
		table.add(Ui.createButton(200, 200, "Graphics/Icon/building")).size(buttonSize, buttonSize);
		table.add(Ui.createButton(200, 200, "Graphics/Icon/building")).size(buttonSize, buttonSize).row();
		
	    ScrollPane scrollPane = new ScrollPane(table,UiManager.instance().skin);
	    scrollPane.setFlickScroll(false);
	    scrollPane.setScrollingDisabled(true, false);
	    //scrollPane.setHeight(50);
		add(scrollPane).size(200, 200);
		
		//table.pack();
		pack();
		//setHeight(50);
	}
	
}
