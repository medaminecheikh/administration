package com.administration.repo;

import com.administration.entity.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur, String> {
    @Query("SELECT a FROM Utilisateur a WHERE a.login LIKE %:kw% AND a.nomU LIKE %:nom% AND a.prenU LIKE %:pren% AND a.estActif = COALESCE(:estActif, a.estActif)")
    Page<Utilisateur> findUtilisateurByLogin(@Param("kw") String keyword, @Param("nom") String nom, @Param("pren") String prenom, @Param("estActif") Integer estActif, Pageable pageable);

    long count();

    @Query("SELECT DISTINCT  u FROM Utilisateur u " +
            "LEFT JOIN u.profilUser pu " +
            "LEFT JOIN pu.profil p " +
            "WHERE " +
            "(:login IS null OR lower(u.login) LIKE lower(concat('%', :login, '%'))) " +
            "AND (:prenU IS null OR lower(u.prenU) LIKE lower(concat('%', :prenU, '%'))) " +
            "AND (:nomU IS null OR lower(u.nomU) LIKE lower(concat('%', :nomU, '%'))) " +
            "AND (:matricule IS null OR lower(u.matricule) LIKE lower(concat('%', :matricule, '%'))) " +
            "AND (:zoneId IS null OR u.ett.dregional.zone.idZone LIKE lower(concat('%', :zoneId, '%'))) " +
            "AND (:estActif IS null OR u.estActif = :estActif) " +
            "AND (:is_EXPIRED IS null OR u.is_EXPIRED = :is_EXPIRED)" +
            "AND current_date >= u.date_EXPIRED " +
            "AND (COALESCE(:zoneId, '') = '' OR u.ett IS NULL OR u.ett.idEtt IS NULL OR u.ett.dregional.idDr IS NULL OR u.ett.dregional.zone IS NULL OR u.ett.dregional.zone.idZone IS NULL OR LOWER(u.ett.dregional.zone.idZone) LIKE LOWER(CONCAT('%', :zoneId, '%'))) " +
            "AND (COALESCE(:drId, '') = '' OR u.ett IS NULL OR u.ett.dregional.idDr IS NULL OR LOWER(u.ett.dregional.idDr) LIKE LOWER(CONCAT('%', :drId, '%'))) " +
            "AND (COALESCE(:ettId, '') = '' OR u.ett IS NULL OR LOWER(u.ett.idEtt) LIKE LOWER(CONCAT('%', :ettId, '%'))) " +
            "AND (COALESCE(:profilId, '') = '' OR p IS NULL OR LOWER(p.idProfil) = LOWER(:profilId))")
    Page<Utilisateur> findUtilisateurExpired(@Param("login") String login,
                                             @Param("prenU") String prenU,
                                             @Param("nomU") String nomU,
                                             @Param("matricule") String matricule,
                                             @Param("estActif") Integer estActif,
                                             @Param("is_EXPIRED") Integer is_EXPIRED,
                                             @Param("zoneId") String zoneId,
                                             @Param("drId") String drId,
                                             @Param("ettId") String ettId,
                                             @Param("profilId") String profilId,
                                             Pageable pageable);

    @Query("SELECT DISTINCT  u FROM Utilisateur u " +
            "LEFT JOIN u.profilUser pu " +
            "LEFT JOIN pu.profil p " +
            "WHERE " +
            "(:login IS null OR lower(u.login) LIKE lower(concat('%', :login, '%'))) " +
            "AND (:prenU IS null OR lower(u.prenU) LIKE lower(concat('%', :prenU, '%'))) " +
            "AND (:nomU IS null OR lower(u.nomU) LIKE lower(concat('%', :nomU, '%'))) " +
            "AND (:matricule IS null OR lower(u.matricule) LIKE lower(concat('%', :matricule, '%'))) " +
            "AND (:zoneId IS null OR u.ett.dregional.zone.idZone LIKE lower(concat('%', :zoneId, '%'))) " +
            "AND (:estActif IS null OR u.estActif = :estActif) " +
            "AND (:is_EXPIRED IS null OR u.is_EXPIRED = :is_EXPIRED)" +
            "AND current_date < u.date_EXPIRED " +
            "AND (COALESCE(:zoneId, '') = '' OR u.ett IS NULL OR u.ett.idEtt IS NULL OR u.ett.dregional.idDr IS NULL OR u.ett.dregional.zone IS NULL OR u.ett.dregional.zone.idZone IS NULL OR LOWER(u.ett.dregional.zone.idZone) LIKE LOWER(CONCAT('%', :zoneId, '%'))) " +
            "AND (COALESCE(:drId, '') = '' OR u.ett IS NULL OR u.ett.dregional.idDr IS NULL OR LOWER(u.ett.dregional.idDr) LIKE LOWER(CONCAT('%', :drId, '%'))) " +
            "AND (COALESCE(:ettId, '') = '' OR u.ett IS NULL OR LOWER(u.ett.idEtt) LIKE LOWER(CONCAT('%', :ettId, '%'))) " +
            "AND (COALESCE(:profilId, '') = '' OR p IS NULL OR LOWER(p.idProfil) = LOWER(:profilId))")
    Page<Utilisateur> findUtilisateurValid(@Param("login") String login,
                                           @Param("prenU") String prenU,
                                           @Param("nomU") String nomU,
                                           @Param("matricule") String matricule,
                                           @Param("estActif") Integer estActif,
                                           @Param("is_EXPIRED") Integer is_EXPIRED,
                                           @Param("zoneId") String zoneId,
                                           @Param("drId") String drId,
                                           @Param("ettId") String ettId,
                                           @Param("profilId") String profilId,
                                           Pageable pageable);

    @Query("SELECT u FROM Utilisateur u WHERE LOWER(COALESCE(u.ett.dregional.zone.idZone, '')) LIKE LOWER(CONCAT('%', :zoneId, '%'))")
    List<Utilisateur> findUtilisateurByZoneId(@Param("zoneId") String zoneId);


    @Query("SELECT DISTINCT u FROM Utilisateur u " +
            "LEFT JOIN u.profilUser pu " +
            "LEFT JOIN pu.profil p " +
            "WHERE " +
            "(:login IS NULL OR LOWER(u.login) LIKE LOWER(CONCAT('%', :login, '%'))) " +
            "AND (:prenU IS NULL OR LOWER(u.prenU) LIKE LOWER(CONCAT('%', :prenU, '%'))) " +
            "AND (:nomU IS NULL OR LOWER(u.nomU) LIKE LOWER(CONCAT('%', :nomU, '%'))) " +
            "AND (:matricule IS NULL OR LOWER(u.matricule) LIKE LOWER(CONCAT('%', :matricule, '%'))) " +
            "AND (:estActif IS NULL OR u.estActif = :estActif) " +
            "AND (" +
            "   (:ettId IS NOT NULL AND COALESCE(:ettId, '') != '' " +
            "       AND u.ett IS NOT NULL AND LOWER(u.ett.idEtt) LIKE LOWER(CONCAT('%', :ettId, '%')) " +
            "       AND (COALESCE(:profilId, '') = '' OR (pu IS NOT NULL AND p IS NOT NULL AND LOWER(p.idProfil) = LOWER(:profilId)))" +
            "       AND (:drId IS NULL OR :drId = '' OR u.ett.dregional IS NOT NULL AND LOWER(u.ett.dregional.idDr) LIKE LOWER(CONCAT('%', :drId, '%'))) " +
            "       AND (:zoneId IS NULL OR :zoneId = '' OR u.ett.dregional IS NOT NULL AND u.ett.dregional.zone IS NOT NULL AND u.ett.dregional.zone.idZone IS NOT NULL AND LOWER(u.ett.dregional.zone.idZone) LIKE LOWER(CONCAT('%', :zoneId, '%')))" +
            "   ) OR " +
            "   (:drId IS NOT NULL AND COALESCE(:drId, '') != '' " +
            "       AND u.ett IS NOT NULL AND u.ett.dregional IS NOT NULL AND LOWER(u.ett.dregional.idDr) LIKE LOWER(CONCAT('%', :drId, '%')) " +
            "       AND (COALESCE(:profilId, '') = '' OR (pu IS NOT NULL AND p IS NOT NULL AND LOWER(p.idProfil) = LOWER(:profilId)))" +
            "       AND (:zoneId IS NULL OR :zoneId = '' OR u.ett.dregional IS NOT NULL AND u.ett.dregional.zone IS NOT NULL AND u.ett.dregional.zone.idZone IS NOT NULL AND LOWER(u.ett.dregional.zone.idZone) LIKE LOWER(CONCAT('%', :zoneId, '%')))" +
            "   ) OR " +
            "   (:zoneId IS NOT NULL AND COALESCE(:zoneId, '') != '' " +
            "       AND u.ett IS NOT NULL AND u.ett.dregional IS NOT NULL AND u.ett.dregional.zone IS NOT NULL AND u.ett.dregional.zone.idZone IS NOT NULL AND LOWER(u.ett.dregional.zone.idZone) LIKE LOWER(CONCAT('%', :zoneId, '%'))" +
            "       AND (COALESCE(:profilId, '') = '' OR (pu IS NOT NULL AND p IS NOT NULL AND LOWER(p.idProfil) = LOWER(:profilId)))" +
            "   ) OR " +
            "   (:ettId IS NULL AND :drId IS NULL AND :zoneId IS NULL" +
            "       AND (COALESCE(:profilId, '') = '' OR (pu IS NOT NULL AND p IS NOT NULL AND LOWER(p.idProfil) = LOWER(:profilId)))" +
            "   )" +
            ")")
    Page<Utilisateur> findUtilisateurAll(
            @Param("login") String login,
            @Param("prenU") String prenU,
            @Param("nomU") String nomU,
            @Param("matricule") String matricule,
            @Param("estActif") Integer estActif,
            @Param("zoneId") String zoneId,
            @Param("drId") String drId,
            @Param("ettId") String ettId,
            @Param("profilId") String profilId,
            Pageable pageable);













    Utilisateur findByLogin(String username);


}
