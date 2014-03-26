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
import org.ribbon.jpa.enteties.User;
import org.ribbon.service.Utils;
import java.util.Date;
import javax.persistence.*;

/**
 * LOGIN command class.
 * @author Stanislav Nepochatov
 */
public class ComLogin implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = Persistence.createEntityManagerFactory("RibbonSystemPU").createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        TypedQuery<User> qr = em.createNamedQuery("User.findByLogin", User.class);
        qr.setParameter("login", request.getParameter("login"));
        User findedUser = qr.getSingleResult();
        if (findedUser == null) {
            response.addHeader("login_error", "NOT_FOUND_OR_INCORRECT_PASSWD " + request.getParameter("login"));
            return Router.DEFAULT_PAGE;
        }
        if (findedUser.getPassw().equals(Utils.getHash(request.getParameter("passw")))) {
            request.getSession().setAttribute("username", findedUser.getLogin());
            findedUser.setIsActive(true);
            findedUser.setLogDate(new Date());
            tr.commit();
            em.close();
            return Router.MAIN_PAGE;
        } else {
            em.close();
            response.addHeader("login_error", "NOT_FOUND_OR_INCORRECT_PASSWD " + request.getParameter("login"));
            return Router.DEFAULT_PAGE;
        }
    }

    @Override
    public Boolean isAuthRequired() {
        return false;
    }
    
}
