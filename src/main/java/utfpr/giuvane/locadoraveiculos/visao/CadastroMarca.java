package utfpr.giuvane.locadoraveiculos.visao;

import utfpr.giuvane.locadoraveiculos.visao.modelo.CadastroPadrao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaMarca;
import utfpr.giuvane.locadoraveiculos.modelo.rn.MarcaRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;

public class CadastroMarca extends CadastroPadrao {

    private static final long serialVersionUID = 1L;

    private JPanel jpCampos;
    private JLabel jlCodigo, jlDescricao;

    private JTextField jtfCodigo, jtfDescricao;

    //private PersistenciaMarca  pMa = new PersistenciaMarca();
    private MarcaRN marcaRN = new MarcaRN();
    private Marca mar = new Marca();

    public CadastroMarca() {
        super(" Cadastro de Marca ", true, true, true, true);

        jpCampos = new JPanel();

        jlCodigo = new JLabel("Código");
        jtfCodigo = new JTextField(10);
        jtfCodigo.setEditable(false);
        jlDescricao = new JLabel("Descricao");
        jtfDescricao = new JTextField(30);

        this.setLayout(new BorderLayout());
        jpCampos.setLayout(null);
        jpCampos.add(jlCodigo);
        jpCampos.add(jtfCodigo);
        jpCampos.add(jlDescricao);
        jpCampos.add(jtfDescricao);

        // posicao dos componentes
        jlCodigo.setBounds(15, 30, 65, 25);  // MD, MS, Lrg ,Alt
        jtfCodigo.setBounds(100, 30, 100, 25);
        jlDescricao.setBounds(15, 60, 65, 25);
        jtfDescricao.setBounds(100, 60, 100, 25);

        this.add(jpBotoes, BorderLayout.SOUTH);
        jpBotoes.setVisible(true);

        this.add(jpCampos);
        this.setVisible(true);
        this.pack();

        jbGravar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                marcaRN.gravar(getObjetoFromCampos());
                JOptionPane.showMessageDialog(null, "A Marca foi cadastrada com sucesso!");
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
                    marcaRN.gravar(getObjetoFromCampos());
                    limpaCampos();
                    JOptionPane.showMessageDialog(null, "A Marca foi alterada com sucesso!");
                }
            }
        }
        );

        //=================================================================
        jbExcluir.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                marcaRN.remover(getObjetoFromCampos());
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Marca excluida com sucesso!");
            }
        }
        );

        //=================================================================
        jbBuscar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                ConsultaMarca cMa = new ConsultaMarca();
                cMa.setModal(true);
                cMa.setVisible(true);
                if (cMa.getObjetoSelecionado() != null) {
                    preencheCampos(cMa.getObjetoSelecionado());
                }

            }
        }
        );
    }

    public boolean camposValidos() {
        if (jtfCodigo.getText() == "" || jtfDescricao.getText() == "");
        return true;
    }

    public Marca getObjetoFromCampos() {

        //mar.setCodMarca(Integer.parseInt(jtfCodigo.getText()));
        mar.setDescricaoMarca(jtfDescricao.getText());

        return mar;
    }

    public void limpaCampos() {
        this.jtfCodigo.setText("");
        this.jtfDescricao.setText("");
    }

    public void preencheCampos(Object obj) {
        limpaCampos();
        Marca mar = (Marca) obj;

        this.jtfCodigo.setText(String.valueOf(mar.getCodMarca()));
        this.jtfDescricao.setText(mar.getDescricaoMarca());
    }

}
