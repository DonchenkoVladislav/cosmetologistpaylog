package com.kosmetologistpaycalc.paycalc.Repo;

import com.kosmetologistpaycalc.paycalc.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
