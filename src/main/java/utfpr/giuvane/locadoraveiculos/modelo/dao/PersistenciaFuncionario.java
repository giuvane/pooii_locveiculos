package utfpr.giuvane.locadoraveiculos.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import utfpr.giuvane.locadoraveiculos.modelo.vo.Cargo;
import utfpr.giuvane.locadoraveiculos.modelo.vo.Funcionario;
import utfpr.giuvane.locadoraveiculos.modelo.exceptions.CadastroNaoExisteException;

public class PersistenciaFuncionario extends PersistenciaJDBC{
	Connection conexao = Conexao.getInstance(); //PersistenciaJDBC.getConexao();
    PreparedStatement stmt;
    ResultSet rs;
    
   
    
   public Object busca(int codigo) throws CadastroNaoExisteException 
	{    
        try {
        	stmt = conexao.prepareStatement("select * from TB_FUNCIONARIO where FUN_CODIGO=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, codigo);
		    rs = stmt.executeQuery();
		    if (rs.first())
		    {
		    	PersistenciaCargo pc = new PersistenciaCargo();
		    	int codCargo = new Integer(rs.getString("FUN_CODCARGO")).intValue();
		    	Cargo car = (Cargo)pc.busca(codCargo);
		    	
		    	Funcionario fun = new Funcionario();
		    	fun.setCodFuncionario(rs.getInt("FUN_CODIGO"));
		    	fun.setCargo(car);
		    	fun.setNome(rs.getString("FUN_NOME"));
		    	fun.setRg(rs.getString("FUN_RG"));
		    	fun.setCpf(rs.getString("FUN_CPF"));
		    	fun.setFone(rs.getString("FUN_FONE"));
		    	fun.setEnd(rs.getString("FUN_ENDERECO"));
		    	return fun;
		    }
		    else
		    	throw new CadastroNaoExisteException("O Funcionario " + codigo + " n�o est� cadastrado.");
		    		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
             
		return null;
	}
    
    
    public void gravar(Object obj)
    {
		try {		
		   PreparedStatement stmt;
		   if (obj instanceof Funcionario)
		   {
			   Funcionario fun = (Funcionario) obj;
			   stmt = conexao
			    .prepareStatement("insert into TB_FUNCIONARIO(FUN_CODIGO,FUN_CODCARGO,FUN_NOME,FUN_RG,FUN_CPF,FUN_FONE,FUN_ENDERECO) " +
			    		                         "values(?,?,?,?,?,?,?)");			   
			   stmt.setInt(1, fun.getCodFuncionario());
			   stmt.setString(2, String.valueOf(fun.getCargo().getCodCargo()));
			   stmt.setString(3, fun.getNome());
			   stmt.setString(4, fun.getRg());
			   stmt.setString(5, fun.getCpf());
			   stmt.setString(6, fun.getFone());
			   stmt.setString(7, fun.getEnd());
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
		   if (obj instanceof Funcionario)
		   {
			   Funcionario fun = (Funcionario) obj;
			   stmt = conexao.prepareStatement("update TB_FUNCIONARIO set FUN_NOME=?, FUN_CODCARGO=?, FUN_RG=?, FUN_CPF=?, FUN_FONE=?, FUN_ENDERECO=? where " +
			    		"FUN_CODIGO=?");			   
			   stmt.setString(1, fun.getNome());
			   stmt.setString(2, String.valueOf(fun.getCargo().getCodCargo()));
			   stmt.setString(3, fun.getRg());
			   stmt.setString(4, fun.getCpf());
			   stmt.setString(5, fun.getFone());
			   stmt.setString(6, fun.getEnd());
			   stmt.setInt(7, fun.getCodFuncionario());
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
		   if (obj instanceof Funcionario)
		   {
			   Funcionario fun = (Funcionario) obj;
			   stmt = conexao
			   .prepareStatement("delete from TB_FUNCIONARIO where FUN_CODIGO = ?");			   
			   stmt.setInt(1, fun.getCodFuncionario());
			   stmt.execute();			 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Vector buscaTudo(Class classe) throws CadastroNaoExisteException
	{
		if (classe == Funcionario.class)
		{
		     Vector<Funcionario> funcionarios = new Vector<Funcionario>();
		        
		     try {
					stmt = conexao.prepareStatement
					             ("select * from TB_FUNCIONARIO",ResultSet.TYPE_SCROLL_SENSITIVE, 
					              ResultSet.CONCUR_READ_ONLY);
				    rs = stmt.executeQuery();
				    while (rs.next())
				    {
				    	
				    	PersistenciaCargo pc = new PersistenciaCargo();
				    	int codCargo = new Integer(rs.getString("FUN_CODCARGO")).intValue();
				    	Cargo car = (Cargo)pc.busca(codCargo);
				    	
				       	Funcionario fun = new Funcionario();
				       	fun.setCargo(car);
				    	fun.setCodFuncionario(rs.getInt("FUN_CODIGO"));
				    	fun.setNome(rs.getString("FUN_NOME"));
				    	fun.setRg(rs.getString("FUN_RG"));
				    	fun.setCpf(rs.getString("FUN_CPF"));
				    	fun.setFone(rs.getString("FUN_FONE"));
				    	fun.setEnd(rs.getString("FUN_ENDERECO"));
				    	funcionarios.add(fun);
				    }
				    return funcionarios;
				    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return null;
	}
}
