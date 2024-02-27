package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MazeConfig;
import java.util.Iterator;

import ElementDeJeu.EnnemiTank;
import ElementDeJeu.Explosion;
import ElementDeJeu.balle;
import constante.TextureFactory;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch _batch;
    private Rectangle _glviewport;
    private OrthographicCamera _camera;
    private MazeConfig mazeConfig;
    
    private Sprite[] tankSprites;
    private Vector2 tankPosition;
    private int currentSpriteIndex = 0;
    private float stateTime = 0;
    private List<balle> bullets = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    public enum Direction {UP, DOWN, LEFT, RIGHT}//坦克的方向
    private Direction tankDirection = Direction.UP; // 默认方向
    private List<EnnemiTank> ennemiTanks = new ArrayList<>();
    
    
    @Override
    public void create() {
        _batch = new SpriteBatch();
        set_glviewport(new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        _camera = new OrthographicCamera(1680, 1680);
        _camera.setToOrtho(false, 1680, 1680);
        _camera.update();
        mazeConfig = MazeConfig.loadConfig("maze_config.json");
        tankSprites = TextureFactory.getInstance().getMonChar(); // 加载坦克纹理
        tankPosition = new Vector2(82, 82); // 假设坦克的初始位置
        ennemiTanks.add(new EnnemiTank(100, 800, this));
        ennemiTanks.add(new EnnemiTank(600, 600, this));
        
    }
    
   
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _camera.update();
        _batch.setProjectionMatrix(_camera.combined);
        // 坦克的移动
        handleInput();

        _batch.begin();
        renderMaze();
        // 坦克运动
        _batch.draw(tankSprites[currentSpriteIndex], tankPosition.x, tankPosition.y);
        Iterator<balle> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            balle bullet = bulletIterator.next();
            bullet.update(Gdx.graphics.getDeltaTime());
            
            int gridX = (int)(bullet.getX() / 82);
            int gridY = (int)(bullet.getY() / 82);
            
            if (gridX >= 0 && gridX < mazeConfig.getMaze().get(0).size() && gridY >= 0 && gridY < mazeConfig.getMaze().size()) {
                String cellType = mazeConfig.getMaze().get(gridY).get(gridX);
                if ("B".equals(cellType)) {
                    mazeConfig.getMaze().get(gridY).set(gridX, "S");
                   // 检查是否已存在于该位置的爆炸效果
                    boolean explosionExists = false;
                    for (Explosion explosion : explosions) {
                        int explosionX = (int)(explosion.x / 82);
                        int explosionY = (int)(explosion.y / 82);
                        if (explosionX == gridX && explosionY == gridY) {
                            explosionExists = true;
                            break;
                        }
                    }
                    // 仅当不存在活跃的爆炸效果时才添加新的爆炸
                    if (!explosionExists) {
                        Sprite[] explosionFrames = TextureFactory.getInstance().getExplode();
                        Explosion explosion = new Explosion(explosionFrames, gridX * 82, gridY * 82);
                        explosions.add(explosion);
                    }
                    bulletIterator.remove();
                }
                
                if ("W".equals(cellType)||"V".equals(cellType)) {
                	boolean explosionExists = false;
                    for (Explosion explosion : explosions) {
                        int explosionX = (int)(explosion.x / 82);
                        int explosionY = (int)(explosion.y / 82);
                        if (explosionX == gridX && explosionY == gridY) {
                            explosionExists = true;
                            break;
                        }
                    }
                    // 仅当不存在活跃的爆炸效果时才添加新的爆炸
                    if (!explosionExists) {
                        Sprite[] explosionFrames = TextureFactory.getInstance().getExplode();
                        Explosion explosion = new Explosion(explosionFrames, gridX * 82, gridY * 82);
                        explosions.add(explosion);
                    }
                    bulletIterator.remove();
                }
            } else {
                bulletIterator.remove(); // 如果子弹超出迷宫范围，则移除
            }

            // 绘制子弹
            if (bullets.contains(bullet)) {
                bullet.draw(_batch);
            }
            
            
        }
            Iterator<Explosion> explosionIterator = explosions.iterator();
            while (explosionIterator.hasNext()) {
                Explosion explosion = explosionIterator.next();
                if (explosion.update(Gdx.graphics.getDeltaTime())) {
                    explosionIterator.remove(); // 动画播放完毕，移除爆炸效果
                } else {
                    explosion.draw(_batch); // 绘制当前帧的爆炸效果
                }
            }
            
            for (EnnemiTank ennemiTank : ennemiTanks) {
                ennemiTank.update(Gdx.graphics.getDeltaTime());
                ennemiTank.draw(_batch);
            }
            
            
            
        _batch.end();

        // 坦克运动的逻辑
        if (stateTime > 0.1) { // 每0.1秒更新一次纹理
            currentSpriteIndex = (currentSpriteIndex + 1) % tankSprites.length;
            stateTime = 0;
        }
        
    }

	private void renderMaze() {
        List<List<String>> maze = mazeConfig.getMaze();
        for (int y = 0; y < maze.size(); y++) {
            for (int x = 0; x < maze.get(y).size(); x++) {
                String cellType = maze.get(y).get(x);
                TextureRegion texture = getTextureForCellType(cellType);
                if (texture != null) {
                    _batch.draw(texture, x * 82, y * 82, 82, 82);
                }
            }
        }
    }
