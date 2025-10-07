package AndresFlores_DiegoGabriel_1_B.PruebaFinal.Controller;

import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Modals.DTO.PeliculaDTO;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.service.PeliculasService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Peliculas")
@CrossOrigin
public class PeliculasController {

    @Autowired
    private PeliculasService service;

    @GetMapping("/GetallPeliculas")
    public List<PeliculaDTO> obtenerdatos(){
     return  service.ObtenerPeliculas;
    }
    @PostMapping("/CrearPelicula")
    public ResponseEntity<Map<String,String>>crear
            (@Valid@RequestBody PeliculaDTO pelicula){
            try {
             PeliculaDTO respuesta = service.CreatePelicula(pelicula);
             if (respuesta == null){
                 return ResponseEntity.badRequest().body(Map.of(
                         "Status","Incorrecto",
                         "Status","Incorrecto",
                         "message","Datos invalidos"
                 ));
             }
             return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                     "Status","Succes",
                     "Data","respuesta"
             ));
            }
    }
}
