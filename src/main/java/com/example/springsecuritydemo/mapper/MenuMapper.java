package com.example.springsecuritydemo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-08-01 11:54
 */
@Mapper
@DS("second")
public interface MenuMapper {

    List<String> selectPermissionByUid(@Param("uid") String uid);
}
