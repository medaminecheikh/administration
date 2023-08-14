package com.administration.service.impl;

import com.administration.dto.*;
import com.administration.entity.Profil;
import com.administration.entity.Utilisateur;
import com.administration.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class InitializationService {
    private final IUtilisateurService utilisateurService;
    private final IFoncService foncService;
    private final IProfilService profilService;
    private final IEttService ettService;
    private final IZoneService zoneService;
    private final IDregService dregService;
    public InitializationService(IUtilisateurService utilisateurService, IFoncService foncService, IProfilService profilService, IEttService ettService, IZoneService zoneService, IDregService dregService) {
        this.utilisateurService = utilisateurService;
        this.foncService = foncService;
        this.profilService = profilService;
        this.ettService = ettService;
        this.zoneService = zoneService;
        this.dregService = dregService;
    }

    public void initializeFonctions() {
        foncService.initializeFonctions(Arrays.asList(
                new FoncRequestDTO("1", "", "Fonction d'encaissement", "Encaissement", "100", 1, 1),
                new FoncRequestDTO("2", "10001", "Fonction d'encaissement de factures", "Encaissement Facture", "100", 1, 1),
                new FoncRequestDTO("3", "10002", "Fonction de paiement d'avances", "Paiement Avance", "100", 1, 1),
                new FoncRequestDTO("4", "", "Fonction de recherche", "Recherche", "200", 1, 1),
                new FoncRequestDTO("5", "20001", "Fonction de recherche d'encaissements", "Recherche Encaissement", "200", 1, 1),
                new FoncRequestDTO("6", "20002", "Fonction de recherche de factures", "Recherche Facture", "200", 1, 1),
                new FoncRequestDTO("30", "20003", "Fonction de Journal Encaissement", "Journal Encaissement", "200", 1, 1),
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
                new FoncRequestDTO("17", "40004", "Fonction de validation de la journée", "Validation journée", "400", 1, 1),
                new FoncRequestDTO("18", "", "Fonction de Gestion Utilisateur", "Gestion Utilisateur", "500", 1, 1),
                new FoncRequestDTO("19", "50001", "Fonction dashboard,update,delete", "Gestion ", "500", 1, 1),
                new FoncRequestDTO("20", "50002", "Fonction de creation", "ajout Utilisateur", "500", 1, 1),
                new FoncRequestDTO("21", "", "Fonction de Gestion Profil", "Gestion Profil", "600", 1, 1),
                new FoncRequestDTO("22", "60001", "Fonction dashboard,update,delete", "Gestion", "600", 1, 1),
                new FoncRequestDTO("23", "60002", "Fonction de creation", "ajout profil", "600", 1, 1),
                new FoncRequestDTO("24", "", "Fonction de Gestion Model", "Gestion Model", "700", 1, 1),
                new FoncRequestDTO("25", "70001", "Fonction dashboard,update,delete", "Gestion  ", "700", 1, 1),
                new FoncRequestDTO("26", "70002", "Fonction de creation", "ajout model", "700", 1, 1)


        ));
    }
    public static List<ZoneRequestDTO> generateDummyZones() {
        List<ZoneRequestDTO> zones = new ArrayList<>();

        zones.add(new ZoneRequestDTO("Z1", "Z1", "Tunis", "منطقة تونس"));
        zones.add(new ZoneRequestDTO("Z2","Z2", "Sousse", "منطقة سوسة"));
        zones.add(new ZoneRequestDTO("Z3","Z3", "Sfax", "منطقة صفاقس"));
        zones.add(new ZoneRequestDTO("Z4","Z4", "Bizerte", "منطقة بنزرت"));
        zones.add(new ZoneRequestDTO("Z5","Z5", "Gabes", "منطقة قابس"));

        return zones;
    }

    public static List<DregionalRequestDTO> generateDummyDregionals() {
        List<DregionalRequestDTO> dregionals = new ArrayList<>();

        dregionals.add(new DregionalRequestDTO("D1","DR1", "TUN-DR1", "المديرية الجهوية 1"));
        dregionals.add(new DregionalRequestDTO("D2","DR2", "TUN-DR2", "المديرية الجهوية 2"));
        dregionals.add(new DregionalRequestDTO("D3","DR3", "TUN-DR3", "المديرية الجهوية 3"));
        dregionals.add(new DregionalRequestDTO("D4","DR4", "TUN-DR4", "المديرية الجهوية 4"));
        dregionals.add(new DregionalRequestDTO("D5","DR5", "TUN-DR5", "المديرية الجهوية 5"));

        return dregionals;
    }

    public static List<EttRequestDTO> generateDummyEtts() {
        List<EttRequestDTO> etts = new ArrayList<>();

        etts.add(new EttRequestDTO("E1","Ett1 SRC", "TUN-CFRX1", "SRC1", "Tunis", 1));
        etts.add(new EttRequestDTO("E2","Ett2 SRC", "TUN-CFRX2", "SRC2", "Sousse", 1));
        etts.add(new EttRequestDTO("E3","Ett3 SRC", "TUN-CFRX3", "SRC3", "Sfax", 1));
        etts.add(new EttRequestDTO("E4","Ett4 SRC", "TUN-CFRX4", "SRC4", "Bizerte", 1));
        etts.add(new EttRequestDTO("E5","Ett5 SRC", "TUN-CFRX5", "SRC5", "Gabes", 1));

        return etts;
    }
    @PostConstruct
    public void initialize() {
        initializeFonctions();
        if (utilisateurService.getUtilisateurbyLogin("firstadmin") == null) {
            utilisateurService.addUtilisateur(new UtilisateurRequestDTO("firstadmin", "admin", "admin", "admin", "admin", "admin", "first admin at start", 1, 1, 1, "admin", null, 0, null));
            if (profilService.getProfilbyName("ADMIN") == null) {
                profilService.addProfile(new ProfilRequestDTO("admin", "gestion des comptes"));
                List<FoncResponseDTO> fonctions = foncService.findFonctionsByNomMenu("500");
                fonctions.addAll(foncService.findFonctionsByNomMenu("600"));
                fonctions.addAll(foncService.findFonctionsByNomMenu("700"));
                for (FoncResponseDTO fonc : fonctions) {
                    profilService.affecterFoncToProfile(fonc.getIdFonc(), profilService.getProfilbyName("admin").getIdProfil());
                }
            }
            Utilisateur user = utilisateurService.getUtilisateurbyLogin("firstadmin");
            Profil profil = profilService.getProfilbyName("ADMIN");
            utilisateurService.affecterProfileToUser(user.getIdUser(), profil.getIdProfil());
        }

        List<ZoneRequestDTO> zones = generateDummyZones();
        List<DregionalRequestDTO> dregionals = generateDummyDregionals();
        List<EttRequestDTO> etts = generateDummyEtts();
        /*for (ZoneRequestDTO zoneDto : zones) {
            if (zoneService.getZone(zoneDto.getIdZone()) == null) {
                ZoneRequestDTO zoneEntity = new ZoneRequestDTO();
                zoneEntity.setIdZone(zoneDto.getIdZone());
                zoneEntity.setCOD_ZONE(zoneDto.getCOD_ZONE());
                zoneEntity.setDES_ZONE(zoneDto.getDES_ZONE());
                zoneEntity.setDES_ZONE_AR(zoneDto.getDES_ZONE_AR());

                // Set other properties if needed

                // Save the manually created Zone entity
                zoneService.addZone(zoneEntity);
            }
        }

        for (DregionalRequestDTO dregionalDto : dregionals) {
            if (dregService.getDregional(dregionalDto.getIdDr()) == null) {
                DregionalRequestDTO dregionalEntity = new DregionalRequestDTO();
                dregionalEntity.setIdDr(dregionalDto.getIdDr());
                dregionalEntity.setCod_DR(dregionalDto.getCod_DR());
                dregionalEntity.setDr(dregionalDto.getDr());
                dregionalEntity.setDrAr(dregionalDto.getDrAr());

                // Set other properties if needed

                // Save the manually created Dregional entity
                dregService.addDreg(dregionalEntity);
            }
        }

        for (EttRequestDTO ettDto : etts) {
            if (ettService.getEtt(ettDto.getIdEtt()) == null) {
                EttRequestDTO ettEntity = new EttRequestDTO();
                ettEntity.setIdEtt(ettDto.getIdEtt());
                ettEntity.setDes_SRC_ENC(ettDto.getDes_SRC_ENC());
                ettEntity.setCOD_CFRX(ettDto.getCOD_CFRX());
                ettEntity.setPrfx_SRC_ENC(ettDto.getPrfx_SRC_ENC());
                ettEntity.setAdr(ettDto.getAdr());
                ettEntity.setIs_BSCS(ettDto.getIs_BSCS());

                // Set other properties if needed

                // Save the manually created Ett entity
                ettService.addEtt(ettEntity);
            }*/


    }

}
