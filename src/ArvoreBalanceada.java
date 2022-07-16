public class ArvoreBalanceada <TIPO extends Comparable>{

    private Elemento raiz;

    public ArvoreBalanceada() {
        this.raiz = raiz;
    }

    public boolean isEmpty(){
        return raiz == null;
    }

    public Elemento getRaiz(){
        return raiz;
    }

    //Insere e já balanceia usando o método de balancear

    public void adicionar(TIPO valor, Elemento atual){

        Elemento<TIPO> novoElemento = new Elemento<TIPO>(valor);

        if(isEmpty()){
            Elemento novo = new Elemento(valor);
            raiz = novo;
            defineFB(raiz); //Metodo para definir o fator balanceamento de cada nó
        }else{
            // Neste caso estou verificando se a comparação resulta em 1 que significa que o novo elemento é maior que o atual!
            if(novoElemento.getValor().compareTo(atual.getValor()) == 1){ //Se o nó inserido for maior que o atual
                if(atual.getDireita() == null){ //Se o nó da direita do atual for vazio insere aqui
                    Elemento novo = new Elemento(valor);
                    atual.setDireita(novo);
                    novo.setPai(atual);
                    defineFB(raiz); //Define o fator de balanceamento de cada nó
                    raiz = balanceia(raiz);//Balanceia a arvore
                }else { //Senão, faz a recursão, passando o nó da direita para o atual.
                    adicionar(valor, atual.getDireita());
                }
                // Neste caso estou verificando se a comparação resulta em 1 que significa que o novo elemento é maior que o atual!
            }else if(novoElemento.getValor().compareTo(atual.getValor()) == -1){ //Se o nó inserido for menor que o atual (No caso o nó da esquerda)
                if(atual.getEsquerda() == null){ //Se o nó da esquerda do atual for vario insere aqui
                    Elemento novo = new Elemento(valor);
                    atual.setEsquerda(novo);
                    novo.setPai(atual);
                    defineFB(raiz); //Define o fator de balanceamento de cada nó
                    raiz = balanceia(raiz);//Balanceia a arvore
                }else{
                    adicionar(valor, atual.getEsquerda());
                }
            }else{ //Caso o nó já existir
                System.out.println("Impossivel inserir! Nó já existente");
            }
        }
    }

    public void imprimir(Elemento f){
        if(isEmpty()){
            System.out.println("Arvore AVL esta vazia!");
        }else{
            System.out.println("Nó: "+f.getValor() +" - Seu fator de balanceamento é: "+ f.getBalanceamento());
            if(f.getDireita() != null){//Se o nó a direita for diferente de vazio
                imprimir(f.getDireita());
            }
            if (f.getEsquerda() != null){ //Se o nó a esquerda for diferente de vazio
                imprimir(f.getEsquerda());
            }
        }
    }

    public Elemento remover(TIPO valor, Elemento atual) {
        Elemento<TIPO> novoElemento = new Elemento<TIPO>(valor);
        if (isEmpty()) {//Se a arvore estiver vazia, não tem nada para remover
            System.out.println("A arvore está vazia! Não tem nada para remover.");
        } else if (novoElemento.getValor().compareTo(atual.getValor()) == 1) {//Se o valor a ser removido for maior que o atual
            atual.setDireita(remover(valor, atual.getDireita()));
        } else if (novoElemento.getValor().compareTo(atual.getValor()) == -1) {//Se o valor a ser removido for menor que o atual
            atual.setEsquerda(remover(valor, atual.getEsquerda()));
        }else{//Se for igual, faz as verificações de remoção
            if(atual.getDireita() == null && atual.getEsquerda() == null){//se o nó não tiver subarvores, ou seja, for uma folha, basta anular ele
                if(atual == raiz){
                    raiz = null;
                }else {
                    atual = null;
                }
            }else if (atual.getEsquerda() == null){
                atual = atual.getDireita();
            }else if (atual.getDireita() == null){
                atual = atual.getEsquerda();
            }else {//Se o nó tiver as duas subárvores
                /*Cria um nó auxiliar pra percorrer a arvore até o valor mais a direita da subarvore da esquerda do nó atual
                 e seta ele como o valor da esquerda do atual*/
                Elemento aux = atual.getEsquerda();
                while (aux.getDireita() != null) {//Faz o aux ir até o valor mais a direita
                    aux = aux.getDireita();
                }
                atual.setValor(aux.getValor()); //substitui o valor do nó atual pelo valor do auxiliar
                aux.setValor(valor); // substitui o valor do auxiliar pelo atual
                atual.setEsquerda(remover(valor, atual.getEsquerda()));//chama a recursão pra remover o valor do atual, q agora tá no auxiliar, por isso, vai remover o auxiliar
            }
        }
        if(raiz != null){
            defineFB(raiz); //Atualiza o fator de balanceamento de cada nó
            raiz = balanceia(raiz); //Balanceia a arvore
        }
        return atual;
    }

    public int altura(Elemento atual){//Verifica a altura de um determinado nó
        if(atual == null){ //Se o nó for nulo, sua altura sera -1
            return -1;
        }
        if(atual.getDireita() == null && atual.getEsquerda() == null){ //Se o nó não tiver nenhum filho, então sua altura será 0
            return 0;
        }else if (atual.getEsquerda() == null){//Se o nó tiver apenar um filho sua altura será 1 + a altura do nó do filho
            return 1 + altura(atual.getDireita());
        }else if (atual.getDireita() == null) { //Mesma do passo anterior aqui
            return 1 + altura(atual.getEsquerda());
        }else{//Se ele tiver dois filhos, temos que verificar qual filho é mais "Alto"
            if(altura(atual.getEsquerda()) > altura(atual.getDireita())){
                return 1 + altura(atual.getEsquerda());
            }else {
                return 1 + altura(atual.getDireita());
            }
        }//e assim recursivamente, até chegar nas folhas q não terão filhos, a altura será 0 e assim a recursão para.
    }
    private void defineFB(Elemento atual) {//Define o valor de balanceamento de cada nó com base na altura.
        atual.setBalanceamento(altura(atual.getEsquerda()) - altura(atual.getDireita())); //O valor do balanceamendo será a altura do filho da direita menos a altura do da direita
        if(atual.getDireita() != null){//Verifica todos os nós
            defineFB(atual.getDireita());
        }
        if(atual.getEsquerda() != null){//Verifica todos os nós
            defineFB(atual.getEsquerda());
        }
    }

    public Elemento rotacaoADireita(Elemento atual) {
        Elemento aux = atual.getEsquerda(); //Armazena o valor do nó da esquerda do atual
        aux.setPai(atual.getPai());
        if (aux.getDireita() != null) {//tratamento para quando a árvore é degenerada
            aux.getDireita().setPai(atual);
        }
        atual.setPai(aux);
        atual.setEsquerda(aux.getDireita());//Joga o valor da direita do nó da esquerda do atual, na esquerda do atual
        aux.setDireita(atual);//troca o valor da direita do nó da esquerda pelo atual
        if (aux.getPai() != null) {//Se aux não for a raiz principal, configura o pai pra apontar pro novo nó
            if (aux.getPai().getDireita() == atual) {
                aux.getPai().setDireita(aux);
            } else if (aux.getPai().getEsquerda() == atual) {
                aux.getPai().setEsquerda(aux);
            }
        }
        defineFB(aux);//atualiza o valor do balanceamento
        return aux; //retorna o valor do nó da esquerda q é o novo pai
    }

    //mesma coisa do rotação a direita, só que invertido pra esquerda
    public Elemento rotacaoAEsquerda(Elemento atual) {
        Elemento aux = atual.getDireita();
        aux.setPai(atual.getPai());
        if (aux.getEsquerda() != null) {//tratamento para quando a árvore é degenerada
            aux.getEsquerda().setPai(atual);
        }

        atual.setPai(aux);
        atual.setDireita(aux.getEsquerda());
        aux.setEsquerda(atual);
        if (aux.getPai() != null) {
            if (aux.getPai().getDireita() == atual) {
                aux.getPai().setDireita(aux);
            } else if (aux.getPai().getEsquerda() == atual) {
                aux.getPai().setEsquerda(aux);
            }
        }
        defineFB(aux);//atualiza o valor do balanceamento
        return aux;
    }

    public Elemento rotacaoDuplaDireita(Elemento atual) {
        Elemento aux = atual.getEsquerda();//Armazena o valor do filho da esquerda
        atual.setEsquerda(rotacaoAEsquerda(aux));//faz uma rotação para a esquerda no filho da esquerda
        Elemento aux2 = rotacaoADireita(atual); //Faz uma rotação para a direita no atual/pai com o filho da esquerda já rodado
        return aux2; //retorna o nó q será o novo pai com seus filhos
    }

    //mesma coisa do de rotação dupla pra direita, só que invertido pra esquerda
    public Elemento rotacaoDuplaEsquerda(Elemento atual) {
        Elemento aux = atual.getDireita();
        atual.setDireita(rotacaoADireita(aux));
        Elemento aux2 = rotacaoAEsquerda(atual);
        return aux2;
    }

    public Elemento balanceia(Elemento atual) {//Recebe como parâmetro a raiz
        /*Se o nó atual tiver FB=2 e o seu filho da esquerda dele tiver Fb>=0,
         troca o valor dele pelo resultado da rotação a direita com ele*/
        if (atual.getBalanceamento() == 2 && atual.getEsquerda().getBalanceamento() >= 0) {
            atual = rotacaoADireita(atual);
            /* Senão se o nó atual tiver FB=-2 e o filho da direita dele tiver Fb<0,
             troca o valor dele pelo resultado da rotação a esquerda com ele*/
        } else if (atual.getBalanceamento() == -2 && atual.getDireita().getBalanceamento() <= 0) { //mudat dps
            atual = rotacaoAEsquerda(atual);
            /*Senão se o nó atual tiver FB=2 e o filho da esquerda dele tiver Fb<0,
             troca o valor dele pelo resultado da rotação dupla a direita com ele*/
        } else if (atual.getBalanceamento() == 2 && atual.getEsquerda().getBalanceamento() < 0) {
            atual = rotacaoDuplaDireita(atual);
            /*Senão se o nó atual tiver FB=-2 e o filho da direita dele tiver Fb>0,
             troca o valor dele pelo resultado da rotação dupla a esquerda com ele*/
        } else if (atual.getBalanceamento() == -2 && atual.getDireita().getBalanceamento() > 0) {
            atual = rotacaoDuplaEsquerda(atual);
        }
        /*Nessa parte aqui a recursão vai procurar por mais nós desbalanceados*/
        if (atual.getDireita() != null) {
            balanceia(atual.getDireita());
        }
        if (atual.getEsquerda() != null) {
            balanceia(atual.getEsquerda());
        }
        return atual; //Retorna a nova raiz com seus filhos balanceados
    }

}
