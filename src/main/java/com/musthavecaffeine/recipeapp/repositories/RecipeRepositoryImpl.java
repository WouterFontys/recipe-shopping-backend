//package com.musthavecaffeine.recipeapp.repositories;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import com.musthavecaffeine.recipeapp.domain.Recipe;
//
////@Repository
//public class RecipeRepositoryImpl implements RecipeRepository {
//
//	@PersistenceContext	
//	private EntityManager entityManager;
//	
//	@Override
//	public List<Recipe> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Recipe> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Recipe> findAllById(Iterable<Long> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Recipe> List<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void flush() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public <S extends Recipe> S saveAndFlush(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteInBatch(Iterable<Recipe> entities) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAllInBatch() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Recipe getOne(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Recipe> List<S> findAll(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Recipe> List<S> findAll(Example<S> example, Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Page<Recipe> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Recipe> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean existsById(Long id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(Long id) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void delete(Recipe entity) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends Recipe> entities) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public <S extends Recipe> Optional<S> findOne(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Recipe> Page<S> findAll(Example<S> example, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Recipe> long count(Example<S> example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public <S extends Recipe> boolean exists(Example<S> example) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	@Override
//	public Recipe findByName(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<Recipe> findById(Long id) {
//		String hql = "FROM recipe";
//		return null;
//	}
//
//}


