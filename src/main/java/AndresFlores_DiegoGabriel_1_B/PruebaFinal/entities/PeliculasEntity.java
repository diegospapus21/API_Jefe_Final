package AndresFlores_DiegoGabriel_1_B.PruebaFinal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "PELICULAS")
@Getter@Setter
@ToString@EqualsAndHashCode
public class PeliculasEntity {
     @Id
     private Long id_pelicula;
    @Column(name = "TITULO")
     private String titulo;
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "ANO_ESTRENO")
    private Long ano_estreno;
    @Column(name = "DURACION_MIN")
    private Long duracion_min;
    @Column(name = "FECHA_CREACION")
    private Date fecha_creacion;
}
