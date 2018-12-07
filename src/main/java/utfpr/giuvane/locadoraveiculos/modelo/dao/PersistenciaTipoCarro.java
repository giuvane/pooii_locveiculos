package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.vo.TipoCarro;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;

public class PersistenciaTipoCarro extends PersistenciaJDBC{

	Connection conexao = PersistenciaJDBC.getConexao();
    PreparedStatement stmt;
    ResultSet rs;
    
   
    
   public Object busca(int codigo) throws CadastroNaoExisteException 
	{    
        try {
        	stmt = conexao.prepareStatement("select * from TB_TIPOCARRO where TPC_CODIGO=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, codigo);
		    rs = stmt.executeQuery();
		    if (rs.first())
		    {
		    	TipoCarro tc = new TipoCarro();
		    	tc.setCodTipoCarro(rs.getInt("TPC_CODIGO"));
		    	tc.setDescricaoTipoCarro(rs.getString("TPC_DESCRICAO"));
		    	return tc;
		    }
		    else
		    	throw new CadastroNaoExisteException("O Tipo de Carro " + codigo + " n�o est� cadastrado.");
		    		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
             
		return null;
	}
    
    
    public void gravar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof TipoCarro)
		   {
			   TipoCarro tc = (TipoCarro) obj;
			   stmt = conexao
			    .prepareStatement("insert into TB_TIPOCARRO(TPC_CODIGO,TPC_DESCRICAO) " +
			    		                         "values(?,?)");			   
			   stmt.setInt(1, tc.getCodTipoCarro());
			   stmt.setString(2, tc.getDescricaoTipoCarro());
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
		   if (obj instanceof TipoCarro)
		   {
			   TipoCarro tc = (TipoCarro) obj;
			   stmt = conexao.prepareStatement("update TB_TIPOCARRO set TPC_DESCRICAO=? where " +
			    		"TPC_CODIGO=?");			   
			   stmt.setInt(2, tc.getCodTipoCarro());
			   stmt.setString(1, tc.getDescricaoTipoCarro());
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
		   if (obj instanceof TipoCarro)
		   {
			   TipoCarro c = (TipoCarro) obj;
			   stmt = conexao
			   .prepareStatement("delete from TB_TIPOCARRO where TPC_CODIGO = ?");			   
			   stmt.setInt(1, c.getCodTipoCarro());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Vector buscaTudo(Class classe) throws CadastroNaoExisteException
	{
		if (classe == TipoCarro.class)
		{
		     Vector<TipoCarro> tipos = new Vector<TipoCarro>();
		        
		     try {
					stmt = conexao.prepareStatement
					             ("select * from TB_TIPOCARRO",ResultSet.TYPE_SCROLL_SENSITIVE, 
					              ResultSet.CONCUR_READ_ONLY);
				    rs = stmt.executeQuery();
				    while (rs.next())
				    {
				       	TipoCarro tc = new TipoCarro();
				    	tc.setCodTipoCarro(rs.getInt("TPC_CODIGO"));
				    	tc.setDescricaoTipoCarro(rs.getString("TPC_DESCRICAO"));
				    	tipos.add(tc);
				    }
				    return tipos;
				    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}
	

}
