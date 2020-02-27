package com.lsl.pachong.run;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.first.common.entity.MovieEntity;
import com.lsl.demo.first.common.mapper.MovieMapper;
import com.lsl.demo.first.common.service.IMovieService;
import com.lsl.demo.first.common.service.impl.MovieServiceImpl;
import com.lsl.demo.first.sys.entity.ArtistEntity;
import com.lsl.demo.first.sys.mapper.ArtistMapper;
import com.lsl.demo.first.sys.service.IArtistService;
import com.lsl.demo.first.sys.service.impl.ArtistServiceImpl;
import com.lsl.pachong.entity.Human;
import com.lsl.pachong.entity.MovieBean;
import com.lsl.pachong.entity.MovieInfoEntity;
import com.lsl.pachong.server.UploadJpg;
import com.lsl.pachong.server.UploadJpgToMyLinux;
import com.lsl.pachong.utils.common.Usually;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lisiliang
 * @since 2020/2/26
 */
@Data
@Component
public class Run {

    @Autowired
    private IArtistService artistService;

    @Autowired
    private IMovieService movieService;

    @Autowired
    private ApplicationContext applicationContext;

    public Run() {
    }

    private int size = 200;

    Set<String> artists = new HashSet<>();

    public void run() {
        run(0);
    }

    private void test() {
        Usually.printCutLine();
        System.out.println(movieService);
        System.out.println(artistService);
        Usually.printCutLine();

        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String str : beans) {
            System.out.println(str);
            System.out.println(applicationContext.getBean(str));
        }
        Usually.printCutLine();

        Assert.notNull(artistService);
        Assert.notNull(artistService.getBaseMapper());
        Usually.printCutLine();

    }

    public void run(int startPage) throws InterruptedException {

        // test();

        int endPage = startPage + size;
        RunSearchSubjects runSearchSubjects = new RunSearchSubjects();
        while (startPage < endPage) {
            List<MovieBean> movieBeans = runSearchSubjects.getTwenty(startPage);
            for (MovieBean item : movieBeans) {
                if (Objects.isNull(getMovieFromDB(item.getUrl()))) {
                    MovieEntity movieEntity = new MovieEntity();
                    MovieInfoEntity movieInfoEntity = RunMovie.run(item.getUrl());
                    movieEntity.setDirectorIds(String.join(",", saveArtists(movieInfoEntity.getDirectors())));
                    movieEntity.setPerformerIds(String.join(",", saveArtists(movieInfoEntity.getPerformers())));
                    movieEntity.setClassIds(movieInfoEntity.getClassId());
                    movieEntity.setSpan(movieInfoEntity.getSpan());
                    movieEntity.setProfile(movieInfoEntity.getProfile());
                    movieEntity.setPictureUrl(UploadJpgToMyLinux.uploadByURL(item.getCover()));
                    movieEntity.setReleaseTime(movieInfoEntity.getReleaseTime());
                    movieEntity.setMovieName(item.getTitle());
                    movieEntity.setDoubanId(item.getId());
                    this.movieService.getBaseMapper().insert(movieEntity);
                }
            }
            Thread.sleep(10000);
            startPage += 20;
        }
    }

    private List<String> saveArtists(List<Human> humans) {
        List<String> rs = new ArrayList<>(humans.size());
        for (Human human : humans) {
            String t = getArtistFromDB(human.getUrl());
            if (Objects.nonNull(t)) {
                rs.add(t);
            } else {
                if (human.getUrl() == null) {
                    System.out.println(human);
                    continue;
                }
                ArtistEntity artistEntity = RunArtist.run(human.getUrl());
                artistEntity.setPictureUrl(UploadJpgToMyLinux.uploadByURL(artistEntity.getPictureUrl()));
                try {
                    artistService.getBaseMapper().insert(artistEntity);
                } catch (Exception e) {
                    System.out.println(artistEntity);
                    System.out.println(artistEntity.getProfile().length());
                    System.exit(-3);
                }
                rs.add(artistEntity.getId());
            }
        }
        return rs;
    }

    private String getMovieFromDB(String urlPath) {
        String[] t = urlPath.split("/");
        String doubanId = t[t.length - 1];
        QueryWrapper<MovieEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("douban_id", doubanId);
        MovieEntity movieEntity = movieService.getBaseMapper().selectOne(queryWrapper);
        if (Objects.isNull(movieEntity)) {
            return null;
        } else {
            return movieEntity.getId();
        }
    }

    private String getArtistFromDB(String urlPath) {
        String[] t = urlPath.split("/");
        String doubanId = t[t.length -1];
        QueryWrapper<ArtistEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("douban_id", doubanId);
        ArtistEntity artistEntity = artistService.getBaseMapper().selectOne(queryWrapper);
        if (Objects.isNull(artistEntity)) {
            return null;
        } else {
            return artistEntity.getId();
        }
    }

}
