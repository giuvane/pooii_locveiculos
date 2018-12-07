package utfpr.giuvane.locadoraveiculos.visao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import utfpr.giuvane.locadoraveiculos.modelo.dao.ConexaoHibernate;

public class Sistema extends JFrame{
	
	private static final long serialVersionUID = 1;
	private JMenuBar   jmBarraMenus = new JMenuBar();
	private JMenu      mOpcoes;
	private JDesktopPane telaInterna;
	
	public Sistema(){
		super("Locadora de Veiculos");
		telaInterna = new JDesktopPane();		
				
		this.setSize(670,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setJMenuBar(jmBarraMenus);
		
		// exibe a tela
				
		this.add(telaInterna);
			
		mOpcoes = new JMenu();
		
		mOpcoes.setText("Opcoes");
		
		jmBarraMenus.add(mOpcoes);
		
		JMenuItem miCliente = new JMenuItem();
		JMenuItem miFuncionario = new JMenuItem();
		JMenuItem miCargo = new JMenuItem();
		JMenuItem miCarro = new JMenuItem();
		JMenuItem miMarca = new JMenuItem();
		JMenuItem miTipoCarro = new JMenuItem();
		JMenuItem miLocacao = new JMenuItem();
		JMenuItem miPagamento = new JMenuItem();
		
		
		// nomes dos items do menu opcoes
		miCliente.setText("Cliente");
		miFuncionario.setText("Funcionario");
		miCargo.setText("Cargo");
		miCarro.setText("Carro");
		miMarca.setText("Marca");
		miTipoCarro.setText("Tipo de Carro");
		miLocacao.setText("Locacao");
		miPagamento.setText("Pagamento");
		
		// 	adicionar itens nos menus	
		
		mOpcoes.add(miCliente);
		mOpcoes.add(miFuncionario);
		mOpcoes.add(miCargo);
		mOpcoes.add(miCarro);
		mOpcoes.add(miMarca);
		mOpcoes.add(miTipoCarro);
		mOpcoes.add(miLocacao);
		mOpcoes.add(miPagamento);
		
		telaInterna.setLayout(new BorderLayout());
		
		
		miMarca.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroMarca cadMarca = new CadastroMarca();
	            		telaInterna.add(cadMarca);	            			            		
	            	}				
				}
	     );				
		
		miCargo.addActionListener(
				
	            new ActionListener() {
	            	
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroCargo cadCargo = new CadastroCargo();
	            		telaInterna.add(cadCargo);	
	            	}				
				}
	     );
		
		miTipoCarro.addActionListener(
				
	            new ActionListener() {
	            	
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroTipoCarro cadTipoCarro = new CadastroTipoCarro();
	            		telaInterna.add(cadTipoCarro);	
	            	}				
				}
	     );
		miCliente.addActionListener(
				
	            new ActionListener() {
	            	
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroCliente cadCliente = new CadastroCliente();
	            		telaInterna.add(cadCliente);	
	            	}				
				}
	     );
		miFuncionario.addActionListener(
				
	            new ActionListener() {
	            	
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroFuncionario cadFuncionario = new CadastroFuncionario();
	            		telaInterna.add(cadFuncionario);	
	            	}				
				}
	     );
		miCarro.addActionListener(
				
	            new ActionListener() {
	            	
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroCarro cadCarro = new CadastroCarro();
	            		telaInterna.add(cadCarro);	
	            	}				
				}
	     );
		miLocacao.addActionListener(
				
	            new ActionListener() {
	            	
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroLocacao cadLocacao = new CadastroLocacao();
	            		telaInterna.add(cadLocacao);	
	            	}				
				}
	     );
		miPagamento.addActionListener(
				
	            new ActionListener() {
	            	
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroPagamento cadPagamento = new CadastroPagamento();
	            		telaInterna.add(cadPagamento);	
	            	}				
				}
	     );
	/*//==============================================================
						
		miCadastroFuncionario.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroFuncionario CadFuncionario = new CadastroFuncionario();
	            		telaInterna.add(CadFuncionario);	            			            		
	            	}				
				}
	     );		
	//==============================================================	
		miCadastroFornecedor.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroFornecedor CadFornecedor = new CadastroFornecedor();
	            		telaInterna.add(CadFornecedor);	            			            		
	            	}				
				}
	     );				
	//==============================================================			
		miCadastroPecas.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroPecas CadPecas = new CadastroPecas();
	            		telaInterna.add(CadPecas);	            			            		
	            	}				
				}
	     );				
	//==============================================================
		miCadastroCaixa.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroCaixa CadCaixa = new CadastroCaixa();
	            		telaInterna.add(CadCaixa);	            			            		
	            	}				
				}
	     );				
		
	//===============================================================
		miCadastroCargo.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroCargo CadCargo = new CadastroCargo();
	            		telaInterna.add(CadCargo);	            			            		
	            	}				
				}
	     );				
		
		//================================================================
		
		miCadastroCompras.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroCompras CadCompras = new CadastroCompras();
	            		telaInterna.add(CadCompras);	            			            		
	            	}				
				}
	     );				
		//=================================================================
		miCadastroContasPagar.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroContasPagar CadContasPagar = new CadastroContasPagar();
	            		telaInterna.add(CadContasPagar);	            			            		
	            	}				
				}
	     );				
		//===================================================================
		
		miCadastroParcelaReceber.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroParcelaReceber CadParcelaReceber = new CadastroParcelaReceber();
	            		telaInterna.add(CadParcelaReceber);	            			            		
	            	}				
				}
	     );				
		//====================================================================
		
		miCadastroVendas.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		CadastroVendas CadVendas = new CadastroVendas();
	            		telaInterna.add(CadVendas);	            			            		
	            	}				
				}
	     );				
	//==========================================================
		/*miConsultaCliente.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		
	            	}			
				}
	     );		
	//==============================================================			
		miConsultaFuncionario.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		System.out.println("Falta Implementar");
	            		
	            	}			
				}
	     );		
	//==============================================================
			miConsultaFornecedor.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		
	            		
	            	}
				
				}
	     );		
	//=========================================================================		
		miAjuda.addActionListener(
				
	            new ActionListener() {
	            	public void actionPerformed(ActionEvent ev){
	            		JOptionPane.showMessageDialog(null, "lalalalalala" +
	            		JOptionPane.INFORMATION_MESSAGE);
	            	}				
				}
	     );	*/
	//==============================================================	
														
		this.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
            EntityManager manager = ConexaoHibernate.getInstance();
            new Sistema();
	}
}