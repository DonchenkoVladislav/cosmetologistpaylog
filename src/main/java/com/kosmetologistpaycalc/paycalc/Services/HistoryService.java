package com.kosmetologistpaycalc.paycalc.Services;

import com.kosmetologistpaycalc.paycalc.Models.Operation;
import com.kosmetologistpaycalc.paycalc.dto.HistorycalMonthItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    public static final String PATTERN_MONTH = "yyyy-MM";

    @Autowired
    private OperationService operationService;

    public List<HistorycalMonthItem> getMonthIncome(Principal principal) {

        List<Operation> allLastOperations = getAllLastOperations(principal);

        List<String> lastMonths = allLastOperations.stream()
                .map(Operation::getDate)
                .map(Calendar::getTime)
                .map(date -> new SimpleDateFormat(PATTERN_MONTH).format(date))
                .distinct()
                .collect(Collectors.toList());

        return lastMonths.stream()
                .map(lastMonth ->
                        HistorycalMonthItem.builder()
                                .monthName(getMonthName(lastMonth))
                                .summury(getSummuryOfLastMonth(allLastOperations, lastMonth))
                                .build())
                .collect(Collectors.toList());
    }

    private String getMonthName(String lastMonth) {
        switch (lastMonth.split("-")[1]) {
            case "01":
                return "Январь " + lastMonth.split("-")[0];
            case "02":
                return "Февраль " + lastMonth.split("-")[0];
            case "03":
                return "Март " + lastMonth.split("-")[0];
            case "04":
                return "Апрель " + lastMonth.split("-")[0];
            case "05":
                return "Май " + lastMonth.split("-")[0];
            case "06":
                return "Июнь " + lastMonth.split("-")[0];
            case "07":
                return "Июль " + lastMonth.split("-")[0];
            case "08":
                return "Август " + lastMonth.split("-")[0];
            case "09":
                return "Сентябрь " + lastMonth.split("-")[0];
            case "10":
                return "Октябрь " + lastMonth.split("-")[0];
            case "11":
                return "Ноябрь " + lastMonth.split("-")[0];
            case "12":
                return "Декабрь " + lastMonth.split("-")[0];

            default:
                return "Нет ни одной записи";
        }
    }

    private Integer getSummuryOfLastMonth(List<Operation> allLastOperations, String lastMonth) {
        return allLastOperations.stream()
                .filter(operation -> new SimpleDateFormat(PATTERN_MONTH).format(operation.getDate().getTime()).equals(lastMonth))
                .filter(operation -> operation.getSummury() != null)
                .mapToInt(Operation::getSummury)
                .sum();
    }

    private List<Operation> getAllLastOperations(Principal principal) {
        return operationService.getAllMyOperations(principal).stream()
                .filter(operation -> operation.getDate().before(Calendar.getInstance()))
                .collect(Collectors.toList());
    }
}