//初始化地图
    private TextureRegion getTextureForCellType(String cellType) {
        TextureFactory factory = TextureFactory.getInstance();
        switch (cellType) {
            case "S":
                return factory.getSol();
            case "B":
                return factory.getMurMiddleHor();
            case "V":
                return factory.getMurVect();
            case "W":
                return factory.getMurHor();
            case "T":
                return factory.getPlant();
            default:
                return null; // 或者返回一个默认纹理
        }
    }
    
    private void handleInput() {
        float moveSpeed = 400 * Gdx.graphics.getDeltaTime();
        // 预设下一个位置为当前位置
        float nextX = tankPosition.x;
        float nextY = tankPosition.y;

        // 检查上
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP) || Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W)) {
            nextY += moveSpeed;
            tankDirection=Direction.UP;
        }
        // 检查下
        else if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN) || Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.S)) {
            nextY -= moveSpeed;
            tankDirection=Direction.DOWN;
        }
        // 检查左
        else if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT) || Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) {
            nextX -= moveSpeed;
            tankDirection=Direction.LEFT;
        }
        // 检查右
        else if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT) || Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) {
            nextX += moveSpeed;
            tankDirection=Direction.RIGHT;
        }

        // 只有当新位置不会导致碰撞时，才更新坦克位置
        if (!isCollision(nextX, nextY)) {
            tankPosition.x = nextX;
            tankPosition.y = nextY;
            stateTime += Gdx.graphics.getDeltaTime(); // 坦克移动了，更新动画状态时间
        } else {
            stateTime = 0; // 发生碰撞或坦克未移动，重置动画状态时间
        }
        
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
            TextureRegion bulletTexture = null;
            float speedX = 0;
            float speedY = 0;
            // 子弹的初始偏移量
            float offsetX =41, offsetY =41;

            switch (tankDirection) {
                case UP:
                    bulletTexture = TextureFactory.getInstance().getBulletUP();
                    speedY = 300;
                    offsetX = tankPosition.x + (tankSprites[currentSpriteIndex].getWidth() - bulletTexture.getRegionWidth()) / 2; // 居中对齐
                    offsetY = tankPosition.y + tankSprites[currentSpriteIndex].getHeight(); // 从炮管末端发射
                    break;
                case DOWN:
                    bulletTexture = TextureFactory.getInstance().getBulletDOWN();
                    speedY = -300;
                    offsetX = tankPosition.x + (tankSprites[currentSpriteIndex].getWidth() - bulletTexture.getRegionWidth()) / 2; // 居中对齐
                    offsetY = tankPosition.y; // 从炮管末端发射
                    break;
                case LEFT:
                    bulletTexture = TextureFactory.getInstance().getBulletLEFT();
                    speedX = -300;
                    offsetX = tankPosition.x; // 从炮管末端发射
                    offsetY = tankPosition.y + (tankSprites[currentSpriteIndex].getHeight() - bulletTexture.getRegionHeight()) / 2; // 居中对齐
                    break;
                case RIGHT:
                    bulletTexture = TextureFactory.getInstance().getBulletRIGHT();
                    speedX = 300;
                    offsetX = tankPosition.x + tankSprites[currentSpriteIndex].getWidth(); // 从炮管末端发射
                    offsetY = tankPosition.y + (tankSprites[currentSpriteIndex].getHeight() - bulletTexture.getRegionHeight()) / 2; // 居中对齐
                    break;
            }

            balle bullet = new balle(offsetX, offsetY, speedX, speedY, bulletTexture);
            bullets.add(bullet);
        }


    }


    public boolean isCollision(float nextX, float nextY) {
        List<List<String>> maze = mazeConfig.getMaze();
        // 坦克的尺寸
        int tankSize = 82;

        // 计算坦克的四个角落对应的迷宫单元格
        int topLeftX = (int) (nextX / tankSize);
        int topLeftY = (int) (nextY / tankSize);

        int topRightX = (int) ((nextX + tankSize - 5) / tankSize);
        int topRightY = topLeftY;

        int bottomLeftX = topLeftX;
        int bottomLeftY = (int) ((nextY + tankSize - 5) / tankSize);

        int bottomRightX = topRightX;
        int bottomRightY = bottomLeftY;

        // 检查坦克的四个角是否进入了不可通过的单元格
        return isBlockedCell(maze, topLeftX, topLeftY) || isBlockedCell(maze, topRightX, topRightY) ||
               isBlockedCell(maze, bottomLeftX, bottomLeftY) || isBlockedCell(maze, bottomRightX, bottomRightY);
    }

    private boolean isBlockedCell(List<List<String>> maze, int x, int y) {
        // 检查是否超出迷宫边界
        if (x < 0 || x >= maze.get(0).size() || y < 0 || y >= maze.size()) {
            return true; // 超出边界
        }
        // 获取单元格类型
        String cellType = maze.get(y).get(x);
        // 判断单元格是否为不可通过类型
        return cellType.equals("W") || cellType.equals("V") || cellType.equals("B");
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

	public Direction getTankDirection() {
		return tankDirection;
	}

	public void setTankDirection(Direction tankDirection) {
		this.tankDirection = tankDirection;
	}


	public void addBullet(balle bullet) {
		// TODO Auto-generated method stub
		
	}
	
	
}
