package com.example.backend_market_place.controllers.utilisateur;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.backend_market_place.exceptions.ResourceNotFoundException;
import com.example.backend_market_place.metier.utilisateur.UtilisateurInterface;
import com.example.backend_market_place.models.utilisateur.ERole;
import com.example.backend_market_place.models.utilisateur.Utilisateur;
import com.example.backend_market_place.models.utilisateur.Utility;
import com.example.backend_market_place.services.utilisateur.UtilisateurService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UtilisateurController {
    @Autowired
    private UtilisateurInterface UtilisateurService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UtilisateurService UtilisService;

    //api enrgistre user avec privilege user
    @PostMapping(value = "/signupUSER")
    public ResponseEntity<Utilisateur> saveUtilisateur(@Valid @RequestBody Utilisateur utilisateur, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        return UtilisateurService.AddUtilisateur(utilisateur, getSiteURL(request));
    }
    //api enrgistre user avec privilege concessioanire
    @PostMapping(value = "/signupCONCESSIONAIRE")
    public ResponseEntity<Utilisateur> saveConcessioanire(@Valid @RequestBody Utilisateur utilisateur, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        return UtilisateurService.AddConcessionaire(utilisateur, getSiteURL(request));
    }

    //api enrgistre user avec privilege admin
    @PostMapping(value = "/signupADMIN")
    public ResponseEntity<Utilisateur> saveUtilisateurAD(@Valid @RequestBody Utilisateur utilisateur, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException{
        return UtilisateurService.AddAdmin(utilisateur, getSiteURL(request));
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (UtilisateurService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    //api d'authentification
    @PostMapping(value = "/signin")
    public ResponseEntity<?>authentication(@Valid @RequestBody Utilisateur utilisateur){
        return UtilisateurService.authenticateUser(utilisateur);
    }

    //api recuperer tout les utilisateurs
    @GetMapping(value = "/allusers")
    public List<Utilisateur> getAllUtilisateurs (@Valid @RequestBody ERole eRole)
    {
        return UtilisateurService.getAllUtilisateurs(eRole);
    }

    //api recuperer mot de passe
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, @Valid @RequestBody Utilisateur utilisateur) {
        String email = utilisateur.getEmailUtilis();
        String token = RandomString.make(30);
       try {
           UtilisService.updateResetPasswordToken(token, email);
           String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
           sendEmail(email, resetPasswordLink);
       }
       catch (ResourceNotFoundException ex){
            return ("Error");
       }
       catch (UnsupportedEncodingException | MessagingException e) {
           return ("Error while sending email");
       }
        return "forgot_password_form";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("najahmbarek08051980@gmail.com", "BRI-Technology");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);

    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token) {
        Utilisateur utilisateur = UtilisService.getByResetPasswordToken(token);
        if( utilisateur == null){
            return "Invalid Token";
        }
        return "reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Utilisateur utilisateur = UtilisService.getByResetPasswordToken(token);


        if (utilisateur == null) {
            return "Invalid Token";
        } else {
            UtilisService.updatePassword(utilisateur, password);
            return  "You have successfully changed your password.";
        }
    }
    @PostMapping("/updateUser")
    public ResponseEntity<Utilisateur> modifierUtilisateur(@Valid @RequestBody Long id, @Valid @RequestBody Utilisateur utilisateur ){
        return UtilisateurService.UpdateUtilisateur(id, utilisateur);
    }

}
