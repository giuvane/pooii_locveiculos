package utfpr.giuvane.locadoraveiculos.visao;

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

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCarro;
import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaMarca;
import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaTipoCarro;
import utfpr.giuvane.locadoraveiculos.modelo.rn.CarroRN;
import utfpr.giuvane.locadoraveiculos.modelo.rn.MarcaRN;
import utfpr.giuvane.locadoraveiculos.modelo.rn.TipoCarroRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Carro;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Passeio;
import utfpr.giuvane.locadoraveiculos.modelo.vo.TipoCarro;

public class CadastroCarro extends CadastroPadrao {

    private static final long serialVersionUID = 1L;

    private JPanel jpCampos;
    private JLabel jlCodigo, jlMarca, jlTipoCarro, jlPlaca, jlDescricao;

    private JTextField jtfCodigo, jtfPlaca, jtfDescricao;
    private JComboBox jtfMarca, jtfTipoCarro;

    //private PersistenciaCarro  pCarro = new PersistenciaCarro();
    private CarroRN carroRN = new CarroRN();
    private TipoCarroRN tcRN = new TipoCarroRN();
    private MarcaRN marcaRN = new MarcaRN();

    private Carro carro = new Passeio();
    private List<Marca> marcas;
    private List<TipoCarro> tipoCarros;

    public CadastroCarro() {
        super("Cadastro de Carro", true, true, true, true);

        jpCampos = new JPanel();

        jlCodigo = new JLabel("C�digo");
        jtfCodigo = new JTextField(10);
        jtfCodigo.setEditable(false);
        jlMarca = new JLabel("Marca");
        jtfMarca = new JComboBox();
        jlTipoCarro = new JLabel("Tipo de Carro");
        jtfTipoCarro = new JComboBox();
        jlPlaca = new JLabel("Placa");
        jtfPlaca = new JTextField(30);
        jlDescricao = new JLabel("Descri��o");
        jtfDescricao = new JTextField(8);

        this.setLayout(new BorderLayout());
        jpCampos.setLayout(null);
        jpCampos.add(jlCodigo);
        jpCampos.add(jtfCodigo);
        jpCampos.add(jlMarca);
        jpCampos.add(jtfMarca);
        jpCampos.add(jlTipoCarro);
        jpCampos.add(jtfTipoCarro);
        jpCampos.add(jlPlaca);
        jpCampos.add(jtfPlaca);
        jpCampos.add(jlDescricao);
        jpCampos.add(jtfDescricao);

        //PersistenciaMarca pm = new PersistenciaMarca();
        ComboBoxModel modelo;
        try {
            Vector linhas = new Vector();
            marcas = marcaRN.buscarTodos();

            for (Marca m : marcas) {
                Vector linha = new Vector();
                //linha.add(c.getCodCargo());
                linha.add(m.getDescricaoMarca());
                linhas.add(linha);
            }
            modelo = new DefaultComboBoxModel(linhas);
            jtfMarca.setModel(modelo);
        } catch (Exception e) {

            e.printStackTrace();
        }

        //PersistenciaTipoCarro ptc = new PersistenciaTipoCarro();
        ComboBoxModel modelo2;
        try {
            Vector linhas = new Vector();
            tipoCarros = tcRN.buscarTodos();

            for (TipoCarro tc : tipoCarros) {
                Vector linha = new Vector();
                //linha.add(c.getCodCargo());
                linha.add(tc.getDescricaoTipoCarro());
                linhas.add(linha);
            }
            modelo2 = new DefaultComboBoxModel(linhas);
            jtfTipoCarro.setModel(modelo2);
        } catch (Exception e) {

            e.printStackTrace();
        }

        // posicao dos componentes
        jlCodigo.setBounds(15, 30, 65, 25);  // MD, MS, Lrg ,Alt
        jtfCodigo.setBounds(100, 30, 100, 25);
        jlMarca.setBounds(15, 60, 65, 25);
        jtfMarca.setBounds(100, 60, 150, 25);
        jlTipoCarro.setBounds(15, 90, 80, 25);
        jtfTipoCarro.setBounds(100, 90, 150, 25);
        jlPlaca.setBounds(15, 120, 65, 25);
        jtfPlaca.setBounds(100, 120, 100, 25);
        jlDescricao.setBounds(15, 150, 65, 25);
        jtfDescricao.setBounds(100, 150, 100, 25);

        this.add(jpBotoes, BorderLayout.SOUTH);
        jpBotoes.setVisible(true);

        this.add(jpCampos);
        this.setVisible(true);
        this.pack();

        jbGravar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                carroRN.gravar(getObjetoFromCampos());
                JOptionPane.showMessageDialog(null, "O Carro foi cadastrado com sucesso!");
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
                    carroRN.gravar(getObjetoFromCampos());
                    limpaCampos();
                    JOptionPane.showMessageDialog(null, "O Carro foi alterado com sucesso!");
                }
            }
        }
        );

        //=================================================================
        jbExcluir.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                carroRN.remover(getObjetoFromCampos());
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Carro excluido com sucesso!");
            }
        }
        );

        //=================================================================
        jbBuscar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                ConsultaCarro cCarro = new ConsultaCarro();
                cCarro.setModal(true);
                cCarro.setVisible(true);
                if (cCarro.getObjetoSelecionado() != null) {
                    preencheCampos(cCarro.getObjetoSelecionado());
                }

            }
        }
        );
    }

    public boolean camposValidos() {
        if (jtfCodigo.getText() == "" || jtfDescricao.getText() == "");
        return true;
    }

    ;

	public Carro getObjetoFromCampos() {

        //carro.setCodCarro(Integer.parseInt(jtfCodigo.getText()));
        carro.setMarca((Marca) getMarcaSelecionado(jtfMarca.getSelectedIndex()));
        carro.setTipoCarro((TipoCarro) getTipoCarroSelecionado(jtfTipoCarro.getSelectedIndex()));
        carro.setPlaca(jtfPlaca.getText());
        carro.setDescricaoCarro(jtfDescricao.getText());

        return carro;
    }

    ;
	
	public void limpaCampos() {
        this.jtfCodigo.setText("");
        if (jtfMarca.getItemCount() > 0) {
            this.jtfMarca.setSelectedIndex(0);
        }
        if (jtfTipoCarro.getItemCount() > 0) {
            this.jtfTipoCarro.setSelectedIndex(0);
        }
        this.jtfPlaca.setText("");
        this.jtfDescricao.setText("");
    }

    ;

	public void preencheCampos(Object obj) {
        limpaCampos();
        Carro c = (Carro) obj;

        this.jtfCodigo.setText(String.valueOf(c.getCodCarro()));
        this.jtfMarca.setSelectedItem(c.getMarca());
        this.jtfTipoCarro.setSelectedItem(c.getTipoCarro());
        this.jtfPlaca.setText(c.getPlaca());
        this.jtfDescricao.setText(c.getDescricaoCarro());
    }

    public Object getMarcaSelecionado(int posicao) {
        Marca m = new Marca();
        m = (Marca) marcas.get(posicao);

        return m;
    }
    
    public Object getTipoCarroSelecionado(int posicao) {
        TipoCarro tc = new TipoCarro();
        tc = (TipoCarro) tipoCarros.get(posicao);

        return tc;
    }

}
