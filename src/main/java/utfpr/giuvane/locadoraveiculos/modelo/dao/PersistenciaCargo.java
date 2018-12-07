package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;

public class PersistenciaCargo extends PersistenciaJDBC{

    Connection conexao = Conexao.getInstance(); //PersistenciaJDBC.getConexao();
    PreparedStatement stmt;
    ResultSet rs;
    
   
    
   public Object busca(int codigo) throws CadastroNaoExisteException 
    {
    try {
            stmt = conexao.prepareStatement("select * from TB_CARGO where CGO_CODIGO=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);
                    stmt.setInt(1, codigo);
                rs = stmt.executeQuery();
                if (rs.first())
                {
                    Cargo c = new Cargo();
                    c.setCodCargo(rs.getInt("CGO_CODIGO"));
                    c.setDescricaoCargo(rs.getString("CGO_DESCRICAO"));
                    return c;
                }
                else
                    throw new CadastroNaoExisteException("O Cargo " + codigo + " n�o est� cadastrado.");

            } catch (SQLException e) {
                    e.printStackTrace();
            }

            return null;
    }
    
    
    public void gravar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Cargo)
		   {
			   Cargo c = (Cargo) obj;
			   stmt = conexao
			    .prepareStatement("insert into TB_CARGO(CGO_DESCRICAO) " +
			    		                         "values(?)");			   
			   
			   stmt.setString(1, c.getDescricaoCargo());
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
		   if (obj instanceof Cargo)
		   {
			   Cargo c = (Cargo) obj;
			   stmt = conexao.prepareStatement("update TB_CARGO set CGO_DESCRICAO=? where " +
			    		"CGO_CODIGO=?");			   
			   stmt.setInt(2, c.getCodCargo());
			   stmt.setString(1, c.getDescricaoCargo());
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
		   if (obj instanceof Cargo)
		   {
			   Cargo c = (Cargo) obj;
			   stmt = conexao
			   .prepareStatement("delete from TB_CARGO where CGO_CODIGO = ?");			   
			   stmt.setInt(1, c.getCodCargo());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Vector buscaTudo(Class classe) throws CadastroNaoExisteException
	{
            if (classe == Cargo.class)
            {
                 Vector<Cargo> cargos = new Vector<Cargo>();

                 try {
                                    stmt = conexao.prepareStatement
                                                 ("select * from TB_CARGO",ResultSet.TYPE_SCROLL_SENSITIVE, 
                                                  ResultSet.CONCUR_READ_ONLY);
                                rs = stmt.executeQuery();
                                while (rs.next())
                                {
                                    Cargo c = new Cargo();
                                    c.setCodCargo(rs.getInt("CGO_CODIGO"));
                                    c.setDescricaoCargo(rs.getString("CGO_DESCRICAO"));
                                    cargos.add(c);
                                }
                                return cargos;

                            } catch (SQLException e) {
                                    e.printStackTrace();
                            }
            }		
            return null;
	}
	

}
