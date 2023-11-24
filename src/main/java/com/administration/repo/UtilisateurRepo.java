package com.administration.repo;

import com.administration.entity.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur,String> {
    @Query("SELECT a FROM Utilisateur a WHERE a.login LIKE %:kw% AND a.nomU LIKE %:nom% AND a.prenU LIKE %:pren% AND a.estActif = COALESCE(:estActif, a.estActif)")
    Page<Utilisateur> findUtilisateurByLogin(@Param("kw") String keyword, @Param("nom") String nom, @Param("pren") String prenom,@Param("estActif") Integer estActif, Pageable pageable);

    long count();

    @Query("select u from Utilisateur u WHERE " +
            "(:login IS null OR lower(u.login) LIKE lower(concat('%', :login, '%'))) " +
            "AND (:prenU IS null OR lower(u.prenU) LIKE lower(concat('%', :prenU, '%'))) " +
            "AND (:nomU IS null OR lower(u.nomU) LIKE lower(concat('%', :nomU, '%'))) " +
            "AND (:matricule IS null OR lower(u.matricule) LIKE lower(concat('%', :matricule, '%'))) " +
            "AND (:zoneId IS null OR u.ett.dregional.zone.idZone LIKE lower(concat('%', :zoneId, '%'))) " +
            "AND (:estActif IS null OR u.estActif = :estActif) " +
            "AND (:is_EXPIRED IS null OR u.is_EXPIRED = :is_EXPIRED)" +
            "AND current_date >= u.date_EXPIRED")
    Page<Utilisateur> findUtilisateurExpired(@Param("login")String login,
                                           @Param("prenU")String prenU,
                                           @Param("nomU")String nomU,
                                           @Param("matricule")String matricule,
                                           @Param("estActif")Integer estActif,
                                           @Param("is_EXPIRED")Integer is_EXPIRED,
                                             @Param("zoneId")String zoneId,
                                           Pageable pageable);
    @Query("select u from Utilisateur u WHERE " +
            "(:login IS null OR lower(u.login) LIKE lower(concat('%', :login, '%'))) " +
            "AND (:prenU IS null OR lower(u.prenU) LIKE lower(concat('%', :prenU, '%'))) " +
            "AND (:nomU IS null OR lower(u.nomU) LIKE lower(concat('%', :nomU, '%'))) " +
            "AND (:matricule IS null OR lower(u.matricule) LIKE lower(concat('%', :matricule, '%'))) " +
            "AND (:zoneId IS null OR u.ett.dregional.zone.idZone LIKE lower(concat('%', :zoneId, '%'))) " +
            "AND (:estActif IS null OR u.estActif = :estActif) " +
            "AND (:is_EXPIRED IS null OR u.is_EXPIRED = :is_EXPIRED)" +
            "AND current_date < u.date_EXPIRED")
    Page<Utilisateur> findUtilisateurValid(@Param("login")String login,
                                           @Param("prenU")String prenU,
                                           @Param("nomU")String nomU,
                                           @Param("matricule")String matricule,
                                           @Param("estActif")Integer estActif,
                                           @Param("is_EXPIRED")Integer is_EXPIRED,
                                           @Param("zoneId")String zoneId,
                                           Pageable pageable);

    Utilisateur findByLogin(String username);


}
