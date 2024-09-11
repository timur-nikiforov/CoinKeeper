package org.denis.coinkeeper.api.repositories;

import org.denis.coinkeeper.api.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity,Long> {
}
