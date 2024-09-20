import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Conta implements ITaxas, Serializable{
    private Cliente donoConta;
    private int agencia;
    final private static long serialVertionUID = 1L;



    protected double saldo = 15000;
    public static transient int totalContas = 0;
    protected int contador = 0;
    protected double limiteMAX, limiteMIN;
    private int num;
    private ArrayList<Operacao> operacoes;
    private int nextOp; //proxima Operacao

    public Conta (Cliente donoConta, double saldo, double limiteMIN, double limiteMAX, int num) {
        this.num = num;
        this.saldo = saldo;
        this.donoConta = donoConta;
        this.agencia = agencia;
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


    public void salvarEmArquivo() {
        String nomeArquivo = this.agencia + "-" + this.num + ".ser";

        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;

        try {
            fileOut = new FileOutputStream(nomeArquivo);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(this);

            System.out.println("Conta serializada com sucesso");

        } catch (IOException e) {
            System.err.println("Erro ao serializar a conta");
            System.err.println(e.getMessage());

        } finally {
            if (out!=null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.err.println("Erro ao serializar a conta");
                    System.err.println(e.getMessage());
                }
            }
            if (fileOut!=null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    System.err.println("Erro ao serializar a conta");
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static Conta carregaDados(int agencia, int numero) {
        String nomeArquivo = agencia + "-" + numero + ".ser";

        FileInputStream fileIn = null;
        ObjectInputStream in = null;

        try {
            fileIn = new FileInputStream(nomeArquivo);
            in = new ObjectInputStream(fileIn);
            Conta conta = (Conta)in.readObject();

            System.out.println("Conta desserializada com sucesso");

            return conta;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao desserializar a conta");
            System.err.println(e.getMessage());

        } finally {
            if (in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("Erro ao desserializar a conta");
                    System.err.println(e.getMessage());
                }
            }
            if (fileIn!=null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    System.err.println("Erro ao desserializar a conta");
                    System.err.println(e.getMessage());
                }
            }
        }
        return null;
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

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int numAgencia) {
        this.agencia = agencia;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}