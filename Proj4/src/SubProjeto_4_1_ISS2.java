import java.util.*;
import java.io.*;

public class SubProjeto_4_1_ISS2 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = receberDados();

        improvedShellSort2(numeros);

        for (Integer numero : numeros) {
            System.out.println(numero);
        }
    }

    public static void improvedShellSort2(ArrayList<Integer> numeros) {
        ArrayList<Integer> gaps = new ArrayList<>();
        gaps.add(1);
        int aux1;
        int aux2 = 1;
        while ((aux1 = (int) (Math.pow(4,aux2) + 3*Math.pow(2,aux2-1) + 1)) < numeros.size()) {
            gaps.add(aux1);
            aux2++;
        }
        gaps.sort(Collections.reverseOrder());

        for (int gap: gaps) {
            int i;
            for (i=gap; i<numeros.size(); i++) {
                int aux = numeros.get(i);

                int j;
                for (j=i; j>=gap && numeros.get(j-gap)>aux; j-=gap) {
                    numeros.set(j, numeros.get(j-gap));
                }

                numeros.set(j, aux);
            }
        }
    }

    public static ArrayList<Integer> receberDados() {
        InputReader ir = new InputReader(System.in);
        ArrayList<Integer> numeros = new ArrayList<>();
        int num = ir.nextInt();
        for (int i=0; i<num; i++) {
            numeros.add(ir.nextInt());
        }
        return numeros;
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
