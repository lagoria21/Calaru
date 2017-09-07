package calaru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import calaru.model.OrdenDeTrabajo;

@Repository 
public interface OrdenDeTrabajoRepository extends JpaRepository<OrdenDeTrabajo, Long> {

}
