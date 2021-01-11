package com.medeirosdaniel.XSSM.Controller;

import com.medeirosdaniel.XSSM.Controller.Request.MkLoginRequest;
import com.medeirosdaniel.XSSM.DTO.OpennedTicketDTO;
import com.medeirosdaniel.XSSM.DTO.TicketDTO;
import com.medeirosdaniel.XSSM.Entity.ProblemEntity;
import com.medeirosdaniel.XSSM.Entity.TicketEntity;
import com.medeirosdaniel.XSSM.Entity.UserEntity;
import com.medeirosdaniel.XSSM.Repository.UserRepository;
import com.medeirosdaniel.XSSM.Service.ProblemService;
import com.medeirosdaniel.XSSM.Service.TicketService;
import com.medeirosdaniel.XSSM.Service.UserService;
import com.medeirosdaniel.XSSM.SystemExceptions.UserNameException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@Controller
public class ThymeleafController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private TicketService ticketService;


    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String goLogin() {
        return "login";
    }

    @GetMapping(value = "/purplet")
    public String showTicketForm(Model model) {
        List<ProblemEntity> problemList = problemService.findAll();
        model.addAttribute("ticket", new TicketEntity());
        model.addAttribute("type", problemList);
        model.addAttribute("titulo", "Titulo dessa Budega");
        return "openticket";
    }

    @PostMapping(value = "/purplet")
    public String saveTicketForm(@ModelAttribute TicketDTO ticketDTO, Model model) throws ParseException, MessagingException {
        OpennedTicketDTO result = ticketService.createOrUpdateTicket(ticketDTO, ticketDTO.getEmail());
        if (ticketDTO.getErroMSG() != null) {
            model.addAttribute("msgErro", ticketDTO.getErroMSG());
            return "erro_";
        }
        model.addAttribute("ticket", ticketDTO);
        model.addAttribute("openticketdto", result);
//        result.getTicketNumber()
        return "opennedticket";
    }
    @GetMapping("/adduser")
    public String showSingUpForm(UserEntity userEntity) {
        return "adduser";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid UserEntity userEntity, BindingResult result, Model model) throws UserNameException, MessagingException {
        if (result.hasErrors()) {
             return "adduser";
        }
        userService.crateOrUpdate(userEntity);
        model.addAttribute("cadOk","Foi enviado um e-mail com uma chave de liveração de sua conta" +
                " use ela para ativar sua conta e começar a usar o Purplet");
        model.addAttribute("user", userEntity);
        return "index";
    }

    @PostMapping("/thmklogin")
    public String thmklogin(MkLoginRequest request,BindingResult result, ModelMapper mapper){
        if(result.hasErrors()){
            return "thmklogin";
        }
        if(userService.checkLogin(request)){
            return "thdashboard";
        }else{
            return "erro_";
        }

    }
    
}

