package com.lsl.pachong;


import com.lsl.demo.first.common.entity.MovieEntity;
import com.lsl.demo.first.common.service.IMovieService;
import com.lsl.demo.first.sys.entity.ArtistEntity;
import com.lsl.demo.first.sys.service.IArtistService;
import com.lsl.pachong.run.Run;
import com.lsl.pachong.utils.common.Usually;
import com.lsl.pachong.utils.string.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;


/**
 * @author lisiliang
 * @since 2020/2/25
 */
@SpringBootTest
public class PaChongApplicationTest {

    @Autowired
    private Run run;

    @Autowired
    private IArtistService artistService;

    @Autowired
    private IMovieService movieService;

    void test() throws Exception {
//        RunSearchSubjects runSearchSubjects = new RunSearchSubjects();
//        List<MovieBean> movieBeans =  runSearchSubjects.getTwenty(0);
//        MovieInfoEntity movieInfoEntity = RunMovie.run(movieBeans.get(1).getUrl());
//        RunArtist.run(movieInfoEntity.getDirectors().get(0).getUrl());
    }

    @Test
    void testPaChong() throws Exception {
        int startPage;
        Scanner fileIn = new Scanner(new FileInputStream("F:\\实习_2019\\stu\\spring_stu_Internet\\bisheDemo2\\pageNow.txt"));
        startPage = fileIn.nextInt();
        run.setSize(40);
        Usually.print(startPage);
        run.run(startPage);
    }

    @Test
    void trimDB() {
        List<ArtistEntity> artistEntityList = this.artistService.list();
        artistEntityList.forEach(item -> item.setProfile(StringUtil.trimCN(item.getProfile())));
        artistService.updateBatchById(artistEntityList);
        artistEntityList.forEach(System.out::println);

        List<MovieEntity> movieEntityList = this.movieService.getAllMovieList();
        movieEntityList.forEach(item -> item.setProfile(StringUtil.trimCN(item.getProfile())));
        movieEntityList.forEach(System.out::println);
        movieService.updateBatchById(movieEntityList);
    }

}
