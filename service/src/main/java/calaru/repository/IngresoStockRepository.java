package calaru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import calaru.model.IngresoStock;

@Repository 
public interface IngresoStockRepository extends JpaRepository<IngresoStock, Long>{

}
