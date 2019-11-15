package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class AvaliacaoDenunciaDAO implements GenericDAO<AvaliacaoDenuncia>{
	private ConexaoMysql conexao;
	private AvaliacaoDenuncia avaliacaoDenuncia;
	private Usuario usuario;
	private Denuncia denuncia;

	public AvaliacaoDenunciaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "sapucaia_alerta");
	}
	
	public void salvar(AvaliacaoDenuncia avaliacaoDenuncia) {
		this.conexao.abrirConexao();
		
		String sqlInsert = "INSERT INTO avaliar_denuncia VALUES(null, ?, ?)";

		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, avaliacaoDenuncia.getUsuario().getEmail());
			statement.setLong(2, avaliacaoDenuncia.getDenuncia().getIdDenuncia());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				avaliacaoDenuncia.setIdDenAvalia(rs.getLong(1));
			}

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}
	
	public void editar(AvaliacaoDenuncia avaliacaoDenuncia) {
		this.conexao.abrirConexao();
		
		String sqlUpdate = "UPDATE avaliar_denuncia SET email=?, id_denuncia=? WHERE id_den_avalia=?";
		
		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, avaliacaoDenuncia.getUsuario().getEmail());
			statement.setLong(2, avaliacaoDenuncia.getDenuncia().getIdDenuncia());
			statement.setLong(3, avaliacaoDenuncia.getIdDenAvalia());
			statement.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}

	public void excluir(long idDenAvalia) {
		this.conexao.abrirConexao();

		String sqlDelete = "DELETE FROM avaliar_denuncia WHERE id_den_avalia=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idDenAvalia);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public AvaliacaoDenuncia buscarPorId(long idAvaliacaoDenuncia) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM avaliar_denuncia WHERE id_den_avalia=?";
		PreparedStatement statement;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idAvaliacaoDenuncia);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				avaliacaoDenuncia = new AvaliacaoDenuncia();
				usuario = new Usuario();
				denuncia = new Denuncia();
				denuncia.setIdDenuncia(rs.getLong("id_denuncia"));
				usuario.setEmail(rs.getString("email"));
				avaliacaoDenuncia.setDenuncia(denuncia);
				avaliacaoDenuncia.setUsuario(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return avaliacaoDenuncia;
	}
	public List<AvaliacaoDenuncia> buscarTodos(){
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM avaliar_denuncia";
		PreparedStatement statement;
		List<AvaliacaoDenuncia> listaAvaliacaoDenuncia = new ArrayList<AvaliacaoDenuncia>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				avaliacaoDenuncia = new AvaliacaoDenuncia();
				usuario = new Usuario();
				denuncia = new Denuncia();
				denuncia.setIdDenuncia(rs.getLong("id_denuncia"));
				usuario.setEmail(rs.getString("email"));
				avaliacaoDenuncia.setDenuncia(denuncia);
				avaliacaoDenuncia.setUsuario(usuario);
				listaAvaliacaoDenuncia.add(avaliacaoDenuncia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAvaliacaoDenuncia;
	}

}
