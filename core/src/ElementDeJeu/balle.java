package ElementDeJeu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class balle extends MoveObject {
    private float speedX; // 水平方向速度
    private float speedY; // 垂直方向速度
    private TextureRegion texture; // 子弹的图像

    public balle(float x, float y, float speedX, float speedY, TextureRegion texture) {
        super(x, y);
        this.speedX = speedX;
        this.speedY = speedY;
        this.texture = texture;
        this.width = 82; // 根据实际图像大小调整
        this.height = 82;
    }

    // 更新子弹的位置
    public void update(float deltaTime) {
        this.x += speedX * deltaTime;
        this.y += speedY * deltaTime;
    }

    
    public boolean isOutOfScreen(float viewportWidth, float viewportHeight) {
        // 确保子弹完全离开视口后才认为超出屏幕
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
    
 // 绘制子弹的方法
    public void draw(com.badlogic.gdx.graphics.g2d.SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    // 添加对texture的getter方法
    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
}
