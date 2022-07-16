public class ArvoreRB{
    static No raiz;  //Raiz da arvore

    public No getRaiz(){
        return raiz;
    }

    //Função para criar o nó
    public static No criarNo(int elemento){
        No no = new No(elemento,'R');
        no.esquerda = new No(-1,'B');
        no.direita = new No(-1,'B');
        return no;
    }

    //Função para adicionar elementos a arvore
    public void adicionar(int elemento, No raiz){
        System.out.println("Adicionando Elemento : " + elemento);
        No no = criarNo(elemento);
        if(ArvoreRB.raiz == null){
            ArvoreRB.raiz = no;
            ArvoreRB.raiz.cor = 'B';
            return;
        }

        No temp = ArvoreRB.raiz;
        while(temp!=null){
            if(temp.elemento >elemento){
                if(temp.esquerda.elemento ==-1){
                    temp.esquerda = no;
                    no.pai = temp;
                    balancear(no);               //balance the tree.
                    return;
                }
                temp = temp.esquerda;
                continue;
            }
            if(temp.elemento <elemento){
                if(temp.direita.elemento ==-1){
                    temp.direita = no;
                    no.pai = temp;
                    balancear(no);               //balance the tree.
                    return;
                }
                temp = temp.direita;
            }
        }
    }

    //Função para remover elementos da arvore
    public void remover(int elemento, No raiz){
        System.out.println("Remover elemento : " + elemento);
        if(ArvoreRB.raiz ==null){
            return;
        }

        //Buscar o elemento na arvore
        No temp = ArvoreRB.raiz;
        while(temp.elemento !=-1){
            if(temp.elemento ==elemento){
                break;
            }
            if(temp.elemento >elemento){
                temp = temp.esquerda;
                continue;
            }
            if(temp.elemento <elemento){
                temp = temp.direita;
                continue;
            }
        }

        //Se não for encontrado retorna null
        if(temp.elemento ==-1){
            return;
        }

        //Encontre o proximo numero maior que o elemento.
        No proximo = buscaProximo(temp);

        //Troque os valores de elemento de certo nó para numero maior próximo
        int t = temp.elemento;
        temp.elemento = proximo.elemento;
        proximo.elemento = t;

        //Remover o proximo nó
        No parent = proximo.pai;
        if(parent==null){
            if(proximo.esquerda.elemento ==-1){
                ArvoreRB.raiz = null;
            }
            else{
                ArvoreRB.raiz = proximo.esquerda;
                proximo.pai = null;
                ArvoreRB.raiz.cor = 'B';
            }
            return;
        }

        if(parent.direita ==proximo){
            parent.direita = proximo.esquerda;
        }
        else{
            parent.esquerda = proximo.esquerda;
        }
        proximo.esquerda.pai = parent;
        String cor = Character.toString(proximo.esquerda.cor) +  Character.toString(proximo.cor);
        balancear(proximo.esquerda,cor);  //balancear a arvore
    }


    //Função para balancear a arvore em caso de EXCLUSÃO
    private void balancear(No no, String color){
        System.out.println("Balanceando no: " + no.elemento + " Cor : " + color);

        //Se não é RED-BLACK
        if(no.pai ==null || color.equals("BR") || color.equals("RB")){
            no.cor = 'B';
            return;
        }

        No pai = no.pai;

        //Obter irmão de um determinado nó
        No irmao = null;
        if(pai.esquerda == no){
            irmao = pai.direita;
        }
        else{
            irmao = pai.esquerda;
        }


        No irmaoEsquerdo = irmao.esquerda;   //irmao nó esquerdo
        No irmaoDireito = irmao.direita; //irmao nó direito

        //Irmao direito e esquerdo são blacks
        if(irmao.cor =='B' && irmaoEsquerdo.cor =='B' && irmaoDireito.cor =='B'){
            irmao.cor = 'R';
            no.cor = 'B';
            String c = Character.toString(pai.cor) + Character.toString('B');
            balancear(pai,c);
            return;
        }

        //irmao é vermelho.
        if(irmao.cor =='R'){
            if(pai.direita ==irmao){
                rotacaoEsquerda(irmao);
            }
            else{
                rotacaoDireita(irmao);
            }
            balancear(no,color);
            return;
        }

        //Irmao é vermelho mais um de seus filhos é vermelho
        if(pai.esquerda ==irmao){
            if(irmaoEsquerdo.cor =='R'){
                rotacaoDireita(irmao);
                irmaoEsquerdo.cor = 'B';
            }
            else{
                rotacaoEsquerda(irmaoDireito);
                rotacaoDireita(irmaoDireito);
                irmaoDireito.esquerda.cor = 'B';
            }
            return;
        }
        else{
            if(irmaoDireito.cor == 'R'){
                rotacaoEsquerda(irmao);
                irmaoDireito.cor = 'B';
            }
            else{
                rotacaoDireita(irmaoEsquerdo);
                rotacaoEsquerda(irmaoEsquerdo);
                irmaoEsquerdo.direita.cor = 'B';
            }
            return;
        }
    }


    //Funçao para encontrar o próximo elemento maior que o numero fornecido
    private No buscaProximo(No no){
        No proximo = no.direita;
        if(proximo.elemento ==-1){
            return no;
        }
        while(proximo.esquerda.elemento !=-1){
            proximo = proximo.esquerda;
        }
        return proximo;
    }


    //Função para balancear a arvore em caso de INSERÇÃO
    public void balancear(No no){
        System.out.println("Balancear No : " + no.elemento);


        //se o elemento não é raiz no.
        if(no.pai == null){
            raiz = no;
            raiz.cor = 'B';
            return;
        }

        //se a cor pai de no for preto.
        if(no.pai.cor =='B'){
            return;
        }


        //pega o irmão do pai do no.
        No irmao = null;
        if(no.pai.pai.esquerda == no.pai){
            irmao = no.pai.pai.direita;
        }
        else{
            irmao = no.pai.pai.esquerda;
        }


        //se a cor do irmão for vermelha.
        if(irmao.cor == 'R'){
            no.pai.cor = 'B';
            irmao.cor = 'B';
            no.pai.pai.cor = 'R';
            balancear(no.pai.pai);
            return;
        }

        //se a cor do irmao for preta.
        else{
            if(no.pai.esquerda == no && no.pai.pai.esquerda == no.pai){
                rotacaoDireita(no.pai);
                balancear(no.pai);
                return;
            }
            if(no.pai.direita == no && no.pai.pai.direita == no.pai){
                rotacaoEsquerda(no.pai);
                balancear(no.pai);
                return;
            }
            if(no.pai.direita == no && no.pai.pai.esquerda == no.pai){
                rotacaoEsquerda(no);
                rotacaoDireita(no);
                balancear(no);
                return;
            }
            if(no.pai.esquerda == no && no.pai.pai.direita == no.pai){
                rotacaoDireita(no);
                rotacaoEsquerda(no);
                balancear(no);
                return;
            }
        }
    }

    //função para realizar a Rotação Esquerda.
    private void rotacaoEsquerda(No no){
        System.out.println("Rotacionando à esquerda: "  + no.elemento);
        No pai = no.pai;
        No esquerda = no.esquerda;
        no.esquerda = pai;
        pai.direita = esquerda;
        if(esquerda!=null){
            esquerda.pai = pai;
        }
        char c = pai.cor;
        pai.cor = no.cor;
        no.cor = c;
        No gp = pai.pai;
        pai.pai = no;
        no.pai = gp;

        if(gp==null){
            raiz = no;
            return;
        }
        else{
            if(gp.esquerda == pai){
                gp.esquerda = no;
            }
            else{
                gp.direita = no;
            }
        }
    }

    //função para realizar a Rotação à Direita.
    private void rotacaoDireita(No no){
        System.out.println("Rotação à direita : " + no.elemento);
        No pai = no.pai;
        No direita = no.direita;
        no.direita = pai;
        pai.esquerda = direita;
        if(direita!=null){
            direita.pai = pai;
        }
        char c = pai.cor;
        pai.cor = no.cor;
        no.cor = c;
        No gp = pai.pai;
        pai.pai = no;
        no.pai = gp;

        if(gp==null){
            raiz = no;
            return;
        }
        else{
            if(gp.esquerda == pai){
                gp.esquerda = no;
            }
            else{
                gp.direita = no;
            }
        }
    }

    //função para PreOrdenas a árvore com cor.
    private void preOrdenar(No no){
        if(no.elemento ==-1){
            return;
        }
        System.out.print(no.elemento + "-" + no.cor + " ");
        preOrdenar(no.esquerda);
        preOrdenar(no.direita);
    }

    private void posOrdenar(No no){
        if(no.elemento == -1){
            return;
        }
        posOrdenar(no.esquerda);
        posOrdenar(no.direita);
        System.out.print(no.elemento + "-" + no.cor + " ");

    }

    private void inOrdem(No no){
        if(no.elemento == -1){
            return;
        }
        posOrdenar(no.esquerda);
        System.out.print(no.elemento + "-" + no.cor + " ");
        posOrdenar(no.direita);


    }

    //função para PreOrdenas a árvore.
    private void preOrdenarValor(No no){
        if(no.elemento ==-1){
            return;
        }
        System.out.print(no.elemento+ " ");
        preOrdenarValor(no.esquerda);
        preOrdenarValor(no.direita);
    }

    //função para imprimir a árvore.
    public void imprimir(){
        if(raiz == null){
            System.out.println("Arvore vazia");
            return;
        }

        System.out.print("Arvore préOrdem com cor: ");
        preOrdenar(raiz);
        System.out.println();
    }
    public void imprimirValor(){
        if(raiz == null){
            System.out.println("Arvore vazia");
            return;
        }

        System.out.print("Arvore préOrdem: ");
        preOrdenarValor(raiz);
        System.out.println();
    }

    public void imprimirInOrdem(){
        if(raiz == null){
            System.out.println("Arvore vazia");
            return;
        }

        System.out.print("Arvore em Ordem: ");
        inOrdem(raiz);
        System.out.println();
    }

    public void imprimirPosOrdem(){
        if(raiz == null){
            System.out.println("Arvore vazia");
            return;
        }

        System.out.print("Arvore posOrdem: ");
        posOrdenar(raiz);
        System.out.println();
    }

}