package com.kosmetologistpaycalc.paycalc;

import com.kosmetologistpaycalc.paycalc.Models.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Datecalendar {
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
//
//    public List<Post> getListPostDateSumm (ArrayList<Post> allPosts, List<String> listPostsDate){
//        List<Post> listPostDateSumm = new ArrayList<Post>();
//        for (int j = 0; j <= listPostsDate.size(); j++) {
//            for (int i = 0; i <= allPosts.size(); i++) {
//                if (allPosts.get(i).getDay().equals(listPostsDate.get(j)))
//                    listPostDateSumm.set(i, ) = allPosts.get(i).getSummary();
//            }
//        }
//        return listPostDateSumm;
//    }
}