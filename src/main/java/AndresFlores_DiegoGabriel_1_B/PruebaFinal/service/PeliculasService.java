package AndresFlores_DiegoGabriel_1_B.PruebaFinal.service;

import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Modals.DTO.PeliculaDTO;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Repository.PeliculaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@Service
@CrossOrigin
public class PeliculasService     {

    @Autowired
    private PeliculaRepository repo;


    public PeliculaDTO CreatePelicula(@Valid PeliculaDTO pelicula) {
    }
}
