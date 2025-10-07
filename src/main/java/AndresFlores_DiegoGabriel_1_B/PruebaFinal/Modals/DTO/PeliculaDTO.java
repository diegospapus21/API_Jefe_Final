package AndresFlores_DiegoGabriel_1_B.PruebaFinal.Modals.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class PeliculaDTO {
    private Long id_pelicula;
    private String titulo;
    private String director;
    private String genero;
    private Long ano_estreno;
    private Long duracion_min;
    private Date fecha_creacion;
}
