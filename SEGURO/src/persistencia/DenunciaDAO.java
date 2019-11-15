package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class DenunciaDAO implements GenericDAO<Denuncia>{

	private ConexaoMysql conexao;
	private Denuncia denuncia;
	private Usuario usuario;
	private Endereco endereco;
	public DenunciaDAO() {
		this.conexao = new ConexaoMysql("localhost", "root", "", "sapucaia_alerta");
	}

	public Denuncia buscarPorId(long idDenuncia) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM denuncia WHERE id_denuncia = ?";
		PreparedStatement statement;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idDenuncia);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {	
				denuncia = new Denuncia();
				denuncia.setIdDenuncia(rs.getLong("id_denuncia"));
				denuncia.setAnonimo(rs.getBoolean("anonimo"));
				denuncia.setDataDenuncia(rs.getString("data_denuncia"));
				denuncia.setDescricao(rs.getString("descricao"));
				denuncia.setNivelViolencia(rs.getInt("nivel_violencia"));
				denuncia.setCategoria(rs.getString("categoria"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return denuncia;
	}

	public void salvar(Denuncia denuncia){
		this.conexao.abrirConexao();
		
		String sqlInsert = "INSERT INTO denuncia VALUES(null, ?, ?, ?, ?, ?, ?, ?)";

		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setBoolean(1, denuncia.isAnonimo());
			statement.setString(2, denuncia.getDataDenuncia());
			statement.setString(3, denuncia.getDescricao());
			statement.setInt(4, denuncia.getNivelViolencia());
			statement.setString(5, denuncia.getCategoria());
			statement.setString(6, denuncia.getUsuario().getEmail());
			statement.setLong(7, denuncia.getEndereco().getIdEndereco());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				denuncia.setIdDenuncia(rs.getLong(1));
			}

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();
		}
	}

	public void editar(Denuncia denuncia){
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE denuncia SET anonimo=?, data_denuncia=?, descricao=?, nivel_violencia=?, categoria=?, email=?, id_endereco=? WHERE id_denuncia=?";
		
		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setBoolean(1, denuncia.isAnonimo());
			statement.setString(2, denuncia.getDataDenuncia());
			statement.setString(3, denuncia.getDescricao());
			statement.setInt(4, denuncia.getNivelViolencia());
			statement.setString(5, denuncia.getCategoria());
			statement.setString(6, denuncia.getUsuario().getEmail());
			statement.setLong(7, denuncia.getEndereco().getIdEndereco());
			statement.setLong(8, denuncia.getIdDenuncia());
			statement.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}

	public void excluir(long idDenuncia) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM denuncia WHERE id_denuncia=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idDenuncia);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public List<Denuncia> buscarTodos(){
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM denuncia";
		PreparedStatement statement;
		List<Denuncia> listaDenuncia = new ArrayList<Denuncia>();	
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				denuncia = new Denuncia();
				usuario = new Usuario();
				endereco = new Endereco();
				denuncia.setIdDenuncia(rs.getLong("id_denuncia"));
				denuncia.setAnonimo(rs.getBoolean("anonimo"));
				denuncia.setDataDenuncia(rs.getString("data_denuncia"));
				denuncia.setDescricao(rs.getString("descricao"));
				denuncia.setNivelViolencia(rs.getInt("nivel_violencia"));
				denuncia.setCategoria(rs.getString("categoria"));
				denuncia.setUsuario(usuario);
				denuncia.setEndereco(endereco);
				listaDenuncia.add(denuncia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaDenuncia;
	}
}