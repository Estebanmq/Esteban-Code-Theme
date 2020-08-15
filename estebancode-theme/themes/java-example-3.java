package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Director;
import modelo.GeneroPelicula;
import modelo.Sexo;
import vista.DialogoDirectorAlta;
import vista.DialogoDirectorBaja;
import vista.DialogoDirectorModificacion;

/**
 * Esta clase está dedicada al manejo de datos de directores en la base de datos
 * @author Sergio Fernández Rivera
 * @since 16/05/2020
 * @version 1.0
 * 
 */

public class DaoDirectorMantenimiento {
	
	/**
	 * Query a ejecutar 
	 */
	private String query;
	/**
	 * Conexión a la BBDD
	 * @see java.sql.Connection
	 */
	private Connection conn;
	/**
	 * Statement para ejecutar sentencias SQL
	 * @see java.sql.Statement 
	 */
	private Statement st;
	/**
	 * PreparedStatement para ejecutar comandos SQL ya precompilados
	 * @see java.sql.PreparedStatement
	 */
	private PreparedStatement ps;
	/**
	 * ResultSet para almacenar el resultado de la sentencia SQL
	 * @see java.sql.ResultSet
	 */
	private ResultSet rs;
	
	private final int prueba;

	/**
	 * Método para insertar un director en la BBDD
	 * 
	 * @param dAlta Dialogo de alta de directores
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public void darAltaDirector (DialogoDirectorAlta dAlta) throws ClassNotFoundException, SQLException {
		
		int codigo = 0;
		//monta la query
		this.setQuery("select max(codigo) from participante");
		
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		if(this.getRs().next()) {
			codigo=this.getRs().getInt(1)+1;
		}
		
		String nombre = dAlta.getTextNombre().getText();
		String fecha = dAlta.getTextFecha().getText();
		//Date date = Date.valueOf(fecha);
		String sexo = dAlta.getBg().getSelection().getActionCommand().toUpperCase();
		String generoPreferido = dAlta.getTextGenero().getText();
		String nacionalidad = dAlta.getComboBoxPais().getSelectedItem().toString();
		
		String insertParticipante = "INSERT INTO PARTICIPANTE VALUES ("+codigo+ ",'" +nombre+ "',DATE('" + fecha + "'),'" +sexo+ "', (SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"'))";
		this.setQuery(insertParticipante);
		st = conn.createStatement();
		st.executeUpdate(this.getQuery());
		
		String insertDirector = "INSERT INTO DIRECTOR VALUES ("+codigo+ ",'" +generoPreferido+ "')";
		this.setQuery(insertDirector);
		st.executeUpdate(this.getQuery());
		conn.commit();
		st.close();
		Conexion.cerrar();
		
	}
	
	/** 
	 * Método que busca el código del director y lo guarda
	 * @return Array con los directores
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public ArrayList<String> obtenerNombreDirectores () throws ClassNotFoundException, SQLException {
		
		String direc;
		ArrayList<String> directores =  new ArrayList<String>();
		
												// Monta la query a ejecutar
		this.setQuery("SELECT participante.nombre FROM director INNER JOIN participante ON director.codigo = participante.codigo");
			
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			direc=this.getRs().getString(1);
			directores.add(direc);
		}
		Conexion.cerrar();
		return directores;
		
	}
	/** 
	 * Método que busca el director con un codigo especifico y lo devuelve creando un objeto
	 * @param codigo del director
	 * @return Un objeto director con los datos de la consulta
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public Director obtenerDirector(int codigo) throws ClassNotFoundException, SQLException {
		DaoPaisMantenimiento daoPaisMantenimiento = new DaoPaisMantenimiento();
		Director direc;
		GeneroPelicula generoPreferido = null;
		
		conn = Conexion.getConexion();
		st=conn.createStatement();
		rs = st.executeQuery("SELECT participante.NOMBRE, participante.FECHANACIMIENTO , participante.SEXO , director.GENEROPREFERIDO, participante.NACIONALIDAD "
				+ "FROM participante INNER JOIN director ON participante.codigo = director.CODIGO WHERE director.codigo="+codigo);
		rs.next();
		
		if (!rs.getString(4).isEmpty()) {
			generoPreferido = GeneroPelicula.valueOf(rs.getString(4));						
		}
		
		direc = new Director(codigo,rs.getString(1),rs.getDate(2),Sexo.valueOf(rs.getString(3).toUpperCase()), generoPreferido ,daoPaisMantenimiento.obtenerPais(rs.getInt(5)));
		
		Conexion.cerrar();
		st.close();
		return direc;
		
	}
	

	/**
	 * Método para modificar datos a un director
	 * @param dialogo Diálogo de modificación de directores
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public void modificarDirector(DialogoDirectorModificacion dialogo) throws ClassNotFoundException, SQLException{
		
		int codigo = Integer.parseInt(dialogo.getTextFieldCodigo().getText());
		
		String nombre = dialogo.getTextFieldNombre().getText();
		String fecha = dialogo.getTextFieldFecha().getText();
		String sexo = dialogo.getBg().getSelection().getActionCommand().toUpperCase();
		String generoPreferido = dialogo.getComboBoxGenero().getSelectedItem().toString();
		String nacionalidad = dialogo.getComboBoxPais().getSelectedItem().toString();
		
		this.setConn(Conexion.getConexion());
		st = conn.createStatement();
		String modificarParticipante = "UPDATE PARTICIPANTE SET NOMBRE = '" +nombre+ "', FECHANACIMIENTO = DATE('" + fecha + "'),SEXO = '" +sexo+ "', NACIONALIDAD = (SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"') WHERE CODIGO ="+ codigo;
		st.executeUpdate(modificarParticipante);
		String modificarDirector = "UPDATE DIRECTOR SET GENEROPREFERIDO = '" + GeneroPelicula.valueOfDescripcion(generoPreferido) + "' WHERE CODIGO =" + codigo;
		st.executeUpdate(modificarDirector);
		st.close();
		Conexion.cerrar();
	}
	
	/**
	 * Método para dar de baja a un director
	 * @param dialogo Diálogo de baja de directores
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public void darBajaDirector (DialogoDirectorBaja dialogo) throws ClassNotFoundException, SQLException{
	
		int codigo = Integer.parseInt(dialogo.getTextFieldCodigo().getText());

		this.setConn(Conexion.getConexion());
		st = conn.createStatement();
		String borrarDirector = "DELETE FROM DIRECTOR WHERE CODIGO=" + codigo;
		st.executeUpdate(borrarDirector);
		String borrarParticipante = "DELETE FROM PARTICIPANTE WHERE CODIGO=" + codigo;
		st.executeUpdate(borrarParticipante);
		st.close();
		Conexion.cerrar();
		
	}
	
	/**
	 * Método para buscar el codigo de un director
	 * @param nombre Nombre del director
	 * @return Código de director localizado 
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public int buscarCodDirector (String nombre) throws ClassNotFoundException, SQLException{
		int codigo = 0;
		this.setConn(Conexion.getConexion());
		st = conn.createStatement();
		rs = st.executeQuery("SELECT codigo FROM PARTICIPANTE WHERE NOMBRE = '"+nombre+"'");
		
		if (rs.next()) {
			codigo=rs.getInt(1);			
		}
		st.close();
		Conexion.cerrar();
		return codigo;
	}

	// GETTERS & SETTERS
	private Connection getConn() {
		return conn;
	}

	private void setConn(Connection conn) {
		this.conn = conn;
	}

	private String getQuery() {
		return query;
	}

	private void setQuery(String query) {
		this.query = query;
	}

	private PreparedStatement getPs() {
		return ps;
	}

	private void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	private ResultSet getRs() {
		return rs;
	}

	private void setRs(ResultSet rs) {
		this.rs = rs;
	}
}
