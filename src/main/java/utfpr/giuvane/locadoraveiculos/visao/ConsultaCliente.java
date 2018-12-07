package utfpr.giuvane.locadoraveiculos.visao;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.ConsultaPadrao;

import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCliente;
import utfpr.giuvane.locadoraveiculos.modelo.rn.ClienteRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;

public class ConsultaCliente extends ConsultaPadrao {
    // atributos

    private List<Cliente> clientes;
    //private static PersistenciaCliente pCli;
    private ClienteRN clienteRN;

    // metodos
    public Vector getCabecalho() {
        Vector cab = new Vector();
        cab.add("C�digo");
        cab.add("Nome");
        cab.add("RG");
        cab.add("CPF");
        cab.add("Fone");
        cab.add("Endere�o");
        return cab;
    }

    public Vector getLinhas() {
        Vector linhas = new Vector();
        clienteRN = new ClienteRN();
        clientes = clienteRN.buscarTodos();

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = (Cliente) clientes.get(i);
            Vector linha = new Vector();
            linha.add(c.getCodCliente());
            linha.add(c.getNome());
            linha.add(c.getRg());
            linha.add(c.getCpf());
            linha.add(c.getFone());
            linha.add(c.getEnd());
            linhas.add(linha);
        }
        return linhas;
    }

    public Object getObjetoSelecionado(int posicao) {
        Cliente cli = new Cliente();
        cli = (Cliente) clientes.get(posicao);
        return cli;
    }
}
