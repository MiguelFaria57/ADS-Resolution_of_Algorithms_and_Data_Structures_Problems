import java.util.*;
import java.io.*;

public class SubProjeto_4_2_QS_IS_15 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = receberDados();

        QS_IS(numeros, 0, numeros.size()-1, 15);

        for (Integer numero : numeros) {
            System.out.println(numero);
        }
    }


    public static void IS(ArrayList<Integer> numeros, int low, int high) {
        for (int i=low+1; i<high+1; i++) {
            int aux = numeros.get(i);

            int j;
            for (j=i; j>=1 && numeros.get(j-1)>aux; j--) {
                numeros.set(j, numeros.get(j-1));
            }
            numeros.set(j, aux);
        }
    }

    public static void QS_IS(ArrayList<Integer> numeros, int low, int high, int k) {
        int pivot = numeros.get(low+(high-low)/2);
        int i=low, j=high;

        while (i <= j) {
            while (numeros.get(i) < pivot)
                i++;
            while (numeros.get(j) > pivot)
                j--;

            if (i <= j) {
                trocar(numeros, i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            if (k < (j-low+1))
                QS_IS(numeros, low, j, k);
            else
                IS(numeros, low, j);
        }
        if (high > i) {
            if (k < (high-i+1))
                QS_IS(numeros, i, high, k);
            else
                IS(numeros, i, high);
        }
    }


    public static void trocar(ArrayList<Integer> array, int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
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
