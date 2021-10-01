package com.kosmetologistpaycalc.paycalc.Controllers;

import com.kosmetologistpaycalc.paycalc.Models.Post;
import com.kosmetologistpaycalc.paycalc.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Sort {

    @Autowired
    private PostRepository postRepository;

    public Sort(Integer[] sortmassive, Model model){
        sorting(sortmassive, model);
    }

    public Sort(){}

    public void sorting(Integer[] sortmassive, Model model) {
        for (int i=0; i < sortmassive.length; i++){
            if ((i == 0 | i == 4 | i == 8 | i == 12 | i ==16 | i == 20) & sortmassive[i] > 0) {
                String procedures = "Губы";
                String summary_type = "Доход";
                String day = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
                Integer summary = sortmassive[i];
                Post post = new Post(summary, procedures, summary_type, day);
                postRepository.save(post);
            }
            if ((i == 1 | i == 5 | i == 9 | i == 13 | i ==17 | i == 21) & sortmassive[i] > 0) {
                String procedures = "Ботокс";
                String summary_type = "Доход";
                String day = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
                Integer summary = sortmassive[i];
                Post post = new Post(summary, procedures, summary_type, day);
                postRepository.save(post);
            }
            if ((i == 2 | i == 6 | i == 10 | i == 14 | i ==18 | i == 22) & sortmassive[i] > 0) {
                String procedures = "Чистка";
                String summary_type = "Доход";
                String day = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
                Integer summary = sortmassive[i];
                Post post = new Post(summary, procedures, summary_type, day);
                postRepository.save(post);
            }
            if ((i == 3 | i == 7 | i == 11 | i == 15 | i ==19 | i == 23) & sortmassive[i] > 0) {
                String procedures = "Предоплата";
                String summary_type = "Доход";
                String day = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
                Integer summary = sortmassive[i];
                Post post = new Post(summary, procedures, summary_type, day);
                postRepository.save(post);
            }
        }
    }
}
