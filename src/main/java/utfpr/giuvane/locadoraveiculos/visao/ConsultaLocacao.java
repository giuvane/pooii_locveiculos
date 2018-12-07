package utfpr.giuvane.locadoraveiculos.visao;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.ConsultaPadrao;

import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaLocacao;
import utfpr.giuvane.locadoraveiculos.modelo.rn.LocacaoRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Locacao;

public class ConsultaLocacao extends ConsultaPadrao {
    // atributos

    private List<Locacao> locacoes;
    //private static PersistenciaLocacao pLoc;
    private LocacaoRN locacaoRN;

    // metodos
    public Vector getCabecalho() {
        Vector cab = new Vector();
        cab.add("C�digo");
        cab.add("Carro");
        cab.add("Cliente");
        cab.add("Data de Loca��o");
        cab.add("Data de Entrega");
        cab.add("Valor");
        return cab;
    }

    public Vector getLinhas() {
        Vector linhas = new Vector();
        locacaoRN = new LocacaoRN();
        locacoes = locacaoRN.buscarTodos();

        for (int i = 0; i < locacoes.size(); i++) {
            Locacao l = (Locacao) locacoes.get(i);
            Vector linha = new Vector();
            linha.add(l.getCodLocacao());
            linha.add(l.getCarro());
            linha.add(l.getCliente());
            linha.add(l.getDtLocacao());
            linha.add(l.getDtEntrega());
            linha.add(l.getValor());
            linhas.add(linha);
        }
        return linhas;
    }

    public Object getObjetoSelecionado(int posicao) {
        Locacao loc = new Locacao();
        loc = (Locacao) locacoes.get(posicao);
        return loc;
    }
}
