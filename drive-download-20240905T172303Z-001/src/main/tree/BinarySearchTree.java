package tree;

import estrut.Tree;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements Tree {
    private Node root;

    // Classe interna Node representando um nó da árvore
    private static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Método público para buscar um elemento na árvore
    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRecursivo(root, valor);
    }

    // Método privado para busca recursiva de um elemento
    private boolean buscaElementoRecursivo(Node node, int valor) {
        if (node == null) {
            return false; // Valor não encontrado
        }
        if (valor < node.value) {
            return buscaElementoRecursivo(node.left, valor);
        } else if (valor > node.value) {
            return buscaElementoRecursivo(node.right, valor);
        } else {
            return true; // Valor encontrado
        }
    }

    // Método público para encontrar o valor mínimo
    @Override
    public int minimo() {
        if (root == null) {
            throw new IllegalStateException("A árvore está vazia");
        }
        return findMin(root).value;
    }

    // Método privado para encontrar o nó com o valor mínimo
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Método público para encontrar o valor máximo
    @Override
    public int maximo() {
        if (root == null) {
            throw new IllegalStateException("A árvore está vazia");
        }
        return findMax(root).value;
    }

    // Método privado para encontrar o nó com o valor máximo
    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // Método público para inserir um novo elemento na árvore
    @Override
    public void insereElemento(int valor) {
        root = insereElementoRecursivo(root, valor);
    }

    // Método privado recursivo para inserir um elemento
    private Node insereElementoRecursivo(Node node, int valor) {
        if (node == null) {
            return new Node(valor); // Insere novo nó na posição correta
        }
        if (valor < node.value) {
            node.left = insereElementoRecursivo(node.left, valor);
        } else if (valor > node.value) {
            node.right = insereElementoRecursivo(node.right, valor);
        }
        return node;
    }

    // Método público para remover um elemento da árvore
    @Override
    public void remove(int valor) {
        root = removeRecursivo(root, valor);
    }

    // Método privado recursivo para remover um elemento
    private Node removeRecursivo(Node node, int valor) {
        if (node == null) {
            return null;
        }

        if (valor < node.value) {
            node.left = removeRecursivo(node.left, valor);
        } else if (valor > node.value) {
            node.right = removeRecursivo(node.right, valor);
        } else {
            // Caso o nó tenha um ou nenhum filho
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Nó com dois filhos: pega o sucessor in-order (menor na subárvore direita)
            Node temp = findMin(node.right);
            node.value = temp.value;
            node.right = removeRecursivo(node.right, temp.value);
        }
        return node;
    }

    // Método público para realizar a travessia pré-ordem
    @Override
    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        preOrdemRecursivo(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    // Método privado recursivo para a travessia pré-ordem
    private void preOrdemRecursivo(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrdemRecursivo(node.left, result);
            preOrdemRecursivo(node.right, result);
        }
    }

    // Método público para realizar a travessia em ordem
    @Override
    public int[] emOrdem() {
        List<Integer> result = new ArrayList<>();
        emOrdemRecursivo(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    // Método privado recursivo para a travessia em ordem
    private void emOrdemRecursivo(Node node, List<Integer> result) {
        if (node != null) {
            emOrdemRecursivo(node.left, result);
            result.add(node.value);
            emOrdemRecursivo(node.right, result);
        }
    }

    // Método público para realizar a travessia pós-ordem
    @Override
    public int[] posOrdem() {
        List<Integer> result = new ArrayList<>();
        posOrdemRecursivo(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    // Método privado recursivo para a travessia pós-ordem
    private void posOrdemRecursivo(Node node, List<Integer> result) {
        if (node != null) {
            posOrdemRecursivo(node.left, result);
            posOrdemRecursivo(node.right, result);
            result.add(node.value);
        }
    }
}
