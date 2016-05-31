// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

/**
 * AutorizacionUsuarioId generated by hbm2java
 */
public class AutorizacionUsuarioId implements java.io.Serializable {

	private int idUsuario;
	private short idAutorizacion;

	public AutorizacionUsuarioId() {
	}

	public AutorizacionUsuarioId(int idUsuario, short idAutorizacion) {
		this.idUsuario = idUsuario;
		this.idAutorizacion = idAutorizacion;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public short getIdAutorizacion() {
		return this.idAutorizacion;
	}

	public void setIdAutorizacion(short idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AutorizacionUsuarioId))
			return false;
		AutorizacionUsuarioId castOther = (AutorizacionUsuarioId) other;

		return (this.getIdUsuario() == castOther.getIdUsuario())
				&& (this.getIdAutorizacion() == castOther.getIdAutorizacion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdUsuario();
		result = 37 * result + this.getIdAutorizacion();
		return result;
	}

}