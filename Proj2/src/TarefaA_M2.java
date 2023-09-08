import java.util.*;
import java.io.*;

public class TarefaA_M2 {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int tam_array = in.nextInt();
        int[] array = new int[tam_array];
        for (int a=0; a<tam_array; a++) {
            array[a] = in.nextInt();
        }

        int primeiro_num = -1;
        int segundo_num = -1;
        for (int i: array) {
            if (i>primeiro_num) {
                segundo_num = primeiro_num;
                primeiro_num = i;
            }
            else if (i<=primeiro_num && i>segundo_num)
                segundo_num = i;
        }
        int soma_max = primeiro_num + segundo_num;
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
