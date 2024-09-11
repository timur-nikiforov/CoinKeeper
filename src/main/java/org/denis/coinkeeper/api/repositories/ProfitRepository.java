package org.denis.coinkeeper.api.repositories;

import org.denis.coinkeeper.api.entities.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepository extends JpaRepository<Profit,Long> {
}
