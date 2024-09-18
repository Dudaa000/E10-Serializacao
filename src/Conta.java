
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.*;
import java.io.IOException;

public abstract class Conta implements Serializable{
    private Cliente donoConta;
    private int numAgencia = 1;


    protected double saldo = 15000;
    public static int totalContas = 0;
    protected int contador = 0;
    protected double limiteMAX, limiteMIN;
    private int num;
    private ArrayList<Operacao> operacoes;
    private int nextOp; //proxima Operacao

    public Conta (Cliente donoConta, double saldo, double limiteMIN, double limiteMAX, int num) {
        this.num = num;
        this.saldo = saldo;
        this.donoConta = donoConta;

        this.operacoes = new ArrayList<Operacao>();
        this.nextOp = 0;
        setLimite(limiteMIN, limiteMAX);

        totalContas++;
    }


    public boolean depositar (double valor) throws ValorNegativoException{
        if (valor < 0) throw new ValorNegativoException("Deposito negativo. Conta: " + donoConta.getNome());
        else {
            operacoes.add(new OperacaoDeposito(valor));
            saldo += valor;

            this.nextOp++;
            contador++;

            return true;
        }
    }

    public boolean sacar (double valor) throws ValorNegativoException, SemLimiteException{
        if (valor < 0) throw new ValorNegativoException("Saque negativo. Conta: " + donoConta.getNome());
        else if (valor > limiteMAX) throw new SemLimiteException("Limite de saque excedido. Conta: " + donoConta.getNome());
        else {
            operacoes.add(new OperacaoSaque(valor));
            saldo -= valor;

            this.nextOp++;
            contador++;

            return true;
        }
    }

    @SuppressWarnings("unchecked")
    public void extrato(int order) {
        if (order == 1)
            Collections.sort(operacoes);
        else
            operacoes.sort((a, b) -> a.getData().compareTo(b.getData()));

        System.out.println("\n Extrato Conta " + donoConta.getNome());
        for(Operacao atual : operacoes) {
            if (atual != null) {
                System.out.println(atual);
            }
        }
        System.out.println(" ");
    }

    @Override
    public String toString() {
        String str = "Conta " + donoConta.getNome() + "\n" +
                this.donoConta.toString() + "\n" +
                "Saldo : " + this.saldo + "\n" +
                "Limite minimo: " + this.limiteMIN + "\n" +
                "Limite maximo: " + this.limiteMAX + "\n" +
                "\n";
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Conta) {
            Conta objConta = (Conta) obj;

            if(this.num == objConta.num) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void extratoTaxas(){
        System.out.println(" EXTRATO DE TAXAS  "+ donoConta.getNome());
        float taxa = 0;

        System.out.println("Taxa de Manutencao: " + donoConta.calculaTaxas()+ "\n");
        taxa += donoConta.calculaTaxas();

        if (this.operacoes.size() >= 1){
            System.out.println("Operacoes:");
            for (Operacao ifSaq : operacoes){
                if (ifSaq == null) break;
                if (ifSaq.getTipo() == 's'){
                    System.out.println("Saque: "+ ifSaq.calculaTaxas());
                    taxa += ifSaq.calculaTaxas();
                }
            }
        }
        System.out.println();
        System.out.printf("Total: %.2f\n", taxa);
    }

    public void serializar() throws IOException{
        FileOutputStream fileOut = new FileOutputStream(this.getNumAgencia() + "-" + this.getNum() + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        out.writeObject(this);

        out.close();
        fileOut.close();
    }

    public Conta deserializar(int numAgencia, int numero) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(numAgencia + "-" + numero + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);

        Conta c = (Conta) in.readObject();

        in.close();
        fileIn.close();

        return c;
    }


    //getters
    public double getSaldo() {
        return saldo;
    }

    public Cliente getDonoConta(){
        return this.donoConta;
    }

    public double getLimiteMax() {
        return limiteMAX;
    }

    public double getLimiteMin() {
        return limiteMIN;
    }

    //setters
    public void setDonoConta(Cliente donoConta) {
        this.donoConta = donoConta;
    }

    abstract void setLimite(double limiteMIN, double limiteMAX);

    public abstract double calculaTaxas();

    public int getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}