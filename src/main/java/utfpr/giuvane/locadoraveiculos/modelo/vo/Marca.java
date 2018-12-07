package utfpr.giuvane.locadoraveiculos.modelo.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Marca {
    
    @Id
    @GeneratedValue
    private int codMarca;
    private String descricaoMarca;
    
    @OneToMany(mappedBy = "marca")
    private List<Carro> carros;

    public Marca(int codMarca, String descricaoMarca) {
            this.codMarca = codMarca;
            this.descricaoMarca = descricaoMarca;
    }

    public Marca() {
            this.codMarca = 0;
            this.descricaoMarca = "";
    }

    public int getCodMarca() {
            return codMarca;
    }
    public void setCodMarca(int codMarca) {
            this.codMarca = codMarca;
    }
    public String getDescricaoMarca() {
            return descricaoMarca;
    }
    public void setDescricaoMarca(String descricaoMarca) {
            this.descricaoMarca = descricaoMarca;
    }

    public String toString(){
            return codMarca + " - " + descricaoMarca;
    }

    public boolean equals(Object obj){
            if(obj instanceof Marca){
                    Marca m = (Marca) obj;
                            if (m.codMarca == this.codMarca)
                                    return true;
            }
            return false;
    }
}
