import java.util.*;
import java.io.*;

public class SubProjeto_3_3 {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> comandos = receberDados();

        Node arvore = null;
        for (ArrayList<String> comando : comandos) {
            switch (comando.get(0)) {
                case "CLIENTE":
                    Cliente cliente = new Cliente(comando.get(1), comando.get(2) + " " + comando.get(3), Integer.parseInt(comando.get(4)));
                    arvore = SplayTree.cliente(arvore, cliente);
                    break;
                case "AQUISICAO":
                    arvore = SplayTree.splay(arvore, comando.get(1));
                    if (arvore == null || !comando.get(1).equals(arvore.getValor().getNome()))
                        System.out.println("CLIENTE NAO REGISTADO");
                    else {
                        arvore.getValor().setVolumeCompras(arvore.getValor().getVolumeCompras() + Integer.parseInt(comando.get(2)));
                        System.out.println("AQUISICAO INSERIDA");
                    }
                    break;
                case "CONSULTA":
                    arvore = SplayTree.splay(arvore, comando.get(1));
                    if (arvore == null || !comando.get(1).equals(arvore.getValor().getNome()))
                        System.out.println("CLIENTE NAO REGISTADO");
                    else
                        System.out.println(arvore.getValor().getNome() + " " + arvore.getValor().getMorada() + " " + arvore.getValor().getVolumeCompras() + "\nFIM");
                    break;
                case "LISTAGEM":
                    SplayTree.listagem(arvore);
                    System.out.println("FIM");
                    break;
                case "APAGA":
                    arvore = null;
                    System.out.println("LISTAGEM DE CLIENTES APAGADA");
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


    public static class Cliente {
        private String nome;
        private String morada;
        private int volumeCompras;

        public Cliente(String nome, String morada, int volumeCompras) {
            this.nome = nome;
            this.morada = morada;
            this.volumeCompras = volumeCompras;
        }

        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMorada() {
            return morada;
        }
        public void setMorada(String morada) {
            this.morada = morada;
        }

        public int getVolumeCompras() {
            return volumeCompras;
        }
        public void setVolumeCompras(int volumeCompras) {
            this.volumeCompras = volumeCompras;
        }
    }

    public static class Node {
        private Cliente valor;
        private Node esquerda, direita;

        public Node() {}

        public Node(Cliente valor) {
            this.valor = valor;
            this.direita = null;
            this.esquerda = null;
        }

        public Cliente getValor() {
            return valor;
        }
        public void setValor(Cliente valor) {
            this.valor = valor;
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

    static class SplayTree {
        public static Node rotacaoDireita(Node x) {
            Node y = x.getEsquerda();
            x.setEsquerda(y.getDireita());
            y.setDireita(x);
            return y;
        }

        public static Node rotacaoEsquerda(Node x) {
            Node y = x.getDireita();
            x.setDireita(y.getEsquerda());
            y.setEsquerda(x);
            return y;
        }

        public static Node splay(Node raiz, String nome) {
            if (raiz == null || raiz.getValor().getNome().equals(nome))
                return raiz;

            if (raiz.getValor().getNome().compareTo(nome) > 0) {
                if (raiz.getEsquerda() == null)
                    return raiz;
                if (raiz.getEsquerda().getValor().getNome().compareTo(nome) > 0) {
                    raiz.getEsquerda().setEsquerda(splay(raiz.getEsquerda().getEsquerda(), nome));
                    raiz = rotacaoDireita(raiz);
                }
                else if (raiz.getEsquerda().getValor().getNome().compareTo(nome) < 0) {
                    raiz.getEsquerda().setDireita(splay(raiz.getEsquerda().getDireita(), nome));

                    if (raiz.getEsquerda().getDireita() != null)
                        raiz.setEsquerda(rotacaoEsquerda(raiz.getEsquerda()));
                }

                if (raiz.getEsquerda() == null)
                    return raiz;
                else
                    return rotacaoDireita(raiz);
            }
            else {
                if (raiz.getDireita() == null)
                    return raiz;
                if (raiz.getDireita().getValor().getNome().compareTo(nome) > 0) {
                    raiz.getDireita().setEsquerda(splay(raiz.getDireita().getEsquerda(), nome));

                    if (raiz.getDireita().getEsquerda() != null)
                        raiz.setDireita(rotacaoDireita(raiz.getDireita()));
                }
                else if (raiz.getDireita().getValor().getNome().compareTo(nome) < 0) {
                    raiz.getDireita().setDireita(splay(raiz.getDireita().getDireita(), nome));
                    raiz = rotacaoEsquerda(raiz);
                }

                if (raiz.getDireita() == null)
                    return raiz;
                else
                    return rotacaoEsquerda(raiz);
            }
        }

        public static Node cliente(Node n, Cliente valor) {
            if (n == null) {
                System.out.println("NOVO CLIENTE INSERIDO");
                return (new Node(valor));
            }

            if (n.getValor().getNome().compareTo(valor.getNome()) > 0)
                n.setEsquerda(cliente(n.getEsquerda(), valor));
            else if (n.getValor().getNome().compareTo(valor.getNome()) < 0)
                n.setDireita(cliente(n.getDireita(), valor));
            else {
                System.out.println("CLIENTE JA EXISTENTE");
            }

            return splay(n, valor.getNome());
        }

        public static void listagem(Node n) {
            if (n == null) {
                return;
            }

            listagem(n.getEsquerda());

            System.out.println(n.getValor().getNome() + " " + n.getValor().getMorada() + " " + n.getValor().getVolumeCompras());

            listagem(n.getDireita());
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
