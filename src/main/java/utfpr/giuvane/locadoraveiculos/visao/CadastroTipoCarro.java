package utfpr.giuvane.locadoraveiculos.visao;

import utfpr.giuvane.locadoraveiculos.visao.modelo.CadastroPadrao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaTipoCarro;
import utfpr.giuvane.locadoraveiculos.modelo.rn.TipoCarroRN;
import utfpr.giuvane.locadoraveiculos.modelo.vo.TipoCarro;

public class CadastroTipoCarro extends CadastroPadrao {

    private static final long serialVersionUID = 1L;

    private JPanel jpCampos;
    private JLabel jlCodigo, jlDescricao;

    private JTextField jtfCodigo, jtfDescricao;

    //private PersistenciaTipoCarro  pTc = new PersistenciaTipoCarro();
    TipoCarroRN tipoCarroRN = new TipoCarroRN();
    private TipoCarro car = new TipoCarro();

    public CadastroTipoCarro() {
        super("Cadastro de Tipo de Carro", true, true, true, true);

        jpCampos = new JPanel();

        jlCodigo = new JLabel("C�digo");
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
                tipoCarroRN.gravar(getObjetoFromCampos());
                JOptionPane.showMessageDialog(null, "O Tipo de Carro foi cadastrado com sucesso!");
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
                    tipoCarroRN.gravar(getObjetoFromCampos());
                    limpaCampos();
                    JOptionPane.showMessageDialog(null, "O Tipo foi alterada com sucesso!");
                }
            }
        }
        );

        //=================================================================
        jbExcluir.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                tipoCarroRN.remover(getObjetoFromCampos());
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Tipo excluido com sucesso!");
            }
        }
        );

        //=================================================================
        jbBuscar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                ConsultaTipoCarro cTc = new ConsultaTipoCarro();
                cTc.setModal(true);
                cTc.setVisible(true);
                if (cTc.getObjetoSelecionado() != null) {
                    preencheCampos(cTc.getObjetoSelecionado());
                }

            }
        }
        );
    }

    public boolean camposValidos() {
        if (jtfCodigo.getText() == "" || jtfDescricao.getText() == "");
        return true;
    }

    public TipoCarro getObjetoFromCampos() {

        //car.setCodTipoCarro(Integer.parseInt(jtfCodigo.getText()));
        car.setDescricaoTipoCarro(jtfDescricao.getText());

        return car;
    }

    public void limpaCampos() {
        this.jtfCodigo.setText("");
        this.jtfDescricao.setText("");
    }

    public void preencheCampos(Object obj) {
        limpaCampos();
        TipoCarro tc = (TipoCarro) obj;

        this.jtfCodigo.setText(String.valueOf(tc.getCodTipoCarro()));
        this.jtfDescricao.setText(tc.getDescricaoTipoCarro());
    }
}
