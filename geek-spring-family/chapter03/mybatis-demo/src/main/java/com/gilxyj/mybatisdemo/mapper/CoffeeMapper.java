package com.gilxyj.mybatisdemo.mapper;

import com.gilxyj.mybatisdemo.model.Coffee;

import java.util.List;

/**
 * @program: geektime-spring-family-master
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-18 13:08
 **/

public interface CoffeeMapper {

    int save(Coffee c);

    Coffee findById(Long id);

    List<Coffee> findAll();

}
