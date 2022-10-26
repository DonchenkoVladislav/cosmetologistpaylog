package com.kosmetologistpaycalc.paycalc;

import com.kosmetologistpaycalc.paycalc.Configs.WebSecurityConfig;
import com.kosmetologistpaycalc.paycalc.Models.DateAndSummDate;
import com.kosmetologistpaycalc.paycalc.Models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Datecalendar {

    //Конвертирует ArrayList<String> в Iterable
    public Iterable<String> ListToIterable (List<String> list){
        Iterable<String> iterableCurrentElement = list;
        return iterableCurrentElement;
    }

    //Конвертирует ArrayList<DateAndSummDate> в Iterable
    public Iterable<DateAndSummDate> ListToIterableInt (List<DateAndSummDate> list){
        Iterable<DateAndSummDate> iterableCurrentElement = list;
        return iterableCurrentElement;
    }

    //Выводит список дат из всех записей в базе данных
    public ArrayList<String> iterableToArrayListDay (Iterable<Post> posts){
        ArrayList<String> arrayListCurrentElement = new ArrayList<>();
        for (Post currentElement:posts) {
            arrayListCurrentElement.add(currentElement.getDay());
        }
        return arrayListCurrentElement;
    }

    //Делает все даты из списка уникальными и сортирует их в порядке возрастания
    public List<String> getListPostsDate (ArrayList<String> list) throws ParseException {
        Set<String> set = new HashSet<>(list);
        List<String> listPostsDate = new ArrayList<>(set);
        List<Date> dateList = new ArrayList<>();
        for (String lists:listPostsDate){
            dateList.add(new SimpleDateFormat("dd.MM.yyyy").parse(lists));
        }
        Collections.sort(dateList);
        listPostsDate = new ArrayList<>();
        for (Date lists:dateList){
            listPostsDate.add(new SimpleDateFormat("dd.MM.yyyy").format(lists));
        }
        return listPostsDate;
    }

    //Выводит список всех записей из базы данных
    public ArrayList<Post> iterableToArrayList(Iterable<Post> posts){
        ArrayList<Post> allPosts = new ArrayList<>();
        for (Post currentElement:posts) {
            allPosts.add(currentElement);
        }
        return allPosts;
    }

    //Проверяет принадлежит ли запись текущему пользователю
    public Iterable<Post> filterUser(ArrayList<Post> allPosts){
        ArrayList<Post> currentUserList = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (Post post : allPosts) {
            try{
                if(post.getUsername().equals(auth.getName())) {
                    currentUserList.add(post);
                }
            }
            catch (NullPointerException nullPointerException){
            }
        }
        Iterable<Post> postCurrentUser = currentUserList;
        return postCurrentUser;
    }
    //Выводит список последних операций (максимум 5)
    public Iterable<Post> getLastPosts (Iterable<Post> postCurrentUser, int countPosts){
        ArrayList<Post> lastPostsList = new ArrayList<>();
        ArrayList<Post> lastPostsListFive = new ArrayList<>();

        try {
            for (Post post : postCurrentUser) {
                lastPostsList.add(post);
            }
            for (int i = lastPostsList.size() - 1; i > lastPostsList.size() - countPosts; i--) {
                lastPostsListFive.add(lastPostsList.get(i));
            }
            Iterable<Post> lastPosts = lastPostsListFive;
            return lastPosts;
        }
        catch (IndexOutOfBoundsException exception){
            Iterable<Post> lastPosts = lastPostsListFive;
            return lastPosts;
        }
    }

    //Считает сумму прибыли всех записей за каждый день и помещает в массив объектов включающих Дату дня и Сумму за день
    public List<DateAndSummDate> getListPostDateSumm (ArrayList<Post> allPosts, List<String> listPostsDate) throws ParseException {
        Integer summ = 0;
        List<DateAndSummDate> listDaypost = new ArrayList<>();
        List<Integer> listPostDateSumm = new ArrayList<>();
        for (String date:listPostsDate) {
            summ = 0;
            for (Post post:allPosts) {
                if (post.getDay().equals(date)){
                    summ = summ + post.getSummary();
                }
            }
            listPostDateSumm.add(summ);
        }
        for (int i = 0; i < listPostsDate.size(); i++){
            DateAndSummDate daypost = new DateAndSummDate(listPostDateSumm.get(i), listPostsDate.get(i));
            listDaypost.add(daypost);
        }
        return listDaypost;
    }

    //Выводит чистую прибыль за текущий месяц
    public String getCurrentMounthSummary(ArrayList<Post> allPosts) throws ParseException {
        Integer currentMounthSummary = 0;
        String currentMounthSummaryStr;
        for (Post day:allPosts){
            if (new SimpleDateFormat("MM.yyyy").format(new SimpleDateFormat("dd.MM.yyyy").parse(day.getDay())).equals(
                    new SimpleDateFormat("MM.yyyy").format(Calendar.getInstance().getTime()))){
                currentMounthSummary = currentMounthSummary + day.getSummary();
            }
        }
        currentMounthSummaryStr = currentMounthSummary.toString() + " ₽";
        return currentMounthSummaryStr;
    }

    //Выводит грязную прибыль за текущий месяц
    public String getCurrentMounthSummaryPlus (ArrayList<Post> allPosts) throws ParseException {
        Integer currentMounthSummary = 0;
        String currentMounthSummaryStr;
        for (Post day:allPosts){
            if (new SimpleDateFormat("MM.yyyy").format(new SimpleDateFormat("dd.MM.yyyy").parse(day.getDay())).equals(
                    new SimpleDateFormat("MM.yyyy").format(Calendar.getInstance().getTime()))){
                if (day.getSummary() > 0){
                    currentMounthSummary = currentMounthSummary + day.getSummary();
                }
            }
        }
        currentMounthSummaryStr = currentMounthSummary.toString() + " ₽";
        return currentMounthSummaryStr;
    }

    //Выводит убыток за текущий месяц
    public String getCurrentMounthSummaryMinus (ArrayList<Post> allPosts) throws ParseException {
        Integer currentMounthSummary = 0;
        String currentMounthSummaryStr;
        for (Post day:allPosts){
            if (new SimpleDateFormat("MM.yyyy").format(new SimpleDateFormat("dd.MM.yyyy").parse(day.getDay())).equals(
                    new SimpleDateFormat("MM.yyyy").format(Calendar.getInstance().getTime()))){
                if (day.getSummary() < 0){
                    currentMounthSummary = currentMounthSummary + day.getSummary();
                }
            }
        }
        currentMounthSummaryStr = currentMounthSummary.toString() + " ₽";
        return currentMounthSummaryStr;
    }

    //Выводит список месяцев из всех записей в базе данных
    public ArrayList<String> iterableToArrayListMouth (Iterable<Post> posts) throws ParseException {
        ArrayList<String> arrayListCurrentElementMouth = new ArrayList<>();
        for (Post currentElement:posts) {
            arrayListCurrentElementMouth.add(new SimpleDateFormat("MM.yyyy").format(
                    new SimpleDateFormat("dd.MM.yyyy").parse(currentElement.getDay())));
        }
        return arrayListCurrentElementMouth;
    }

    //Делает все даты из списка уникальными и сортирует их в порядке возрастания
    public List<String> getListPostsDateMouth (ArrayList<String> list) throws ParseException {
        Set<String> set = new HashSet<>(list);
        List<String> listPostsDate = new ArrayList<>(set);
        List<Date> dateList = new ArrayList<>();
        for (String lists:listPostsDate){
            dateList.add(new SimpleDateFormat("MM.yyyy").parse(lists));
        }
        Collections.sort(dateList);
        listPostsDate = new ArrayList<>();
        for (Date lists:dateList){
            listPostsDate.add(new SimpleDateFormat("MM.yyyy").format(lists));
        }
        return listPostsDate;
    }

    //Считает сумму прибыли всех записей за каждый месяц и помещает в массив объектов включающих номер месяца и Сумму за месяц
    public List<DateAndSummDate> getHomeCalendar (ArrayList<Post> allPosts, List<String> list)
            throws ParseException {
        Integer summ = 0;
        List<DateAndSummDate> listDaypost = new ArrayList<>();
        List<Integer> listPostDateSumm = new ArrayList<>();
        for (String date:list) {
            summ = 0;
            for (Post post:allPosts) {
                if (new SimpleDateFormat("MM.yyyy").format(
                        new SimpleDateFormat("dd.MM.yyyy").parse(post.getDay())).equals(date)){
                    summ = summ + post.getSummary();
                }
            }
            listPostDateSumm.add(summ);
        }
        for (int i = 0; i < listPostDateSumm.size(); i++){
            DateAndSummDate daypost = new DateAndSummDate(listPostDateSumm.get(i), list.get(i));
            listDaypost.add(daypost);
        }
        return listDaypost;
    }
}
