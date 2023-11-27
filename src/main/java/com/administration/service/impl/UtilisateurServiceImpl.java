package com.administration.service.impl;

import com.administration.dto.UtilisateurRequestDTO;
import com.administration.dto.UtilisateurResponseDTO;
import com.administration.dto.UtilisateurUpdateDTO;
import com.administration.entity.*;
import com.administration.repo.EttRepo;
import com.administration.repo.ProfilUserRepo;
import com.administration.repo.ProfileRepo;
import com.administration.repo.UtilisateurRepo;
import com.administration.service.IUtilisateurService;
import com.administration.service.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UtilisateurServiceImpl implements IUtilisateurService {
    UtilisateurRepo utilisateurRepo;
    UserMapper userMapper;
    ProfileRepo profileRepo;
    EttRepo ettRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    ProfilUserRepo profilUserRepo;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public UtilisateurResponseDTO addUtilisateur(UtilisateurRequestDTO RequestDTO) {
        String login = RequestDTO.getLogin().toLowerCase();
        Utilisateur userexist = utilisateurRepo.findByLogin(login);
        if (userexist != null) {
            throw new IllegalArgumentException("Login with the name " + login + " already exists.");
        }
        if (!RequestDTO.getPwdU().equals(RequestDTO.getConfirmedpassword())) {
            throw new RuntimeException("Please confirm your password !!!!!!!");
        }
        Utilisateur utilisateur = userMapper.UtilisateurRequestDTOUtilisateur(RequestDTO);
        utilisateur.setIdUser(UUID.randomUUID().toString());
        utilisateur.setLogin(utilisateur.getLogin().toLowerCase());
        utilisateur.setPwdU(bCryptPasswordEncoder.encode(utilisateur.getPwdU()));
        utilisateur.setDate_CREATION(new Date());
        utilisateurRepo.save(utilisateur);
        return userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
    }

    @Override
    public UtilisateurResponseDTO getUtilisateur(String id) {
        Utilisateur utilisateur = utilisateurRepo.findById(id).get();
        return userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateurbyLogin(String username) {

        return utilisateurRepo.findByLogin(username);
    }

    @Override
    public UtilisateurResponseDTO getbyLogin(String username) {
        Utilisateur utilisateur = utilisateurRepo.findByLogin(username);
        return userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
    }

    @Override
    public List<UtilisateurResponseDTO> findUtilisateurExpired(String login, String prenU, String nomU, String matricule, Integer estActif, Integer isExpired, String zoneId,
                                                               String drId, String ettId, String profilId, PageRequest pageable) {
        Page<Utilisateur> utilisateurPage = utilisateurRepo.findUtilisateurExpired(login, prenU, nomU, matricule, estActif, isExpired, zoneId, drId, ettId, profilId, pageable);
        long count = utilisateurPage.getTotalElements();

        return utilisateurPage.getContent().stream().map(utilisateur -> {
            UtilisateurResponseDTO responseDTO = userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
            responseDTO.setTotalElements(count);
            return responseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurResponseDTO> findUtilisateurValid(String login, String prenU, String nomU, String matricule, Integer estActif, Integer isExpired, String zoneId, String drId, String ettId, String profilId, PageRequest pageable) {
        Page<Utilisateur> utilisateurPage = utilisateurRepo.findUtilisateurValid(login, prenU, nomU, matricule, estActif, isExpired, zoneId, drId, ettId, profilId, pageable);
        long count = utilisateurPage.getTotalElements();

        return utilisateurPage.getContent().stream().map(utilisateur -> {
            UtilisateurResponseDTO responseDTO = userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
            responseDTO.setTotalElements(count);
            return responseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurResponseDTO> findUtilisateurAll(String login, String prenU, String nomU, String matricule,
                                                           Integer estActif, String zoneId, String drId, String ettId, String profilId, PageRequest pageable) {
        TypedQuery<Utilisateur> query = buildTypedQuery(login, prenU, nomU, matricule, estActif, zoneId, drId, ettId, profilId);
        List<Utilisateur> resultList = executeQuery(query, pageable);

        long totalResults = countTotalResults(query);

        return resultList.stream()
                .map(utilisateur -> {
                    UtilisateurResponseDTO responseDTO = userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur);
                    responseDTO.setTotalElements(totalResults);
                    return responseDTO;
                })
                .collect(Collectors.toList());

    }
    private TypedQuery<Utilisateur> buildTypedQuery(
            String login, String prenU, String nomU, String matricule,
            Integer estActif, String zoneId, String drId, String ettId, String profilId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> criteriaQuery = criteriaBuilder.createQuery(Utilisateur.class);
        Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);

        List<Predicate> predicates = buildPredicates(criteriaBuilder, root, login, prenU, nomU, matricule, estActif, zoneId, drId, ettId, profilId);

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(criteriaQuery);
    }

    private List<Predicate> buildPredicates(
            CriteriaBuilder criteriaBuilder, Root<Utilisateur> root,
            String login, String prenU, String nomU, String matricule,
            Integer estActif, String zoneId, String drId, String ettId, String profilId) {

        List<Predicate> predicates = new ArrayList<>();

        if (ettId != null && !ettId.isEmpty()) {
            Join<Utilisateur, Ett> ettJoin = root.join("ett");
            predicates.add(criteriaBuilder.equal(ettJoin.get("idEtt"), ettId));

            if (profilId != null && !profilId.isEmpty()) {
                Join<Ett, ProfilUser> profilJoin = ettJoin.join("users").join("profilUser");
                Join<ProfilUser, Profil> pJoin = profilJoin.join("profil");
                predicates.add(criteriaBuilder.equal(pJoin.get("idProfil"), profilId));
            }

            // Add other attribute filters for Ett users
        } else if (drId != null && !drId.isEmpty()) {
            Join<Utilisateur, Dregional> drJoin = root.join("ett").join("dregional");
            predicates.add(criteriaBuilder.equal(drJoin.get("idDr"), drId));

            if (profilId != null && !profilId.isEmpty()) {
                Join<Dregional, Ett> ettJoin = drJoin.join("etts");
                Join<Ett, ProfilUser> profilJoin = ettJoin.join("users").join("profilUser");
                Join<ProfilUser, Profil> pJoin = profilJoin.join("profil");
                predicates.add(criteriaBuilder.equal(pJoin.get("idProfil"), profilId));
            }

            // Add other attribute filters for Dr users
        } else if (zoneId != null && !zoneId.isEmpty()) {
            Join<Utilisateur, Dregional> drJoin = root.join("ett").join("dregional");
            Join<Dregional, Zone> zoneJoin = drJoin.join("zone");
            predicates.add(criteriaBuilder.equal(zoneJoin.get("idZone"), zoneId));

            if (profilId != null && !profilId.isEmpty()) {
                Join<Dregional, Ett> ettJoin = drJoin.join("etts");
                Join<Ett, ProfilUser> profilJoin = ettJoin.join("users").join("profilUser");
                Join<ProfilUser, Profil> pJoin = profilJoin.join("profil");
                predicates.add(criteriaBuilder.equal(pJoin.get("idProfil"), profilId));
            }

            // Add other attribute filters for Zone users
        } else {
            if (profilId != null && !profilId.isEmpty()) {
                Join<Utilisateur, ProfilUser> profilJoin = root.join("profilUser");
                Join<ProfilUser, Profil> pJoin = profilJoin.join("profil");
                predicates.add(criteriaBuilder.equal(pJoin.get("idProfil"), profilId));
            }

            // Add other attribute filters for all users
        }

        // Add other attribute filters common to all cases


        return predicates;
    }
    private List<Utilisateur> executeQuery(TypedQuery<Utilisateur> query, PageRequest pageable) {
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }
    private long countTotalResults(TypedQuery<Utilisateur> query) {
        return query.getResultList().size();
    }
    @Override
    public List<UtilisateurResponseDTO> listUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepo.findAll();
        return utilisateurs.stream()
                .map(utilisateur -> userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur))
                .collect(Collectors.toList());
    }

    @Override
    public void updateUtilisateurDTO(UtilisateurUpdateDTO dto) {
        Utilisateur utilisateur = utilisateurRepo.findById(dto.getIdUser()).get();
        if (dto.getPwdU() != null) {
            dto.setPwdU(bCryptPasswordEncoder.encode(dto.getPwdU()));
        }
        userMapper.updateUtilisateurFromDto(dto, utilisateur);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void affecterUserToEtt(String idUser, String idEtt) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).get();
        Ett ett = ettRepo.findById(idEtt).get();
        utilisateur.setEtt(ett);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void affecterProfileToUser(String idUser, String idProfile) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        Profil profil = profileRepo.findById(idProfile).orElseThrow(() -> new RuntimeException("Profile not found"));

        boolean profileExists = utilisateur.getProfilUser().stream()
                .anyMatch(profilUser -> profilUser.getProfil().equals(profil));

        if (!profileExists) {
            ProfilUser profilUser = new ProfilUser();
            profilUser.setProfil(profil);
            profilUser.setUtilisateur(utilisateur);
            utilisateur.getProfilUser().add(profilUser);
            utilisateurRepo.save(utilisateur);
        } else {
            throw new RuntimeException("This profile already exists for the user");
        }
    }


    @Override
    public void removeEtt(String idUser) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).get();
        utilisateur.setEtt(null);
        utilisateurRepo.save(utilisateur);
    }

    @Override
    public void removeProfile(String userId, String profilId) {
        Utilisateur utilisateur = utilisateurRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        Profil profilToRemove = profileRepo.findById(profilId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profil id"));

        List<ProfilUser> profilUsers = utilisateur.getProfilUser();
        if (profilUsers != null) {
            Iterator<ProfilUser> iterator = profilUsers.iterator();
            while (iterator.hasNext()) {
                ProfilUser profilUser = iterator.next();
                if (profilUser.getProfil().getIdProfil().equals(profilId)) {
                    // Set the user and profil to null
                    profilUser.setUtilisateur(null);
                    profilUser.setProfil(null);

                    // Delete the association from the database
                    profilUserRepo.deleteById(profilUser.getId());

                    // Remove the profil user from the list
                    iterator.remove();
                }
            }

            // Update the utilisateur with the new list of profil users
            utilisateur.setProfilUser(profilUsers);
            utilisateurRepo.save(utilisateur);
        }
    }


    @Override
    public void deleteUser(String idUser) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUser).get();
        if (utilisateur.getEtt() == null && utilisateur.getProfilUser() == null) {
            utilisateurRepo.deleteById(idUser);
        } else throw new RuntimeException("This user " + utilisateur.getNomU() + " has associations");
    }

    @Override
    public List<UtilisateurResponseDTO> findUtilisateurByLogin(String kw, String nom, String prenom, Integer estActif, int page, int size) {
        Sort sort = Sort.by("date_CREATION");
        Page<Utilisateur> utilisateurs = utilisateurRepo.findUtilisateurByLogin("%" + kw + "%", "%" + nom + "%", "%" + prenom + "%", estActif, PageRequest.of(page, size, sort));
        List<UtilisateurResponseDTO> utilisateurResponseDTOList = utilisateurs
                .map(utilisateur -> userMapper.UtilisateurTOUtilisateurResponseDTO(utilisateur))
                .getContent();
        long count = utilisateurs.getTotalElements();
        utilisateurResponseDTOList.forEach(dto -> dto.setTotalElements(count));

        return utilisateurResponseDTOList;
    }


}
