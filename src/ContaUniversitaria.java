public class ContaUniversitaria extends Conta{
    public ContaUniversitaria(Cliente donoConta, double limiteMIN, double limiteMAX, double saldo, int num) {
        super(donoConta, saldo, limiteMIN, limiteMAX, num);
    }

    @Override
    public void setLimite(double limiteMIN, double limiteMAX) {
        if (limiteMIN < 0) throw new IllegalArgumentException("Limite abaixo do que foi permitido. Conta: " + getDonoConta().getNome());
        if (limiteMAX > 500) throw new IllegalArgumentException("Limite acima do que foi permitido. Conta: " + getDonoConta().getNome());

        this.limiteMIN = limiteMIN;
        this.limiteMAX = limiteMAX;
    }

    @Override
    public double calculaTaxas() {
        return 0;
    }
}