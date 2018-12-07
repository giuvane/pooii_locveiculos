package utfpr.giuvane.locadoraveiculos.visao;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.ConsultaPadrao;

import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaPagamento;
import utfpr.giuvane.locadoraveiculos.modelo.rn.PagamentoRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Pagamento;

public class ConsultaPagamento extends ConsultaPadrao {
    // atributos

    private List<Pagamento> pagamentos;
    //private static PersistenciaPagamento pPag;
    private PagamentoRN pagamentoRN;

    // metodos
    public Vector getCabecalho() {
        Vector cab = new Vector();
        cab.add("Código");
        cab.add("Locação");
        cab.add("Cliente");
        cab.add("Valor Multa");
        return cab;
    }

    public Vector getLinhas() {
        Vector linhas = new Vector();
        pagamentoRN = new PagamentoRN();
        pagamentos = pagamentoRN.buscarTodos();
        
        for (int i = 0; i < pagamentos.size(); i++) {
            Pagamento pag = (Pagamento) pagamentos.get(i);
            Vector linha = new Vector();
            linha.add(pag.getCodPagamento());
            linha.add(pag.getLocacao());
            linha.add(pag.getCliente());
            linha.add(pag.getMulta());
            linhas.add(linha);
        }
        return linhas;
    }

    public Object getObjetoSelecionado(int posicao) {
        Pagamento pag = new Pagamento();
        pag = (Pagamento) pagamentos.get(posicao);
        return pag;
    }
}
