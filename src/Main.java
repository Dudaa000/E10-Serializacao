import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date data = new Date();

        PessoaFisica pF1 = new PessoaFisica("Maria Eduarda", "deus e mais", data, "100.568.746.20", 17, 'F');
        PessoaFisica pF2 = new PessoaFisica("Miguel Mariz", "pai e deus", data, "105.098.52-88", 17, 'M');
        PessoaFisica pF3 = new PessoaFisica("MIMI", "25 de marco", data, "892.605.888.50", 2, 'F');
        PessoaJuridica pJ = new PessoaJuridica("Doces da duda", "rua dos doces", data, "24 405 850/0001-67", "Alimenticio", 6);

        Conta contaPF1 = new ContaCorrente(pF1, 1000000, 10, 100000, 0);
        Conta contaPF2 = new ContaCorrente(pF2, 1000000, 20, 500000, 1);
        Conta contaPF3 = new ContaUniversitaria(pF3, 200, 10, 200, 3);
        Conta contaPJ = new ContaCorrente(pJ, 1000000, 5000, 500000, 2);

        System.out.println("<< NOVO BANCO >>\n<< IMPRIMINDO INFORMACOES DOS CLIENTES >>");
        System.out.println(contaPF1.toString());
        System.out.println(contaPF2.toString());
        System.out.println(contaPF3.toString());
        System.out.println(contaPJ.toString());

        System.out.println("\n<< REALIZANDO OPERACOES >>");
        try {
            contaPF1.depositar(5000);
            //contaPF1.depositar(-10);
            contaPF2.depositar(2000);
            contaPJ.depositar(10000);
            contaPJ.depositar(2000);

            contaPF1.sacar(200);
            contaPF2.sacar(1500);
            contaPF2.sacar(200);
            contaPJ.sacar(130);
            contaPJ.sacar(200);
            contaPJ.sacar(-300);
            contaPJ.sacar(1640);

            contaPF1.extratoTaxas();
            contaPF2.extratoTaxas();
            contaPJ.extratoTaxas();
        } catch (Exception i){
            i.printStackTrace();
        }

        System.out.println("\n<EXTRATO");
        contaPF1.extrato(1);
        contaPF2.extrato(1);
        contaPJ.extrato(1);

        System.out.println("\nA media e:");
        //int media = Operacao.totalOperacoes/Conta.totalContas;

        //System.out.println("Media: " +media);

        //verificacao pelo metodo equals
        System.out.println("\nVERIFICANDO SE HA CONTAS IGUAIS (cpf ou cnpj)");
        System.out.println("\nConta " + pF1.getNome() + " equals (igual) Conta " + pF2.getNome() + "? ");
        System.out.println(pF1.autenticar(pF2.getCpf()));
        System.out.println("\nConta " + pF1.getNome() + " equals (igual) Conta " + pF3.getNome() + "? ");
        System.out.println(pF1.autenticar(pF3.getCpf()));
    }
}