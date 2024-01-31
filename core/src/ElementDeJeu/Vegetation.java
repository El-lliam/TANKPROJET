package ElementDeJeu;

import constante.TextureFactory;

public class Vegetation extends statiqueObject{
	public Vegetation(float x, float y) {
		super(x, y);
		width = TextureFactory.getTexture(this.getClass(), 0).getWidth();
		height = TextureFactory.getTexture(this.getClass(), 0).getHeight();
	}
}