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

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCargo;
import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaFuncionario;
import utfpr.giuvane.locadoraveiculos.modelo.rn.CargoRN;
import utfpr.giuvane.locadoraveiculos.modelo.rn.FuncionarioRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Funcionario;

public class CadastroFuncionario extends CadastroPadrao {

    private static final long serialVersionUID = 1L;

    private JPanel jpCampos;
    private JLabel jlCodigo, jlCargo, jlNome, jlRg, jlCpf, jlFone, jlEndereco;

    private JTextField jtfCodigo, jtfNome, jtfRg, jtfCpf, jtfFone, jtfEndereco;
    private JComboBox jtfCargo;

    //private PersistenciaFuncionario  pFun = new PersistenciaFuncionario();
    private FuncionarioRN funcionarioRN = new FuncionarioRN();
    private CargoRN cargoRN = new CargoRN();
    private Funcionario fun = new Funcionario();
    private List<Cargo> cargos;

    public CadastroFuncionario() {
        super("Cadastro de Funcionario", true, true, true, true);

        jpCampos = new JPanel();

        jlCodigo = new JLabel("Código");
        jtfCodigo = new JTextField(10);
        jtfCodigo.setEditable(false);
        jlCargo = new JLabel("Cargo");
        jtfCargo = new JComboBox();
        jlNome = new JLabel("Nome");
        jtfNome = new JTextField(30);
        jlRg = new JLabel("RG");
        jtfRg = new JTextField(8);
        jlCpf = new JLabel("CPF");
        jtfCpf = new JTextField(11);
        jlFone = new JLabel("Fone");
        jtfFone = new JTextField(10);
        jlEndereco = new JLabel("Endere�o");
        jtfEndereco = new JTextField(8);

        this.setLayout(new BorderLayout());
        jpCampos.setLayout(null);
        jpCampos.add(jlCodigo);
        jpCampos.add(jtfCodigo);
        jpCampos.add(jlCargo);
        jpCampos.add(jtfCargo);
        jpCampos.add(jlNome);
        jpCampos.add(jtfNome);
        jpCampos.add(jlRg);
        jpCampos.add(jtfRg);
        jpCampos.add(jlCpf);
        jpCampos.add(jtfCpf);
        jpCampos.add(jlFone);
        jpCampos.add(jtfFone);
        jpCampos.add(jlEndereco);
        jpCampos.add(jtfEndereco);

        ComboBoxModel modelo;
        try {
            Vector linhas = new Vector();
            cargos = cargoRN.buscarTodos();

            for (Cargo c : cargos) {
                Vector linha = new Vector();
                //linha.add(c.getCodCargo());
                linha.add(c.getDescricaoCargo());
                linhas.add(linha);
            }
            modelo = new DefaultComboBoxModel(linhas);
            jtfCargo.setModel(modelo);
        } catch (Exception e) {

            e.printStackTrace();
        }

        // posicao dos componentes
        jlCodigo.setBounds(15, 30, 65, 25);  // MD, MS, Lrg ,Alt
        jtfCodigo.setBounds(100, 30, 100, 25);
        jlCargo.setBounds(15, 60, 65, 25);
        jtfCargo.setBounds(100, 60, 150, 25);
        jlNome.setBounds(15, 90, 65, 25);
        jtfNome.setBounds(100, 90, 100, 25);
        jlRg.setBounds(15, 120, 65, 25);
        jtfRg.setBounds(100, 120, 100, 25);
        jlCpf.setBounds(15, 150, 65, 25);
        jtfCpf.setBounds(100, 150, 100, 25);
        jlFone.setBounds(15, 180, 65, 25);
        jtfFone.setBounds(100, 180, 100, 25);
        jlEndereco.setBounds(15, 210, 65, 25);
        jtfEndereco.setBounds(100, 210, 100, 25);

        this.add(jpBotoes, BorderLayout.SOUTH);
        jpBotoes.setVisible(true);

        this.add(jpCampos);
        this.setVisible(true);
        this.pack();

        jbGravar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                funcionarioRN.gravar(getObjetoFromCampos());
                JOptionPane.showMessageDialog(null, "O Funcionario foi cadastrado com sucesso!");
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
                    funcionarioRN.gravar(getObjetoFromCampos());
                    limpaCampos();
                    JOptionPane.showMessageDialog(null, "O Funcionario foi alterado com sucesso!");
                }
            }
        }
        );

        //=================================================================
        jbExcluir.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                funcionarioRN.remover(getObjetoFromCampos());
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Funcionario excluido com sucesso!");
            }
        }
        );

        //=================================================================
        jbBuscar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                ConsultaFuncionario cFun = new ConsultaFuncionario();
                cFun.setModal(true);
                cFun.setVisible(true);
                if (cFun.getObjetoSelecionado() != null) {
                    preencheCampos(cFun.getObjetoSelecionado());
                }

            }
        }
        );
    }

    public boolean camposValidos() {
        if (jtfCodigo.getText() == "" || jtfNome.getText() == "");
        return true;
    }

    ;

    public Funcionario getObjetoFromCampos() {

        //fun.setCodFuncionario(Integer.parseInt(jtfCodigo.getText()));
        fun.setCargo((Cargo) getObjetoSelecionado(jtfCargo.getSelectedIndex()));
        fun.setNome(jtfNome.getText());
        fun.setRg(jtfRg.getText());
        fun.setCpf(jtfCpf.getText());
        fun.setFone(jtfFone.getText());
        fun.setEnd(jtfEndereco.getText());

        return fun;
    }

    public void limpaCampos() {
        this.jtfCodigo.setText("");
        if (jtfCargo.getItemCount() > 0) {
            this.jtfCargo.setSelectedIndex(0);
        }
        this.jtfNome.setText("");
        this.jtfRg.setText("");
        this.jtfCpf.setText("");
        this.jtfFone.setText("");
        this.jtfEndereco.setText("");
    }

    public void preencheCampos(Object obj) {
        limpaCampos();
        Funcionario f = (Funcionario) obj;

        this.jtfCodigo.setText(String.valueOf(f.getCodFuncionario()));
        this.jtfCargo.setSelectedItem(f.getCargo());
        this.jtfNome.setText(f.getNome());
        this.jtfRg.setText(f.getRg());
        this.jtfCpf.setText(f.getCpf());
        this.jtfFone.setText(f.getFone());
        this.jtfEndereco.setText(f.getEnd());
    }

    public Object getObjetoSelecionado(int posicao) {
        Cargo c = new Cargo();
        c = (Cargo) cargos.get(posicao);

        return c;
    }
}
