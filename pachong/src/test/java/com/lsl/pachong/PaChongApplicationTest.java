package com.lsl.pachong;


import com.lsl.pachong.run.Run;
import com.lsl.pachong.utils.common.Usually;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * @author lisiliang
 * @since 2020/2/25
 */
@SpringBootTest
public class PaChongApplicationTest {

    @Autowired
    private Run run;

    void test() throws Exception{
//        RunSearchSubjects runSearchSubjects = new RunSearchSubjects();
//        List<MovieBean> movieBeans =  runSearchSubjects.getTwenty(0);
//        MovieInfoEntity movieInfoEntity = RunMovie.run(movieBeans.get(1).getUrl());
//        RunArtist.run(movieInfoEntity.getDirectors().get(0).getUrl());
    }

    @Test
    void testPaChong() throws FileNotFoundException {
        int startPage;
        Scanner fileIn = new Scanner(new FileInputStream("F:\\实习_2019\\stu\\spring_stu_Internet\\bisheDemo2\\pageNow.txt"));
        startPage = fileIn.nextInt();
        run.setSize(40);
        Usually.print(startPage);

        run.run(startPage);
    }

}
