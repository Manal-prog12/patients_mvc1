package ma.enset.patients_mvc;

import ma.enset.patients_mvc.entities.Patient;
import ma.enset.patients_mvc.repositories.PatientRepository;
import ma.enset.patients_mvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
   // @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(
                    new Patient(null,"Hassan",new Date(),false,122));
            patientRepository.save(
                    new Patient(null,"Mohammed",new Date(),true,321));
            patientRepository.save(
                    new Patient(null,"Yassmine",new Date(),true,165));
            patientRepository.save(
                    new Patient(null,"Hanane",new Date(),false,132));
            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
   // @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
securityService.saveNewUser("mohamed","1234","1234");
securityService.saveNewUser("yassmine","1234","1234");
securityService.saveNewUser("hassan","1234","1234");

securityService.saveNewRole("USER","");
securityService.saveNewRole("ADMIN","");

securityService.addRoleToUser("mohamed","USER");
securityService.addRoleToUser("mohamed","ADMIN");
securityService.addRoleToUser("yassmine","USER");
securityService.addRoleToUser("hassan","USER");
        };
    }


}
