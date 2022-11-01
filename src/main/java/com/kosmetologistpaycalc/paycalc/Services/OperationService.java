package com.kosmetologistpaycalc.paycalc.Services;

import com.kosmetologistpaycalc.paycalc.Models.Client;
import com.kosmetologistpaycalc.paycalc.Models.Operation;
import com.kosmetologistpaycalc.paycalc.Repo.ClientRepository;
import com.kosmetologistpaycalc.paycalc.Repo.OperationRepository;
import com.kosmetologistpaycalc.paycalc.dto.DayInfo;
import com.kosmetologistpaycalc.paycalc.dto.CurrentNote;
import com.kosmetologistpaycalc.paycalc.dto.NoteInfo;
import com.kosmetologistpaycalc.paycalc.dto.OperationNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OperationService {

    private static final String PATTERN_DATE = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_DAY = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm";

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private ClientRepository clientRepository;

    //Создание операции вместе с клиентов (тут только операция)
    public void createOperation(Client client, NoteInfo body, Principal principal) throws ParseException {

        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
        date.setTime(sdf.parse(String.format("%s %s", body.getDate(), body.getTime())));

        operationRepository.save(new Operation(client.getId(),
            date,
            body.getOperationName(),
            body.getMedicament(),
            Integer.parseInt(body.getCost()),
            principal.getName()));
    }

    public List<CurrentNote> createCurrentNoteList(Principal principal, long limit) {
        List<Operation> myAllActualOperations = getAllMyActualOperations(principal);
        List<CurrentNote> myCurrentNoteList = new ArrayList<>();

        getAllDatesNotes(myAllActualOperations, limit).forEach(date -> {
            String countOperations = getCountOperationsOnDay(myAllActualOperations, date) + " записей";

            myCurrentNoteList.add(
                CurrentNote.builder()
                    .day(date)
                    .timeProperty("Пример")
                    .countDaysNotes(countOperations)
                    .build()
            );
        });
        return myCurrentNoteList;
    }

    //Все operationsNotes principal
    public List<OperationNote> operationNoteList(Principal principal, long limit) {
        List<Client> AllMyClients = getAllMyClients(principal);

        List<OperationNote> operationsNotes = new ArrayList<>();

        getAllMyActualOperations(principal).stream().forEach(currentOperation -> {
            operationsNotes.add(
                OperationNote.builder()
                    .day(new SimpleDateFormat(PATTERN_DAY).format(currentOperation.getDate().getTime()))
                    .time(new SimpleDateFormat(PATTERN_TIME).format(currentOperation.getDate().getTime()))
                    .medicament(currentOperation.getMedicament())
                    .summury(currentOperation.getSummury())
                    .name(currentOperation.getName())
                    .clientName(AllMyClients.stream()
                        .filter(client -> client.getId().equals(currentOperation.getClientId()))
                        .findFirst()
                        .orElseThrow(
                            () -> new NoSuchElementException("Не удалось найти клинета по id = "
                                + currentOperation.getClientId()))
                        .getData())
                    .clientId(currentOperation.getClientId())
                    .id(currentOperation.getId())
                    .build());
        });
        return operationsNotes.stream().sorted(new Comparator<OperationNote>() {
            public int compare(OperationNote a1, OperationNote a2) {
                return a1.getDay().compareTo(a2.getDay());
            }
        }).limit(limit).collect(Collectors.toList());
    }

    public List<DayInfo> getDayInfo(Principal principal, Long limit) {
        List<DayInfo> dayInfoList = new ArrayList<>();

        getAllDatesNotes(getAllMyActualOperations(principal), limit).forEach(date -> {
            dayInfoList.add(DayInfo.builder()
                .date(date)
                .countNotes(operationNoteList(principal, limit).stream().count() + " записей")
                .operationNotes(operationNoteList(principal, limit).stream()
                    .filter(note -> note.getDay().equals(date)).sorted(new Comparator<OperationNote>() {
                        public int compare(OperationNote a1, OperationNote a2) {
                            return a2.getTime().compareTo(a1.getTime());
                        }
                    })
                    .collect(Collectors.toList()))
                .build());
        });
        return dayInfoList;
    }

    public List<Operation> getAllMyOperations(Principal principal) {
        List<Operation> operations = new ArrayList<>();
        operationRepository.findAll().forEach(operation -> operations.add(operation));
        return operations.stream()
            .filter(operation -> operation.getUserName().equals(principal.getName()))
            .collect(Collectors.toList());
    }

    public List<Operation> getAllMyActualOperations(Principal principal) {
        return getAllMyOperations(principal).stream()
            .filter(operation -> operation.getDate().getTime().equals(Calendar.getInstance().getTime())
                || operation.getDate().getTime().after(Calendar.getInstance().getTime()))
            .sorted(new Comparator<Operation>() {
                public int compare(Operation a1, Operation a2) {
                    return a1.getDate().compareTo(a2.getDate());
                }
            })
            .collect(Collectors.toList());
    }

    private List<Client> getAllMyClients(Principal principal) {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(client -> clients.add(client));
        return clients.stream()
            .filter(operation -> operation.getUserName().equals(principal.getName()))
            .collect(Collectors.toList());
    }

    private int getCountOperationsOnDay(List<Operation> allMyOperations, String date) {
        return (int) allMyOperations.stream()
            .filter(operation -> new SimpleDateFormat(PATTERN_DAY).format(operation.getDate().getTime()).equals(date))
            .count();
    }

    //Получить все даты записей
    private List<String> getAllDatesNotes(List<Operation> operations, long limit) {
        return operations.stream()
            .map(Operation::getDate)
            .map(date -> new SimpleDateFormat(PATTERN_DAY).format(date.getTime()))
            .distinct()
            .sorted(Comparator.naturalOrder())
            .limit(limit)
            .collect(Collectors.toList());
    }
}
