package org.denis.coinkeeper.api.repositories;

import org.denis.coinkeeper.api.entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
}
