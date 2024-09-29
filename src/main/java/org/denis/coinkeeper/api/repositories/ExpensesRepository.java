package org.denis.coinkeeper.api.repositories;


import org.denis.coinkeeper.api.entities.ExpensesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExpensesRepository extends JpaRepository<ExpensesEntity,Long> {

    @Query(value = """
    SELECT *
    FROM expenses
    INNER JOIN users
    ON expenses.user_id=users.user_id
    WHERE users.email= :email
    """, nativeQuery = true)
    List<ExpensesEntity> getExpensesList(@Param("email") String email);

}
