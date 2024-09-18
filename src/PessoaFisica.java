import java.util.Date;

public class PessoaFisica extends Cliente {
    private String cpf;
    private int idade;
    private char sexo;

    public PessoaFisica(String nome, String endereco, Date data, String cpf, int idade, char sexo) {
        super(nome, endereco, data);
        this.cpf = cpf;
        this.idade = idade;
        this.sexo = sexo;
    }


    @Override
    public String toString() {
        String str = "PESSOA FISICA (PF)\n" +
                "Nome: " + this.getNome() + "\n" +
                "CPF:" + cpf + "\n" +
                "Idade:" + idade + "\n" +
                "Genero:" + sexo + "\n" +
                "Data: " + this.getData() + "\n" +
                "Endereco: " + this.getEndereco() + "\n" +
                "\n";
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PessoaFisica) {
            PessoaFisica objPF = (PessoaFisica) obj;

            if(this.cpf.equals(objPF.cpf)) {
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
        return chave.equals(this.cpf);
    }

    public double calculaTaxas(){
        return 10;
    }

    //setters
    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public void setSexo(char sexo){
        this.sexo = sexo;
    }

    //getters
    public String getCpf(){
        return cpf;
    }

    public int getIdade(){
        return idade;
    }

    public char getSexo(){
        return sexo;
    }
}