package com.me.mygdxgame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class WindowBase extends Window {

	public WindowBase(String title, Skin skin) {
		super(title, skin);
		//pad(16);
		WindowMgr.add(this);
	}
}
