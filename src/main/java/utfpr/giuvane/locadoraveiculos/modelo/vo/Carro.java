package utfpr.giuvane.locadoraveiculos.modelo.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Generated;

@Entity
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
public class Carro {
        @Id
        @GeneratedValue
	private int codCarro;
	
        @ManyToOne
        private Marca marca;
        
        @ManyToOne
	private TipoCarro tipoCarro;
        
	private String placa; 
        
	private String descricaoCarro;
        
        @OneToMany(mappedBy = "carro")
        private List<Locacao> locacoes;
	
	public Carro(int codCarro, Marca marca, TipoCarro tipoCarro, String placa,
			String descricaoCarro) {
		this.codCarro = codCarro;
		this.marca = marca;
		this.tipoCarro = tipoCarro;
		this.placa = placa;
		this.descricaoCarro = descricaoCarro;
	}
	
	public Carro() {
		// TODO Auto-generated constructor stub
	}

	public int getCodCarro() {
		return codCarro;
	}
	public void setCodCarro(int codCarro) {
		this.codCarro = codCarro;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public TipoCarro getTipoCarro() {
		return tipoCarro;
	}
	public void setTipoCarro(TipoCarro tipoCarro) {
		this.tipoCarro = tipoCarro;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getDescricaoCarro() {
		return descricaoCarro;
	}
	public void setDescricaoCarro(String descricaoCarro) {
		this.descricaoCarro = descricaoCarro;
	}
	
	public String toString(){
		return codCarro + " - " + descricaoCarro;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Carro){
			Carro c = (Carro) obj;
				if (c.codCarro == this.codCarro)
					return true;
		}
		return false;
	}
}
