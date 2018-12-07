package utfpr.giuvane.locadoraveiculos.modelo.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue
    private int codFuncionario;
    
    @ManyToOne
    private Cargo cargo;
    
    @OneToMany(mappedBy = "funcionario")
    private List<Locacao> locacoes;
    
    private String nome;
    private String rg;
    private String cpf;
    private String fone;
    private String end;

    public Funcionario(int codFuncionario, Cargo cargo, String nome, String rg,
                    String cpf, String fone, String end) {
            this.codFuncionario = codFuncionario;
            this.cargo = cargo;
            this.nome = nome;
            this.rg = rg;
            this.cpf = cpf;
            this.fone = fone;
            this.end = end;
    }

    public Funcionario() {

    }

    public int getCodFuncionario() {
            return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
            this.codFuncionario = codFuncionario;
    }

    public Cargo getCargo() {
            return cargo;
    }

    public void setCargo(Cargo cargo) {
            this.cargo = cargo;
    }

    public String getNome() {
            return nome;
    }

    public void setNome(String nome) {
            this.nome = nome;
    }

    public String getRg() {
            return rg;
    }

    public void setRg(String rg) {
            this.rg = rg;
    }

    public String getCpf() {
            return cpf;
    }

    public void setCpf(String cpf) {
            this.cpf = cpf;
    }

    public String getFone() {
            return fone;
    }

    public void setFone(String fone) {
            this.fone = fone;
    }

    public String getEnd() {
            return end;
    }

    public void setEnd(String end) {
            this.end = end;
    }
}
