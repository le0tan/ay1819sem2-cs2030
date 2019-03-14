class Face {
    int[][] grid;

    public Face(int[][] grid) {
        this.grid = new int[3][3];
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public Face rotateRight() {
        int[][] temp = new int[3][3];
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                temp[j][2-i] = this.getGrid()[i][j];
            }
        }
        return new Face(temp);
    }

    public Face rotateLeft() {
        int[][] temp = new int[3][3];
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                temp[2-j][i] = this.getGrid()[i][j];
            }
        }
        return new Face(temp);
    }

    public Face rotateHalf() {
        int[][] temp = new int[3][3];
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                temp[2-i][2-j] = this.getGrid()[i][j];
            }
        }
        return new Face(temp);
    }

    public int[][] getGrid() {
        int[][] t = new int[3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                t[i][j] = this.grid[i][j];
        return t;
    }

    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                res += String.format("%02d", this.grid[i][j]);
            }
            res += "\n";
        }
        return res;
    }
}
