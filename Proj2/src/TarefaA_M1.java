import java.util.*;
import java.io.*;

public class TarefaA_M1 {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int tam_array = in.nextInt();
        int[] array = new int[tam_array];
        for (int a=0; a<tam_array; a++) {
            array[a] = in.nextInt();
        }

        Arrays.sort(array);
        int soma_max = array[array.length-1]+array[array.length-2];
        System.out.println(soma_max);
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
}
