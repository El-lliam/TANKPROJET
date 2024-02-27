package ElementDeJeu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.MyGdxGame.Direction;

import constante.TextureFactory;

public class EnnemiTank {
    private Vector2 position; // 坦克的位置
    private Direction direction; // 坦克的方向
    private float shootCooldown = 0; // 射击冷却计时器
    private MyGdxGame game; // 游戏实例
    private Sprite[] sprites; // 敌人坦克的纹理数组
    private float speed = 100; // 这是坦克每秒移动的像素数，可根据需要调整

    
    public EnnemiTank(float x, float y, MyGdxGame game) {
        this.position = new Vector2(x, y);
        this.game = game;
        this.direction = Direction.UP; // 默认方向
        this.sprites = TextureFactory.getInstance().getCharEnnemi(); // 加载敌人坦克纹理
        this.shootCooldown = 2.0f; // 初始化射击冷却时间
    }
    public void update(float deltaTime) {
        // 随机改变方向的逻辑
        if (MathUtils.random() < 0.01) {
            direction = Direction.values()[MathUtils.random(Direction.values().length - 1)];
        }

        // 根据当前方向和可能的碰撞来更新位置
        Vector2 movement = new Vector2();
        switch (direction) {
            case UP: movement.y = speed * deltaTime; break;
            case DOWN: movement.y = -speed * deltaTime; break;
            case LEFT: movement.x = -speed * deltaTime; break;
            case RIGHT: movement.x = speed * deltaTime; break;
        }

        Vector2 newPos = position.cpy().add(movement);
        if (!game.isCollision(newPos.x, newPos.y)) {
            position = newPos;
        }

        // 尝试射击
        if (shootCooldown <= 0) {
            shoot();
            shootCooldown = MathUtils.random(1.5f, 2.5f); // 随机冷却时间，使行为更不可预测
        } else {
            shootCooldown -= deltaTime;
        }
    }
    private void shoot() {
        // 根据方向生成子弹
    	TextureRegion bulletTexture = TextureFactory.getInstance().getPointRouge(); // 获取子弹纹理
        float bulletSpeedX = 0;
        float bulletSpeedY = 0;
        switch (direction) {
            case UP:
                bulletSpeedY = 300;
                break;
            case DOWN:
                bulletSpeedY = -300;
                break;
            case LEFT:
                bulletSpeedX = -300;
                break;
            case RIGHT:
                bulletSpeedX = 300;
                break;
        }
        float bulletX = position.x + (sprites[0].getWidth() - bulletTexture.getRegionWidth()) / 2;
        float bulletY = position.y + (sprites[0].getHeight() - bulletTexture.getRegionHeight()) / 2;
        // 根据方向调整子弹位置
        balle bullet = new balle(bulletX, bulletY, bulletSpeedX, bulletSpeedY, bulletTexture);
        game.addBullet(bullet); // 将子弹添加到游戏中
    }
    
    public void draw(SpriteBatch batch) {
        batch.draw(sprites[direction.ordinal()], position.x, position.y);
    }

}

