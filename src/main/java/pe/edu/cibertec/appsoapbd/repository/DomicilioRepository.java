package pe.edu.cibertec.appsoapbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.appsoapbd.model.Domicilio;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {

}
