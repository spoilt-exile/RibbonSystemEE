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
import org.ribbon.dao.mysql.*;
import org.ribbon.enteties.*;

/**
 * VIEW_MESG command class.
 * @author Stanislav Nepochatov
 */
public class ComViewMesg implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message vMessage = MySqlDAOFactory.getNewInstance().getNewDaoMessageInstance().getMessageById(new Integer(request.getParameter("id")));
        request.setAttribute("mesg", vMessage);
        User author = MySqlDAOFactory.getNewInstance().getNewIDaoUserInstance().getUserById(vMessage.getAuthId());
        request.setAttribute("auth", author);
        return Router.COM_VIEW_MESG;
    }

    @Override
    public Boolean isAuthRequired() {
        return true;
    }
    
}
