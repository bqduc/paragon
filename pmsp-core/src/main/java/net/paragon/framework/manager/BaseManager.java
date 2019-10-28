package net.paragon.framework.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import net.paragon.framework.entity.ObjectBase;
import net.paragon.framework.repository.BaseRepository;
import net.paragon.framework.service.RootService;
import net.paragon.utility.CommonConstants;


public abstract class BaseManager<T extends ObjectBase, PK extends Serializable> extends RootService <T, PK>{
	private static final long serialVersionUID = -1326030262778654331L;

	protected abstract BaseRepository<T, PK> getRepository();

	protected Page<T> performSearch(String keyword, Pageable pageable){
		return DUMMY_PAGEABLE;
	}

	protected Page<T> getPaginatedObjects(Integer page, Integer size){
    PageRequest pageRequest = PageRequest.of(page-1, size, Sort.Direction.ASC, "id");
    return getRepository().findAll(pageRequest);
	}

	public Page<T> getList(Integer pageNumber) {
		return getPaginatedObjects(pageNumber, CommonConstants.DEFAULT_PAGE_SIZE);
	}

	public Page<T> getList(Integer pageNumber, Integer size) {
		return getPaginatedObjects(pageNumber, size);
	}

	public T save(T entity) {
		return getRepository().save(entity);
	}

	public T create(T entity) {
		return getRepository().save(entity);
	}

	public T get(PK id) {
		T entity = getRepository().getOne(id);
		return entity;
	}

	public T getById(PK id) {
		return get(id);
	}

	public void delete(PK id) {
		try {
			//getRepository().delete(id);
		} catch (EmptyResultDataAccessException e) {
			log.error("Delete object by key", e);
		}
	}

	public void delete(T entity) {
		try {
			getRepository().delete(entity);
		} catch (EmptyResultDataAccessException e) {
			log.error("Delete object. ", e);
		}
	}

	public void deleteAll() {
		try {
			getRepository().deleteAll();
		} catch (EmptyResultDataAccessException e) {
			log.error("Delete all objects. ", e);
		}
	}

	
	public T update(T entity) {
		T getEntity = getRepository().getOne((PK) entity.getId());
		getRepository().save(entity);
		log.info("Merged entity: " + getEntity.getId());
		return entity;
	}

	public long count() {
		return getRepository().count();
	}

	@Transactional(readOnly = true)
	public List<T> getAll() {
		List<T> results = new ArrayList<>();
		getRepository().findAll().forEach(results::add);
		return results;
	}
	
	@Transactional(readOnly = true)
	public Page<T> search(String keyword, Pageable pageable) {
		return performSearch(keyword, pageable);
	}
}