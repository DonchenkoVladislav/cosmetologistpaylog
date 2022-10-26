package com.kosmetologistpaycalc.paycalc.dto;

import com.kosmetologistpaycalc.paycalc.Models.Client;
import com.kosmetologistpaycalc.paycalc.Models.Operation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ClientsInfo {
    Client client;
    List<Operation> operations;
}
