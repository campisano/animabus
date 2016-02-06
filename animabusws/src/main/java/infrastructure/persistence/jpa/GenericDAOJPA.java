package infrastructure.persistence.jpa;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenericDAOJPA<T> {

	@PersistenceContext
	public EntityManager entityManager;

	Logger logger = Logger.getLogger(GenericDAOJPA.class.getName());

	public GenericDAOJPA() {
	}
	
	@Transactional
	public void create(T entity) {
		try {
			entityManager.persist(entity);
		} catch (Exception ex) {
			logger.warning(ex.toString());
			throw new DAOException("Entity create error.", ex);
		}
	}

	@Transactional
	public T read(Class<T> classType, Object id) throws DAOException {
		T entity = null;

		try {
			logger.info("Getting entity with id = " + id + " and class = "
					+ classType.getName());
			entity = entityManager.find(classType, id);

		} catch (RuntimeException ex) {
			logger.warning(ex.toString());
			throw new DAOException("Entity retreive error.", ex);
		}

		return entity;
	}

	public List<T> readAll(Class<T> classType) {
		logger.info("Getting all entities with class = " + classType.getName());

		String entityName = classType.getName().substring(
				classType.getName().lastIndexOf('.') + 1);

		return getEntities("SELECT e FROM " + entityName + " e");
	}

	@Transactional
	protected List<T> getEntities(String queryString,
			final Object... positionalParams) {
		Query query = entityManager.createQuery(queryString);
		int i = 0;

		for (Object p : positionalParams) {
			query.setParameter(++i, p);
		}

		@SuppressWarnings("unchecked")
		List<T> entities = query.getResultList();

		return entities;
	}

	@Transactional
	protected T getEntity(String queryString, final Object... positionalParams) {
		Query query = entityManager.createQuery(queryString);
		int i = 0;

		for (Object p : positionalParams) {
			query.setParameter(++i, p);
		}

		@SuppressWarnings("unchecked")
		T entity = (T) query.getSingleResult();

		return entity;
	}

	protected boolean hasEntity(String queryString,
			final Object... positionalParams) {
		try {
			getEntity(queryString, positionalParams);

			return true;
		} catch (NoResultException ex) {
			logger.warning(ex.toString());
			return false;
		}
	}

	@Transactional
	public void update(T entity) {
		try {
			entity = entityManager.merge(entity);
		} catch (Exception ex) {
			logger.warning(ex.toString());
			throw new DAOException("Entity update error.", ex);
		}
	}

	@Transactional
	public void delete(Class<T> c, Object id) {
		try {
			T entidade = entityManager.find(c, id);
			entityManager.remove(entidade);

		} catch (Exception ex) {
			logger.warning(ex.toString());
			throw new DAOException("Entity delete error.", ex);
		}
	}
}
