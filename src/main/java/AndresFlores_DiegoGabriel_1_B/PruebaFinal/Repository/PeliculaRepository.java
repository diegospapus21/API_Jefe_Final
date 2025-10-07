package AndresFlores_DiegoGabriel_1_B.PruebaFinal.Repository;

import AndresFlores_DiegoGabriel_1_B.PruebaFinal.entities.PeliculasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculasEntity , Long> {
}
