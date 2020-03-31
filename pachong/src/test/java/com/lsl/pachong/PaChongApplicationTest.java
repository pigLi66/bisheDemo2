package com.lsl.pachong;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.model.common.entity.CommentCollectEntity;
import com.lsl.demo.model.common.entity.MovieEntity;
import com.lsl.demo.model.common.service.ICommentCollectService;
import com.lsl.demo.model.common.service.IMovieService;
import com.lsl.demo.model.sys.entity.ArtistEntity;
import com.lsl.demo.model.sys.service.IArtistService;
import com.lsl.pachong.run.Run;
import com.lsl.pachong.utils.common.Usually;
import com.lsl.pachong.utils.string.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import top.lslllxq.utils.print.Print;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @author lisiliang
 * @since 2020/2/25
 */
@Profile("dev")
@SpringBootTest
public class PaChongApplicationTest {

    @Autowired
    private Run run;

    @Autowired
    private IArtistService artistService;

    @Autowired
    private IMovieService movieService;

    @Autowired
    private ICommentCollectService commentCollectService;

    @Test
    void test() throws Exception {
        int[] arr = new int[1];
        Class clazz = arr.getClass();
        Print.print(clazz.getName());
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();

        Print.printLine();
        Print.print(arr.getClass().getSuperclass().getName());
        Arrays.stream(constructors).map(Constructor::getName).forEach(System.out::println);
        Print.printLine();
        Arrays.stream(methods).map(Method::getName).forEach(System.out::println);
        Print.printLine();
        Arrays.stream(fields).map(Field::getName).forEach(System.out::println);
        Print.printLine();
//        RunSearchSubjects runSearchSubjects = new RunSearchSubjects();
//        List<MovieBean> movieBeans =  runSearchSubjects.getTwenty(0);
//        MovieInfoEntity movieInfoEntity = RunMovie.run(movieBeans.get(1).getUrl());
//        RunArtist.run(movieInfoEntity.getDirectors().get(0).getUrl());
    }

    @Test
    void testPaChong() throws Exception {
        char c = '\0';
        System.out.println("\'\\0\' = " + (int) c);
        int startPage;
        Scanner fileIn = new Scanner(new FileInputStream("F:\\实习_2019\\stu\\spring_stu_Internet\\bisheDemo2\\pageNow.txt"));
        startPage = fileIn.nextInt();
        run.setSize(40);
        Usually.print(startPage);
        run.run(startPage);
    }

    @Test
    void initCollectDB() {
        List<MovieEntity> movieEntityList = this.movieService.list();
        movieEntityList.forEach(commentCollectService::initByMovie);
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
