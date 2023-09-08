import java.util.*;
import java.io.*;

public class SubProjeto_3_1 {
    public static void main(String[] args) {
        ArrayList<Long> array = receberDados();

        ArrayList<ArrayList<Long>> merkleTree;
        merkleTree = construirMerkleTree(array);

        imprimirArvore(merkleTree);
    }

    public static long hashcode(long x) {
        return x % 1000000007;
    }

    public static long hashcode(long x, long y) {
        int mod = 1000000007;
        return ((x % mod) + (y % mod)) % mod;
    }

    public static ArrayList<Long> receberDados() {
        InputReader ir = new InputReader(System.in);
        int numTransacoes = ir.nextInt();
        ArrayList<Long> arrayTransacoes = new ArrayList<>();
        for (int a=0; a<numTransacoes; a++) {
            arrayTransacoes.add(ir.nextLong());
        }
        return arrayTransacoes;
    }

    public static ArrayList<ArrayList<Long>> construirMerkleTree(ArrayList<Long> array) {
        ArrayList<ArrayList<Long>> merkleTree = new ArrayList<>();
        int i, j;
        for (i=0; i<array.size(); i++) {
            array.set(i, hashcode(array.get(i)));
        }
        merkleTree.add(array);
        while (merkleTree.get(0).size() != 1) {
            ArrayList<Long> arrayHashcodes = new ArrayList<>(merkleTree.get(0));
            j=0;
            for (i=0; i<merkleTree.get(0).size()/2; i++) {
                arrayHashcodes.add(hashcode(arrayHashcodes.get(j), arrayHashcodes.get(j+1)));
                arrayHashcodes.remove(j);
                arrayHashcodes.remove(j);
            }
            merkleTree.add(0, arrayHashcodes);
        }
        return merkleTree;
    }

    public static void imprimirArvore(ArrayList<ArrayList<Long>> merkleTree) {
        int a, b;
        for (a=0; a<merkleTree.size(); a++) {
            for (b=0; b<merkleTree.get(a).size(); b++) {
                System.out.println(merkleTree.get(a).get(b));
            }
        }
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

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
