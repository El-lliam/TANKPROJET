package ElementDeJeu;

import constante.TextureFactory;

public class MurBriques extends statiqueObject{

	public MurBriques(float x, float y) {
		super(x, y);
		width = TextureFactory.getTexture(this.getClass(), 0).getWidth();
		height = TextureFactory.getTexture(this.getClass(), 0).getHeight();
	}

}