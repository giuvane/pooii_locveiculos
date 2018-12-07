package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.vo.Carro;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Locacao;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;

public class PersistenciaLocacao extends PersistenciaJDBC
{
	Connection conexao = PersistenciaJDBC.getConexao();
    PreparedStatement stmt;
    ResultSet rs;
    
   
    
   public Object busca(int codigo) throws CadastroNaoExisteException 
	{    
        try {
        	stmt = conexao.prepareStatement("select * from TB_LOCACAO where LOC_CODIGO=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, codigo);
		    rs = stmt.executeQuery();
		    if (rs.first())
		    {
		    	PersistenciaCarro pcar = new PersistenciaCarro();
		    	int codCarro = new Integer(rs.getString("LOC_CODCARRO")).intValue();
		    	Carro carro = (Carro)pcar.busca(codCarro);
		    	
		    	PersistenciaCliente pcli = new PersistenciaCliente();
		    	int codCliente = new Integer(rs.getString("LOC_CODCLIENTE")).intValue();
		    	Cliente cli = (Cliente)pcli.busca(codCliente);
		    	
		    	Locacao loc = new Locacao();
		    	loc.setCodLocacao(rs.getInt("LOC_CODIGO"));
		    	loc.setCarro(carro);
		    	loc.setCliente(cli);
		    	loc.setDtLocacao(rs.getDate("LOC_DTLOCACAO"));
		    	loc.setDtEntrega(rs.getDate("LOC_DTENTREGA"));
		    	loc.setValor(rs.getFloat("LOC_VALOR"));
		    	return loc;
		    }
		    else
		    	throw new CadastroNaoExisteException("A Loca��o " + codigo + " n�o est� cadastrada.");
		    		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
             
		return null;
	}
    
    
    public void gravar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Locacao)
		   {
			   Locacao loc = (Locacao) obj;
			   stmt = conexao
			    .prepareStatement("insert into TB_LOCACAO(LOC_CODIGO,LOC_CODCARRO,LOC_CODCLIENTE,LOC_DTLOCACAO,LOC_DTENTREGA,LOC_VALOR) " +
			    		                         "values(?,?,?,?,?,?)");			   
			   stmt.setInt(1, loc.getCodLocacao());
			   stmt.setString(2, String.valueOf(loc.getCarro().getCodCarro()));
			   stmt.setString(3, String.valueOf(loc.getCliente().getCodCliente()));
			   stmt.setDate(4, new java.sql.Date(loc.getDtLocacao().getTime()));
			   stmt.setDate(5, new java.sql.Date(loc.getDtEntrega().getTime()));
			   stmt.setFloat(6, loc.getValor());
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
		   if (obj instanceof Locacao)
		   {
			   Locacao loc = (Locacao) obj;
			   stmt = conexao.prepareStatement("update TB_LOCACAO set LOC_CODCARRO=?, LOC_CODCLIENTE=?, LOC_DTLOCACAO=?, LOC_DTENTREGA=?, LOC_VALOR=? where " +
			    		"LOC_CODIGO=?");			   
			   stmt.setString(1, String.valueOf(loc.getCarro().getCodCarro()));
			   stmt.setString(2, String.valueOf(loc.getCliente().getCodCliente()));
			   stmt.setDate(3, new java.sql.Date(loc.getDtLocacao().getTime()));
			   stmt.setDate(4, new java.sql.Date(loc.getDtEntrega().getTime()));
			   stmt.setFloat(5, loc.getValor());
			   stmt.setInt(6, loc.getCodLocacao());
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
		   if (obj instanceof Locacao)
		   {
			   Locacao loc = (Locacao) obj;
			   stmt = conexao
			   .prepareStatement("delete from TB_LOCACAO where LOC_CODIGO = ?");			   
			   stmt.setInt(1, loc.getCodLocacao());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Vector buscaTudo(Class classe) throws CadastroNaoExisteException
	{
		if (classe == Locacao.class)
		{
		     Vector<Locacao> locacoes = new Vector<Locacao>();
		        
		     try {
					stmt = conexao.prepareStatement
					             ("select * from TB_LOCACAO",ResultSet.TYPE_SCROLL_SENSITIVE, 
					              ResultSet.CONCUR_READ_ONLY);
				    rs = stmt.executeQuery();
				    while (rs.next())
				    {
				    	
				    	PersistenciaCarro pcar = new PersistenciaCarro();
				    	int codCarro = new Integer(rs.getString("LOC_CODCARRO")).intValue();
				    	Carro carro = (Carro)pcar.busca(codCarro);
				    	
				    	PersistenciaCliente pcli = new PersistenciaCliente();
				    	int codCliente = new Integer(rs.getString("LOC_CODCLIENTE")).intValue();
				    	Cliente cli = (Cliente)pcli.busca(codCliente);
				    	
				       	Locacao loc = new Locacao();
				       	loc.setCodLocacao(rs.getInt("LOC_CODIGO"));
				       	loc.setCarro(carro);
				       	loc.setCliente(cli);
				    	loc.setDtLocacao(rs.getDate("LOC_DTLOCACAO"));
				    	loc.setDtEntrega(rs.getDate("LOC_DTENTREGA"));
				    	loc.setValor(rs.getFloat("LOC_VALOR"));
				    	locacoes.add(loc);
				    }
				    return locacoes;
				    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}
}
