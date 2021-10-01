package com.kosmetologistpaycalc.paycalc.Repo;

import com.kosmetologistpaycalc.paycalc.Models.LastIncome;
import org.springframework.data.repository.CrudRepository;

public interface PostRepositoryLastExpenses extends CrudRepository<LastIncome, Long> {
}
