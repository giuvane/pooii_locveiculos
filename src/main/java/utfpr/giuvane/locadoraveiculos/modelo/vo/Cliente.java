package utfpr.giuvane.locadoraveiculos.modelo.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {
    @Id
    @GeneratedValue
    private int codCliente;
    private String nome;
    private String rg;
    private String cpf;
    private String fone;
    private String end;
    
    @OneToMany(mappedBy = "cliente")
    private List<Locacao> locacoes;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pagamento> pagamentos;

    public Cliente(int codCliente, String nome, String rg, String cpf,
                    String fone, String end) {
            this.codCliente = codCliente;
            this.nome = nome;
            this.rg = rg;
            this.cpf = cpf;
            this.fone = fone;
            this.end = end;
    }

    public Cliente(){
            this.codCliente = 0;
            this.nome = "";
            this.rg = "";
            this.cpf = "";
            this.fone = "";
            this.end = "";
    }

    public int getCodCliente() {
            return codCliente;
    }

    public void setCodCliente(int codCliente) {
            this.codCliente = codCliente;
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


    @Override
    public String toString(){
            return codCliente + " - " + nome;
    }


    public boolean equals(Object obj){
            if(obj instanceof Cliente){
                    Cliente c = (Cliente) obj;
                            if (c.codCliente == this.codCliente)
                                    return true;
            }
            return false;
    }
}
