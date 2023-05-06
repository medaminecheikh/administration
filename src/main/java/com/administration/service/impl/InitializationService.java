package com.administration.service.impl;

import com.administration.dto.FoncRequestDTO;
import com.administration.dto.ProfilRequestDTO;
import com.administration.dto.UtilisateurRequestDTO;
import com.administration.entity.Profil;
import com.administration.entity.Utilisateur;
import com.administration.service.IFoncService;
import com.administration.service.IProfilService;
import com.administration.service.IUtilisateurService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class InitializationService {
    private final IUtilisateurService utilisateurService;
    private final IFoncService foncService;
    private final IProfilService profilService;

    public InitializationService(IUtilisateurService utilisateurService, IFoncService foncService, IProfilService profilService) {
        this.utilisateurService = utilisateurService;
        this.foncService = foncService;
        this.profilService = profilService;
    }
    public void initializeFonctions() {
        foncService.initializeFonctions(Arrays.asList(
                new FoncRequestDTO("1", "", "Fonction d'encaissement", "Encaissement", "100", 1, 1),
                new FoncRequestDTO("2", "10001", "Fonction d'encaissement de factures", "Encaissement Facture", "100", 1, 1),
                new FoncRequestDTO("3", "10002", "Fonction de paiement d'avances", "Paiement Avance", "100", 1, 1),
                new FoncRequestDTO("4", "", "Fonction de recherche", "Recherche", "200", 1, 1),
                new FoncRequestDTO("5", "20001", "Fonction de recherche d'encaissements", "Recherche Encaissement", "200", 1, 1),
                new FoncRequestDTO("6", "20002", "Fonction de recherche de factures", "Recherche Facture", "200", 1, 1),
                new FoncRequestDTO("7", "", "Fonction de journal d'encaissement", "Etat d'edition", "300", 1, 1),
                new FoncRequestDTO("8", "30001", "Fonction de journal d'encaissement E1", "E1 Journal Encaissement", "300", 1, 1),
                new FoncRequestDTO("9", "30002", "Fonction de journal de modification E2", "E2 Journal Modification", "300", 1, 1),
                new FoncRequestDTO("10", "30003", "Fonction de bordereau de versement E3", "E3 Bordereau de versement", "300", 1, 1),
                new FoncRequestDTO("11", "", "Fonction de gestion ETT", "Gestion ETT", "400", 1, 1),
                new FoncRequestDTO("12", "40001", "Fonction de gestion des paiements", "Gestion des paiement", "400", 1, 1),
                new FoncRequestDTO("13", "40002", "Fonction de gestion des bordereaux", "Gestion Bordereau", "400", 1, 1),
                new FoncRequestDTO("14", "40002-1", "Fonction de génération des bordereaux", "Generation Bordereau", "400", 1, 1),
                new FoncRequestDTO("15", "40002-2", "Fonction de consultation des bordereaux", "Consultation Bordereau", "400", 1, 1),
                new FoncRequestDTO("16", "40003", "Fonction de gestion des caisses", "Gestion des caisses", "400", 1, 1),
                new FoncRequestDTO("17", "40004", "Fonction de validation de la journée", "Validation journée", "400", 1, 1)
        ));
    }
    @PostConstruct
    public void initialize() {
        initializeFonctions();
        if (utilisateurService.getUtilisateurbyLogin("firstadmin") == null) {
            utilisateurService.addUtilisateur(new UtilisateurRequestDTO("firstadmin", "admin", "admin", "admin", "admin", "admin", "first admin at start", 1, 1, 1, "admin", null, 0, null));
            if (profilService.getProfilbyName("ADMIN") == null) {
                profilService.addProfile(new ProfilRequestDTO("admin", "first admin at start"));
            }
            Utilisateur user = utilisateurService.getUtilisateurbyLogin("firstadmin");
            Profil profil = profilService.getProfilbyName("ADMIN");
            utilisateurService.affecterProfileToUser(user.getIdUser(), profil.getIdProfil());
        }
    }

}
