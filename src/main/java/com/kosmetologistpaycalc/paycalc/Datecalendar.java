package com.kosmetologistpaycalc.paycalc;

import com.kosmetologistpaycalc.paycalc.Models.Daypost;
import com.kosmetologistpaycalc.paycalc.Models.Post;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Datecalendar {
    public Daypost daypost = new Daypost();

    public ArrayList<Date> iterableToArrayListDay (Iterable<Post> posts){
        ArrayList<Date> arrayListCurrentElement = new ArrayList<>();
        for (Post currentElement:posts) {
            arrayListCurrentElement.add(currentElement.getDate());
        }
        return arrayListCurrentElement;
    }

    public List<Date> getListPostsDate (ArrayList<Date> list){
        Set<Date> set = new HashSet<Date>(list);
        List<Date> listPostsDate = new ArrayList<Date>(set);
        return listPostsDate;
    }

    public Iterable<Date> ListToIterable (List<Date> list){
        Iterable<Date> iterableCurrentElement = list;
        return iterableCurrentElement;
    }

    public ArrayList<Post> iterableToArrayListSumm (Iterable<Post> posts){
        ArrayList<Post> allPosts = new ArrayList<>();
        for (Post currentElement:posts) {
            allPosts.add(currentElement);
        }
        return allPosts;
    }

    public List<Daypost> getListPostDateSumm (ArrayList<Post> allPosts, List<Date> listPostsDate){
        Integer summ = 0;
        List<Daypost> listDaypost = new ArrayList<>();
        List<Integer> listPostDateSumm = new ArrayList<>();
        for (Date date:listPostsDate) {
            summ = 0;
            for (Post post:allPosts) {
                if (post.getDate().equals(date)){
                    summ = summ + post.getSummary();
                }
            }
            listPostDateSumm.add(summ);
        }
        for (int i = 0; i < listPostsDate.size(); i++){
            Daypost daypost = new Daypost(listPostDateSumm.get(i), listPostsDate.get(i));
            listDaypost.add(daypost);
        }
        return listDaypost;
    }

    public Iterable<Daypost> ListToIterableInt (List<Daypost> list){
        Iterable<Daypost> iterableCurrentElement = list;
        return iterableCurrentElement;
    }
}
