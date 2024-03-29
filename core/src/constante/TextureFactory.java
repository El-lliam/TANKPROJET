package constante;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class TextureFactory {
	static public final int IMG_SZ = 84;
	static private TextureFactory instance = null;
	private Texture texture;
	private Texture plantTexture;
	private Texture bulletU;
	private Texture bulletR;
	private Texture bulletD;
	private Texture bulletL;
	public static HashMap<Class<?>, ArrayList<Texture>> map;
	
	private TextureFactory() {
		texture = new Texture(Gdx.files.local("texturemap.png"));
		plantTexture = new Texture(Gdx.files.local("plant.png")); // 加载 plant.png
		bulletU= new Texture(Gdx.files.local("bulletU.png.png"));
		bulletR= new Texture(Gdx.files.local("bulletR.png.png"));
		bulletD= new Texture(Gdx.files.local("bulletD.png.png"));
		bulletL= new Texture(Gdx.files.local("bulletL.png.png"));
	}
	
	static public TextureFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instance = new TextureFactory();
			return instance;
		}
	}
	
	public TextureRegion getSol() {
        return new TextureRegion(texture, 0, 0, IMG_SZ, IMG_SZ);
    }
	
	public Sprite[] getMonChar() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*1 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*2 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*3 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*4 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*5 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*6 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*7 , IMG_SZ*0, IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*0 , IMG_SZ*1, IMG_SZ, IMG_SZ)
		};
	
		return sprites ;
	}
	


	public Sprite[] getCharEnnemi() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*1,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*2,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*3,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*4,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*5,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*6,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*7,IMG_SZ*1 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*0,IMG_SZ*2 ,  IMG_SZ, IMG_SZ)
		};
	
		return sprites ;
	}
	
	public TextureRegion getMurMiddleHor() {
        return new TextureRegion(texture, IMG_SZ*2, IMG_SZ*3, IMG_SZ, IMG_SZ);
    }
	
	public TextureRegion getMurMiddleVect() {
		return new TextureRegion(texture,IMG_SZ*4, IMG_SZ*3, IMG_SZ, IMG_SZ);
	}
	
	public TextureRegion getMurVect() {
		return new TextureRegion(texture,IMG_SZ*6, IMG_SZ*3, IMG_SZ, IMG_SZ);
	}
	
	public TextureRegion getMurHor() {
		return new TextureRegion(texture,IMG_SZ*7, IMG_SZ*3, IMG_SZ, IMG_SZ);
	}
	
	public Sprite[] getExplode() {
		Sprite[] sprites = {
			new Sprite(texture, IMG_SZ*1,IMG_SZ*2 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*2,IMG_SZ*2 ,  IMG_SZ, IMG_SZ),
			new Sprite(texture, IMG_SZ*3,IMG_SZ*2 ,  IMG_SZ, IMG_SZ)
		};
		return sprites ;
	}
	
	public TextureRegion getCanon() {
		return new TextureRegion(texture,IMG_SZ*4,IMG_SZ*2,IMG_SZ,IMG_SZ);
	}
	
	public TextureRegion getPointRouge() {
		return new TextureRegion(texture,IMG_SZ*5,IMG_SZ*2,IMG_SZ,IMG_SZ);
	}
	
	public TextureRegion getVictory() {
		return new TextureRegion(texture,IMG_SZ*7,IMG_SZ*2,IMG_SZ,IMG_SZ);
	}
	
	public static Texture getTexture(Class<?> o, int index) {
		return map.get(o).get(index);
	}
	
	public TextureRegion getPlant() {
        return new TextureRegion(plantTexture); // 返回整个 plant.png 的 TextureRegion
    }
	
	public TextureRegion getBulletUP() {
		return new TextureRegion(bulletU);
	}
	
	public TextureRegion getBulletDOWN() {
		return new TextureRegion(bulletD);
	}
	
	public TextureRegion getBulletLEFT() {
		return new TextureRegion(bulletL);
	}
	
	public TextureRegion getBulletRIGHT() {
		return new TextureRegion(bulletR);
	}
	
	
}