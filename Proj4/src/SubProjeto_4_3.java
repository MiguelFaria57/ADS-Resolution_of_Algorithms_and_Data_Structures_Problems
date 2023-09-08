import java.util.*;
import java.io.*;

public class SubProjeto_4_3 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = receberDados();

        RS(numeros);

        for (Integer numero : numeros) {
            System.out.println(numero);
        }
    }

    public static void RS(ArrayList<Integer> numeros) {
        int maior_num = numeros.get(0);
        for (Integer num: numeros) {
            if (num > maior_num)
                maior_num = num;
        }
        int num_digitos = 0;
        if (maior_num != 0) {
            while (maior_num != 0) {
                maior_num /= 10;
                num_digitos++;
            }
        }
        else {
            return;
        }

        int i,j,l;
        int temp, digito = 0;
        for (i=1; i<=num_digitos; i++) {
            ArrayList<ArrayList<Integer>> algarismos = new ArrayList<>();
            for (j=0; j<10; j++) {
                ArrayList<Integer> arrayAux = new ArrayList<>();
                algarismos.add(arrayAux);
            }

            for (Integer num : numeros) {
                temp = num;

                for(j=0; j<i; j++) {
                    digito = temp % 10;
                    temp = temp / 10;
                }

                algarismos.get(digito).add(num);
            }

            temp = 0;
            for (j=0; j<algarismos.size(); j++){
                for (l=0; l<algarismos.get(j).size(); l++) {
                    numeros.set(temp, algarismos.get(j).get(l));
                    temp++;
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
