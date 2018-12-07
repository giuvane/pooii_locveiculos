package utfpr.giuvane.locadoraveiculos.visao;

import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.visao.modelo.CadastroPadrao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCarro;
import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCliente;
import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaLocacao;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Carro;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Locacao;

import com.toedter.calendar.JDateChooser;
import java.util.List;
import java.util.Vector;
import utfpr.giuvane.locadoraveiculos.modelo.rn.CarroRN;
import utfpr.giuvane.locadoraveiculos.modelo.rn.ClienteRN;
import utfpr.giuvane.locadoraveiculos.modelo.rn.LocacaoRN;
import utfpr.giuvane.locadoraveiculos.modelo.util.SelecionaEntidade;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;

public class CadastroLocacao extends CadastroPadrao {

    private static final long serialVersionUID = 1L;

    private JPanel jpCampos;
    private JLabel jlCodigo, jlCarro, jlCliente, jlDtLocacao, jlDtEntrega, jlValor;

    private JTextField jtfCodigo, jtfValor;
    private JComboBox jtfCarro, jtfCliente;
    private JDateChooser jtfDtLocacao, jtfDtEntrega;

    //private PersistenciaLocacao  pLoc = new PersistenciaLocacao();
    private LocacaoRN locacaoRN = new LocacaoRN();
    private CarroRN carroRN = new CarroRN();
    private ClienteRN clienteRN = new ClienteRN();

    private Locacao loc = new Locacao();
    private List<Carro> carros;
    private List<Cliente> clientes;

    public CadastroLocacao() {
        super("Cadastro de Loca��o", true, true, true, true);

        jpCampos = new JPanel();

        //new SimpleDateFormat("dd/MM/yyyy").format(<data>);
        jlCodigo = new JLabel("Código");
        jtfCodigo = new JTextField(10);
        jtfCodigo.setEditable(false);
        jlCarro = new JLabel("Carro");
        jtfCarro = new JComboBox();
        jlCliente = new JLabel("Cliente");
        jtfCliente = new JComboBox();
        jlDtLocacao = new JLabel("Data de Loca��o");
        jtfDtLocacao = new JDateChooser(Calendar.getInstance().getTime());
        jlDtEntrega = new JLabel("Data de Entrega");
        jtfDtEntrega = new JDateChooser(Calendar.getInstance().getTime());
        jlValor = new JLabel("Valor");
        jtfValor = new JTextField(10);

        this.setLayout(new BorderLayout());
        jpCampos.setLayout(null);
        jpCampos.add(jlCodigo);
        jpCampos.add(jtfCodigo);
        jpCampos.add(jlCarro);
        jpCampos.add(jtfCarro);
        jpCampos.add(jlCliente);
        jpCampos.add(jtfCliente);
        jpCampos.add(jlDtLocacao);
        jpCampos.add(jtfDtLocacao);
        jpCampos.add(jlDtEntrega);
        jpCampos.add(jtfDtEntrega);
        jpCampos.add(jlValor);
        jpCampos.add(jtfValor);

        // Combobox Carro
        ComboBoxModel modelo;
        try {
            Vector linhas = new Vector();
            carros = carroRN.buscarTodos();

            for (Carro c : carros) {
                Vector linha = new Vector();
                //linha.add(c.getCodCargo());
                linha.add(c.getDescricaoCarro());
                linhas.add(linha);
            }
            modelo = new DefaultComboBoxModel(linhas);
            jtfCarro.setModel(modelo);
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
        jtfCodigo.setBounds(120, 30, 100, 25);
        jlCarro.setBounds(15, 60, 65, 25);
        jtfCarro.setBounds(120, 60, 150, 25);
        jlCliente.setBounds(15, 90, 65, 25);
        jtfCliente.setBounds(120, 90, 150, 25);
        jlDtLocacao.setBounds(15, 120, 100, 25);
        jtfDtLocacao.setBounds(120, 120, 100, 25);
        jlDtEntrega.setBounds(15, 150, 100, 25);
        jtfDtEntrega.setBounds(120, 150, 100, 25);
        jlValor.setBounds(15, 180, 65, 25);
        jtfValor.setBounds(120, 180, 100, 25);

        this.add(jpBotoes, BorderLayout.SOUTH);
        jpBotoes.setVisible(true);

        this.add(jpCampos);
        this.setVisible(true);
        this.pack();

        jbGravar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                locacaoRN.gravar(getObjetoFromCampos());
                JOptionPane.showMessageDialog(null, "Loca��o Cadastrada com sucesso!");
                limpaCampos();
            }
        }
        );

        // ==============================================================
        jbAlterar.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (camposValidos()) {
                    getObjetoFromCampos();
                    locacaoRN.gravar(getObjetoFromCampos());
                    limpaCampos();
                    JOptionPane.showMessageDialog(null, "Loca��o Alterada com sucesso!");
                }
            }
        }
        );

        //=================================================================
        jbExcluir.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                locacaoRN.remover(getObjetoFromCampos());
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Loca��o excluida com sucesso!");
            }
        }
        );

        //=================================================================
        jbBuscar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                ConsultaLocacao cLoc = new ConsultaLocacao();
                cLoc.setModal(true);
                cLoc.setVisible(true);
                if (cLoc.getObjetoSelecionado() != null) {
                    preencheCampos(cLoc.getObjetoSelecionado());
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

	public Locacao getObjetoFromCampos() {

        //loc.setCodLocacao(Integer.parseInt(jtfCodigo.getText()));
        loc.setCarro((Carro) SelecionaEntidade.getCarroSelecionado(carros, jtfCarro.getSelectedIndex()));
        loc.setCliente((Cliente) SelecionaEntidade.getClienteSelecionado(clientes, jtfCliente.getSelectedIndex()));
        loc.setDtLocacao(jtfDtLocacao.getDate());
        loc.setDtEntrega(jtfDtEntrega.getDate());
        loc.setValor(new Float(jtfValor.getText()).floatValue());

        return loc;
    }

    ;
	
	public void limpaCampos() {
        this.jtfCodigo.setText("");
        if (jtfCarro.getItemCount() > 0) {
            this.jtfCarro.setSelectedIndex(0);
        }
        if (jtfCliente.getItemCount() > 0) {
            this.jtfCliente.setSelectedIndex(0);
        }
        this.jtfDtLocacao.setDate(Calendar.getInstance().getTime());
        this.jtfDtEntrega.setDate(Calendar.getInstance().getTime());
        this.jtfValor.setText("");
    }

    ;

	public void preencheCampos(Object obj) {
        limpaCampos();
        Locacao loc = (Locacao) obj;

        this.jtfCodigo.setText(String.valueOf(loc.getCodLocacao()));
        this.jtfCarro.setSelectedItem(loc.getCarro());
        this.jtfCliente.setSelectedItem(loc.getCliente());
        this.jtfDtLocacao.setDate(loc.getDtLocacao());
        this.jtfDtEntrega.setDate(loc.getDtEntrega());
        this.jtfValor.setText(String.valueOf(loc.getValor()));
    }

}
