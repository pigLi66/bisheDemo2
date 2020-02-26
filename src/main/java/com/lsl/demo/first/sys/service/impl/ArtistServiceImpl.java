package com.lsl.demo.first.sys.service.impl;

import com.lsl.demo.first.sys.entity.ArtistEntity;
import com.lsl.demo.first.sys.mapper.ArtistMapper;
import com.lsl.demo.first.sys.service.IArtistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lsl_ja
 * @since 2020-02-26
 */
@Service
public class ArtistServiceImpl extends ServiceImpl<ArtistMapper, ArtistEntity> implements IArtistService {

}
