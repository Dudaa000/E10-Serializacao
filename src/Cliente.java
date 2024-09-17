import java.util.Date;

public abstract class Cliente implements ITaxas {

    private String nome;
    private String endereco;
    private Date data;
    private String chave;

    public static int totalDeContas;

    public Cliente (String nome, String endereco, Date data) {
        this.nome = nome;
        this.endereco = endereco;
        this.data = data;
    }

    public abstract boolean autenticar (String chave);

    //getters
    public String getNome(){
        return nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public Date getData(){
        return data;
    }

    //setters
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
}
