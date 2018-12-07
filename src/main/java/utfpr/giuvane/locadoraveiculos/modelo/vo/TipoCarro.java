package utfpr.giuvane.locadoraveiculos.modelo.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipoCarro {
    @Id
    @GeneratedValue
    private int codTipoCarro;
    private String descricaoTipoCarro;
    
    @OneToMany(mappedBy = "tipoCarro")
    private List<Carro> carros;

    public TipoCarro(int codTipoCarro, Marca marca, String descricaoTipoCarro) {
            this.codTipoCarro = codTipoCarro;
            this.descricaoTipoCarro = descricaoTipoCarro;
    }

    public TipoCarro() {
            this.codTipoCarro = 0;
            this.descricaoTipoCarro = "";
    }

    public int getCodTipoCarro() {
            return codTipoCarro;
    }

    public void setCodTipoCarro(int codTipoCarro) {
            this.codTipoCarro = codTipoCarro;
    }

    public String getDescricaoTipoCarro() {
            return descricaoTipoCarro;
    }

    public void setDescricaoTipoCarro(String descricaoTipoCarro) {
            this.descricaoTipoCarro = descricaoTipoCarro;
    }

    public String toString(){
            return codTipoCarro + " - " + descricaoTipoCarro;
    }

    public boolean equals(Object obj){
            if(obj instanceof TipoCarro){
                    TipoCarro tc = (TipoCarro) obj;
                            if (tc.codTipoCarro == this.codTipoCarro)
                                    return true;
            }
            return false;
    }
	
}