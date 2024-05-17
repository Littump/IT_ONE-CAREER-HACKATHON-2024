package com.jk.it_one.repositories;

import com.jk.it_one.Interfaces.OperationRepository;
import com.jk.it_one.models.Balance;
import com.jk.it_one.models.Income;
import com.jk.it_one.models.IncomePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomePeriodRepository extends OperationRepository<IncomePeriod> {
}
