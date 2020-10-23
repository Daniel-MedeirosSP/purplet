package com.medeirosdaniel.XSSM.Service;

import com.medeirosdaniel.XSSM.DTO.OpennedTicketDTO;
import com.medeirosdaniel.XSSM.DTO.TicketDTO;
import com.medeirosdaniel.XSSM.Entity.*;
import com.medeirosdaniel.XSSM.Enums.PriorityEnum;
import com.medeirosdaniel.XSSM.Enums.SlaEnum;
import com.medeirosdaniel.XSSM.Enums.StatusEnum;
import com.medeirosdaniel.XSSM.Repository.TicketRepository;
import com.medeirosdaniel.XSSM.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    static final Logger logger = LogManager.getLogger(TicketService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private TicketUserService ticketUserService;



    public OpennedTicketDTO createOrUpdateTicket(TicketDTO ticketDTO, String email) throws ParseException, MessagingException {
        Date data = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyMMdd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyy");

        OpennedTicketDTO opennedTicketDTO = new OpennedTicketDTO();
        ProblemEntity problemEntity = problemService.findByProblem(ticketDTO.getTitle());
        TicketEntity checksConsistency = new TicketEntity();
        String ticketNumber = sdf2.format(data) + System.currentTimeMillis();

        TicketUserEntity userOpenTicket = ticketUserService.findByEmail(email);

        if (userOpenTicket == null) {
            opennedTicketDTO.setErroMSG("E-Mail não Cadastrado na Base, contate seu provedor de Suporte!");
            return opennedTicketDTO;
        }

        checksConsistency.setEmail(email);
        checksConsistency.setTitle(ticketDTO.getTitle());
        checksConsistency.setDescription(ticketDTO.getDescription());
        checksConsistency.setStatus(StatusEnum.New);
        checksConsistency.setPriority(PriorityEnum.valueOf(problemEntity.getPriority()));
        checksConsistency.setSla(SlaEnum.valueOf(problemEntity.getSla()));
        checksConsistency.setTicketDateOpen(data);
        checksConsistency.setTicketNumber(ticketNumber);
        ticketRepository.save(checksConsistency);

        /**
         * Até este ponto, faz a persistência do ticket
         */
        BusinessEntity businessEntity = businessService.findAddressByCnpj(userOpenTicket.getCnpj());

        opennedTicketDTO.setTicketUser(userOpenTicket);
        opennedTicketDTO.setEmail(email);
        opennedTicketDTO.setTitle(checksConsistency.getTitle());
        opennedTicketDTO.setDescription(checksConsistency.getDescription());
        opennedTicketDTO.setStatus(checksConsistency.getStatus());
        opennedTicketDTO.setPriority(checksConsistency.getPriority());
        opennedTicketDTO.setSla(checksConsistency.getSla());
        opennedTicketDTO.setTicketDateOpen(sdf3.format(checksConsistency.getTicketDateOpen()));
        opennedTicketDTO.setTicketNumber(checksConsistency.getTicketNumber());
        opennedTicketDTO.setCnpj(businessEntity.getCnpj());
        opennedTicketDTO.setCompanyName(businessEntity.getCompanyName());
        opennedTicketDTO.setStateRegistration(businessEntity.getStateRegistration());
        opennedTicketDTO.setBusinessSegment(businessEntity.getBusinessSegment());
        opennedTicketDTO.setCep(businessEntity.getCep());
        opennedTicketDTO.setStreet(businessEntity.getStreet());
        opennedTicketDTO.setNumber(businessEntity.getNumber());
        opennedTicketDTO.setComplement(businessEntity.getComplement());
        opennedTicketDTO.setNeighborhood(businessEntity.getNeighborhood());
        opennedTicketDTO.setCity(businessEntity.getCity());
        opennedTicketDTO.setState(businessEntity.getState());

        if (opennedTicketDTO != null){

            MimeMessage msg = javaMailSender.createMimeMessage();

            //true multipart msg
            MimeMessageHelper helper = new MimeMessageHelper(msg,true);
            helper.setTo(email);
            helper.setSubject("Purplet: "+checksConsistency.getTicketNumber());
            helper.setText("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html\" ; charset=\"utf-8\" />\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "\n" +
                    "    <style type=\"text/css\">\n" +
                    "        body {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            background-color: #f6f9ff;\n" +
                    "        }\n" +
                    "\n" +
                    "        table {\n" +
                    "            border-spacing: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        td {\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        img {\n" +
                    "            border: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        .wrapper {\n" +
                    "            width: 100%;\n" +
                    "            table-layout: fixed;\n" +
                    "            background-color: #f6f9fc;\n" +
                    "            padding-bottom: 40px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .webkit {\n" +
                    "            max-width: 600px;\n" +
                    "            background-color: #ffffff;\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "        .outer {\n" +
                    "            margin: 0 auto;\n" +
                    "            width: 100%;\n" +
                    "            max-width: 600px;\n" +
                    "            border-spacing: 0;\n" +
                    "            font-family: sans-serif;\n" +
                    "            color: #4a4a4a;\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "        .three-columns {\n" +
                    "            text-align: center;\n" +
                    "            font-size: 0;\n" +
                    "            padding-top: 40px;\n" +
                    "            padding-bottom: 30px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .three-columns .columns {\n" +
                    "            width: 100%;\n" +
                    "            max-width: 300px;\n" +
                    "            display: inline-block;\n" +
                    "            vertical-align: top;\n" +
                    "        }\n" +
                    "\n" +
                    "        .padding {\n" +
                    "            padding: 15px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .three-columns .content {\n" +
                    "            font-size: 15px;\n" +
                    "            line-height: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        a {\n" +
                    "            text-decoration: none;\n" +
                    "            color: blueviolet;\n" +
                    "            font-size: 16px;\n" +
                    "        }\n" +
                    "\n" +
                    "        @media screen and (max-width: 600px) {}\n" +
                    "\n" +
                    "        @media screen and (max-width: 400px) {}\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "\n" +
                    "    <center class=\"wrapper\">\n" +
                    "        <div class=\"webkit\">\n" +
                    "            <table class=\"outer\" align=\"center\">\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <table width=\"100%\" style=\"border-spacing:0;\">\n" +
                    "                            <tr>\n" +
                    "                                <td style=\"background-color: blueviolet;padding: 10px;text-align: center;\">\n" +
                    "\n" +
                    "                                    <a\n" +
                    "                                        style=\"color: #ffffff; font: Impact; text-align: center;\"><strong>Purplet</strong></a><br>\n" +
                    "                                    <a style=\"color: #ffffff; font: Impact; text-align: center;\"><small>Confirmação da\n" +
                    "                                            Abertura de Chamado - Solicitação de Suporte</small></a>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td><a href=\"#\"><img src=\"https://www.wallpapertip.com/wmimgs/5-58903_sunset.jpg\" width=\"600px\"\n" +
                    "                                alt=\"Banner\"></a></td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <table width=\"100%\" style=\"border-spacing:0;\">\n" +
                    "                            <tr>\n" +
                    "                                <td style=\"background-color:  blueviolet;padding: 10px;text-align: center;\">\n" +
                    "\n" +
                    "                                    <a style=\"color: #ffffff; font: Impact; text-align: center;\">\n" +
                    "                                        Foi aberto o chamado <strong>"+opennedTicketDTO.getTicketNumber()+"</strong> por\n" +
                    "                                        <strong>"+userOpenTicket.getFullname()+"</strong>, segue os dados da Abertura do\n" +
                    "                                        Ticket:</a><br>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                        <table class=\"content\" style=\"padding: 10px;\" width=100%>\n" +
                    "                            <tr>\n" +
                    "                                <td>\n" +
                    "                                    <p style=\"font-size: 17px; font-weight: bold;\"><em>Dados do Usuário</em></p>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>e-mail: </small><strong>"+opennedTicketDTO.getEmail()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>celular: </small><strong>"+userOpenTicket.getCelular()+"</strong></P>  \n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                        <table class=\"content\" style=\" background-color:  #b589df;padding: 10px;\" width=100%>\n" +
                    "                            <tr>\n" +
                    "                                <td>\n" +
                    "                                    <p style=\"font-size: 17px; font-weight: bold;\"><em>Dados da Empresa</em></p>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>empresa: </small><strong>"+opennedTicketDTO.getCompanyName()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>cnpj: </small><strong>"+opennedTicketDTO.getCnpj()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>ins.est: </small><strong>"+opennedTicketDTO.getStateRegistration()+"</strong></P>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                        <table class=\"content\" style=\"padding: 10px;\" width=100%>\n" +
                    "                            <tr>\n" +
                    "                                <td>\n" +
                    "                                    <p style=\"font-size: 17px; font-weight: bold;\"><em>Local de Atendimento</em></p>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>cep: </small><strong>"+opennedTicketDTO.getCep()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>logradouro: </small><strong>"+opennedTicketDTO.getStreet()+" Nº "+opennedTicketDTO.getNumber()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>complemento: </small><strong>"+opennedTicketDTO.getComplement()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>bairro: </small><strong>"+opennedTicketDTO.getNeighborhood()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>cidade: </small><strong>"+opennedTicketDTO.getCity()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>uf: </small><strong>"+opennedTicketDTO.getState()+"</strong></P>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                        <table class=\"content\" style=\" background-color:  #b589df;padding: 10px;\" width=100%>\n" +
                    "                            <tr>\n" +
                    "                                <td>\n" +
                    "                                    <p style=\"font-size: 17px; font-weight: bold;\"><em>Dados do Ticket</em></p>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>titulo: </small><strong>"+opennedTicketDTO.getTitle()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>descrição: </small><strong>"+opennedTicketDTO.getDescription()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><smal>status: </smal><strong>"+opennedTicketDTO.getStatus()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>prioridade: </small><strong>"+opennedTicketDTO.getPriority()+"</strong></P>\n" +
                    "                                    <P style=\"font-size: 12px;\"><small>sla: </small><strong>"+opennedTicketDTO.getSla()+"</strong></P>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </td>\n" +
                    "            </tr>\n" +
                    "            <br>\n" +
                    "            <tr>\n" +
                    "                <td>\n" +
                    "                    <table width=\"100%\" style=\"border-spacing: 0;\">\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"background-color: blueviolet;padding: 15px;text-align: center;\">\n" +
                    "                                <a style=\"font-size: 18px;color: #ffffff;margin-bottom: 13px; font: Impact;\">\n" +
                    "                                    Fique Conectado com a Gente - Saiba das novidades, upgrades e demais facilidades do <strong>Purplet</strong>. Feito para você!</a><br>\n" +
                    "                                <a href=\"#\"><img\n" +
                    "                                        src=\"https://www.pngfind.com/pngs/m/174-1744532_facebook-icon-link-facebook-white-icon-hd-png.png\"\n" +
                    "                                        width=\"30\" alt=\"\"> </a>\n" +
                    "                                <a href=\"#\"><img\n" +
                    "                                        src=\"https://www.pngfind.com/pngs/m/174-1744532_facebook-icon-link-facebook-white-icon-hd-png.png\"\n" +
                    "                                        width=\"30\" alt=\"\"> </a>\n" +
                    "                                <a href=\"#\"><img\n" +
                    "                                        src=\"https://www.pngfind.com/pngs/m/174-1744532_facebook-icon-link-facebook-white-icon-hd-png.png\"\n" +
                    "                                        width=\"30\" alt=\"\"></a>\n" +
                    "                                <a href=\"#\"><img\n" +
                    "                                        src=\"https://www.pngfind.com/pngs/m/174-1744532_facebook-icon-link-facebook-white-icon-hd-png.png\"\n" +
                    "                                        width=\"30\" alt=\"\"></a>\n" +
                    "                                <a href=\"#\"><img\n" +
                    "                                        src=\"https://www.pngfind.com/pngs/m/174-1744532_facebook-icon-link-facebook-white-icon-hd-png.png\"\n" +
                    "                                        width=\"30\" alt=\"\"></a>\n" +
                    "                                <a href=\"#\"><img\n" +
                    "                                        src=\"https://www.pngfind.com/pngs/m/174-1744532_facebook-icon-link-facebook-white-icon-hd-png.png\"\n" +
                    "                                        width=\"30\" alt=\"\"></a>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"backgoubnd-color: #efefef\">\n" +
                    "                                <table width=\"100%\" style=\"border-spacing:0;\">\n" +
                    "                                    <tr>\n" +
                    "                                        <td style=\"padding:20px;text-align: center;padding-bottom: 10px;\">\n" +
                    "                                            <a href=\"#\"><img src=\"\" alt=\"\" width=\"160\"></a>\n" +
                    "                                            <p style=\"font-size: 16px;margin-top:18px;margin-bottom:10px;\">Purplet -\n" +
                    "                                                Abertura de Chamados e Controle de Protocolos</p>\n" +
                    "                                            <p style=\"font-size: 16px;margin-top:18px;margin-bottom:10px;\">São Paulo/SP,\n" +
                    "                                                Cep: 05881-010</p>\n" +
                    "                                            <p><a\n" +
                    "                                                    href=\"mailto:purplet@medeirosdaniel.com\">purplet@medeirosdaniel.com</a>\n" +
                    "                                            </p>\n" +
                    "                                            <p><a href:=\"tel:11981596932\">(11)98159-6932</a></p>\n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "\n" +
                    "                                </table>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td height=\"20\" style=\"background-color: blueviolet;\">\n" +
                    "                                <a><small>ver 0.4</small></a>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "\n" +
                    "                    </table>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            </table>\n" +
                    "        </div>\n" +
                    "    </center>\n" +
                    "   \n" +
                    "\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>",true);
            javaMailSender.send(msg);
        }

        return opennedTicketDTO;

    }


    public List<TicketEntity> listTicket() {
        return this.ticketRepository.findAll();
    }

    public List<TicketEntity> findTicketByEmail(String email) {
        return ticketRepository.findTicketByEmail(email);
    }
}