package AndresFlores_DiegoGabriel_1_B.PruebaFinal.service;

import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Exception.ExceptionDatoNoEncontrado;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Modals.DTO.PeliculaDTO;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Repository.PeliculaRepository;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.entities.PeliculasEntity;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.websocket.OnError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@CrossOrigin
public class PeliculasService     {

    @Autowired
    private PeliculaRepository repo;

    public List<PeliculaDTO> getAllPeliculas() {
        return repo.findAll().stream() .map( this::ConvertirADTO).collect(Collectors.toList());
    }

    public PeliculaDTO CreatePelicula(@Valid PeliculaDTO peliculaDTO) {
        if (peliculaDTO == null){
            throw new IllegalArgumentException("El rol puede se nulo");
        }
        try {
            PeliculasEntity peliculasEntity=
                    ConvertirAEntity(peliculaDTO);
            PeliculasEntity peliculaGuardado = repo.save(peliculasEntity);
            return ConvertirADTO(peliculaGuardado);
        }catch (Exception e){
            log.error("error al registrar pelicula: " + e.getMessage());

            throw new
                    ExceptionDatoNoEncontrado("Error al registra el dato" + e.getMessage());
        }
    }
    public PeliculaDTO update(Long id, @Valid PeliculaDTO peliculaDTO) {
        PeliculasEntity peliculaExistente = repo.findById(id).orElseThrow(()->new ExceptionDatoNoEncontrado("Pelicuna no encontrada"));

        peliculaExistente.setId_pelicula(peliculaDTO.getId_pelicula());

        PeliculasEntity peliculactualizada = repo.save(peliculaExistente);

                return ConvertirADTO(peliculactualizada);
    }

    public boolean delete(Long id) {
        try {
            PeliculasEntity objPeli = repo.findById(id).orElse(null);

            if (objPeli != null){
                repo.deleteById(id);
                return true;
            }else{
                System.out.println("Pelicula no encontrado");
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("No se encontro la pelicula con el id:"
             + id + "Para Eliminar",1);
        }
    }

    public PeliculasEntity ConvertirAEntity(@Valid PeliculaDTO dto) {
    PeliculasEntity peliculas2 = new PeliculasEntity();

    peliculas2.setId_pelicula(dto.getId_pelicula());
    peliculas2.setTitulo(dto.getTitulo());
    peliculas2.setGenero(dto.getGenero());
    peliculas2.setAno_estreno(dto.getAno_estreno());
    peliculas2.setDirector(dto.getDirector());
    peliculas2.setDuracion_min(dto.getDuracion_min());
    peliculas2.setFecha_creacion(dto.getFecha_creacion());
    return peliculas2;
    }

    public PeliculaDTO ConvertirADTO(PeliculasEntity peliculaGuardado) {
        PeliculaDTO pelicula3 = new PeliculaDTO();

        pelicula3.setId_pelicula(peliculaGuardado.getId_pelicula());
        pelicula3.setFecha_creacion(peliculaGuardado.getFecha_creacion());
        pelicula3.setDirector(pelicula3.getDirector());
        pelicula3.setTitulo(pelicula3.getTitulo());
        pelicula3.setAno_estreno(pelicula3.getAno_estreno());
        pelicula3.setDuracion_min(pelicula3.getDuracion_min());
        pelicula3.setGenero(peliculaGuardado.getGenero());

        return pelicula3;
    }

}
