package br.com.desafio.infrastructure.repository;

import br.com.desafio.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {
    boolean existsByClientId(String clientId);
}
