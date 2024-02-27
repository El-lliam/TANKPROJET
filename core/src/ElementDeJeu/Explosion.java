package ElementDeJeu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion {
    private Sprite[] frames;
    public float x;
	public float y;
    private float duration = 0.1f; // ÿ֡��������ʱ��
    private float elapsedTime = 0;

    public Explosion(Sprite[] frames, float x, float y) {
        this.frames = frames;
        this.x = x;
        this.y = y;
    }

    public boolean update(float delta) {
        elapsedTime += delta;
        return elapsedTime >= duration * frames.length; // �����Ƿ񶯻�����
    }

    public void draw(SpriteBatch batch) {
        int index = Math.min((int)(elapsedTime / duration), frames.length - 1);
        batch.draw(frames[index], x, y);
    }
}
