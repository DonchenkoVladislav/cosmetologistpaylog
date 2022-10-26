package com.kosmetologistpaycalc.paycalc.Services;

import com.kosmetologistpaycalc.paycalc.Models.Client;
import com.kosmetologistpaycalc.paycalc.Models.Operation;
import com.kosmetologistpaycalc.paycalc.Repo.ClientRepository;
import com.kosmetologistpaycalc.paycalc.Repo.OperationRepository;
import com.kosmetologistpaycalc.paycalc.dto.ClientsInfo;
import com.kosmetologistpaycalc.paycalc.dto.NoteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private OperationService operationService;

    public Client createClient(NoteInfo body, Principal principal) {
        Client client = new Client(
                body.getClientName(),
                body.getPhone(),
                body.getComment(),
                principal.getName());

        clientRepository.save(client);
        return client;
    }

    public List<ClientsInfo> getAllMyClientInfo(Principal principal) {
        List<ClientsInfo> allMyClientInfo = new ArrayList<>();
        getAllMyClients(principal).forEach(client -> {
            allMyClientInfo.add(ClientsInfo.builder()
                            .client(client)
                            .operations(operationService.getAllMyOperations(principal).stream()
                                    .filter(operation -> operation.getClientId().equals(client.getId()))
                                    .collect(Collectors.toList()))
                    .build());
        });
        return allMyClientInfo;
    }

    public ClientsInfo getClientInfo(String clientId, Principal principal) {
        Client client = getAllMyClients(principal).stream()
                .filter(c -> c.getId().equals(Long.valueOf(clientId).longValue()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такой клиент не найден"));

        return ClientsInfo.builder()
                .client(client)
                .operations(operationService.getAllMyOperations(principal).stream()
                        .filter(operation -> operation.getClientId().equals(client.getId()))
                        .collect(Collectors.toList()))
                .build();
    }

    public Client getClientById(String id) {
        return clientRepository.findById(Long.valueOf(id).longValue())
                .orElseThrow(() -> new NoSuchElementException("Не найден клиент " + id));
    }

    private List<Client> getAllMyClients(Principal principal) {
        List<Client> allMyClients = new ArrayList<>();
        clientRepository.findAll().forEach(client -> allMyClients.add(client));
        return allMyClients.stream()
                .filter(client -> client.getUserName().equals(principal.getName()))
                .collect(Collectors.toList());
    }
}
