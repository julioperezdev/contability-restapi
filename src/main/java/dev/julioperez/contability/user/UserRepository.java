package dev.julioperez.contability.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    String queryCreateUser =
            "EXEC createUser @Username = :username , @Password = :password , @Email = :email , @Created = :created, @Enable = :enable , @IdRol = :idRol ";
    @Query(value = queryCreateUser, nativeQuery = true)
    User createUser(
            @Param("username") String username,
            @Param("password") String password,
            @Param("email") String email,
            @Param("created") Date created,
            @Param("enable") boolean enable,
            @Param("idRol") int idRol);



    String queryUpdateEnableUser =
            "EXEC updateEnableUser @Id = :id , @Enable = :enable";
    @Query(value = queryUpdateEnableUser, nativeQuery = true)
    Optional<User> updateEnableUser(
            @Param("id") Long userid,
            @Param("enable") boolean enable);


    String queryFindByUsername =
            "SELECT * FROM users WHERE username = :username ;";
    @Query(value = queryFindByUsername, nativeQuery = true)
    Optional<User> findByUsername(
            @Param("username") String username);

    String queryFindById =
            "SELECT * FROM users " +
                    "WHERE id = :id ;";
    @Query(value = queryFindById, nativeQuery = true)
    Optional<User> findUserById(
            @Param("id") Long userid);


}