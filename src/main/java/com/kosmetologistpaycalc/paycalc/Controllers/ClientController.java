package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Services.ClientService;
import com.kosmetologistpaycalc.paycalc.dto.ClientsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    public ClientService clientService;

    @GetMapping("/client_info")
    public String getClientInfo(@RequestParam(defaultValue = "0") String id, Principal principal, Model model) {
        model.addAttribute("clientId", clientService.getClientInfo(id, principal).getClient().getId());
        return "client.html";
    }

    @GetMapping("/client")
    public @ResponseBody ClientsInfo getClientInfo(@RequestParam(defaultValue = "0") String id, Principal principal) {
        return clientService.getClientInfo(id, principal);
    }

    @GetMapping("/newnote/create/select-client/all_my_clients")
    public @ResponseBody List<ClientsInfo> getAllMyClient(Principal principal) {
        return clientService.getAllMyClientInfo(principal);
    }

    @GetMapping("/newnote/create/select-client")
    public String createNote(@RequestParam(defaultValue = "0") String date,
                             @RequestParam(defaultValue = "0") String time,
                             Model model) {

        model.addAttribute("date", date);
        model.addAttribute("time", time);
        return "selectclient.html";
    }
}
