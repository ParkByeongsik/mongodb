package com.example.mongodb.dao;

import com.example.mongodb.domain.ScoreDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
//spring-data내부에서 자동으로 하위객체를 생성하고 코드를 만들고 mongodb에서 데이터를 조회해서 매핑시킨다.
public interface ScoreRepository extends PagingAndSortingRepository<ScoreDTO,String> {
}
