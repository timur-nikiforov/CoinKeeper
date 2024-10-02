package org.denis.coinkeeper.api.repositories;

import org.denis.coinkeeper.api.entities.ProfitEntity;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepository extends JpaRepository<ProfitEntity,Long> {

    ProfitEntity findProfitEntitiesByProfitIdAndUser(Long profitId, UserEntity user);
}
