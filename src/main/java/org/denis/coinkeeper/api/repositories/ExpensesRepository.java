package org.denis.coinkeeper.api.repositories;


import org.denis.coinkeeper.api.entities.ExpensesEntity;
import org.denis.coinkeeper.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesEntity,Long> {

    ExpensesEntity findProfitEntitiesByExpensesIdAndUser(Long expensesId, UserEntity user);

}
