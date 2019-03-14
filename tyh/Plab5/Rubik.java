class Rubik {
    Face[] faces;

    public Rubik(int[][][] grid) {
        this.faces = new Face[6];
        for(int i=0;i<6;i++) {
            this.faces[i] = new Face(grid[i]);
        }
    }

    public Rubik viewRight() {
        Face[] t = new Face[6];
        t[0] = this.faces[0].rotateRight();
        t[5] = this.faces[1].rotateHalf();
        t[1] = this.faces[2];
        t[2] = this.faces[3];
        t[4] = this.faces[4].rotateLeft();
        t[3] = this.faces[5].rotateHalf();
        int[][][] temp = new int[6][3][3];
        for(int i=0;i<6;i++) {
            for(int j=0;j<3;j++) {
                for(int k=0;k<3;k++) {
                    temp[i][j][k] = t[i].getGrid()[j][k];
                }
            }
        }
        return new Rubik(temp);
    }

    // 0,2,3,5,4,1
    public Rubik viewLeft() {
        return this.viewRight().viewRight().viewRight();
    }

    public Rubik viewUp() {
        Face[] t = new Face[6];
        t[0] = this.faces[5];
        t[1] = this.faces[1].rotateRight();
        t[2] = this.faces[0];
        t[3] = this.faces[3].rotateLeft();
        t[4] = this.faces[2];
        t[5] = this.faces[4];
        int[][][] temp = new int[6][3][3];
        for(int i=0;i<6;i++) {
            for(int j=0;j<3;j++) {
                for(int k=0;k<3;k++) {
                    temp[i][j][k] = t[i].getGrid()[j][k];
                }
            }
        }
        return new Rubik(temp);
    }

    public Rubik viewDown() {
        return this.viewUp().viewUp().viewUp();
    }

    @Override
    public String toString() {
        String res = "";
        final String dots = "......";
        for(int i=0;i<3;i++) {
            res += dots;
            for(int j=0;j<3;j++) {
                res += String.format("%02d", this.faces[0].getGrid()[i][j]);
            }
            res += dots + "\n";
        }
        for(int i=0;i<3;i++) {
            for(int j=1;j<=3;j++) {
                for(int k=0;k<3;k++) {
                    res += String.format("%02d", this.faces[j].getGrid()[i][k]);
                }
            }
            res += "\n";
        }
        for(int k=4;k<6;k++) {
            for(int i=0;i<3;i++) {
                res += dots;
                for(int j=0;j<3;j++) {
                    res += String.format("%02d", this.faces[k].getGrid()[i][j]);
                }
                res += dots + "\n";
            }
        }
        return res;
    }

    public Rubik frontfaceLeft() {
        Face frontTurned = this.faces[2].rotateLeft();
        int[][][] t = new int[6][3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                t[2][i][j] = frontTurned.getGrid()[i][j];
        for(int k=0;k<6;k++)
            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    if(k!=2) t[k][i][j] = this.faces[k].getGrid()[i][j];
        for(int i=0;i<3;i++) {
            t[0][2][i] = this.faces[3].getGrid()[i][0];
            t[1][i][2] = this.faces[0].getGrid()[2][2-i];
            t[3][i][0] = this.faces[4].getGrid()[0][2-i];
            t[4][0][i] = this.faces[1].getGrid()[i][2];
        }
        return new Rubik(t);
    }

    public Rubik frontfaceRight() {
        return this.frontfaceLeft().frontfaceLeft().frontfaceLeft();
    }

    public Rubik frontfaceHalf() {
        return this.frontfaceLeft().frontfaceLeft();
    }

    public Rubik rightfaceRight() {
        return this.viewRight().frontfaceRight().viewLeft();
    }

    public Rubik rightfaceLeft() {
        return this.viewRight().frontfaceLeft().viewLeft();
    }

    public Rubik rightfaceHalf() {
        return this.viewRight().frontfaceHalf().viewLeft();
    }

    public Rubik leftfaceRight() {
        return this.viewLeft().frontfaceRight().viewRight();
    }

    public Rubik leftfaceLeft() {
        return this.viewLeft().frontfaceLeft().viewRight();
    }

    public Rubik leftfaceHalf() {
        return this.viewLeft().frontfaceHalf().viewRight();
    }

    public Rubik upfaceRight() {
        return this.viewUp().frontfaceRight().viewDown();
    }

    public Rubik upfaceLeft() {
        return this.viewUp().frontfaceLeft().viewDown();
    }

    public Rubik upfaceHalf() {
        return this.viewUp().frontfaceHalf().viewDown();
    }

    public Rubik downfaceRight() {
        return this.viewDown().frontfaceRight().viewUp();
    }

    public Rubik downfaceLeft() {
        return this.viewDown().frontfaceLeft().viewUp();
    }

    public Rubik downfaceHalf() {
        return this.viewDown().frontfaceHalf().viewUp();
    }

    public Rubik backfaceRight() {
        return this.viewDown().viewDown().frontfaceRight().viewUp().viewUp();
    }

    public Rubik backfaceLeft() {
        return this.viewDown().viewDown().frontfaceLeft().viewUp().viewUp();
    }

    public Rubik backfaceHalf() {
        return this.viewDown().viewDown().frontfaceHalf().viewUp().viewUp();
    }
}
