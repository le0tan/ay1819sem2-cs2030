import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][][] grid = new int[6][3][3];
        for (int k = 0; k < 6; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    grid[k][i][j] = sc.nextInt();
                }
            }
        }
        Rubik rb = new Rubik(grid);
        while (sc.hasNextLine()) {
            String[] cmds = sc.nextLine().split(" ");
            for (String cmd : cmds) {
                switch (cmd) {
                case "F":
                    rb = rb.frontfaceRight();
                    break;
                case "F'":
                    rb = rb.frontfaceLeft();
                    break;
                case "F2":
                    rb = rb.frontfaceHalf();
                    break;
                case "R":
                    rb = rb.rightfaceRight();
                    break;
                case "R'":
                    rb = rb.rightfaceLeft();
                    break;
                case "R2":
                    rb = rb.rightfaceHalf();
                    break;
                case "L":
                    rb = rb.leftfaceRight();
                    break;
                case "L'":
                    rb = rb.leftfaceLeft();
                    break;
                case "L2":
                    rb = rb.leftfaceHalf();
                    break;
                case "U":
                    rb = rb.upfaceRight();
                    break;
                case "U'":
                    rb = rb.upfaceLeft();
                    break;
                case "U2":
                    rb = rb.upfaceHalf();
                    break;
                case "D":
                    rb = rb.downfaceRight();
                    break;
                case "D'":
                    rb = rb.downfaceLeft();
                    break;
                case "D2":
                    rb = rb.downfaceHalf();
                    break;
                case "B":
                    rb = rb.backfaceRight();
                    break;
                case "B'":
                    rb = rb.backfaceLeft();
                    break;
                case "B2":
                    rb = rb.backfaceHalf();
                    break;
                default:
                    break;
                }
            }
        }
        System.out.println(rb);
    }
}