package org.denis.coinkeeper.api.repositories;

import org.denis.coinkeeper.api.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {
    ClientEntity getClientByClientId(Long clientId);
}
