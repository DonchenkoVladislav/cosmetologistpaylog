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
import java.util.*;
import java.util.stream.Collectors;

import static com.kosmetologistpaycalc.paycalc.commons.Constants.COUNT_NOTE;
import static com.kosmetologistpaycalc.paycalc.dto.TimeSelect.TIME_SELECT;

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
    public void saveOperation(Client client, NoteInfo body, Principal principal) throws ParseException {

        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE);
        date.setTime(sdf.parse(String.format("%s %s", body.getDate(), body.getTime())));

        operationRepository.save(new Operation(client.getId(),
                date,
                body.getOperationName(),
                body.getMedicament(),
                Integer.parseInt(body.getCost()),
                principal.getName(),
                body.getDuration()));
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

    //Все актуальные operationsNotes principal
    public List<OperationNote> operationNoteList(Principal principal, long limit) {
        List<Client> allMyClients = getAllMyClients(principal);

        List<OperationNote> operationsNotes = new ArrayList<>();

        getAllMyActualOperations(principal).forEach(operation -> createOperationsNote(operationsNotes, operation, allMyClients));

        return operationsNotes.stream().sorted(new Comparator<OperationNote>() {
            public int compare(OperationNote a1, OperationNote a2) {
                return a1.getDay().compareTo(a2.getDay());
            }
        }).limit(limit).collect(Collectors.toList());
    }

    public OperationNote getLastNote(Principal principal) {
        return operationNoteList(principal, COUNT_NOTE).stream().max(new Comparator<OperationNote>() {
                    public int compare(OperationNote a1, OperationNote a2) {
                        return a1.getId().compareTo(a2.getId());
                    }
                })
                .orElseThrow(() -> new NoSuchElementException("Не удалось найти последнюю запись"));
    }

    //Все operationsNotes principal
    public List<OperationNote> allOperationNoteList(Principal principal, long limit) {
        List<Client> allMyClients = getAllMyClients(principal);

        List<OperationNote> operationsNotes = new ArrayList<>();

        getAllMyOperations(principal).forEach(operation -> createOperationsNote(operationsNotes, operation, allMyClients));
        return operationsNotes.stream().sorted(new Comparator<OperationNote>() {
            public int compare(OperationNote a1, OperationNote a2) {
                return a2.getDay().compareTo(a1.getDay());
            }
        }).limit(limit).collect(Collectors.toList());
    }

    private List<OperationNote> createOperationsNote(List<OperationNote> operationNotes, Operation operation, List<Client> clients) {
        String time = new SimpleDateFormat(PATTERN_TIME).format(operation.getDate().getTime());

        operationNotes.add(
                OperationNote.builder()
                        .day(new SimpleDateFormat(PATTERN_DAY).format(operation.getDate().getTime()))
                        .time(time)
                        .durationList(getDurations(operation.getDuration(), time))
                        .duration(operation.getDuration())
                        .medicament(operation.getMedicament())
                        .summury(operation.getSummury())
                        .name(operation.getName())
                        .clientName(clients.stream()
                                .filter(client -> client.getId().equals(operation.getClientId()))
                                .findFirst()
                                .orElseThrow(
                                        () -> new NoSuchElementException("Не удалось найти клинета по id = "
                                                + operation.getClientId()))
                                .getData())
                        .clientId(operation.getClientId())
                        .id(operation.getId())
                        .build());

        return operationNotes;
    }

    private List<String> getDurations(String duration, String time) {
        List<String> timeList = new ArrayList<>();

        for (int t = 0; t < TIME_SELECT.length; t++) {
            if (duration == null) {
                timeList.add(time);
                break;
            }
            if (time.equals(TIME_SELECT[t])) {
                switch (duration) {
                    case ("30"):
                        timeList.add(time);
                        break;
                    case ("60"):
                        timeList.add(time);
                        timeList.add(TIME_SELECT[t + 1]);
                        break;
                    case ("90"):
                        timeList.add(time);
                        timeList.add(TIME_SELECT[t + 1]);
                        timeList.add(TIME_SELECT[t + 2]);
                        break;
                    case ("120"):
                        timeList.add(time);
                        timeList.add(TIME_SELECT[t + 1]);
                        timeList.add(TIME_SELECT[t + 2]);
                        timeList.add(TIME_SELECT[t + 3]);
                        break;
                }
            }
        }
        return timeList;
    }

    public List<DayInfo> getDayInfo(Principal principal, Long limit) {
        List<DayInfo> dayInfoList = new ArrayList<>();

        getAllDatesNotes(getAllMyOperations(principal), limit).forEach(date -> {
            dayInfoList.add(DayInfo.builder()
                    .date(date)
                    .countNotes(allOperationNoteList(principal, limit).stream()
                            .filter(note -> note.getDay().equals(date)).count() + " записей")
                    .operationNotes(allOperationNoteList(principal, limit).stream()
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
