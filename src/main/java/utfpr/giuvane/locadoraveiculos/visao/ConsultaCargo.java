package utfpr.giuvane.locadoraveiculos.visao;

import java.util.ArrayList;
import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.ConsultaPadrao;

import java.util.Vector;
import utfpr.giuvane.locadoraveiculos.modelo.rn.CargoRN;
import utfpr.giuvane.locadoraveiculos.modelo.dao.GenericDAO;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCargo;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;

public class ConsultaCargo extends ConsultaPadrao {

    // atributos
    //private Vector cargos;
    private List<Cargo> cargoss;
    //private static PersistenciaCargo pCa;
    private CargoRN cargoRN;

    // metodos
    public Vector getCabecalho() {
        Vector cab = new Vector();
        cab.add("Código");
        cab.add("Descrição");
        return cab;
    }

    public Vector getLinhas() {
        Vector linhas = new Vector();
        cargoRN = new CargoRN();
        cargoss = cargoRN.buscarTodos();
        /*
	    try {
			cargos = pCa.buscaTudo(Cargo.class);
		} catch (CadastroNaoExisteException e) {
			e.printStackTrace();
		}
         */

        for (Cargo c : cargoss) {
            Vector linha = new Vector();
            linha.add(c.getCodCargo());
            linha.add(c.getDescricaoCargo());
            linhas.add(linha);
        }
        /*  
	    for(int i=0; i<cargos.size(); i++)
	    {
	    	Cargo c = (Cargo) cargos.get(i);
		    Vector linha = new Vector();
		    linha.add(c.getCodCargo());
		    linha.add(c.getDescricaoCargo());
		    linhas.add(linha);
	    }
         */
        return linhas;
    }

    public Object getObjetoSelecionado(int posicao) {
        Cargo c = new Cargo();
        c = (Cargo) cargoss.get(posicao);
        return c;
    }
}
