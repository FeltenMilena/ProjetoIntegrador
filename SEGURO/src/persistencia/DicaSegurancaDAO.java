package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class DicaSegurancaDAO implements GenericDAO<DicaSeguranca>{
	private ConexaoMysql conexao;
	private DicaSeguranca dicaSeguranca;
	private Usuario usuario;
	private Endereco endereco;

	public DicaSegurancaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "sapucaia_alerta");
	}
	
	public void salvar(DicaSeguranca dicaSeguranca) {
		this.conexao.abrirConexao();
		
		String sqlInsert = "INSERT INTO dica_seguranca VALUES(null, ?, ?, ?, ?, ?)";

		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, dicaSeguranca.isAnonimo());
            statement.setString(2, dicaSeguranca.getDescricao());
            statement.setString(3, dicaSeguranca.getDataDica());
            statement.setString(4, dicaSeguranca.getUsuario().getEmail());
			statement.setLong(5, dicaSeguranca.getEndereco().getIdEndereco());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				dicaSeguranca.setIdDica(rs.getLong(1));
			}

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}
	
	public void editar(DicaSeguranca dicaSeguranca) {
		this.conexao.abrirConexao();
		
		String sqlUpdate = "UPDATE dica_seguranca SET anonimo=?, descricao=?, data_dica=?, email=?, id_endereco=? WHERE id_dica=?";
		
		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
            statement.setBoolean(1, dicaSeguranca.isAnonimo());
            statement.setString(2, dicaSeguranca.getDescricao());
            statement.setString(3, dicaSeguranca.getDataDica());
            statement.setString(4, dicaSeguranca.getUsuario().getEmail());
            statement.setLong(5, dicaSeguranca.getEndereco().getIdEndereco());
            statement.setLong(6, dicaSeguranca.getIdDica());
			statement.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}

	public void excluir(long idDica) {
		this.conexao.abrirConexao();

		String sqlDelete = "DELETE FROM dica_seguranca WHERE id_dica=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idDica);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public DicaSeguranca buscarPorId(long idDica) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM dica_seguranca WHERE id_dica=?";
		PreparedStatement statement;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idDica);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
                dicaSeguranca = new DicaSeguranca();
                dicaSeguranca.setIdDica(rs.getLong("id_dica"));
                dicaSeguranca.setAnonimo(rs.getBoolean("anonimo"));
                dicaSeguranca.setDescricao(rs.getString("descricao"));
                dicaSeguranca.setDataDica(rs.getString("data_dica"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return dicaSeguranca;
	}
	public List<DicaSeguranca> buscarTodos(){
		this.conexao.abrirConexao();
		String sqlSelect= "SELECT * FROM dica_seguranca";
		PreparedStatement statement;
		List<DicaSeguranca> listaDicaSeguranca = new ArrayList<DicaSeguranca>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
                dicaSeguranca = new DicaSeguranca();
                usuario = new Usuario();
                endereco = new Endereco();
                dicaSeguranca.setIdDica(rs.getLong("id_dica"));
                dicaSeguranca.setAnonimo(rs.getBoolean("anonimo"));
                dicaSeguranca.setDescricao(rs.getString("descricao"));
                dicaSeguranca.setDataDica(rs.getString("data_dica"));
                dicaSeguranca.setUsuario(usuario);
                dicaSeguranca.setEndereco(endereco);
                listaDicaSeguranca.add(dicaSeguranca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return listaDicaSeguranca;
	}
}
