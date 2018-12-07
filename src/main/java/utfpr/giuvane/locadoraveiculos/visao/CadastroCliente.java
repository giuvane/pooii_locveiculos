package utfpr.giuvane.locadoraveiculos.visao;

import utfpr.giuvane.locadoraveiculos.visao.modelo.CadastroPadrao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCliente;
import utfpr.giuvane.locadoraveiculos.modelo.rn.ClienteRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;

public class CadastroCliente extends CadastroPadrao {

    private static final long serialVersionUID = 1L;

    private JPanel jpCampos;
    private JLabel jlCodigo, jlNome, jlRg, jlCpf, jlFone, jlEndereco;

    private JTextField jtfCodigo, jtfNome, jtfRg, jtfCpf, jtfFone, jtfEndereco;

    //private PersistenciaCliente pCli = new PersistenciaCliente();
    private ClienteRN clienteRN = new ClienteRN();
    private Cliente cli = new Cliente();

    public CadastroCliente() {
        super("Cadastro de Cliente", true, true, true, true);

        jpCampos = new JPanel();

        jlCodigo = new JLabel("Código");
        jtfCodigo = new JTextField(10);
        jtfCodigo.setEditable(false);
        jlNome = new JLabel("Nome");
        jtfNome = new JTextField(30);
        jlRg = new JLabel("RG");
        jtfRg = new JTextField(8);
        jlCpf = new JLabel("CPF");
        jtfCpf = new JTextField(11);
        jlFone = new JLabel("Fone");
        jtfFone = new JTextField(10);
        jlEndereco = new JLabel("Endereço");
        jtfEndereco = new JTextField(8);

        this.setLayout(new BorderLayout());
        jpCampos.setLayout(null);
        jpCampos.add(jlCodigo);
        jpCampos.add(jtfCodigo);
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

        // posicao dos componentes
        jlCodigo.setBounds(15, 30, 65, 25);  // MD, MS, Lrg ,Alt
        jtfCodigo.setBounds(100, 30, 100, 25);
        jlNome.setBounds(15, 60, 65, 25);
        jtfNome.setBounds(100, 60, 100, 25);
        jlRg.setBounds(15, 90, 65, 25);
        jtfRg.setBounds(100, 90, 100, 25);
        jlCpf.setBounds(15, 120, 65, 25);
        jtfCpf.setBounds(100, 120, 100, 25);
        jlFone.setBounds(15, 150, 65, 25);
        jtfFone.setBounds(100, 150, 100, 25);
        jlEndereco.setBounds(15, 180, 65, 25);
        jtfEndereco.setBounds(100, 180, 100, 25);

        this.add(jpBotoes, BorderLayout.SOUTH);
        jpBotoes.setVisible(true);

        this.add(jpCampos);
        this.setVisible(true);
        this.pack();

        jbGravar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                clienteRN.gravar(getObjetoFromCampos());
                JOptionPane.showMessageDialog(null, "O Cliente foi cadastrado com sucesso!");
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
                    clienteRN.gravar(getObjetoFromCampos());
                    limpaCampos();
                    JOptionPane.showMessageDialog(null, "O Cliente foi alterado com sucesso!");
                }
            }
        }
        );

        //=================================================================
        jbExcluir.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                clienteRN.remover(getObjetoFromCampos());
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
            }
        }
        );

        //=================================================================
        jbBuscar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                ConsultaCliente cCli = new ConsultaCliente();
                cCli.setModal(true);
                cCli.setVisible(true);
                if (cCli.getObjetoSelecionado() != null) {
                    preencheCampos(cCli.getObjetoSelecionado());
                }

            }
        }
        );
    }

    public boolean camposValidos() {
        if (jtfCodigo.getText() == "" || jtfNome.getText() == "");
        return true;
    }

    public Cliente getObjetoFromCampos() {

        //cli.setCodCliente(Integer.parseInt(jtfCodigo.getText()));
        cli.setNome(jtfNome.getText());
        cli.setRg(jtfRg.getText());
        cli.setCpf(jtfCpf.getText());
        cli.setFone(jtfFone.getText());
        cli.setEnd(jtfEndereco.getText());

        return cli;
    }

    public void limpaCampos() {
        this.jtfCodigo.setText("");
        this.jtfNome.setText("");
        this.jtfRg.setText("");
        this.jtfCpf.setText("");
        this.jtfFone.setText("");
        this.jtfEndereco.setText("");
    }

    public void preencheCampos(Object obj) {
        limpaCampos();
        Cliente c = (Cliente) obj;

        this.jtfCodigo.setText(String.valueOf(c.getCodCliente()));
        this.jtfNome.setText(c.getNome());
        this.jtfRg.setText(c.getRg());
        this.jtfCpf.setText(c.getCpf());
        this.jtfFone.setText(c.getFone());
        this.jtfEndereco.setText(c.getEnd());
    }

}
