package com.kosmetologistpaycalc.paycalc.Controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.kosmetologistpaycalc.paycalc.Models.Client;
import com.kosmetologistpaycalc.paycalc.Repo.OperationRepository;
import com.kosmetologistpaycalc.paycalc.Services.ClientService;
import com.kosmetologistpaycalc.paycalc.Services.OperationService;
import com.kosmetologistpaycalc.paycalc.dto.DayInfo;
import com.kosmetologistpaycalc.paycalc.dto.NoteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@Controller
public class NoteController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("/newnote")
    public String allNotes(Model model) {
        return "addnote.html";
    }

    @PostMapping(path = "/add_note")
    public @ResponseBody String getNewNote(@RequestBody NoteInfo data, Principal principal) throws Exception  {
        Client newClient;
        if (data.getClientId() == "") {
            newClient = clientService.createClient(data, principal);
        } else {
            newClient  = clientService.getClientById(data.getClientId());
        }
        operationService.createOperation(newClient, data, principal);
        return "Запись добавлена";
    }

//    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/newnote/currentday")
    public @ResponseBody List<DayInfo> getAllActualNotes(Principal principal, Model model) {
        return operationService.getDayInfo(principal, 99L);
    }
}
