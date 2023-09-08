import java.util.*;
import java.io.*;

public class SubProjeto_3_2 {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> comandos = receberDados();
        Node arvore = null;
        for (ArrayList<String> comando : comandos) {
            switch (comando.get(0)) {
                case "ACRESCENTA":
                    arvore = acrescenta(arvore, comando.get(1), comando.get(2), comando.get(3));
                    break;
                case "CONSULTA":
                    consulta(arvore, comando.get(1));
                    break;
                case "LISTAGEM":
                    listagem(arvore);
                    break;
                case "APAGA":
                    arvore = apaga();
                    break;
            }
        }
    }

    public static ArrayList<ArrayList<String>> receberDados() {
        InputReader ir = new InputReader(System.in);
        ArrayList<ArrayList<String>> linhas = new ArrayList<>();
        ArrayList<String> aux=ir.nextLine();
        while (!aux.get(0).equals("FIM")) {
            linhas.add(aux);
            aux=ir.nextLine();
        }
        return linhas;
    }

    private static Node acrescenta(Node arvore, String numUtente, String vacina, String dataLimite) {
        Vacina vac = new Vacina(vacina, Integer.parseInt(dataLimite));
        Utente utente = new Utente(Integer.parseInt(numUtente));
        utente.adicionaVacina(vac);
        return AVLTree.acrescentar(arvore, utente);
    }

    private static void consulta(Node arvore, String numUtente) {
        Utente utente = AVLTree.consultar(arvore, Integer.parseInt(numUtente));
        if (utente == null)
            System.out.println("NAO ENCONTRADO");
        else {
            String info = "" + utente.getNumUtente();
            ArrayList<Vacina> vacs = utente.getVacinas();
            vacs.sort(Comparator.comparing(Vacina::getVacina));
            for (Vacina vac: vacs) {
                info += " " + vac.getVacina() + " " + vac.getDataLimite();
            }
            System.out.println(info + "\nFIM");
        }
    }

    private static void listagem(Node arvore) {
        AVLTree.listar(arvore);
        System.out.println("FIM");
    }

    private static Node apaga() {
        System.out.println("LISTAGEM DE NOMES APAGADA");
        return null;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Utente {
        private int numUtente;
        private ArrayList<Vacina> vacinas;

        public Utente(int numUtente) {
            this.numUtente = numUtente;
            this.vacinas = new ArrayList<>();
        }

        public int getNumUtente() {
            return numUtente;
        }
        public void setNumUtente(int numUtente) {
            this.numUtente = numUtente;
        }

        public ArrayList<Vacina> getVacinas() {
            return vacinas;
        }
        public void setVacinas(ArrayList<Vacina> vacinas) {
            this.vacinas = vacinas;
        }

        public void adicionaVacina(Vacina vac) {
            this.vacinas.add(vac);
        }
    }

    public static class Vacina {
        private String vacina;
        private int dataLimite;

        public Vacina(String vacina, int dataLimite) {
            this.vacina = vacina;
            this.dataLimite = dataLimite;
        }

        public String getVacina() {
            return vacina;
        }
        public void setVacina(String vacina) {
            this.vacina = vacina;
        }

        public int getDataLimite() {
            return dataLimite;
        }
        public void setDataLimite(int dataLimite) {
            this.dataLimite = dataLimite;
        }
    }

    public static class Node {
        private Utente valor;
        private int altura;
        private Node esquerda, direita;

        public Node() {}

        public Node(Utente valor) {
            this.valor = valor;
            this.altura = 1;
        }

        public Utente getValor() {
            return valor;
        }
        public void setValor(Utente valor) {
            this.valor = valor;
        }

        public int getAltura() {
            return altura;
        }
        public void setAltura(int altura) {
            this.altura = altura;
        }

        public Node getEsquerda() {
            return esquerda;
        }
        public void setEsquerda(Node esquerda) {
            this.esquerda = esquerda;
        }

        public Node getDireita() {
            return direita;
        }
        public void setDireita(Node direita) {
            this.direita = direita;
        }
    }

    public static class AVLTree {
        public static int altura(Node n) {
            if(n == null)
                return 0;
            return n.getAltura();
        }

        public static void atualizarAltura(Node n) {
            n.setAltura(Math.max(altura(n.getEsquerda()), altura(n.getDireita()))+1);
        }

        public static int fatorEquilibrio(Node n) {
            if (n == null)
                return 0;
            return altura(n.getDireita()) - altura(n.getEsquerda());
        }

        public static Node rotacaoDireita(Node y) {
            Node x = y.getEsquerda();
            Node sT = x.getDireita();
            x.setDireita(y);
            y.setEsquerda(sT);

            atualizarAltura(y);
            atualizarAltura(x);

            return x;
        }

        public static Node rotacaoEsquerda(Node x) {
            Node y = x.getDireita();
            Node sT = y.getEsquerda();
            y.setEsquerda(x);
            x.setDireita(sT);

            atualizarAltura(x);
            atualizarAltura(y);

            return y;
        }

        public static Node equilibrar(Node z) {
            atualizarAltura(z);
            int fE = fatorEquilibrio(z);
            if (fE > 1) {
                // DD
                if (altura(z.getDireita().getDireita()) > altura(z.getDireita().getEsquerda())) {
                    z = rotacaoEsquerda(z);
                }
                // DE
                else {
                    z.setDireita(rotacaoDireita(z.getDireita()));
                    z = rotacaoEsquerda(z);
                }
            }
            else if (fE < -1) {
                // EE
                if (altura(z.getEsquerda().getEsquerda()) > altura(z.getEsquerda().getDireita())) {
                    z = rotacaoDireita(z);
                }
                // ED
                else {
                    z.setEsquerda(rotacaoEsquerda(z.getEsquerda()));
                    z = rotacaoDireita(z);
                }
            }

            return z;
        }

        public static Node acrescentar(Node n, Utente valor) {
            if (n == null) {
                System.out.println("NOVO UTENTE CRIADO");
                return (new Node(valor));
            }

            if (valor.getNumUtente() < n.getValor().getNumUtente())
                n.setEsquerda(acrescentar(n.getEsquerda(), valor));
            else if (valor.getNumUtente() > n.getValor().getNumUtente())
                n.setDireita(acrescentar(n.getDireita(), valor));
            else {
                int aux = 0;
                for (Vacina vac: n.getValor().getVacinas()) {
                    if (vac.getVacina().equals(valor.getVacinas().get(0).getVacina())) {
                        vac.setDataLimite(valor.getVacinas().get(0).getDataLimite());
                        aux = 1;
                        System.out.println("VACINA ATUALIZADA");
                    }
                }
                if (aux == 0) {
                    n.getValor().adicionaVacina(valor.getVacinas().get(0));
                    System.out.println("NOVA VACINA INSERIDA");
                }
            }

            return equilibrar(n);
        }

        public static Utente consultar(Node n, int numUtente) {
            if (n == null)
                return null;
            else if (numUtente == n.getValor().getNumUtente())
                return n.getValor();
            else if (numUtente < n.getValor().getNumUtente())
                return consultar(n.getEsquerda(), numUtente);
            else if (numUtente > n.getValor().getNumUtente())
                return consultar(n.getDireita(), numUtente);
            return null;
        }

        public static void listar(Node n) {
            if (n == null) {
                return;
            }
            listar(n.getEsquerda());

            String info = "" + n.getValor().getNumUtente();
            ArrayList<Vacina> vacs = n.getValor().getVacinas();
            vacs.sort(Comparator.comparing(Vacina::getVacina));
            for (Vacina vac : vacs) {
                info += " " + vac.getVacina() + " " + vac.getDataLimite();
            }
            System.out.println(info);

            listar(n.getDireita());
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public ArrayList<String> nextLine() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            ArrayList<String> line = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                line.add(tokenizer.nextToken());
            }
            return line;
        }
    }
}
