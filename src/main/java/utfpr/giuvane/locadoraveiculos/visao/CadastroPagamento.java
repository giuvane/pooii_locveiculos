package utfpr.giuvane.locadoraveiculos.visao;

import utfpr.giuvane.locadoraveiculos.modelo.rn.PagamentoRN;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.CadastroPadrao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCliente;
import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaLocacao;
import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaPagamento;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Locacao;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Pagamento;
import utfpr.giuvane.locadoraveiculos.modelo.factory.ObjectFactory;
import utfpr.giuvane.locadoraveiculos.modelo.rn.ClienteRN;
import utfpr.giuvane.locadoraveiculos.modelo.rn.LocacaoRN;
import utfpr.giuvane.locadoraveiculos.modelo.util.SelecionaEntidade;

public class CadastroPagamento extends CadastroPadrao {

    private static final long serialVersionUID = 1L;

    private JPanel jpCampos;
    private JLabel jlCodigo, jlLocacao, jlCliente, jlMulta;

    private JTextField jtfCodigo, jtfMulta;
    private JComboBox jtfLocacao, jtfCliente;

    private PagamentoRN pagamentoRN = new PagamentoRN();
    private LocacaoRN locacaoRN = new LocacaoRN();
    private ClienteRN clienteRN = new ClienteRN();
    //private PersistenciaPagamento  pPag = new PersistenciaPagamento();
    
    private Pagamento pag = new Pagamento();
    private List<Locacao> locacoes;
    private List<Cliente> clientes;

    public CadastroPagamento() {
        super("Pagamento", true, true, true, true);

        jpCampos = new JPanel();

        jlCodigo = new JLabel("Código");
        jtfCodigo = new JTextField(10);
        jtfCodigo.setEditable(false);
        jlLocacao = new JLabel("Locação");
        jtfLocacao = new JComboBox();
        jlCliente = new JLabel("Cliente");
        jtfCliente = new JComboBox();
        jlMulta = new JLabel("Valor Multa");
        jtfMulta = new JTextField(8);

        this.setLayout(new BorderLayout());
        jpCampos.setLayout(null);
        jpCampos.add(jlCodigo);
        jpCampos.add(jtfCodigo);
        jpCampos.add(jlLocacao);
        jpCampos.add(jtfLocacao);
        jpCampos.add(jlCliente);
        jpCampos.add(jtfCliente);
        jpCampos.add(jlMulta);
        jpCampos.add(jtfMulta);

        // Combobox Locação
        ComboBoxModel modelo;
        try {
            Vector linhas = new Vector();
            locacoes = locacaoRN.buscarTodos();

            for (Locacao l : locacoes) {
                Vector linha = new Vector();
                //linha.add(c.getCodCargo());
                linha.add(l.getDtLocacao());
                linhas.add(linha);
            }
            modelo = new DefaultComboBoxModel(linhas);
            jtfLocacao.setModel(modelo);
        } catch (Exception e) {

            e.printStackTrace();
        }

        // Combobox Cliente
        ComboBoxModel modelo2;
        try {
            Vector linhas = new Vector();
            clientes = clienteRN.buscarTodos();

            for (Cliente c : clientes) {
                Vector linha = new Vector();
                //linha.add(c.getCodCargo());
                linha.add(c.getNome());
                linhas.add(linha);
            }
            modelo2 = new DefaultComboBoxModel(linhas);
            jtfCliente.setModel(modelo2);
        } catch (Exception e) {

            e.printStackTrace();
        }

        // posicao dos componentes
        jlCodigo.setBounds(15, 30, 65, 25);  // MD, MS, Lrg ,Alt
        jtfCodigo.setBounds(100, 30, 100, 25);
        jlLocacao.setBounds(15, 60, 65, 25);
        jtfLocacao.setBounds(100, 60, 150, 25);
        jlCliente.setBounds(15, 90, 65, 25);
        jtfCliente.setBounds(100, 90, 150, 25);
        jlMulta.setBounds(15, 120, 65, 25);
        jtfMulta.setBounds(100, 120, 100, 25);

        this.add(jpBotoes, BorderLayout.SOUTH);
        jpBotoes.setVisible(true);

        this.add(jpCampos);
        this.setVisible(true);
        this.pack();

        jbGravar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                pagamentoRN.gravar(getObjetoFromCampos());
                //pPag.gravar(getObjetoFromCampos());
                JOptionPane.showMessageDialog(null, "Pagamento efetuado!");
                limpaCampos();
            }
        }
        );

        // ==============================================================
        jbAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (camposValidos()) {
                    getObjetoFromCampos();
                    //pPag.alterar(getObjetoFromCampos());
                    pagamentoRN.excluir(getObjetoFromCampos());
                    limpaCampos();
                    JOptionPane.showMessageDialog(null, "Pagamento alterado!");
                }
            }
        }
        );

        //=================================================================
        jbExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //pPag.excluir(getObjetoFromCampos());
                pagamentoRN.excluir(getObjetoFromCampos());
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Pagamento excluido com sucesso!");
            }
        }
        );

        //=================================================================
        jbBuscar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                ConsultaPagamento cPag = new ConsultaPagamento();
                cPag.setModal(true);
                cPag.setVisible(true);
                if (cPag.getObjetoSelecionado() != null) {
                    preencheCampos(cPag.getObjetoSelecionado());
                }

            }
        }
        );
    }

    public boolean camposValidos() {
        if (jtfCodigo.getText() == "");
        return true;
    }

    ;

	public Pagamento getObjetoFromCampos() {

        //pag.setCodPagamento(Integer.parseInt(jtfCodigo.getText()));
        pag.setLocacao((Locacao) SelecionaEntidade.getLocacaoSelecionada(locacoes, jtfLocacao.getSelectedIndex()));
        pag.setCliente((Cliente) SelecionaEntidade.getClienteSelecionado(clientes, jtfCliente.getSelectedIndex()));
        pag.setMulta(new Float(jtfMulta.getText()).floatValue());

        return pag;
    }

    ;
	
	public void limpaCampos() {
        this.jtfCodigo.setText("");
        if (jtfLocacao.getItemCount() > 0) {
            this.jtfLocacao.setSelectedIndex(0);
        }
        if (jtfCliente.getItemCount() > 0) {
            this.jtfCliente.setSelectedIndex(0);
        }
        this.jtfMulta.setText("");
    }

    ;

	public void preencheCampos(Object obj) {
        limpaCampos();
        Pagamento pag = (Pagamento) obj;

        this.jtfCodigo.setText(String.valueOf(pag.getCodPagamento()));
        this.jtfLocacao.setSelectedItem(pag.getLocacao());
        this.jtfCliente.setSelectedItem(pag.getCliente());
        this.jtfMulta.setText(String.valueOf(pag.getMulta()));
    }

}
