package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import constante.TextureFactory;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch _batch;
    private Rectangle _glviewport;
    private OrthographicCamera _camera;

    @Override
    public void create() {
        _batch = new SpriteBatch();
        set_glviewport(new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        _camera = new OrthographicCamera(1450, 1000);
        _camera.setToOrtho(false, 1450, 1000);
        _camera.update();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _camera.update();
        _batch.setProjectionMatrix(_camera.combined);

        _batch.begin();
        TextureRegion sol = TextureFactory.getInstance().getSol(); // 获取sol
        _batch.draw(sol, 0, 0, 84,84); // 使用sol
        _batch.end();
    }

    @Override
    public void dispose() {
        _batch.dispose();
    }

	public Rectangle get_glviewport() {
		return _glviewport;
	}

	public void set_glviewport(Rectangle _glviewport) {
		this._glviewport = _glviewport;
	}

}
