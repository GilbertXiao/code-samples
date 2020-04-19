package com.gilxyj.jpademo.repository;

import com.gilxyj.jpademo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * @program: chapter03
 * @description:
 * @author: GilbertXiao
 * @create: 2020-04-19 01:46
 **/
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {

}
