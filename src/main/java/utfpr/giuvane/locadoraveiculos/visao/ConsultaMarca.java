package utfpr.giuvane.locadoraveiculos.visao;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.ConsultaPadrao;

import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaMarca;
import utfpr.giuvane.locadoraveiculos.modelo.rn.MarcaRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;

public class ConsultaMarca extends ConsultaPadrao {

    // atributos
    //private Vector marcas;
    private List<Marca> marcass;
    private MarcaRN marcaRN;

    // metodos
    public Vector getCabecalho() {
        Vector cab = new Vector();
        cab.add("Código");
        cab.add("Descrição");
        return cab;
    }

    public Vector getLinhas() {

        Vector linhas = new Vector();
        marcaRN = new MarcaRN();
        marcass = marcaRN.buscarTodos();

        for (Marca m : marcass) {
            Vector linha = new Vector();
            linha.add(m.getCodMarca());
            linha.add(m.getDescricaoMarca());
            linhas.add(linha);
        }

        return linhas;
    }

    public Object getObjetoSelecionado(int posicao) {
        Marca m = new Marca();
        m = (Marca) marcass.get(posicao);
        return m;
    }
}
