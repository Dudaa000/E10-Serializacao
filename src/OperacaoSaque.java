public class OperacaoSaque extends Operacao implements ITaxas {
    public OperacaoSaque(double valor) {
        super('s', valor);
    }

    @Override
    public String toString() {
        String str = this.getData() + "\t" + this.getTipo() + "\t" + this.getValor();
        return str;
    }

    public double calculaTaxas(){
        return 0.05;
    }
}