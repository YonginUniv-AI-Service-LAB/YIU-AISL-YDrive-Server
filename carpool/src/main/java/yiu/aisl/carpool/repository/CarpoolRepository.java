package yiu.aisl.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yiu.aisl.carpool.domain.Carpool;

public interface CarpoolRepository extends JpaRepository<Carpool, String> {
}