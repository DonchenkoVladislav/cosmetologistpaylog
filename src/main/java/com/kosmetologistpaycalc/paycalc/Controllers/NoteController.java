package com.kosmetologistpaycalc.paycalc.Controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.kosmetologistpaycalc.paycalc.Models.Client;
import com.kosmetologistpaycalc.paycalc.Models.Operation;
import com.kosmetologistpaycalc.paycalc.Repo.OperationRepository;
import com.kosmetologistpaycalc.paycalc.Services.ClientService;
import com.kosmetologistpaycalc.paycalc.Services.OperationService;
import com.kosmetologistpaycalc.paycalc.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;

import static com.kosmetologistpaycalc.paycalc.Services.OperationService.PATTERN_DAY;
import static com.kosmetologistpaycalc.paycalc.Services.OperationService.PATTERN_TIME;
import static com.kosmetologistpaycalc.paycalc.commons.Constants.COUNT_NOTE;

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
    public String allNotes() {
        return "addnote.html";
    }

    @GetMapping("/newnote/currentday")
    public @ResponseBody List<DayInfo> getAllActualNotes(Principal principal) {
        return operationService.getDayInfo(principal, 99L);
    }

    @GetMapping("/addnoteresult")
    public String result() {
        return "addnoteresult.html";
    }

    @PostMapping(path = "/add_note")
    public @ResponseBody Alert getNewNote(@RequestBody NoteInfo data, Principal principal) throws Exception {
        Client newClient;
        try {
            if (data.getClientId().equals("")) {
                newClient = clientService.createClient(data, principal);
            } else {
                newClient = clientService.getClientById(data.getClientId());
            }
            operationService.saveOperation(newClient, data, principal);
            return Alert.builder().title("OK").build();
        } catch (Exception e) {
            return Alert.builder().title("Упс! ").subtitle(e.getMessage()).build();
        }
    }

    @GetMapping("/several_actual_operations")
    public @ResponseBody List<OperationNote> getSeveralMyActualOperations(Principal principal) {
        return operationService.operationNoteList(principal, 3);
    }

    @GetMapping("/all_operations")
    public String getHtmlAllMyActualOperations() {
        return "alloperations.html";
    }

    @GetMapping("/all_actual_operations")
    public @ResponseBody List<OperationNote> getAllMyActualOperations(Principal principal) {
        return operationService.operationNoteList(principal, COUNT_NOTE);
    }

    @PostMapping("/delete_operation")
    public @ResponseBody Alert deleteOperation(@RequestBody DeleteId data, Principal principal) {
        try {
            Operation operation = operationRepository.findById(Long.parseLong(data.getId()))
                .filter(o -> o.getUserName().equals(principal.getName()))
                .orElseThrow(() -> new NoSuchElementException("Операция не найдена"));

            String oName = operation.getName();
            String date = new SimpleDateFormat(PATTERN_DAY).format(operation.getDate().getTime());
            String time = new SimpleDateFormat(PATTERN_TIME).format(operation.getDate().getTime());

            operationRepository.delete(operation);
            return Alert.builder()
                    .info(date)
                    .title(String.format("Процедура \"%s\" на  %s %s удалена", oName, date, time))
                    .build();

        } catch (NoSuchElementException nse) {
            return Alert.builder()
                    .title("Упс! Что-то сломалось, но мы скоро починим")
                    .build();
        }
    }

    @GetMapping("/last_note")
    public @ResponseBody OperationNote result(Principal principal) {
        return operationService.getLastNote(principal);
    }
}
