public class No {
    int elemento;    //Dados
    No esquerda;   //Aponta para o filho esquerdo
    No direita;  //Aponta para o filho direito
    No pai; //Aponta para o nó pai
    char cor;  //Cor do nó

    public No(int elemento, char cor){
        this.elemento = elemento;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
        this.cor = cor;
    }
}