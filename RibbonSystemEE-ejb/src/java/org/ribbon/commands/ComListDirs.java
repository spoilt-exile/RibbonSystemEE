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
import java.util.List;
import org.ribbon.jpa.JPAManager;
import org.ribbon.jpa.enteties.Directory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.*;
import org.ribbon.controller.Router;

/**
 * LIST_DIRS command class.
 * @author Stanislav Nepochatov
 */
public class ComListDirs implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAManager.getEntityManager();
        TypedQuery qr = em.createNamedQuery("Directory.findAllSortPath", Directory.class);
        List<Directory> dirs = qr.getResultList();
        em.close();
        request.setAttribute("dirs", dirs);
        return Router.COM_LIST_DIRS;
    }

    @Override
    public Boolean isAuthRequired() {
        return true;
    }
    
}
