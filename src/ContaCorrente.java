public class ContaCorrente extends  Conta {
    public ContaCorrente(Cliente donoConta, double limiteMAX, double limiteMIN, double saldo, int num) {
        super(donoConta, saldo, limiteMIN, limiteMAX, num);
    }

    @Override
    public void setLimite(double limiteMIN, double limiteMAX) {
        if (limiteMIN <= -100) throw new IllegalArgumentException("Limite abaixo do que foi permitido. Conta: " + getDonoConta().getNome());

        this.limiteMIN = limiteMIN;
        this.limiteMAX = limiteMAX;
    }

    @Override
    public double calculaTaxas() {
        return 0;
    }
}