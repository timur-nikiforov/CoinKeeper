package org.denis.coinkeeper.api.repositories;

import org.denis.coinkeeper.api.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client getClientByClientId(Long clientId);
}
