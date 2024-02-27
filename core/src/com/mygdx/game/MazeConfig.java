package com.mygdx.game;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class MazeConfig {
    private BlockSize blockSize;
    private List<List<String>> maze;

    // 这是一个新的内部类来表示块的大小
    public static class BlockSize {
        private float width;
        private float height;

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }
    }

    public BlockSize getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(BlockSize blockSize) {
        this.blockSize = blockSize;
    }

    public List<List<String>> getMaze() {
        return maze;
    }

    public void setMaze(List<List<String>> maze) {
        this.maze = maze;
    }
    
    public static MazeConfig loadConfig(String configFile) {
        Json json = new Json();
        return json.fromJson(MazeConfig.class, Gdx.files.internal(configFile));
    }
    
}
