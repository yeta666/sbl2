package com.yeta.sbl2.mapper;

import com.yeta.sbl2.pojo.Seckill;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;

public interface MySeckillMapper {

    List<Seckill> findAllSeckill();

    Seckill findById(Integer id);

    int reduceNumber(@Param("id") Integer id, @Param("killTime") Date killTime);
}