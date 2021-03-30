package com.example.backend_market_place.services.utilisateur;

import com.example.backend_market_place.exceptions.ResourceNotFoundException;
import com.example.backend_market_place.metier.utilisateur.UtilisateurInterface;
import com.example.backend_market_place.models.utilisateur.ERole;
import com.example.backend_market_place.models.utilisateur.Privilege;
import com.example.backend_market_place.models.utilisateur.Stat;
import com.example.backend_market_place.models.utilisateur.Utilisateur;
import com.example.backend_market_place.repository.utilisateur.PrivilegeRepository;
import com.example.backend_market_place.repository.utilisateur.UtilisateurRepository;
import com.example.backend_market_place.security.Jwt.JwtResponse;
import com.example.backend_market_place.security.Jwt.JwtUtils;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Slf4j
@Service
public class UtilisateurService implements UtilisateurInterface, UserDetailsService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    JwtUtils jwtUtils;


    //chercher utilisateur avec username
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilis = utilisateurRepository.findByUsernameUtilis(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return utilis;
    }


    //recuperer tout les etulisateurs
    @Override
    public List<Utilisateur> getAllUtilisateurs(ERole eRole) {
        return utilisateurRepository.findByPrivilegeName(eRole);
    }



    @Override
    public Utilisateur findByIdUtilisateur(Long id) {
        return utilisateurRepository.findById(id).get();
    }

    @Override
    public void deleteByIdUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public void deleteAllUtilisateurs() {
        utilisateurRepository.deleteAll();
    }

    //ajouter un utilisateur avec privilege ROLE_USER
    @Override
    public ResponseEntity<Utilisateur> AddUtilisateur(Utilisateur utilisateur, String siteURL)
            throws UnsupportedEncodingException, MessagingException{
        if (utilisateurRepository.existsByusernameUtilis(utilisateur.getUsernameUtilis())) {
            return (ResponseEntity<Utilisateur>) ResponseEntity.badRequest();
        }
        if (utilisateurRepository.existsByemailUtilis(utilisateur.getEmailUtilis())) {
            return (ResponseEntity<Utilisateur>) ResponseEntity.badRequest();
        }
        utilisateur.setPwdUtilis(encoder.encode(utilisateur.getPwdUtilis()));
        Privilege userPrivilege = new Privilege();
        userPrivilege.setName(ERole.ROLE_USER);
        userPrivilege.setStatut(Stat.Active);
        privilegeRepository.save(userPrivilege);
        utilisateur.setPrivilege(userPrivilege);
        String randomCode = RandomString.make(64);
        utilisateur.setVerificationCode(randomCode);
        utilisateur.setEnabled(false);
        sendVerificationEmail(utilisateur, siteURL);
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
    }
    //ajouter un utilisateur avec privilege ROLE_ADMIN
    @Override
    public ResponseEntity<Utilisateur> AddAdmin(Utilisateur utilisateur, String siteURL)
            throws UnsupportedEncodingException, MessagingException{
        if (utilisateurRepository.existsByusernameUtilis(utilisateur.getUsernameUtilis())) {
            return (ResponseEntity<Utilisateur>) ResponseEntity.badRequest();
        }
        if (utilisateurRepository.existsByemailUtilis(utilisateur.getEmailUtilis())) {
            return (ResponseEntity<Utilisateur>) ResponseEntity.badRequest();
        }
        utilisateur.setPwdUtilis(encoder.encode(utilisateur.getPwdUtilis()));
        Privilege userPrivilege = new Privilege();
        userPrivilege.setName(ERole.ROLE_ADMIN);
        userPrivilege.setStatut(Stat.Active);
        privilegeRepository.save(userPrivilege);
        utilisateur.setPrivilege(userPrivilege);
        String randomCode = RandomString.make(64);
        utilisateur.setVerificationCode(randomCode);
        utilisateur.setEnabled(false);
        sendVerificationEmail(utilisateur, siteURL);
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
    }

    //ajouter un utilisateur avec privilege ROLE_CONCESSIOANIRE
    @Override
    public ResponseEntity<Utilisateur> AddConcessionaire(Utilisateur utilisateur, String siteURL)
            throws UnsupportedEncodingException, MessagingException{
        if (utilisateurRepository.existsByusernameUtilis(utilisateur.getUsernameUtilis())) {
            return (ResponseEntity<Utilisateur>) ResponseEntity.badRequest();
        }
        if (utilisateurRepository.existsByemailUtilis(utilisateur.getEmailUtilis())) {
            return (ResponseEntity<Utilisateur>) ResponseEntity.badRequest();
        }
        utilisateur.setPwdUtilis(encoder.encode(utilisateur.getPwdUtilis()));
        Privilege userPrivilege = new Privilege();
        userPrivilege.setName(ERole.ROLE_CONCESSIOANIRE);
        userPrivilege.setStatut(Stat.Active);
        privilegeRepository.save(userPrivilege);
        utilisateur.setPrivilege(userPrivilege);
        String randomCode = RandomString.make(64);
        utilisateur.setVerificationCode(randomCode);
        utilisateur.setEnabled(false);
        sendVerificationEmail(utilisateur, siteURL);
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
    }

    //verifier email
    private void sendVerificationEmail(Utilisateur utilisateur, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = utilisateur.getEmailUtilis();
        String fromAddress = "BozYacine1@gmail.com";
        String senderName = "BRI-technology";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "BRI-technology.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", utilisateur.getFullName());
        String verifyURL = siteURL + "/verify?code=" + utilisateur.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }
    public boolean verify(String verificationCode) {
        Utilisateur utilisateur = utilisateurRepository.findByVerificationCode(verificationCode);
        if (utilisateur == null || utilisateur.isEnabled()) {
            return false;
        } else {
            utilisateur.setVerificationCode(null);
            utilisateur.setEnabled(true);
            utilisateurRepository.save(utilisateur);
            return true;
        }
    }

    //authentification utilisateur
    @Override
    public ResponseEntity<?> authenticateUser(Utilisateur utilisateur) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(utilisateur.getUsername(), utilisateur.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        Utilisateur utilis = (Utilisateur) authentication.getPrincipal();
        String privilege = utilis.getPrivilege().getName().name();

        return ResponseEntity.ok(new JwtResponse(jwt,
                utilis.getIdUtilis(),
                privilege));
    }

    //modifier utilisateur
    @Override
    public ResponseEntity<Utilisateur> UpdateUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur oldUtilisateur = utilisateurRepository.findById(id).get();
        utilisateurRepository.save(oldUtilisateur);
        return ResponseEntity.ok(oldUtilisateur);
    }

    //recuperer mot de passe

    public void updateResetPasswordToken(String token, String email) throws ResourceNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null) {
            utilisateur.setResetPasswordToken(token);
            utilisateurRepository.save(utilisateur);
        } else {
            throw new ResourceNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public Utilisateur getByResetPasswordToken(String token) {
        return utilisateurRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(Utilisateur utilisateur, String newPassword) {
        String encodedPassword = encoder.encode(newPassword);
        utilisateur.setPwdUtilis(encodedPassword);
        utilisateur.setResetPasswordToken(null);
        utilisateurRepository.save(utilisateur);
    }


}
