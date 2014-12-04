package org.tim.dbtester.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.zeal.common.persistence.PersistableObject;

public abstract class EntityService {

	@PersistenceContext
	public EntityManager em;

	@SuppressWarnings("unchecked")
	protected <E> List<E> findAll(Class<E> o) {

		try {
			E result;
			result = o.newInstance();
			Query q = em.createQuery("Select o from "
					+ ((PersistableObject) result).getMappedTableName() + " o",
					o);
			List<E> list = q.getResultList();
			return list;
		} catch (InstantiationException e) {
			PersistenceException pe = new PersistenceException("");
			throw pe;

		} catch (IllegalAccessException e) {
			PersistenceException pe = new PersistenceException("");
			throw pe;
		}

	}

}
