package persistencia;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO <T>{
	
	public void salvar (T objeto) throws SQLException;
	public void editar (T objeto) throws SQLException;
	public void excluir (long id) throws SQLException;
	public List<T> buscarTodos() throws SQLException;
}
