package ElementDeJeu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class balle extends MoveObject {
    private float speedX; // ˮƽ�����ٶ�
    private float speedY; // ��ֱ�����ٶ�
    private TextureRegion texture; // �ӵ���ͼ��

    public balle(float x, float y, float speedX, float speedY, TextureRegion texture) {
        super(x, y);
        this.speedX = speedX;
        this.speedY = speedY;
        this.texture = texture;
        this.width = 82; // ����ʵ��ͼ���С����
        this.height = 82;
    }

    // �����ӵ���λ��
    public void update(float deltaTime) {
        this.x += speedX * deltaTime;
        this.y += speedY * deltaTime;
    }

    
    public boolean isOutOfScreen(float viewportWidth, float viewportHeight) {
        // ȷ���ӵ���ȫ�뿪�ӿں����Ϊ������Ļ
        return x + width < 0 || x > viewportWidth || y + height < 0 || y > viewportHeight;
    }


    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    
 // �����ӵ��ķ���
    public void draw(com.badlogic.gdx.graphics.g2d.SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    // ��Ӷ�texture��getter����
    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
}
