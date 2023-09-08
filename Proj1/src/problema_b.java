import java.util.*;
import java.io.*;

public class problema_b {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int l = in.nextInt();
        int c = in.nextInt();

        int[][] m = new int[l][c];
        for (int i=0; i<l; i++) {
            for (int j=0; j<c; j++) {
                m[i][j] = in.nextInt();
            }
        }

        int[][] x1 = rodar90(l, c, m);
        int[][] x2 = rodar90(x1.length, x1[0].length, x1);
        int[][] x3 = rodar90(x2.length, x2[0].length, x2);

        /*for(int a1=0; a1<m.length; a1++) {
            for (int a11=0; a11<m[a1].length; a11++) {
                System.out.print(m[a1][a11] + " ");
            }
            System.out.println();
        }*/

        System.out.println("90");
        imprimeMatriz(x1);
        System.out.println("180");
        imprimeMatriz(x2);
        System.out.println("270");
        imprimeMatriz(x3);

        /*PrintWriter out = new PrintWriter(System.out);
        out.println();
        out.close();*/
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static int[][] rodar90(int l, int c, int [][] input) {
        int[][] output = new int [c][l];
        for (int i=0; i<l; i++)
            for (int j=0;j<c; j++)
                output[j][l-1-i] = input[i][j];
        return output;
    }

    public static void imprimeMatriz(int[][] m) {
        for(int a=0; a<m.length; a++) {
            for (int b=0; b<m[a].length; b++) {
                if (b == m[a].length -1)
                    System.out.print(m[a][b]);
                else
                    System.out.print(m[a][b] + " ");
            }
            System.out.println();
        }
    }
}
