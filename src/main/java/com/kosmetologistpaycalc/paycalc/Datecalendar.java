package com.kosmetologistpaycalc.paycalc;

import com.kosmetologistpaycalc.paycalc.Models.Daypost;
import com.kosmetologistpaycalc.paycalc.Models.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Datecalendar {
    public Daypost daypost = new Daypost();

    public ArrayList<String> iterableToArrayListDay (Iterable<Post> posts){
        ArrayList<String> arrayListCurrentElement = new ArrayList<>();
        for (Post currentElement:posts) {
            arrayListCurrentElement.add(currentElement.getDay());
        }
        return arrayListCurrentElement;
    }

    public List<String> getListPostsDate (ArrayList<String> list){
        Set<String> set = new HashSet<String>(list);
        List<String> listPostsDate = new ArrayList<String>(set);
        return listPostsDate;
    }

    public Iterable<String> ListToIterable (List<String> list){
        Iterable<String> iterableCurrentElement = list;
        return iterableCurrentElement;
    }

    public ArrayList<Post> iterableToArrayListSumm (Iterable<Post> posts){
        ArrayList<Post> allPosts = new ArrayList<>();
        for (Post currentElement:posts) {
            allPosts.add(currentElement);
        }
        return allPosts;
    }

    public List<Daypost> getListPostDateSumm (ArrayList<Post> allPosts, List<String> listPostsDate){
        Integer summ = 0;
        List<Daypost> listDaypost = new ArrayList<>();
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
