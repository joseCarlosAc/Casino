package api;

import java.sql.SQLException;

public interface Operable {
	public void open();
	
	public void close() throws SQLException;
	
	public void set();

}
