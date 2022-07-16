
public class Elemento<TIPO> {
    private TIPO valor;
    private Elemento<TIPO> esquerda;
    private Elemento<TIPO> direita;

    private Elemento pai;

    private int balanceamento;


    public Elemento(TIPO novoValor){
        this.valor = novoValor;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;

    }

    public TIPO getValor() {
        return valor;
    }

    public void setValor(TIPO valor) {
        this.valor = valor;
    }

    public Elemento<TIPO> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Elemento<TIPO> esquerda) {
        this.esquerda = esquerda;
    }

    public Elemento<TIPO> getDireita() {
        return direita;
    }

    public void setDireita(Elemento<TIPO> direita) {
        this.direita = direita;
    }

    public Elemento getPai() {
        return pai;
    }

    public void setPai(Elemento pai) {
        this.pai = pai;
    }

    public int getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }
}