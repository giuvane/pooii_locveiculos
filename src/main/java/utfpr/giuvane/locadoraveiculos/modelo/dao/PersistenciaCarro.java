package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.vo.Carro;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Marca;
import utfpr.giuvane.locadoraveiculos.modelo.vo.TipoCarro;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Passeio;

public class PersistenciaCarro extends PersistenciaJDBC
{
	Connection conexao = PersistenciaJDBC.getConexao();
    PreparedStatement stmt;
    ResultSet rs;
    
   
    
   public Object busca(int codigo) throws CadastroNaoExisteException 
	{    
        try {
        	stmt = conexao.prepareStatement("select * from TB_CARRO where CAR_CODIGO=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, codigo);
		    rs = stmt.executeQuery();
		    if (rs.first())
		    {
		    	PersistenciaMarca pm = new PersistenciaMarca();
		    	int codMarca = new Integer(rs.getString("CAR_CODMARCA")).intValue();
		    	Marca mar = (Marca)pm.busca(codMarca);
		    	
		    	PersistenciaTipoCarro ptc = new PersistenciaTipoCarro();
		    	int codTipoCarro = new Integer(rs.getString("CAR_CODTIPOCARRO")).intValue();
		    	TipoCarro tpc = (TipoCarro)ptc.busca(codTipoCarro);
		    	
		    	Carro carro = new Passeio();
		    	carro.setCodCarro(rs.getInt("CAR_CODIGO"));
		    	carro.setMarca(mar);
		    	carro.setTipoCarro(tpc);
		    	carro.setPlaca(rs.getString("CAR_PLACA"));
		    	carro.setDescricaoCarro(rs.getString("CAR_DESCRICAO"));
		    	return carro;
		    }
		    else
		    	throw new CadastroNaoExisteException("O Carro " + codigo + " n�o est� cadastrado.");
		    		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
             
		return null;
	}
    
    
    public void gravar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Carro)
		   {
			   Carro carro = (Carro) obj;
			   stmt = conexao
			    .prepareStatement("insert into TB_CARRO(CAR_CODIGO,CAR_CODMARCA,CAR_CODTIPOCARRO,CAR_PLACA,CAR_DESCRICAO) " +
			    		                         "values(?,?,?,?,?)");			   
			   stmt.setInt(1, carro.getCodCarro());
			   stmt.setString(2, String.valueOf(carro.getMarca().getCodMarca()));
			   stmt.setString(3, String.valueOf(carro.getTipoCarro().getCodTipoCarro()));
			   stmt.setString(4, carro.getPlaca());
			   stmt.setString(5, carro.getDescricaoCarro());
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
		   if (obj instanceof Carro)
		   {
			   Carro carro = (Carro) obj;
			   stmt = conexao.prepareStatement("update TB_CARRO set CAR_CODMARCA=?, CAR_CODTIPOCARRO=?, CAR_PLACA=?, CAR_DESCRICAO=? where " +
			    		"CAR_CODIGO=?");			   
			   stmt.setString(1, String.valueOf(carro.getMarca().getCodMarca()));
			   stmt.setString(2, String.valueOf(carro.getTipoCarro().getCodTipoCarro()));
			   stmt.setString(3, carro.getPlaca());
			   stmt.setString(4, carro.getDescricaoCarro());
			   stmt.setInt(5, carro.getCodCarro());
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
		   if (obj instanceof Carro)
		   {
			   Carro carro = (Carro) obj;
			   stmt = conexao
			   .prepareStatement("delete from TB_CARRO where CAR_CODIGO = ?");			   
			   stmt.setInt(1, carro.getCodCarro());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Vector buscaTudo(Class classe) throws CadastroNaoExisteException
	{
		if (classe == Carro.class)
		{
		     Vector<Carro> carros = new Vector<Carro>();
		        
		     try {
					stmt = conexao.prepareStatement
					             ("select * from TB_CARRO",ResultSet.TYPE_SCROLL_SENSITIVE, 
					              ResultSet.CONCUR_READ_ONLY);
				    rs = stmt.executeQuery();
				    while (rs.next())
				    {
				    	PersistenciaMarca pm = new PersistenciaMarca();
				    	int codMarca = new Integer(rs.getString("CAR_CODMARCA")).intValue();
				    	Marca mar = (Marca)pm.busca(codMarca);
				    	
				    	PersistenciaTipoCarro ptc = new PersistenciaTipoCarro();
				    	int codTipoCarro = new Integer(rs.getString("CAR_CODTIPOCARRO")).intValue();
				    	TipoCarro tpc = (TipoCarro)ptc.busca(codTipoCarro);
				    	
				       	Carro carro = new Passeio();
				    	carro.setCodCarro(rs.getInt("CAR_CODIGO"));
				    	carro.setMarca(mar);
				    	carro.setTipoCarro(tpc);
				    	carro.setPlaca(rs.getString("CAR_PLACA"));
				    	carro.setDescricaoCarro(rs.getString("CAR_DESCRICAO"));
				    	carros.add(carro);
				    }
				    return carros;
				    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}
}
