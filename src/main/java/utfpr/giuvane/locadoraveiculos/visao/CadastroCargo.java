package utfpr.giuvane.locadoraveiculos.visao;

import utfpr.giuvane.locadoraveiculos.visao.modelo.CadastroPadrao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utfpr.giuvane.locadoraveiculos.modelo.rn.CargoRN;

import utfpr.giuvane.locadoraveiculos.modelo.dao.PersistenciaCargo;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;

public class CadastroCargo extends CadastroPadrao {

    private static final long serialVersionUID = 1L;

    private JPanel jpCampos;
    private JLabel jlCodigo, jlDescricao;
    private JTextField jtfCodigo, jtfDescricao;

    //private PersistenciaCargo  pCa = new PersistenciaCargo();
    private CargoRN cargoRN = new CargoRN();
    private Cargo car = new Cargo();

    public CadastroCargo() {
        super("Cadastro de Cargo", true, true, true, true);

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

        jbGravar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                cargoRN.gravar(getObjetoFromCampos());
                //pCa.gravar(getObjetoFromCampos());
                JOptionPane.showMessageDialog(null, "O Cargo foi cadastrado com sucesso!");
                limpaCampos();
            }
        }
        );

        // ==============================================================
        jbAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (camposValidos()) {
                    getObjetoFromCampos();

                    //pCa.alterar(getObjetoFromCampos());
                    cargoRN.gravar(car);
                    limpaCampos();
                    JOptionPane.showMessageDialog(null, "O Cargo foi alterada com sucesso!");
                }
            }
        }
        );

        //=================================================================
        jbExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                cargoRN.remover(car);
                //pCa.excluir(getObjetoFromCampos());
                limpaCampos();
                JOptionPane.showMessageDialog(null, "Cargo excluido com sucesso!");
            }
        }
        );

        //=================================================================
        jbBuscar.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                ConsultaCargo cCa = new ConsultaCargo();
                cCa.setModal(true);
                cCa.setVisible(true);
                if (cCa.getObjetoSelecionado() != null) {
                    preencheCampos(cCa.getObjetoSelecionado());
                }
            }
        }
        );
    }

    public boolean camposValidos() {
        if (jtfCodigo.getText() == "" || jtfDescricao.getText() == "");
        return true;
    }

    public Cargo getObjetoFromCampos() {

        //car.setCodCargo(Integer.parseInt(jtfCodigo.getText()));
        car.setDescricaoCargo(jtfDescricao.getText());

        return car;
    }

    public void limpaCampos() {
        this.jtfCodigo.setText("");
        this.jtfDescricao.setText("");
    }

    public void preencheCampos(Object obj) {
        limpaCampos();
        car = (Cargo) obj;

        this.jtfCodigo.setText(String.valueOf(car.getCodCargo()));
        this.jtfDescricao.setText(car.getDescricaoCargo());
    }

}
