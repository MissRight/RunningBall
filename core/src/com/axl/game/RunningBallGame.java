package com.axl.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class RunningBallGame extends ApplicationAdapter {

    Stage stage;

    SpriteBatch batch;
    Texture img;
    TextureRegionDrawable background;
    TextureRegionDrawable knobRegion;

    Touchpad.TouchpadStyle mTouchpadStyle;
    Touchpad mTouchpad;

    int speed = 3;
    int x = 0;
    int y = 0;

    @Override
    public void create() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        background = new TextureRegionDrawable(new TextureRegion(img, 0, 0, 128, 128));
        knobRegion = new TextureRegionDrawable(new TextureRegion(img, 32, 32, 96, 96));

        mTouchpadStyle = new Touchpad.TouchpadStyle(background, knobRegion);
        mTouchpad = new Touchpad(15, mTouchpadStyle);
        mTouchpad.setBounds(0,0,650,650);
        stage.addActor(mTouchpad);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    public void update() {
        if (mTouchpad.isTouched()) {
            x += mTouchpad.getKnobPercentX() * speed;
            y += mTouchpad.getKnobPercentY() * speed;
        }
    }
}
