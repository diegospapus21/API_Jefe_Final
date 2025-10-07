package AndresFlores_DiegoGabriel_1_B.PruebaFinal.service;

import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Exception.ExceptionDatoNoEncontrado;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Modals.DTO.PeliculaDTO;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Repository.PeliculaRepository;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.entities.PeliculasEntity;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collector;

@Slf4j
@Service
@CrossOrigin
public class PeliculasService     {

    @Autowired
    private PeliculaRepository repo;

    public List<PeliculaDTO> getAllPeliculas() {
        List<PeliculasEntity> list PeliculasEntity = repo.findAll();

        return PeliculasEntity Stream().map(this::ConvertirADTO)
                .Collect(Collector.tolist());

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

    public boolean delete(Long id) {
        try {
            PeliculasEntity objPeli = repo.findById(id).orElse(null);

            if (objPeli != null){
                repo.deleteById(id);
                return true;
            }else{
                System.out.println("Pelicula no encontrado");
            }
        }
    }

    private PeliculaDTO ConvertirADTO(PeliculasEntity peliculaGuardado) {
    }

    private PeliculasEntity ConvertirAEntity(@Valid PeliculaDTO pelicula) {
    }



}
