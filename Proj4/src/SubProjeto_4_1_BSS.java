import java.util.*;
import java.io.*;

public class SubProjeto_4_1 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = receberDados();
        //System.out.println(numeros);

        shellSortv1(numeros);

        for (Integer numero : numeros) {
            System.out.println(numero);
        }
    }

    public static void shellSortv1(ArrayList<Integer> numeros) {
        int gap = (int) Math.pow(2, (int) (Math.log(numeros.size())/Math.log(2)));
        //System.out.println(gap);

        for (; gap>0; gap/=2) {
            //System.out.println(gap);
            int i;
            for (i=gap; i<numeros.size(); i++) {
                int aux = numeros.get(i);

                int j;
                for (j=i; j>=gap && numeros.get(j-gap)>aux; j-=gap) {
                    numeros.set(j, numeros.get(j-gap));

                    /*if (numeros.get(j-gap) > aux)
                        numeros.set(j, numeros.get(j - gap));
                    else
                        break;*/
                }

                numeros.set(j, aux);
            }
        }
    }

    public static void shellSortv2(ArrayList<Integer> numeros) {
        int gap = (int) Math.pow(2, (int) (Math.log(numeros.size())/Math.log(2)));
        //System.out.println(gap);

        for (int g=gap; g>0; g/=2) {
            //System.out.println(gap);
            for (int i=0; i<numeros.size()-g; i++) {
                if (numeros.get(i+g) < numeros.get(i)) {
                    int aux1 = numeros.get(i);
                    numeros.set(i, numeros.get(i+g));
                    numeros.set(i+g, aux1);

                    for (int j=i; j>=g; j-=g) {
                        if (numeros.get(j-g) > numeros.get(j)) {
                            int aux = numeros.get(j);
                            numeros.set(j, numeros.get(j-g));
                            numeros.set(j-g, aux);
                        }
                        else
                            break;
                    }
                }
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
