package AndresFlores_DiegoGabriel_1_B.PruebaFinal.Controller;

import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Exception.ExceptionDatoNoEncontrado;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Exception.ExceptionDatosDuplicados;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.Modals.DTO.PeliculaDTO;
import AndresFlores_DiegoGabriel_1_B.PruebaFinal.service.PeliculasService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Peliculas")
@CrossOrigin("*")
public class PeliculasController {

    @Autowired
    private PeliculasService service;

    @GetMapping("/GetAllPeliculas")
    public ResponseEntity<List<PeliculaDTO>> getAllCars(){
        List<PeliculaDTO> cars = service.getAllPeliculas();

        if (cars == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(cars);
    }

    @PostMapping("/CrearPeliculas")
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
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                        "Status","Error",
                        "message","error al registrar",
                        "detail",e.getMessage()
                ));
            }
    }

    @PutMapping("/ModificarPeliculas/{id}")
    public ResponseEntity<?>actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PeliculaDTO peliculaDTO,
                  BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            PeliculaDTO peliculaActualizar = service.update(id,peliculaDTO);

            return ResponseEntity.ok(peliculaActualizar);
    }
        catch (ExceptionDatoNoEncontrado e){
            return ResponseEntity.notFound().build();
        }catch (ExceptionDatosDuplicados e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "Error", "Datos duplicados",
                    "Campo", e.getcampoduplicado
            ));
        }

    }

    @DeleteMapping("/EliminarPeliculas/{id}")
    public ResponseEntity<Map<String,String>>eliminar(@PathVariable Long id){
        try {
            if (!service.delete(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-message-error","Pelicula no encotrado").body(Map.of(
                        "Error","not found",
                        "mensaje","la pelicula no se encontro",
                        "timestamp", Instant.now().toString()
                ));

            }
            return ResponseEntity.ok().body(Map.of(
                    "status","completo",
                    "message","Pelicula Eliminado"
            ));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "Status" , "Error",
                    "message","erro al elminar",
                    "detail" , e .getMessage()
            ));
        }
    }
}
