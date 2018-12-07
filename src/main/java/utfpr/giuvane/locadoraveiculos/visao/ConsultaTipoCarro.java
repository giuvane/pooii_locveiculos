package utfpr.giuvane.locadoraveiculos.visao;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.ConsultaPadrao;

import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaTipoCarro;
import utfpr.giuvane.locadoraveiculos.modelo.rn.TipoCarroRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.TipoCarro;

public class ConsultaTipoCarro extends ConsultaPadrao {

    // atributos
    //private Vector tipos;
    private List<TipoCarro> tipos;
    //private static PersistenciaTipoCarro pTc;
    private TipoCarroRN tipoCarroRN;

    // metodos
    public Vector getCabecalho() {
        Vector cab = new Vector();
        cab.add("Código");
        cab.add("Descrição");
        return cab;
    }

    public Vector getLinhas() {
        Vector linhas = new Vector();
        tipoCarroRN = new TipoCarroRN();
        tipos = tipoCarroRN.buscarTodos();

        for (TipoCarro tc : tipos) {
            Vector linha = new Vector();
            linha.add(tc.getCodTipoCarro());
            linha.add(tc.getDescricaoTipoCarro());
            linhas.add(linha);
        }

        return linhas;
    }

    public Object getObjetoSelecionado(int posicao) {
        TipoCarro tc = new TipoCarro();
        tc = (TipoCarro) tipos.get(posicao);
        return tc;
    }
}
