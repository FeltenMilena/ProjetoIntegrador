package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;


public class UsuarioDAO implements GenericDAO<Usuario> {

	private ConexaoMysql conexao;
	private Usuario usuario;
	private Endereco endereco;
	
	public UsuarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "sapucaia_alerta");
	}

	public void salvar(Usuario usuario) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO usuario VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getNome());
			statement.setString(3, usuario.getSenha());
			statement.setLong(4, usuario.getEndereco().getIdEndereco());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void editar(Usuario usuario, String emailParaEditar) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE usuario SET email=?, nome=?, senha=? WHERE email=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getNome());
			statement.setString(3, usuario.getSenha());
			statement.setString(4, emailParaEditar);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(String email) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM usuario WHERE email=?";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setString(1, email);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Usuario buscarPorEmail(String email) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM usuario WHERE email=?";
		PreparedStatement statement;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuario;
	}

	public List<Usuario> buscarTodos() {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM usuario";
		PreparedStatement statement;
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				usuario = new Usuario();
				endereco = new Endereco();
				usuario.setEmail(rs.getString("email"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEndereco(endereco);
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaUsuarios;
	}

	public void excluir(long id) throws SQLException {

	}

	public void editar(Usuario objeto) throws SQLException {

	}
}