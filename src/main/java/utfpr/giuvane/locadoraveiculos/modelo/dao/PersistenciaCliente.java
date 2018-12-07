package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Cliente;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;

public class PersistenciaCliente extends PersistenciaJDBC
{
    Connection conexao = Conexao.getInstance(); //PersistenciaJDBC.getConexao();
    PreparedStatement stmt;
    ResultSet rs;
    
   
    
   public Object busca(int codigo) throws CadastroNaoExisteException 
	{    
        try {
        	stmt = conexao.prepareStatement("select * from TB_CLIENTE where CLI_CODIGO=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, codigo);
		    rs = stmt.executeQuery();
		    if (rs.first())
		    {
		    	Cliente cli = new Cliente();
		    	cli.setCodCliente(rs.getInt("CLI_CODIGO"));
		    	cli.setNome(rs.getString("CLI_NOME"));
		    	cli.setRg(rs.getString("CLI_RG"));
		    	cli.setCpf(rs.getString("CLI_CPF"));
		    	cli.setFone(rs.getString("CLI_FONE"));
		    	cli.setEnd(rs.getString("CLI_END"));
		    	return cli;
		    }
		    else
		    	throw new CadastroNaoExisteException("O Cliente " + codigo + " n�o est� cadastrado.");
		    		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
             
		return null;
	}
    
    
    public void gravar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Cliente)
		   {
			   Cliente cli = (Cliente) obj;
			   stmt = conexao
			    .prepareStatement("insert into TB_CLIENTE(CLI_NOME,CLI_RG,CLI_CPF,CLI_FONE,CLI_ENDERECO) " +
			    		                         "values(?,?,?,?,?)");			   
			   //stmt.setInt(1, cli.getCodCliente());
			   stmt.setString(1, cli.getNome());
			   stmt.setString(2, cli.getRg());
			   stmt.setString(3, cli.getCpf());
			   stmt.setString(4, cli.getFone());
			   stmt.setString(5, cli.getEnd());
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
		   if (obj instanceof Cliente)
		   {
			   Cliente cli = (Cliente) obj;
			   stmt = conexao.prepareStatement("update TB_CLIENTE set CLI_NOME=?, CLI_RG=?, CLI_CPF=?, CLI_FONE=?, CLI_ENDERECO=? where " +
			    		"CLI_CODIGO=?");			   
			   stmt.setString(1, cli.getNome());
			   stmt.setString(2, cli.getRg());
			   stmt.setString(3, cli.getCpf());
			   stmt.setString(4, cli.getFone());
			   stmt.setString(5, cli.getEnd());
			   stmt.setInt(6, cli.getCodCliente());
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
		   if (obj instanceof Cliente)
		   {
			   Cliente cli = (Cliente) obj;
			   stmt = conexao
			   .prepareStatement("delete from TB_CLIENTE where CLI_CODIGO = ?");			   
			   stmt.setInt(1, cli.getCodCliente());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Vector buscaTudo(Class classe) throws CadastroNaoExisteException
	{
		if (classe == Cliente.class)
		{
		     Vector<Cliente> clientes = new Vector<Cliente>();
		        
		     try {
					stmt = conexao.prepareStatement
					             ("select * from TB_CLIENTE",ResultSet.TYPE_SCROLL_SENSITIVE, 
					              ResultSet.CONCUR_READ_ONLY);
				    rs = stmt.executeQuery();
				    while (rs.next())
				    {
				       	Cliente cli = new Cliente();
				    	cli.setCodCliente(rs.getInt("CLI_CODIGO"));
				    	cli.setNome(rs.getString("CLI_NOME"));
				    	cli.setRg(rs.getString("CLI_RG"));
				    	cli.setCpf(rs.getString("CLI_CPF"));
				    	cli.setFone(rs.getString("CLI_FONE"));
				    	cli.setEnd(rs.getString("CLI_ENDERECO"));
				    	clientes.add(cli);
				    }
				    return clientes;
				    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}
}
