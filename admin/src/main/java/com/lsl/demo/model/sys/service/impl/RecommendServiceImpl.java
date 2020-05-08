package com.lsl.demo.model.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.demo.common.base.entity.BaseEntity;
import com.lsl.demo.model.common.service.IMovieService;
import com.lsl.demo.model.common.service.impl.MovieServiceImpl;
import com.lsl.demo.model.sys.entity.RecommendEntity;
import com.lsl.demo.model.sys.mapper.RecommendMapper;
import com.lsl.demo.model.sys.service.IRecommendService;
import com.lsl.demo.model.sys.service.IUserService;
import com.lsl.demo.utils.global.BaseContextHandler;
import org.aspectj.util.SoftHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sun.misc.SoftCache;
import top.lslllxq.utils.print.Print;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lisiliang
 * @since 2020/4/2
 */
@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, RecommendEntity> implements IRecommendService {

    @Autowired
    private IMovieService movieService;
    @Autowired
    private IUserService userService;
    private Map<String, List<String>> recommendCache = new SoftHashMap<>();

    @Override
    public void tap(String movieId) {
        if (BaseContextHandler.isLogin() && this.movieIsValid(movieId)) {
            RecommendEntity entity = this.get(movieId);
            entity.setTapCount(entity.getTapCount() == null ? 1 : entity.getTapCount() + 1);
            this.baseMapper.updateById(entity);
        }
    }

    @Override
    public void comment(String movieId) {
        if (BaseContextHandler.isLogin() && this.movieIsValid(movieId)) {
            RecommendEntity entity = this.get(movieId);
            entity.setCommentCount(entity.getCommentCount() == null ? 1 : entity.getCommentCount() + 1);
            this.baseMapper.updateById(entity);
        }
    }

    @Override
    public List<String> listSortedUserId() {
        return null;
    }

    @Override
    public List<String> listSortedMovieId(String userId) {
        if (Objects.isNull(userId)) {
            return movieService.list().stream().map(BaseEntity::getId).collect(Collectors.toList());
        }
        return Optional.ofNullable(this.recommendCache.get(userId)).orElseGet(() -> {
            List<String> recommend = recommend(userId, userSimilarity());
            this.recommendCache.put(userId, recommend);
            return recommend;
        });
    }

    private List<String> recommend(String userId, Map<String, Map<String, Double>> worth) {
        Map<String, List<RecommendEntity>> train = this.list().stream().collect(Collectors.groupingBy(RecommendEntity::getUserId));
        Map<String, Double> rank = new HashMap<>(worth.size());
        // 与当前用户相关的电影列表
        List<String> interactedItems = train.get(userId).stream().map(RecommendEntity::getMovieId).collect(Collectors.toList());
        // 排序worth map
        List<Map.Entry<String, Double>> list = new ArrayList<>(worth.get(userId).entrySet());
        list.sort((entry1, entry2) -> (int) (entry2.getValue() - entry1.getValue()));
        // String 表示用户v,
        for (Map.Entry<String, Double> vWorth : list) {
            for (RecommendEntity recommendEntity : train.get(vWorth.getKey())) {
                if (interactedItems.contains(recommendEntity.getMovieId())) {
                    double w = rank.getOrDefault(recommendEntity.getMovieId(), 0.0);
                    rank.put(recommendEntity.getMovieId(), w + vWorth.getValue() * (recommendEntity.getTapCount() + recommendEntity.getCommentCount()));
                }
            }
        }
        return rank.entrySet().stream().sorted((entry1, entry2) -> (int) (entry2.getValue() - entry1.getValue())).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private Map<String, Map<String, Double>> userSimilarity() {
        List<RecommendEntity> list = this.list();
        Map<String, List<RecommendEntity>> train = list.stream().collect(Collectors.groupingBy(RecommendEntity::getUserId));
        Map<String, Map<String, Double>> rs = new HashMap<>(train.size());
        for (Map.Entry<String, List<RecommendEntity>> u : train.entrySet()) {
            Map<String, Double> map = new HashMap<>(train.size());
            rs.put(u.getKey(), map);
            for (Map.Entry<String, List<RecommendEntity>> v : train.entrySet()) {
                if (u.equals(v)) {
                    continue;
                }
                double value = 0;
                for (RecommendEntity uEntity : u.getValue()) {
                    for (RecommendEntity vEntity : v.getValue()) {
                        if (uEntity.getMovieId().equals(vEntity.getMovieId())) {
                            value += uEntity.getCommentCount() * vEntity.getCommentCount() +
                                    uEntity.getTapCount() * vEntity.getTapCount();
                            break;
                        }
                    }
                }
                value /= Math.sqrt(u.getValue().size() * v.getValue().size());
                map.put(v.getKey(), value);
            }
        }
        return rs;
    }

    private boolean movieIsValid(String movieId) {
        return Objects.nonNull(this.movieService.getById(movieId));
    }

    private RecommendEntity get(String movieId) {
        Optional<RecommendEntity> optionalRecommendEntity = Optional.ofNullable(this.baseMapper.selectOne(new QueryWrapper<RecommendEntity>().lambda()
                .eq(RecommendEntity::getUserId, BaseContextHandler.getUserId())
                .eq(RecommendEntity::getMovieId, movieId)));
        return optionalRecommendEntity.orElseGet(() -> {
            RecommendEntity entity = new RecommendEntity();
            entity.setUserId(BaseContextHandler.getUserId());
            entity.setMovieId(movieId);
            this.baseMapper.insert(entity);
            return entity;
        });
    }

}
