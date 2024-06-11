package com.example.mongodb.dao;

import java.util.List;

import com.example.mongodb.domain.ScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
@Repository
public class ScoreMongoDAOImpl implements ScoreMongoDAO{
	//spring-data-mongodb에서 제공하는 라이브러리
	MongoTemplate mongoTemplate;
	//페이징처리와 소트를 쉽게 구현하기 위해서(spring-data-commons라이브러리에서 제공) - CRUD(CLRUD)를 위한 메소드를 제공
	ScoreRepository scoreRepository;//SimpleMongoRepository나 MongoRepository이용해서 작업
	
	@Autowired
	public ScoreMongoDAOImpl(MongoTemplate mongoTemplate,ScoreRepository scoreRepository) {
		super();
		this.mongoTemplate = mongoTemplate;
		this.scoreRepository = scoreRepository;
	}

	@Override
	public void insertDocument(ScoreDTO doc) {
		mongoTemplate.insert(doc);
	}

	@Override
	public List<ScoreDTO> findCriteria(String key, String value) {
		//1. 선택한 key에 따른 value가 정확하게 일치하는 document들을 검색
//		Criteria criteria = new Criteria(key);
//		criteria.is(value);
//		Query query = new Query(criteria);
		//2. key와 조건을 value함께 적용해보기
//		String[] data =  key.split(",");
//		Criteria criteria = new Criteria(data[0]);
//		if(data[1].equals("is")) {
//			criteria.is(value);
//		}else if(data[1].equals("gt")) {
//			criteria.gt(Integer.parseInt(value));
//		}
		
		//3. where메소드를 이용해서 작업하기
		String[] data =  key.split(",");
//		Query query = new Query();
//		query.addCriteria(Criteria.where(data[0]).is(value));
		//1-find의 조건 정보가 담긴 객체,2-document가 매핑될 객체,3-컬렉션명
		
		//4. 정규표현식으로 검색
		Criteria criteria = new Criteria(data[0]);
		//^ =>해당 필드가 ^다음의 문자열로 시작하는 데이터 => like 연산자와 동일 where dept like '인사%' 
		//criteria.regex("^"+value);
		criteria.regex(".*"+value+".*");//dept like '%사%'
		Query query = new Query(criteria);
		List<ScoreDTO> docs = mongoTemplate.find(query, ScoreDTO.class,"score");
		System.out.println("dao=>"+docs);
		return docs;
	}

	@Override
	public ScoreDTO findById(String key, String value) {
		//Criteria객체는 spring data mongodb에서 조건을 모델링한 객체
		//어떤 필드에 어떤 조건을 적용할 것인지 정의
		//필드명과 필드의 조건을 정의하면 내부에서 json의 쿼리조건을 만들어 처리
		//1. 조건을 객체로 정의
		Criteria criteria = new Criteria(key);
		//조건에 대한 설정
		criteria.is(value);
		
		//2.Criteria객체를 이용해서 Query를 생성
		Query query = new Query(criteria);
		ScoreDTO doc = mongoTemplate.findOne(query, ScoreDTO.class,"score");
		return doc;
	}

	@Override
	public void insertAllDocument(List<ScoreDTO> docs) {
		mongoTemplate.insertAll(docs);
		
	}

	@Override
	public void update(ScoreDTO document) {
		//조건(Criteria,Query) - 조건에 만족하는 document를 수정
		Criteria criteria = new Criteria("id");
		criteria.is(document.getId());
		Query query = new Query(criteria);
		
		//업데이트기능을 수행하는 객체를 생성하고 적절한 값을 셋팅
		Update update = new Update();
		update.set("addr", document.getAddr());
		update.set("java", document.getJava());
		mongoTemplate.updateMulti(query, update,"score");
	}

	@Override
	public void test1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ScoreDTO> findAll() {
		// TODO Auto-generated method stub
		System.out.println("====================================================");
		List<ScoreDTO> list = mongoTemplate.findAll(ScoreDTO.class,"score");
		System.out.println(list);
		return list;
	}

	@Override
	public List<ScoreDTO> findAll(int pageNo) {
		//PagingAndSortingRepository의 findAll메소드를 호출하면 페이징 처리된 객체를 전달받을 수 있다.
		//findAll메소드 내부에서 페이징 처리를 할 수 있도록 PageRequest객체를 이용해서 실행할 페이지의 번호와 한 페이지를 구성할 document
		//를 매개변수로 전달해야 한다.
		//Page객체는 한 페이지에 보여질 document들의 정보를 갖고 있는 객체
		Sort sort = Sort.by("property").ascending();
		Page<ScoreDTO> page=  scoreRepository.findAll(PageRequest.of(pageNo, 5,sort));
		//한페이지에 출력할 document꺼내기
		return page.getContent();
	}

}













