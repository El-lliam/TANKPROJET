package com.mygdx.game;
import java.util.List;

public class MazeConfig {
    private BlockSize blockSize;
    private List<List<String>> maze;

    // ����һ���µ��ڲ�������ʾ��Ĵ�С
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
}
