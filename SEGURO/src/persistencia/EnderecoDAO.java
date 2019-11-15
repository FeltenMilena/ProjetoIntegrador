package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class EnderecoDAO implements GenericDAO<Endereco> {

	private ConexaoMysql conexao;
	private Endereco endereco;

	public EnderecoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "sapucaia_alerta");
	}

	public void salvar(Endereco endereco) {

		this.conexao.abrirConexao();

		String sqlInsert = "INSERT INTO endereco VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setDouble(1, endereco.getIndicePerigo());
			statement.setString(2, endereco.getBairro());
			statement.setInt(3, endereco.getNumero());
			statement.setString(4, endereco.getRua());
			statement.setString(5, endereco.getComplemento());
			statement.setString(6, endereco.getCidade());
			statement.setString(7, endereco.getEstado());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				endereco.setIdEndereco(rs.getLong(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void editar(Endereco endereco) {

		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE endereco SET indice_perigo=?, bairro=?, número=?, rua=?, complemento=?, cidade=?, estado=? WHERE id_endereco=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setDouble(1, endereco.getIndicePerigo());
			statement.setString(2, endereco.getBairro());
			statement.setInt(3, endereco.getNumero());
			statement.setString(4, endereco.getRua());
			statement.setString(5, endereco.getComplemento());
			statement.setString(6, endereco.getCidade());
			statement.setString(7, endereco.getEstado());
			statement.setLong(8, endereco.getIdEndereco());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long idEndereco) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE * FROM endereco WHERE id_endereco=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idEndereco);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Endereco buscarPorId(long idEndereco) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM endereco WHERE id_endereco=?";
		PreparedStatement statement;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idEndereco);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				endereco = new Endereco();
				endereco.setIdEndereco(rs.getLong("id_endereco"));
				endereco.setIndicePerigo(rs.getDouble("indice_perigo"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setNumero(rs.getInt("número"));
				endereco.setRua(rs.getString("rua"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return endereco;
	}

	public List<Endereco> buscarTodos() {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM endereco";
		PreparedStatement statement;
		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				endereco = new Endereco();
				endereco.setIdEndereco(rs.getLong("id_endereco"));
				endereco.setIndicePerigo(rs.getDouble("indice_perigo"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setNumero(rs.getInt("número"));
				endereco.setRua(rs.getString("rua"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
				listaEnderecos.add(endereco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaEnderecos;
	}
}