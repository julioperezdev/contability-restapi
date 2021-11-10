package dev.julioperez.contability.securityAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    String queryCreateToken =
            "EXEC createToken @Token = :token , @Id = :userid ";
//            "INSERT INTO TOKEN (token, userid) " +
//                    "VALUES( :token, :userid) ;";

    @Query(value = queryCreateToken, nativeQuery = true)
    Optional<VerificationToken> createToken(
            @Param("token") String token,
            @Param("userid") Long userid);


    String queryFindByToken =
            "SELECT * FROM token WHERE token = :token ;";
    @Query(value = queryFindByToken, nativeQuery = true)
    Optional<VerificationToken> findByToken(@Param("token") String token);
}