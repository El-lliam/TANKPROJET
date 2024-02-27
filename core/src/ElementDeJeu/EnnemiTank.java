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
    private Vector2 position; // ̹�˵�λ��
    private Direction direction; // ̹�˵ķ���
    private float shootCooldown = 0; // �����ȴ��ʱ��
    private MyGdxGame game; // ��Ϸʵ��
    private Sprite[] sprites; // ����̹�˵���������
    private float speed = 100; // ����̹��ÿ���ƶ������������ɸ�����Ҫ����

    
    public EnnemiTank(float x, float y, MyGdxGame game) {
        this.position = new Vector2(x, y);
        this.game = game;
        this.direction = Direction.UP; // Ĭ�Ϸ���
        this.sprites = TextureFactory.getInstance().getCharEnnemi(); // ���ص���̹������
        this.shootCooldown = 2.0f; // ��ʼ�������ȴʱ��
    }
    public void update(float deltaTime) {
        // ����ı䷽����߼�
        if (MathUtils.random() < 0.01) {
            direction = Direction.values()[MathUtils.random(Direction.values().length - 1)];
        }

        // ���ݵ�ǰ����Ϳ��ܵ���ײ������λ��
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

        // �������
        if (shootCooldown <= 0) {
            shoot();
            shootCooldown = MathUtils.random(1.5f, 2.5f); // �����ȴʱ�䣬ʹ��Ϊ������Ԥ��
        } else {
            shootCooldown -= deltaTime;
        }
    }
    private void shoot() {
        // ���ݷ��������ӵ�
    	TextureRegion bulletTexture = TextureFactory.getInstance().getPointRouge(); // ��ȡ�ӵ�����
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
        // ���ݷ�������ӵ�λ��
        balle bullet = new balle(bulletX, bulletY, bulletSpeedX, bulletSpeedY, bulletTexture);
        game.addBullet(bullet); // ���ӵ���ӵ���Ϸ��
    }
    
    public void draw(SpriteBatch batch) {
        batch.draw(sprites[direction.ordinal()], position.x, position.y);
    }

}

