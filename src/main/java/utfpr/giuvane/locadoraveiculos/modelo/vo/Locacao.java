package utfpr.giuvane.locadoraveiculos.modelo.vo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Locacao {
    @Id
    @GeneratedValue
	private int codLocacao;
    
    @ManyToOne
    private Carro carro;
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Funcionario funcionario;
    
    private Date dtLocacao;
    private Date dtEntrega;
    private float valor;
    
    @OneToMany(mappedBy = "locacao")
    private List<Pagamento> pagamentos;

    public Locacao(int codLocacao, Carro carro, Cliente cliente,
                    Date dtLocacao, Date dtEntrega, float valor) {
            super();
            this.codLocacao = codLocacao;
            this.carro = carro;
            this.cliente = cliente;
            this.dtLocacao = dtLocacao;
            this.dtEntrega = dtEntrega;
            this.valor = valor;
    }

    public Locacao() {

    }

    public int getCodLocacao() {
            return codLocacao;
    }
    public void setCodLocacao(int codLocacao) {
            this.codLocacao = codLocacao;
    }
    public Carro getCarro() {
            return carro;
    }
    public void setCarro(Carro carro) {
            this.carro = carro;
    }
    public Cliente getCliente() {
            return cliente;
    }
    public void setCliente(Cliente cliente) {
            this.cliente = cliente;
    }
    public Date getDtLocacao() {
            return dtLocacao;
    }
    public void setDtLocacao(Date dtLocacao) {
            this.dtLocacao = dtLocacao;
    }
    public Date getDtEntrega() {
            return dtEntrega;
    }
    public void setDtEntrega(Date dtEntrega) {
            this.dtEntrega = dtEntrega;
    }
    public float getValor() {
            return valor;
    }
    public void setValor(float valor) {
            this.valor = valor;
    }

    public String toString(){
            return codLocacao + " - " + dtLocacao;
    }

    public boolean equals(Object obj){
            if(obj instanceof Locacao){
                    Locacao l = (Locacao) obj;
                            if (l.codLocacao == this.codLocacao)
                                    return true;
            }
            return false;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
