package com.medeirosdaniel.XSSM.Service;

import com.medeirosdaniel.XSSM.Controller.Request.MkLoginRequest;
import com.medeirosdaniel.XSSM.Entity.UserEntity;
import com.medeirosdaniel.XSSM.Enums.ProfileEnum;
import com.medeirosdaniel.XSSM.Repository.UserRepository;
import com.medeirosdaniel.XSSM.SystemExceptions.UserNameException;
import com.medeirosdaniel.XSSM.Util.Formats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(String id) {
        return userRepository.findById(id);
    }

    public UserEntity crateOrUpdate(UserEntity userEntity) throws UserNameException, MessagingException {
        logger.info("Iniciando Validação de Dados");
        if (userEntity.getUsername().length() < 8) {
            throw new UserNameException("Tamanho do UserName menor que o Requerido (8)");
        }
        if (userEntity.getPhone1().length() < 10 && userEntity.getPhone1().length() > 11) {
            throw new UserNameException("Tamanho do numeto telefonico não atende os requisitos");
        }
        UserEntity checkUsername = userRepository.findByUsername(userEntity.getUsername());
        if (checkUsername != null) {
            throw new UserNameException("Usuário já cadastrado na Base");
        }
        UserEntity checkEmail = userRepository.findByEmail(userEntity.getEmail());
        if (checkEmail != null) {
            throw new UserNameException("E-Mail já cadastrado na Base");
        }
        userEntity.setUsername(userEntity.getUsername().toLowerCase());

        logger.info("Dados Validados");
        if (userEntity.getProfile() == null) {
            userEntity.setProfile(ProfileEnum.ROLE_USER);
        }
        Formats formats = new Formats();

        userEntity.setLockAccount(true);
        userEntity.setUnlockCode(formats.generateCode());

        // Desbloqueio de Senha
        String keyAccess = "http://127.0.0.1:8080/unlock/"+userEntity.getUnlockCode()+"/"+userEntity.getUsername();
        MimeMessage msg = javaMailSender.createMimeMessage();

        //true multipart msg
        MimeMessageHelper helper = new MimeMessageHelper(msg,true);
        helper.setTo(userEntity.getEmail());
        helper.setSubject("Desbloquei de Conta - Purplet");
        helper.setText("Obrigado por Usar os Serviços de MedeirosDaniel");

        helper.setText("<h1>Desbloquei de Conta!</h1><br><a>Para realizar o desbloqueio da sua conta clique </a><a href=\""+keyAccess+"\">aqui</a>",true);
        javaMailSender.send(msg);

        return userRepository.save(userEntity);
    }

    public Boolean lockAccount(String username, String code) throws MessagingException {
        Formats formats = new Formats();
        UserEntity unlock = userRepository.findByUsername(username);
        if (code.equals(unlock.getUnlockCode())) {
            unlock.setLockAccount(Boolean.FALSE);
            unlock.setPassword(formats.generateCommonLangPassword());
            unlock.setUnlockCode(formats.generateCode());
            String text = "<h1>Purplet: Envio de Senha!</h1><br><p>Obrigado por Ativar sua conta, segue agora sua senha de ao Sistema:</p>" +
                    "<a>Sua senha é:<h2>"+unlock.getPassword()+"</h2></a><p>Caso deseje mudar ela, acesse login e depois [alterar senha]";
            sendGenericEmail(unlock.getEmail(), "Purplet: Envio da Senha de Acesso",text);
            userRepository.save(unlock);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void sendGenericEmail(String mail, String subject, String text)
            throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,true);
        helper.setTo(mail);
        helper.setSubject(subject);
        helper.setText(text,true);
        javaMailSender.send(msg);
    }

    public Boolean checkLogin(MkLoginRequest request){
        Boolean validLogin = false;
        UserEntity checkLogin = userRepository.findByEmail(request.getEmail());
        if(checkLogin.getEmail() != null &&
                checkLogin.getPassword().equals(request.getPassword())){

            validLogin = true;

        }
        return validLogin;
    }

}
