package com.gilxyj.jpademo.repository;

import com.gilxyj.jpademo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee,Long> {
}
