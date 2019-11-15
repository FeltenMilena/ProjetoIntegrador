package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class AvaliacaoDicaDAO implements GenericDAO<AvaliacaoDica>{
	private ConexaoMysql conexao;
	private AvaliacaoDica avaliacaoDica;
	private Usuario usuario;
	private DicaSeguranca dicaSeguranca;

	public AvaliacaoDicaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "sapucaia_alerta");
	}
	
	public void salvar(AvaliacaoDica avaliacaoDica) {
		this.conexao.abrirConexao();
		
		String sqlInsert = "INSERT INTO avaliar_dica VALUES(null, ?, ?)";

		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, avaliacaoDica.getUsuario().getEmail());
			statement.setLong(2, avaliacaoDica.getDicaSeguranca().getIdDica());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				avaliacaoDica.setIdDicaAvalia(rs.getLong(1));
			}

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}
	
	public void editar(AvaliacaoDica avaliacaoDica) {
		this.conexao.abrirConexao();
		
		String sqlUpdate = "UPDATE avaliar_dica SET email=?, id_dica=? WHERE id_dica_avalia=?";
		
		try{
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			statement.setString(1, avaliacaoDica.getUsuario().getEmail());
			statement.setLong(2, avaliacaoDica.getDicaSeguranca().getIdDica());
			statement.setLong(3, avaliacaoDica.getIdDicaAvalia());
			statement.executeUpdate();

		} catch(SQLException e){
			e.printStackTrace();

		} finally{
			this.conexao.fecharConexao();

		}
	}

	public void excluir(long idDicaAvalia) {
		this.conexao.abrirConexao();

		String sqlDelete = "DELETE FROM avaliar_dica WHERE id_dica_avalia=?";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idDicaAvalia);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public AvaliacaoDica buscarPorId(long idAvaliacaoDica) {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM avaliar_dica WHERE id_dica_avalia=?";
		PreparedStatement statement;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, idAvaliacaoDica);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				avaliacaoDica = new AvaliacaoDica();
				usuario = new Usuario();
				dicaSeguranca = new DicaSeguranca();
				dicaSeguranca.setIdDica(rs.getLong("id_dica"));
				usuario.setEmail(rs.getString("email"));
				avaliacaoDica.setDicaSeguranca(dicaSeguranca);
				avaliacaoDica.setUsuario(usuario);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return avaliacaoDica;
	}

	public List<AvaliacaoDica> buscarTodos(){
		this.conexao.abrirConexao();
		String sqlSelect= "SELECT * FROM avaliar_dica";
		PreparedStatement statement;
		List<AvaliacaoDica> listaAvaliacaoDica = new ArrayList<AvaliacaoDica>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				avaliacaoDica = new AvaliacaoDica();
				usuario = new Usuario();
				dicaSeguranca = new DicaSeguranca();
				dicaSeguranca.setIdDica(rs.getLong("id_dica"));
				usuario.setEmail(rs.getString("email"));
				avaliacaoDica.setDicaSeguranca(dicaSeguranca);
				avaliacaoDica.setUsuario(usuario);
				listaAvaliacaoDica.add(avaliacaoDica);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return listaAvaliacaoDica;
	}

}
