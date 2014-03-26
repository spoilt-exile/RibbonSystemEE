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
import org.ribbon.jpa.enteties.Message;
import org.ribbon.jpa.enteties.Directory;
import org.ribbon.jpa.JPAManager;
import javax.persistence.*;

/**
 * LIST_MESG command class.
 * @author Stanislav Nepochatov
 */
public class ComListMesg implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("dirid") != null && request.getParameter("dirname") != null) {
            request.getSession().setAttribute("last_dir_name", request.getParameter("dirname"));
            request.getSession().setAttribute("last_dir", request.getParameter("dirid"));
            EntityManager em = JPAManager.getEntityManager();
            TypedQuery qr = em.createNamedQuery("Message.findByDirIdSortId", Message.class);
            qr.setParameter("dirId", em.find(Directory.class, new Integer(request.getParameter("dirid"))));
            request.setAttribute("mlist", qr.getResultList());
            em.close();
        }
        return Router.COM_LIST_MESG;
    }

    @Override
    public Boolean isAuthRequired() {
        return true;
    }
    
}
