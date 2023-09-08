import java.util.*;
import java.io.*;

public class SubProjeto_4_1_ISS4 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = receberDados();

        improvedShellSort4(numeros);

        for (Integer numero : numeros) {
            System.out.println(numero);
        }
    }

public static void improvedShellSort4(ArrayList<Integer> numeros) { // Ciura, 2001
        ArrayList<Integer> gaps = new ArrayList<>(Arrays.asList(1, 4, 9, 57, 132, 301, 701, 1750));
        if (gaps.get(gaps.size()-1) > numeros.size()){
            for (int a=0; a< gaps.size(); a++) {
                if (gaps.get(a) >= numeros.size()) {
                    gaps.remove(a);
                    a--;
                }
            }
        }
        else {
            int aux;
            while ((aux = (int) ((gaps.get(gaps.size()-1))*2.25)) < numeros.size()) {
                gaps.add(aux);
            }
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
