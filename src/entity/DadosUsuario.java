package entity;

public class DadosUsuario {
    public Object telefone;
    public String nome;
    public String email;
    public int idade;
    public String endereco;

    public DadosUsuario(String nome, String email, int idade, String endereco, String telefone) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone.toString(); }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public void excluirDados() {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public void exibir() {
        System.out.println("\n--- Dados Cadastrados ---");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);
    }
}