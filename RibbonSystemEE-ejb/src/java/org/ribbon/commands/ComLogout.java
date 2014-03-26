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

package org.ribbon.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ribbon.controller.Router;
import javax.persistence.*;
import org.ribbon.jpa.JPAManager;
import org.ribbon.jpa.enteties.*;

/**
 * LOGOUT command (for exit from the system).
 * @author Stanislav Nepochatov
 */
public class ComLogout implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAManager.getEntityManager();
        TypedQuery<User> qr = em.createNamedQuery("User.findByLogin", User.class);
        qr.setParameter("login", request.getSession().getAttribute("username"));
        User findedUser = qr.getSingleResult();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        if (findedUser != null) {
            findedUser.setIsActive(false);
            request.getSession().removeAttribute("username");
        }
        tr.commit();
        em.close();
        return Router.DEFAULT_PAGE;
    }

    @Override
    public Boolean isAuthRequired() {
        return true;
    }
    
}
