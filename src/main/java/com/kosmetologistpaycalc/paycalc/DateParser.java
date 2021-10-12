package com.kosmetologistpaycalc.paycalc;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateParser {

    public String parserToMouthYear (String day) throws ParseException {
        String mounth = new String();
        String year = new String();
        if (new SimpleDateFormat("MM.yyyy").format(
                new SimpleDateFormat("MM.yyyy").parse(day)).equals(day)){
            mounth = new SimpleDateFormat("MM").format(
                    new SimpleDateFormat("MM.yyyy").parse(day));
            year = new SimpleDateFormat("yyyy").format(
                    new SimpleDateFormat("MM.yyyy").parse(day));
        }
        switch (mounth){
            case ("01") : mounth = "Январь " + year; break;
            case ("02") : mounth = "Февраль " + year; break;
            case ("03") : mounth = "Март " + year; break;
            case ("04") : mounth = "Апрель " + year; break;
            case ("05") : mounth = "Май " + year; break;
            case ("06") : mounth = "Июнь " + year; break;
            case ("07") : mounth = "Июль " + year; break;
            case ("08") : mounth = "Август " + year; break;
            case ("09") : mounth = "Сентябрь " + year; break;
            case ("10") : mounth = "Октябрь " + year; break;
            case ("11") : mounth = "Ноябрь " + year; break;
            case ("12") : mounth = "Декабрь " + year; break;
        }
        return mounth;
    }

    public String parserToMouth (String day) throws ParseException {
        String mounth = new String();
        if (new SimpleDateFormat("MM.yyyy").format(
                new SimpleDateFormat("MM.yyyy").parse(day)).equals(day)){
            mounth = new SimpleDateFormat("MM").format(
                    new SimpleDateFormat("MM.yyyy").parse(day));
        }
        switch (mounth){
            case ("01") : mounth = "Январь"; break;
            case ("02") : mounth = "Февраль"; break;
            case ("03") : mounth = "Март"; break;
            case ("04") : mounth = "Апрель"; break;
            case ("05") : mounth = "Май"; break;
            case ("06") : mounth = "Июнь"; break;
            case ("07") : mounth = "Июль"; break;
            case ("08") : mounth = "Август"; break;
            case ("09") : mounth = "Сентябрь"; break;
            case ("10") : mounth = "Октябрь"; break;
            case ("11") : mounth = "Ноябрь"; break;
            case ("12") : mounth = "Декабрь"; break;
        }
        return mounth;
    }
}
