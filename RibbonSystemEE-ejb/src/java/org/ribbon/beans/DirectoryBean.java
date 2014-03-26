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

package org.ribbon.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.ribbon.jpa.enteties.Directory;

/**
 * Directory entity bean.
 * @author Stanislav Nepochatov
 * @see org.ribbon.jpa.enteties.Directory
 */
@Stateless
public class DirectoryBean extends AbstractBean<Directory> {
    
    /**
     * Linked entity manager.
     */
    @PersistenceContext(unitName = "RibbonSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Default constructor.
     */
    public DirectoryBean() {
        super(Directory.class);
    }
    
    /**
     * Get all directories sorted by path (for listing);
     * @return list of directories;
     */
    public List<Directory> findAllSortByPath() {
        return this.getEntityManager().createNamedQuery("Directory.findAllSortPath", Directory.class).getResultList();
    }
    
    /**
     * Get directory by path.
     * @param path directory search path;
     * @return finded directory;
     */
    public Directory findByPath(String path) {
        EntityManager em = this.getEntityManager();
        TypedQuery<Directory> tr = em.createNamedQuery("Directory.findByPath", Directory.class);
        tr.setParameter("path", path);
        return tr.getSingleResult();
    }
    
}
