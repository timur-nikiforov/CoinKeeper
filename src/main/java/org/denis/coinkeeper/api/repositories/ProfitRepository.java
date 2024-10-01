package org.denis.coinkeeper.api.repositories;

import org.denis.coinkeeper.api.entities.ExpensesEntity;
import org.denis.coinkeeper.api.entities.ProfitEntity;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfitRepository extends JpaRepository<ProfitEntity,Long> {

    @Query(value = """
    SELECT *
    FROM profit
    INNER JOIN users
    ON profit.user_id=users.user_id
    WHERE users.email= :email
    """, nativeQuery = true)
    List<ProfitEntity> getProfitList(@Param("email") String email);

    ProfitEntity findProfitEntitiesByProfitIdAndUser(Long profitId, UserEntity user);
}
