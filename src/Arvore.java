
//Foi usado extends Comparable para mostrar que o TIPO é um objeto comparavel.
public class Arvore<TIPO extends Comparable> {

    private Elemento<TIPO> raiz;

    public Arvore(){
        this.raiz = null;
    }

//    public boolean isEmpty(){
//        return raiz == null;
//    }
    public void adicionar(TIPO valor){
        Elemento<TIPO> novoElemento = new Elemento<TIPO>(valor);

       //Se raiz for null o novoElemento passa a ser a raiz.
        if (raiz == null){
            this.raiz = novoElemento;
        }else{// Caso já exista uma raiz, cai nesse else!
            Elemento<TIPO> atual = this.raiz; // E o a raiz passa a ser atual

            while(true){ //Laço de repetição roda até que algum IF seja satisfeito

                //Aqui é comparado o valor do novoElemento com o elemento atual
                //O metodo compareTo compara o valor de dois objetos.
                //O ComparteTo retorna um valor
                //
                // -1 quando o elemento é menor
                // 0 quando os elementos são iguais
                // 1 quando o elemento é maior
                if (novoElemento.getValor().compareTo(atual.getValor()) == -1){// Neste caso estou verificando se a comparação resulta em -1 que significa que o novo elemento é menor que o atual!
                    if (atual.getEsquerda() != null){// Atual tem um filho a esquerda? Se for diferente de null o atual a esquerda passa a ser o atual.
                        atual = atual.getEsquerda();
                    }else{// Caso seja null a gente seja um novoElemento a equerda que passa a ser atual
                        atual.setEsquerda(novoElemento);
                        break;
                    }
                }else{ //se for maior ou igual
                    if (atual.getDireita() != null){// Atual tem um filho a Direita? Se for diferente de null o atual a direita passa a ser o atual.
                        atual = atual.getDireita();
                    }else{// Caso seja null a gente seja um novoElemento a direita que passa a ser atual
                        atual.setDireita(novoElemento);
                        break;
                    }
                }
            }
        }
    }

    public Elemento<TIPO> getRaiz() {
        return raiz;
    }

    public void emOrdem(Elemento<TIPO> atual){
        if (atual != null){// Atual é diferente de null?
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getValor());
            emOrdem(atual.getDireita());
        }
    }

    public boolean remover(TIPO valor){
        //buscar o elemento na árvore
        Elemento<TIPO> atual = this.raiz;
        Elemento<TIPO> paiAtual = null;
        while(atual != null){
            if (atual.getValor().equals(valor)){
                break;
            }else if (valor.compareTo(atual.getValor()) == -1){ //valor procurado é menor que o atual
                paiAtual = atual;
                atual = atual.getEsquerda();
            }else{
                paiAtual = atual;
                atual = atual.getDireita();
            }
        }
        //verifica se existe o elemento
        if (atual != null){

            //elemento tem 2 filhos ou elemento tem somente filho à direita
            if (atual.getDireita() != null){

                Elemento<TIPO> substituto = atual.getDireita();
                Elemento<TIPO> paiSubstituto = atual;
                while(substituto.getEsquerda() != null){
                    paiSubstituto = substituto;
                    substituto = substituto.getEsquerda();
                }
                substituto.setEsquerda(atual.getEsquerda());
                if (paiAtual != null){
                    if (atual.getValor().compareTo(paiAtual.getValor()) == -1){ //atual < paiAtual
                        paiAtual.setEsquerda(substituto);
                    }else{
                        paiAtual.setDireita(substituto);
                    }
                }else{ //se não tem paiAtual, então é a raiz
                    this.raiz = substituto;
                    paiSubstituto.setEsquerda(null);
                    this.raiz.setDireita(paiSubstituto);
                    this.raiz.setEsquerda(atual.getEsquerda());
                }

                //removeu o elemento da árvore
                if (substituto.getValor().compareTo(paiSubstituto.getValor()) == -1){ //substituto < paiSubstituto
                    paiSubstituto.setEsquerda(null);
                }else{
                    paiSubstituto.setDireita(null);
                }

            }else if (atual.getEsquerda() != null){ //tem filho só à esquerda
                Elemento<TIPO> substituto = atual.getEsquerda();
                Elemento<TIPO> paiSubstituto = atual;
                while(substituto.getDireita() != null){
                    paiSubstituto = substituto;
                    substituto = substituto.getDireita();
                }
                if (paiAtual != null){
                    if (atual.getValor().compareTo(paiAtual.getValor()) == -1){ //atual < paiAtual
                        paiAtual.setEsquerda(substituto);
                    }else{
                        paiAtual.setDireita(substituto);
                    }
                }else{ //se for a raiz
                    this.raiz = substituto;
                }

                //removeu o elemento da árvore
                if (substituto.getValor().compareTo(paiSubstituto.getValor()) == -1){ //substituto < paiSubstituto
                    paiSubstituto.setEsquerda(null);
                }else{
                    paiSubstituto.setDireita(null);
                }
            }else{ //não tem filho
                if (paiAtual != null){
                    if (atual.getValor().compareTo(paiAtual.getValor()) == -1){ //atual < paiAtual
                        paiAtual.setEsquerda(null);
                    }else{
                        paiAtual.setDireita(null);
                    }
                }else{ //é a raiz
                    this.raiz = null;
                }
            }

            return true;
        }else{
            return false;
        }
    }

}

