package edu.vn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractEntityDao<T> {
	private Class<T> entityClass;

	public AbstractEntityDao(Class<T> cls) {
		this.entityClass = cls;
	}

	public void insert(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	public void update(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.merge(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	public void delete(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			T entity = em.find(entityClass, id);

			em.remove(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
	}

	// T la 1 kieu du lieu nao do
	// tim kiem theo id
	public T findById(Object id) {
		EntityManager em = JpaUtils.getEntityManager();
		T entity = em.find(entityClass, id);
		return entity;

	}

	// Tra ve tat ca entity
	public List<T> findAll() {
		EntityManager em = JpaUtils.getEntityManager();

		// cho phep xay dung truy van
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();

			cq.select(cq.from(entityClass));
			return em.createQuery(cq).getResultList();// tra ve danh sach tim thay duoc
		} finally {
			em.close();
		}
	}

	//xac dinh cac gia tri phan trang
	public List<T> findAll(boolean all, int firstResult, int maxResult) {
		EntityManager em = JpaUtils.getEntityManager();

		// cho phep xay dung truy van
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Query q = em.createQuery(cq);
			if (!all) {
				cq.select(cq.from(entityClass));
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	
	//dem tra va ve ca entity
	public Long count() {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<T> rt = cq.from(entityClass);
			cq.select(em.getCriteriaBuilder().count(rt));

			Query q = em.createQuery(cq);
			return (Long) q.getSingleResult();
		} finally {
			em.close();
		}
	}

}
