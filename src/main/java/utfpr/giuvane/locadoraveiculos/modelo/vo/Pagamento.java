package utfpr.giuvane.locadoraveiculos.modelo.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue
    private int codPagamento;
    
    @ManyToOne
    private Locacao locacao;
    
    @ManyToOne
    private Cliente cliente;
    private Float multa;

    public Pagamento(int codPagamento, Locacao locacao, Cliente cliente,
                    Float multa) {
            super();
            this.codPagamento = codPagamento;
            this.locacao = locacao;
            this.cliente = cliente;
            this.multa = multa;
    }

    public Pagamento() {

    }

    public int getCodPagamento() {
            return codPagamento;
    }
    public void setCodPagamento(int codPagamento) {
            this.codPagamento = codPagamento;
    }
    public Locacao getLocacao() {
            return locacao;
    }
    public void setLocacao(Locacao locacao) {
            this.locacao = locacao;
    }
    public Cliente getCliente() {
            return cliente;
    }
    public void setCliente(Cliente cliente) {
            this.cliente = cliente;
    }
    public Float getMulta() {
            return this.multa;
    }
    public void setMulta(Float multa) {
            this.multa = multa;
    }
	
}
