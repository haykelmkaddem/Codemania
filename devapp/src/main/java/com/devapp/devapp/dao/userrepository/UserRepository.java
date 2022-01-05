package com.devapp.devapp.dao.userrepository;


import com.devapp.devapp.entities.userentity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername (String userName);
    public User findByEmail (String email);

    @Query("select  count(c) from User c")
    public Long NBUser();


}
