package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;

public class PersistenciaMarca extends PersistenciaJDBC{

	Connection conexao = PersistenciaJDBC.getConexao();
    PreparedStatement stmt;
    ResultSet rs;
    
   
    
   public Object busca(int codigo) throws CadastroNaoExisteException 
	{    
        try {
        	stmt = conexao.prepareStatement("select * from TB_MARCA where MAR_CODIGO=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, codigo);
		    rs = stmt.executeQuery();
		    if (rs.first())
		    {
		    	// a busca teve sucesso, o nome existe na tabela
		    	Marca m = new Marca();
		    	m.setCodMarca(rs.getInt("MAR_CODIGO"));
		    	m.setDescricaoMarca(rs.getString("MAR_DESCRICAO"));
		    	return m;
		    }
		    else
		    	throw new CadastroNaoExisteException("A Marca " + codigo + " n�o est� cadastrado.");
		    		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
             
		return null;
	}
    
    
    public void gravar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Marca)
		   {
			   Marca m = (Marca) obj;
			   stmt = conexao
			    .prepareStatement("insert into TB_MARCA(MAR_CODIGO,MAR_DESCRICAO) " +
			    		                         "values(?,?)");			   
			   stmt.setInt(1, m.getCodMarca());
			   stmt.setString(2, m.getDescricaoMarca());
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
		   if (obj instanceof Marca)
		   {
			   Marca marca = (Marca) obj;
			   stmt = conexao.prepareStatement("update TB_MARCA set MAR_DESCRICAO=? where " +
			    		"MAR_CODIGO=?");			   
			   stmt.setInt(2, marca.getCodMarca());
			   stmt.setString(1, marca.getDescricaoMarca());
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
		   if (obj instanceof Marca)
		   {
			   Marca m = (Marca) obj;
			   stmt = conexao
			   .prepareStatement("delete from TB_MARCA where MAR_CODIGO = ?");			   
			   stmt.setInt(1, m.getCodMarca());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Vector buscaTudo(Class classe) throws CadastroNaoExisteException
	{
		if (classe == Marca.class)
		{
		     Vector<Marca> marcas = new Vector<Marca>();
		        
		     try {
					stmt = conexao.prepareStatement
					             ("select * from TB_MARCA",ResultSet.TYPE_SCROLL_SENSITIVE, 
					              ResultSet.CONCUR_READ_ONLY);
				    rs = stmt.executeQuery();
				    while (rs.next())
				    {
				       	Marca m = new Marca();
				    	m.setCodMarca(rs.getInt("MAR_CODIGO"));
				    	m.setDescricaoMarca(rs.getString("MAR_DESCRICAO"));
				    	marcas.add(m);
				    }
				    return marcas;
				    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}
	

}
