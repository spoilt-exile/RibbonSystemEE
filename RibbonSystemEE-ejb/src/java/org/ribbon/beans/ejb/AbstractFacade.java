/*
 * Copyright (C) 2014 Stanislav Nepochatov
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.ribbon.beans.ejb;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Abstract entity bean.
 * @author Stanislav Nepochatov
 */
public abstract class AbstractFacade<T> {
    
    /**
     * Internal type of bean.
     */
    private Class<T> entityClass;

    /**
     * Constructor of bean.
     * @param entityClass class to init;
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Get entity manager for this bean.
     * @return entity manager;
     */
    protected abstract EntityManager getEntityManager();

    /**
     * Create entity reference in database (same as INSERT);
     * @param entity object to store;
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Edit entity reference in database (same as UPDATE);
     * @param entity object to store;
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Remove entity reference in database ()
     * @param entity object to store;
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Find entity by it's id;
     * @param id number to search;
     * @return finded entity or null;
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Find all beans;
     * @return list with all enteties with specified by generic type;
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Find all beans with specified range.
     * @param range array range of results (same as LIMIT);
     * @return ranged list with enteties;
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Get count of enteties;
     * @return total count of entities with specified type;
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
