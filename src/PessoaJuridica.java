import java.util.Date;
import java.util.Enumeration;

public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String setor;
    private int numFuncionarios;

    public PessoaJuridica(String nome, String endereco, Date data, String cnpj, String setor, int numFuncionarios) {
        super(nome, endereco, data);
        this.cnpj = cnpj;
        this.setor = setor;
        this.numFuncionarios = numFuncionarios;
    }

    @Override
    public String toString() {
        String str = "PESSOA JURIDICA (PJ) \n" +
                "Nome: " + this.getNome() + "\n" +
                "Data: " + this.getData() + "\n" +
                "Número de Funcionários: " + numFuncionarios + "\n" +
                "Setor: " + setor + "\n" +
                "Endereco: " + this.getEndereco() + "\n" +
                "CNPJ: " + cnpj + "\n" +
                "\n";
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PessoaJuridica) {
            PessoaJuridica objPJ = (PessoaJuridica) obj;

            if(this.cnpj.equals(objPJ.cnpj)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean autenticar(String chave) {
        return chave.equals(this.cnpj);
    }

    public double calculaTaxas(){
        return 20;
    }

    //setters
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public void setSetor(String setor){
        this.setor = setor;
    }

    public void setNumFuncionarios(int numFuncionarios){
        this.numFuncionarios = numFuncionarios;
    }

    //getters
    public String getCnpj(){
        return cnpj;
    }

    public String getSetor(){
        return setor;
    }

    public int getNumFuncionarios(){
        return numFuncionarios;
    }
}