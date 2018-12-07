package utfpr.giuvane.locadoraveiculos.visao;

import java.util.List;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.ConsultaPadrao;

import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaFuncionario;
import utfpr.giuvane.locadoraveiculos.modelo.rn.FuncionarioRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Funcionario;

public class ConsultaFuncionario extends ConsultaPadrao {
    // atributos

    private List<Funcionario> funcionarios;
    //private static PersistenciaFuncionario pFun;
    private FuncionarioRN FuncionarioRN;

    // metodos
    public Vector getCabecalho() {
        Vector cab = new Vector();
        cab.add("C�digo");
        cab.add("Cargo");
        cab.add("Nome");
        cab.add("RG");
        cab.add("CPF");
        cab.add("Fone");
        cab.add("Endere�o");
        return cab;
    }

    public Vector getLinhas() {
        Vector linhas = new Vector();
        FuncionarioRN = new FuncionarioRN();
        funcionarios = FuncionarioRN.buscarTodos();

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario f = (Funcionario) funcionarios.get(i);
            Vector linha = new Vector();
            linha.add(f.getCodFuncionario());
            linha.add(f.getCargo());
            linha.add(f.getNome());
            linha.add(f.getRg());
            linha.add(f.getCpf());
            linha.add(f.getFone());
            linha.add(f.getEnd());
            linhas.add(linha);
        }
        return linhas;
    }

    public Object getObjetoSelecionado(int posicao) {
        Funcionario fun = new Funcionario();
        fun = (Funcionario) funcionarios.get(posicao);
        return fun;
    }
}
