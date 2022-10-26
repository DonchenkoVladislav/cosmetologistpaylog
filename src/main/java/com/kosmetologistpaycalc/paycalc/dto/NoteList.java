package com.kosmetologistpaycalc.paycalc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class NoteList {

    private List<CurrentNote> currentNoteList;
}
