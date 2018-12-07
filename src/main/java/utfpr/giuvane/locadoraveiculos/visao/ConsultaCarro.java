package utfpr.giuvane.locadoraveiculos.visao;

import static java.util.Collections.list;
import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.ConsultaPadrao;

import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCarro;
import utfpr.giuvane.locadoraveiculos.modelo.rn.CarroRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Carro;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Passeio;

public class ConsultaCarro extends ConsultaPadrao {
    // atributos

    private List<Carro> carros;
    //private static PersistenciaCarro pCarro;
    private CarroRN carroRN;

    // metodos
    public Vector getCabecalho() {
        Vector cab = new Vector();
        cab.add("Código");
        cab.add("Marca");
        cab.add("Tipo de Carro");
        cab.add("Placa");
        cab.add("Descrição");
        return cab;
    }

    public Vector getLinhas() {
        Vector linhas = new Vector();
        carroRN = new CarroRN();
        carros = carroRN.buscarTodos();

        for (int i = 0; i < carros.size(); i++) {
            Carro c = (Carro) carros.get(i);
            Vector linha = new Vector();
            linha.add(c.getCodCarro());
            linha.add(c.getMarca());
            linha.add(c.getTipoCarro());
            linha.add(c.getPlaca());
            linha.add(c.getDescricaoCarro());
            linhas.add(linha);
        }
        return linhas;
    }

    public Object getObjetoSelecionado(int posicao) {
        Carro carro = new Passeio();
        carro = (Carro) carros.get(posicao);
        return carro;
    }
}
