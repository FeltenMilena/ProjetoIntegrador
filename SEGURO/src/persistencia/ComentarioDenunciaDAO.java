package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class ComentarioDenunciaDAO implements GenericDAO<ComentarioDenuncia>{
	private ConexaoMysql conexao;
	private ComentarioDenuncia comentarioDenuncia;
	private Usuario usuario;
	private Denuncia denuncia;

	public ComentarioDenunciaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "sapucaia_alerta");
	}
	
	public void salvar(ComentarioDenuncia comentarioDenuncia) {
		this.conexao.abrirConexao();
		
		String sqlInsert = "INSERT INTO comentario_denuncia VALUES(null, ?, ?)";

		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, comentarioDenuncia.getUsuario().getEmail());
			statement.setLong(2, comentarioDenuncia.getDenuncia().getIdDenuncia());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				comentarioDenuncia.setIdDenComen(rs.getLong(1));
			}

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}
	
	public void editar(ComentarioDenuncia comentarioDenuncia) {
		this.conexao.abrirConexao();
		
		String sqlUpdate = "UPDATE comentario_denuncia SET email=?, id_denuncia=? WHERE id_den_comen=?";
		
		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, comentarioDenuncia.getUsuario().getEmail());
			statement.setLong(2, comentarioDenuncia.getDenuncia().getIdDenuncia());
			statement.setLong(3, comentarioDenuncia.getIdDenComen());
			statement.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}

	public void excluir(long idDenComen) {
		this.conexao.abrirConexao();

		String sqlDelete = "DELETE FROM comentario_denuncia WHERE id_den_comen=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idDenComen);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	public ComentarioDenuncia buscarPorId(long idComentarioDenuncia) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM comentario_denuncia WHERE id_den_comen=?";
		PreparedStatement statement;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idComentarioDenuncia);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				comentarioDenuncia = new ComentarioDenuncia();
				usuario = new Usuario();
				denuncia = new Denuncia();
				denuncia.setIdDenuncia(rs.getLong("id_denuncia"));
				usuario.setEmail(rs.getString("email"));
				comentarioDenuncia.setDenuncia(denuncia);
				comentarioDenuncia.setUsuario(usuario);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return comentarioDenuncia;
	}
	public List<ComentarioDenuncia> buscarTodos(){
		this.conexao.abrirConexao();
		String sqlSelect= "SELECT * FROM comentario_denuncia";
		PreparedStatement statement;
		List<ComentarioDenuncia> listaComentarioDenuncia = new ArrayList<ComentarioDenuncia>();	
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				comentarioDenuncia = new ComentarioDenuncia();
				usuario = new Usuario();
				denuncia = new Denuncia();
				denuncia.setIdDenuncia(rs.getLong("id_denuncia"));
				usuario.setEmail(rs.getString("email"));
				comentarioDenuncia.setDenuncia(denuncia);
				comentarioDenuncia.setUsuario(usuario);
				listaComentarioDenuncia.add(comentarioDenuncia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return listaComentarioDenuncia;
	}

}
