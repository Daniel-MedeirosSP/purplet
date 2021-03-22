package com.medeirosdaniel.XSSM.Controller;

import com.medeirosdaniel.XSSM.Controller.Request.CriteriaEmailRequest;
import com.medeirosdaniel.XSSM.Controller.Request.MkLoginRequest;
import com.medeirosdaniel.XSSM.DTO.CriteriaDTO;
import com.medeirosdaniel.XSSM.DTO.OpennedTicketDTO;
import com.medeirosdaniel.XSSM.DTO.TicketDTO;
import com.medeirosdaniel.XSSM.Entity.*;
import com.medeirosdaniel.XSSM.Repository.ProblemRepository;
import com.medeirosdaniel.XSSM.Repository.UserRepository;
import com.medeirosdaniel.XSSM.Service.*;
import com.medeirosdaniel.XSSM.SystemExceptions.UserNameException;
import com.medeirosdaniel.XSSM.Util.BeanMapper;
import com.medeirosdaniel.XSSM.Util.Formats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ThymeleafController {

    private static String hashUrl = "1614737404391";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ProblemCriteriasService problemCriteriasService;

    @Autowired
    private ServiceInitService serviceInit;


    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String goLogin(Model model) {
        model.addAttribute("request", new MkLoginRequest());
        return "login";
    }

    @PostMapping("/thmklogin")
    public String thmkLogin(@ModelAttribute MkLoginRequest request, Model model) {
        Formats formats = new Formats();
        ProblemCriteriasEntity getProblem = new ProblemCriteriasEntity();
        Boolean getAuth = userService.checkLogin(request);
        if (getAuth) {
            String greetings = "Olá ";
            String username = formats.convertCamelCase(userService.getUserNameByEmail(request.getEmail()));
            greetings = greetings + username;

            if (problemCriteriasService.findByEmail(request.getEmail()).get_id() == null) {
                getProblem.setEmail(request.getEmail());
                getProblem.setCriteria(new ArrayList<>());
            } else {
                getProblem = problemCriteriasService.findByEmail(request.getEmail());
            }
            String description = "";
            List<String> getSla = new ArrayList<>();

            model.addAttribute("greetings", greetings);
            model.addAttribute("email", getProblem.getEmail());
            model.addAttribute("listacriterios", getProblem.getCriteria());
            model.addAttribute("description", description);
            model.addAttribute("sla", getSla);
            model.addAttribute("criteria", new CriteriaEmailRequest());
            model.addAttribute("savetuser", new TicketUserEntity());
            model.addAttribute("saveempresa",new BusinessEntity());
            model.addAttribute("savecontact",new ContactEntity());


            return "thdashboard";
        } else {
            return "erro_";
        }

    }

    @PostMapping(value = "/savecriteria")
    public String saveCriteria(@ModelAttribute CriteriaEmailRequest criteriaEmailRequest, Model model) {

        Formats formats = new Formats();

        CriteriaDTO getCr = new CriteriaDTO();
        getCr.setDescription(criteriaEmailRequest.getDescription());
        getCr.setSla(criteriaEmailRequest.getSla());
        String getMail;
        ProblemCriteriasEntity getCriteria =
                problemCriteriasService.createProblemCriteria(criteriaEmailRequest.getGetEmail(), new BeanMapper().map(getCr, CriteriaEntity.class));
        if (getCr.getErroMSG() != null) {
            model.addAttribute("msgErro", getCr.getErroMSG());
            return "erro_";
        }

        model.addAttribute("criteria", new CriteriaEmailRequest());
        model.addAttribute("listacriterios", getCriteria.getCriteria());

        String greetings = "Olá ";
        String username = formats.convertCamelCase(userService.getUserNameByEmail(criteriaEmailRequest.getGetEmail()));
        String description = "";
        List<String> getSla = new ArrayList<>();

        greetings = greetings + username;
        model.addAttribute("greetings", greetings);
        model.addAttribute("email", criteriaEmailRequest.getGetEmail());
        model.addAttribute("listacriterios", getCriteria.getCriteria());
        model.addAttribute("description", description);
        model.addAttribute("sla", getSla);
        model.addAttribute("criteria", new CriteriaEmailRequest());

        return "thdashboard";

    }
    @PostMapping(value = "/saveticketuser")
    public String saveTicketUser(@ModelAttribute TicketUserEntity ticketUserEntity, Model model){

        model.addAttribute("savetuser", ticketUserEntity);

        return "thdashboard";

    }

    @PostMapping(value = "/savecontact")
    public String saveContact(){

        return "thdashboard";
    }


    @GetMapping(value = "/purplet")
    public String showTicketForm(@RequestParam("hashUrl") String hashUrl, Model model) {

        UserEntity getUser = userRepository.findByHashUrl(hashUrl);
        ProblemCriteriasEntity getProblemList = problemCriteriasService.findByEmail(getUser.getEmail());
        List<CriteriaEntity> problemList = getProblemList.getCriteria();
        model.addAttribute("hashUrl",hashUrl);
        model.addAttribute("ticket", new TicketEntity());
        model.addAttribute("type", problemList);
        model.addAttribute("titulo", "Titulo dessa Budega");
        return "openticket";
    }

    @PostMapping(value = "/purplet")
    public String saveTicketForm(@ModelAttribute TicketDTO ticketDTO, Model model) throws ParseException, MessagingException {
        UserEntity getUserTec = userRepository.findByHashUrl(ticketDTO.getHashUrl());
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
        model.addAttribute("cadOk", "Foi enviado um e-mail com uma chave de liveração de sua conta" +
                " use ela para ativar sua conta e começar a usar o Purplet");
        model.addAttribute("user", userEntity);
        return "index";
    }

    @GetMapping("/teste")
    public String returnText(@RequestParam("hashUrl") String caminho) {
        System.out.println(caminho);
        return caminho;
    }

    @GetMapping("/viacep")
    public String viacep(){
        return "teste";
    }

}

