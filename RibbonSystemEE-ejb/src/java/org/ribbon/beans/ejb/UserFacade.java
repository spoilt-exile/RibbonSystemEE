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

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.ribbon.enteties.User;

/**
 * Message entity bean.
 * @author Stanislav Nepochatov
 * @see org.ribbon.jpa.enteties.Message
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {
    
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
    public UserFacade() {
        super(User.class);
    }

    @Override
    public User findByLogin(String login) {
        EntityManager em = this.getEntityManager();
        TypedQuery<User> tr = em.createNamedQuery("User.findByLogin", User.class);
        tr.setParameter("login", login);
        try {
            return tr.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void performLogin(User logined) {
        logined.setLogDate(new Date());
        logined.setIsActive(true);
        this.edit(logined);
    }

    @Override
    public void performLogout(User logined) {
        logined.setIsActive(false);
        this.edit(logined);
    }
}
