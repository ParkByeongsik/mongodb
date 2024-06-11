package com.example.mongodb.service;

import java.util.List;

import com.example.mongodb.dao.ScoreMongoDAO;
import com.example.mongodb.domain.ScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ScoreMongoServiceImpl implements ScoreMongoService {
	ScoreMongoDAO dao;
	@Autowired
	public ScoreMongoServiceImpl(ScoreMongoDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ScoreDTO findById(String key, String value) {
		// TODO Auto-generated method stub
		return dao.findById(key, value);
	}

	@Override
	public void insertDocument(ScoreDTO doc) {
		dao.insertDocument(doc);
	}

	@Override
	public void insertAllDocument(List<ScoreDTO> docs) {
		dao.insertAllDocument(docs);
	}

	@Override
	public List<ScoreDTO> findCriteria(String key, String value) {
		// TODO Auto-generated method stub
		return dao.findCriteria(key, value);
	}

	@Override
	public void update(ScoreDTO document) {
		dao.update(document);

	}

	@Override
	public void test1() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ScoreDTO> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<ScoreDTO> findAll(int pageNo) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNo);
	}

}
