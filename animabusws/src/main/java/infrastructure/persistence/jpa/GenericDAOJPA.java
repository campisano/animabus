package infrastructure.persistence.jpa;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class GenericDAOJPA<T> {

	private static EntityManager entityManager;

	Logger logger = Logger.getLogger(GenericDAOJPA.class.getName());

	public GenericDAOJPA() {
		GenericDAOJPA.entityManager = new JPAUtil().getEntityManager();

	}

	public void create(T entity) {
		EntityTransaction tx = null;

		try {
			tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(entity);
			tx.commit();

		} catch (Exception ex) {

			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			throw new DAOException("Entity create error.", ex);
		}
	}

	public T read(Class<T> classType, Object id) throws DAOException {
		T entity = null;

		try {
			logger.info("Getting entity with id = " + id + " and class = "
					+ classType.getName());
			entity = entityManager.find(classType, id);

		} catch (RuntimeException ex) {

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
			return false;
		}
	}

	public void update(T entity) {
		EntityTransaction tx = null;

		try {
			tx = entityManager.getTransaction();
			tx.begin();
			entity = entityManager.merge(entity);
			tx.commit();

		} catch (Exception ex) {

			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			throw new DAOException("Entity update error.", ex);
		}
	}

	public void delete(Class<T> c, Object id) {
		EntityTransaction tx = null;

		try {
			tx = entityManager.getTransaction();
			tx.begin();
			T entidade = entityManager.find(c, id);
			entityManager.remove(entidade);
			tx.commit();

		} catch (Exception ex) {

			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			throw new DAOException("Entity delete error.", ex);
		}
	}
}
