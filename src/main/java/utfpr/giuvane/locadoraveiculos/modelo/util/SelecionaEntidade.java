/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.util;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Carro;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Locacao;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;
import utfpr.giuvane.locadoraveiculos.modelo.vo.TipoCarro;

/**
 *
 * @author Giuvane Conti
 */
public class SelecionaEntidade {
    public static Object getMarcaSelecionada(List<Marca> marcas, int posicao) {
        
        Marca m = new Marca();
        m = (Marca) marcas.get(posicao);

        return m;
    }
    
    public static Object getTipoCarroSelecionado(List<TipoCarro> tipoCarros, int posicao) {
        TipoCarro tc = new TipoCarro();
        tc = (TipoCarro) tipoCarros.get(posicao);

        return tc;
    }
    
    public static Object getCarroSelecionado(List<Carro> carro, int posicao) {
        Carro c = new Carro();
        c = (Carro) carro.get(posicao);

        return c;
    }
    
    public static Object getClienteSelecionado(List<Cliente> clientes, int posicao) {
        Cliente c = new Cliente();
        c = (Cliente) clientes.get(posicao);

        return c;
    }
    
    public static Object getLocacaoSelecionada(List<Locacao> locacoes, int posicao) {
        Locacao l = new Locacao();
        l = (Locacao) locacoes.get(posicao);

        return l;
    }
}
