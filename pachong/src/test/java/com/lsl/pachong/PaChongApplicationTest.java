package com.lsl.pachong;

import com.lsl.pachong.entity.MovieBean;
import com.lsl.pachong.entity.MovieInfoEntity;
import com.lsl.pachong.run.Run;
import com.lsl.pachong.run.RunArtist;
import com.lsl.pachong.run.RunMovie;
import com.lsl.pachong.run.RunSearchSubjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void testPaChong() {
        run.setSize(20);
        run.run();
    }

}
