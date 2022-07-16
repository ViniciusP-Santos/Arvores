import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Arvore<Integer> arvore = new Arvore<Integer>();
        ArvoreBalanceada<Integer> arvoreAvl = new ArvoreBalanceada<Integer>();
        ArvoreRB arvoreRB = new ArvoreRB();

        int aux;
        int menu;

        while(true){
            System.out.println("Selecione a opção desejada------");
            System.out.println("\n1- Arvore Binaria\n2- Arvore AVL\n3- Arvore RB\n");
            menu = input.nextInt();

            switch (menu){
                case 1:
                    while (true){
                        System.out.println("----------- Arvore Binaria -----------");
                        System.out.println("----------- Digite uma Opção -----------");
                        System.out.println("\n1- Adicionar elemento a arvore\n2- Imprimir arvore\n3- Remover elemento da arvore.");
                        System.out.println("--------------------------------------");
                        aux = input.nextInt();

                        switch (aux){
                            case 1:
                                System.out.println("Digite o valor: ");
                                int element = input.nextInt();
                                arvore.adicionar(element);
                                break;
                            case 2:
                                arvore.emOrdem(arvore.getRaiz());
                                break;
                            case 3:
                                System.out.println("Digite o valor para ser removido: ");
                                int remover = input.nextInt();
                                arvore.remover(remover);
                                break;
                        }
                    }
                case 2:
                    while (true){
                        System.out.println("----------- Arvore AVL -----------");
                        System.out.println("----------- Digite uma Opção -----------");
                        System.out.println("\n1- Adicionar elemento a arvore\n2- Imprimir arvore\n3- Remover elemento da arvore.");
                        System.out.println("--------------------------------------");
                        aux = input.nextInt();

                        switch (aux){
                            case 1:
                                System.out.println("Digite o valor: ");
                                int element = input.nextInt();
                                arvoreAvl.adicionar(element, arvoreAvl.getRaiz());
                                break;
                            case 2:
                                arvoreAvl.imprimir(arvoreAvl.getRaiz());
                                break;
                            case 3:
                                System.out.println("Digite o valor para ser removido: ");
                                int remover = input.nextInt();
                                arvoreAvl.remover(remover, arvoreAvl.getRaiz());
                                break;
                        }
                    }
                case 3:
                    while (true){
                        System.out.println("----------- Arvore RedBlack -----------");
                        System.out.println("----------- Digite uma Opção -----------");
                        System.out.println("\n1- Adicionar elemento a arvore\n2- Imprimir arvore em préOrdem\n3- Imprimir em pré, pós e emOrdem\n4- Remover elemento da arvore.");
                        System.out.println("--------------------------------------");
                        aux = input.nextInt();

                        int aux2;
                        switch (aux){
                            case 1:
                                System.out.println("Digite o valor: ");
                                int element = input.nextInt();
                                arvoreRB.adicionar(element, arvoreRB.getRaiz());
                                break;
                            case 2:
                                System.out.println("\n----------- Digite a opção desejada -----------");
                                System.out.println("\n1- Com a cor de cada nó\n2- Apenas o valor do nó\n");
                                aux2 = input.nextInt();
                                if(aux2 == 1){
                                    arvoreRB.imprimir();
                                }else{
                                    arvoreRB.imprimirValor();
                                }
                                break;
                            case 3:
                                System.out.println("\n----------- Digite a opção desejada -----------");
                                System.out.println("\n1- emOrdem\n2- PréOdem\n3- PósOrdem");
                                aux2 = input.nextInt();
                                switch (aux2){
                                    case 1:
                                        arvoreRB.imprimirInOrdem();
                                        break;
                                    case 2:
                                        arvoreRB.imprimir();
                                        break;
                                    case 3:
                                        arvoreRB.imprimirPosOrdem();
                                        break;
                                }
                                break;
                            case 4:
                                System.out.println("Digite o valor para ser removido: ");
                                int remover = input.nextInt();
                                arvoreRB.remover(remover, arvoreRB.getRaiz());
                                break;
                        }
                    }
                default:
                    System.out.println("Opção INEXISTENTE");
            }

        }

    }

}