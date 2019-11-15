package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class ComentarioDicaDAO implements GenericDAO<ComentarioDica>{
	private ConexaoMysql conexao;
	private ComentarioDica comentarioDica;
	private Usuario usuario;
	private DicaSeguranca dicaSeguranca;

	public ComentarioDicaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "sapucaia_alerta");
	}
	
	public void salvar(ComentarioDica comentarioDica) {
		this.conexao.abrirConexao();
		
		String sqlInsert = "INSERT INTO comentario_dica VALUES(null, ?, ?)";

		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, comentarioDica.getUsuario().getEmail());
			statement.setLong(2, comentarioDica.getDicaSeguranca().getIdDica());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				comentarioDica.setIdDicaComen(rs.getLong(1));
			}

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}
	
	public void editar(ComentarioDica comentarioDica) {
		this.conexao.abrirConexao();
		
		String sqlUpdate = "UPDATE comentario_dica SET email=?, id_dica=? WHERE id_dica_comen=?";
		
		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, comentarioDica.getUsuario().getEmail());
			statement.setLong(2, comentarioDica.getDicaSeguranca().getIdDica());
			statement.setLong(3, comentarioDica.getIdDicaComen());
			statement.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}

	public void excluir(long idDicaComen) {
		this.conexao.abrirConexao();

		String sqlDelete = "DELETE FROM comentario_dica WHERE id_dica_comen=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idDicaComen);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public ComentarioDica buscarPorId(long idComentarioDenuncia) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM comentario_dica WHERE id_dica_comen=?";
		PreparedStatement statement;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idComentarioDenuncia);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				comentarioDica = new ComentarioDica();
				usuario = new Usuario();
				dicaSeguranca = new DicaSeguranca();
				dicaSeguranca.setIdDica(rs.getLong("id_dica"));
				usuario.setEmail(rs.getString("email"));
				comentarioDica.setDicaSeguranca(dicaSeguranca);
				comentarioDica.setUsuario(usuario);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return comentarioDica;
	}
	public List<ComentarioDica> buscarTodos(){
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM comentario_dica";
		PreparedStatement statement;
		List<ComentarioDica> listaComentarioDica = new ArrayList<ComentarioDica>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				comentarioDica = new ComentarioDica();
				dicaSeguranca = new DicaSeguranca();
				dicaSeguranca.setIdDica(rs.getLong("id_dica"));
				usuario.setEmail(rs.getString("email"));
				comentarioDica.setDicaSeguranca(dicaSeguranca);
				comentarioDica.setUsuario(usuario);
				listaComentarioDica.add(comentarioDica);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return listaComentarioDica;
	}

}
