package ElementDeJeu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import constante.TextureFactory;

public class Terrain extends statiqueObject{

	public Terrain(float x, float y) {
		super(x, y);
		width = TextureFactory.getTexture(this.getClass(), 0).getWidth();
		height = TextureFactory.getTexture(this.getClass(), 0).getHeight();
	}

	public void setTexture(TextureRegion sol) {
		// TODO Auto-generated method stub
		
	}

}