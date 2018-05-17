package com.yeta.sbl2.mapper;

import com.yeta.sbl2.pojo.SeckillSuccessed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MySeckillSuccessedMapper {

    List<SeckillSuccessed> findSeckillSuccessedBySeckillIdAndUsername(@Param("seckillId") Integer seckillId, @Param("username") String username);

    int insertSeckillSuccessed(SeckillSuccessed seckillSuccessed);
}