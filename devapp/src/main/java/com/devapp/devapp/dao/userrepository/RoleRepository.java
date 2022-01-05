package com.devapp.devapp.dao.userrepository;



import com.devapp.devapp.entities.userentity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByName (String roleName);

}
