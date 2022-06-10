package elementos;

import javax.persistence.*;

@Entity
@Table(name= "tipo_usuario")
public class UserType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "tipo_usuario_id")
    private Integer tipoUsuarioId;
    @Column(name = "descripcion")
    private String descripcion;

    public UserType() {
    }

    public UserType(Integer tipoUsuarioId, String descripcion) {
        this.tipoUsuarioId = tipoUsuarioId;
        this.descripcion = descripcion;
    }

    public Integer getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(Integer tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
