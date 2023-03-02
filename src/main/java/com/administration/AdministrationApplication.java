package com.administration;

import com.administration.dto.ProfilRequestDTO;
import com.administration.dto.UtilisateurRequestDTO;
import com.administration.entity.Profil;
import com.administration.entity.Utilisateur;
import com.administration.service.IProfilService;
import com.administration.service.IUtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AdministrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdministrationApplication.class, args);
    }
    @Bean
    CommandLineRunner start(IUtilisateurService utilisateurService, IProfilService profilService){
        return args->{if (utilisateurService.getUtilisateurbyLogin("firstadmin")==null){
            utilisateurService.addUtilisateur(
                 new UtilisateurRequestDTO("firstadmin","admin","admin","admin","admin","admin","first admin at start",1,1,1,"admin",null,0,null));
            if(profilService.getProfilbyName("ADMIN")==null){
            profilService.addProfile(new ProfilRequestDTO("admin","first admin at start"));
            }
            Utilisateur user =utilisateurService.getUtilisateurbyLogin("firstadmin");
            Profil profil=profilService.getProfilbyName("ADMIN");
            utilisateurService.affecterProfileToUser(user.getIdUser(), profil.getIdProfil());
        }
        };
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
