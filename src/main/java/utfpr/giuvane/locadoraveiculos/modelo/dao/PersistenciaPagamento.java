package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Locacao;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Pagamento;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;

public class PersistenciaPagamento extends PersistenciaJDBC
{
    Connection conexao = PersistenciaJDBC.getConexao();
    PreparedStatement stmt;
    ResultSet rs;
    
   
    
   public Object busca(int codigo) throws CadastroNaoExisteException 
	{    
        try {
        	stmt = conexao.prepareStatement("select * from TB_PAGAMENTO where PAG_CODIGO=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, codigo);
		    rs = stmt.executeQuery();
		    if (rs.first())
		    {
		    	PersistenciaLocacao pl = new PersistenciaLocacao();
		    	int codLocacao = new Integer(rs.getString("PAG_CODLOCACAO")).intValue();
		    	Locacao loc = (Locacao)pl.busca(codLocacao);
		    	
		    	PersistenciaCliente pcli = new PersistenciaCliente();
		    	int codCliente = new Integer(rs.getString("PAG_CODCLIENTE")).intValue();
		    	Cliente cli = (Cliente)pcli.busca(codCliente);
		    	
		    	Pagamento pag = new Pagamento();
		    	pag.setCodPagamento(rs.getInt("PAG_CODIGO"));
		    	pag.setLocacao(loc);
		    	pag.setCliente(cli);
		    	pag.setMulta(rs.getFloat("PAG_MULTA"));
		    	return pag;
		    }
		    else
		    	throw new CadastroNaoExisteException("O Pagamento " + codigo + " n�o est� cadastrada.");
		    		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
             
		return null;
	}
    
    
    public void gravar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Pagamento)
		   {
			   Pagamento pag = (Pagamento) obj;
			   stmt = conexao
			    .prepareStatement("insert into TB_PAGAMENTO(PAG_CODIGO,PAG_CODLOCACAO,PAG_CODCLIENTE,PAG_MULTA) " +
			    		                         "values(?,?,?,?)");			   
			   stmt.setInt(1, pag.getCodPagamento());
			   stmt.setString(2, String.valueOf(pag.getLocacao().getCodLocacao()));
			   stmt.setString(3, String.valueOf(pag.getCliente().getCodCliente()));
			   stmt.setFloat(4, pag.getMulta());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
    public void alterar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Pagamento)
		   {
			   Pagamento pag = (Pagamento) obj;
			   stmt = conexao.prepareStatement("update TB_PAGAMENTO set PAG_CODLOCACAO=?, PAG_CODCLIENTE=?, PAG_MULTA=? where " +
			    		"PAG_CODIGO=?");			   
			   stmt.setString(1, String.valueOf(pag.getLocacao().getCodLocacao()));
			   stmt.setString(2, String.valueOf(pag.getCliente().getCodCliente()));
			   stmt.setFloat(3, pag.getMulta());
			   stmt.setInt(4, pag.getCodPagamento());
			   stmt.execute();
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void excluir(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Pagamento)
		   {
			   Pagamento pag = (Pagamento) obj;
			   stmt = conexao
			   .prepareStatement("delete from TB_PAGAMENTO where PAG_CODIGO = ?");			   
			   stmt.setInt(1, pag.getCodPagamento());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Vector buscaTudo(Class classe) throws CadastroNaoExisteException
	{
		if (classe == Pagamento.class)
		{
		     Vector<Pagamento> pagamentos = new Vector<Pagamento>();
		        
		     try {
					stmt = conexao.prepareStatement
					             ("select * from TB_PAGAMENTO",ResultSet.TYPE_SCROLL_SENSITIVE, 
					              ResultSet.CONCUR_READ_ONLY);
				    rs = stmt.executeQuery();
				    while (rs.next())
				    {
				    	
				    	PersistenciaLocacao pl = new PersistenciaLocacao();
				    	int codLocacao = new Integer(rs.getString("PAG_CODLOCACAO")).intValue();
				    	Locacao loc = (Locacao)pl.busca(codLocacao);
				    	
				    	PersistenciaCliente pcli = new PersistenciaCliente();
				    	int codCliente = new Integer(rs.getString("PAG_CODCLIENTE")).intValue();
				    	Cliente cli = (Cliente)pcli.busca(codCliente);
				    	
				       	Pagamento pag = new Pagamento();
				       	pag.setCodPagamento(rs.getInt("PAG_CODIGO"));
				       	pag.setLocacao(loc);
				       	pag.setCliente(cli);
				    	pag.setMulta(rs.getFloat("PAG_MULTA"));
				    	pagamentos.add(pag);
				    }
				    return pagamentos;
				    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}
}
