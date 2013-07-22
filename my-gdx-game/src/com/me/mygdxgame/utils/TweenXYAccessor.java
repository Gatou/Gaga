package com.me.mygdxgame.utils;

import aurelienribon.tweenengine.TweenAccessor;

import com.me.mygdxgame.game.GameMover;


public class TweenXYAccessor implements TweenAccessor<GameMover> {
	
    public static final int X = 1;
    public static final int Y = 2;
    public static final int XY = 3;

    public int getValues(GameMover target, int tweenType, float[] value) {
        switch (tweenType) {
            case X: value[0] = target.sprite.getX();return 1;
            case Y: value[0] = target.sprite.getY(); return 1;
            case XY:
            	value[0] = target.sprite.getX();
            	value[1] = target.sprite.getY();
                return 2;
            default: assert false; return 0;
        }
    }
    
    public void setValues(GameMover target, int tweenType, float[] newValues) {
        switch (tweenType) {
            //case X: target.setX(newValues[0]); break;
            //case Y: target.setY(newValues[1]); break;
            case XY:
                target.setPosition(newValues[0], newValues[1]);
                break;
            default: assert false; break;
        }
    }
}