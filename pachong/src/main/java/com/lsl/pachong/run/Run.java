package com.lsl.pachong.run;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsl.demo.first.common.entity.LevelEntity;
import com.lsl.demo.first.common.entity.MovieEntity;
import com.lsl.demo.first.common.mapper.MovieMapper;
import com.lsl.demo.first.common.service.ILevelService;
import com.lsl.demo.first.common.service.IMovieService;
import com.lsl.demo.first.common.service.impl.MovieServiceImpl;
import com.lsl.demo.first.sys.entity.ArtistEntity;
import com.lsl.demo.first.sys.mapper.ArtistMapper;
import com.lsl.demo.first.sys.service.IArtistService;
import com.lsl.demo.first.sys.service.impl.ArtistServiceImpl;
import com.lsl.pachong.entity.Human;
import com.lsl.pachong.entity.MovieBean;
import com.lsl.pachong.entity.MovieInfoEntity;
import com.lsl.pachong.exceptions.PaChongException;
import com.lsl.pachong.server.UploadJpg;
import com.lsl.pachong.server.UploadJpgToMyLinux;
import com.lsl.pachong.utils.common.Usually;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
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

    @Autowired
    private ILevelService levelService;

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

    public void run(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Scanner fileIn = new Scanner(fileInputStream);
            int startPage = fileIn.nextInt();
            run(startPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(int startPage) {

        // test();
        int endPage = startPage + size;
        RunSearchSubjects runSearchSubjects = new RunSearchSubjects();
        while (startPage < endPage) {
            List<MovieBean> movieBeans = runSearchSubjects.getTwenty(startPage);
            for (MovieBean item : movieBeans) {
                if (Objects.isNull(getMovieFromDB(item.getUrl()))) {
                    MovieEntity movieEntity = new MovieEntity();
                    MovieInfoEntity movieInfoEntity = RunMovie.run(item.getUrl());
                    if (movieInfoEntity == null) {
                        Usually.print(item.getUrl());
                        throw new PaChongException("爬取数据失败");
                    }
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
                    LevelEntity levelEntity = new LevelEntity();
                    levelEntity.setMovieId(movieEntity.getId());
                    levelEntity.setUserId("admin");
                    levelEntity.setLevel(movieInfoEntity.getLevel());
                    levelService.save(levelEntity);
                    Usually.print(movieInfoEntity);
                }
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-5);
            }
            startPage += 20;
            try (FileWriter fileOut = new FileWriter("F:\\实习_2019\\stu\\spring_stu_Internet\\bisheDemo2\\pageNow.txt")) {
                fileOut.write(startPage+" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Usually.print("一次爬取结束");
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
                    e.printStackTrace();
                    System.out.println(artistEntity);
                    System.out.println(artistEntity.getProfile().length());
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
        String doubanId = t[t.length - 1];
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
