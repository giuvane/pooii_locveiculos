package utfpr.giuvane.locadoraveiculos.modelo.vo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cargo implements Serializable {
        @Id
        @GeneratedValue
	private int codCargo;
	private String descricaoCargo;
        
        @OneToMany(mappedBy = "cargo")
        private List<Funcionario> funcionarios;
	
	public Cargo(int codCargo, String descricaoCargo) {
		this.codCargo = codCargo;
		this.descricaoCargo = descricaoCargo;
	}
	
	public Cargo() {
		this.codCargo = 0;
		this.descricaoCargo = "";
	}

	public int getCodCargo() {
		return codCargo;
	}
	public void setCodCargo(int codCargo) {
		this.codCargo = codCargo;
	}
	public String getDescricaoCargo() {
		return descricaoCargo;
	}
	public void setDescricaoCargo(String descricaoCargo) {
		this.descricaoCargo = descricaoCargo;
	}
	
	public String toString(){
		return codCargo + " - " + descricaoCargo;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Cargo){
			Cargo c = (Cargo) obj;
				if (c.codCargo == this.codCargo)
					return true;
		}
		return false;
	}

}