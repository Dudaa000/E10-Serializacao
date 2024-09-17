public class OperacaoDeposito extends Operacao implements ITaxas {
    public OperacaoDeposito(double valor) {
        super('d', valor);
    }

    @Override
    public String toString() {
        String str = this.getData() + "\t" + this.getTipo() + "\t" + this.getValor();
        return str;
    }

    public double calculaTaxas(){
        return 0;
    }
}