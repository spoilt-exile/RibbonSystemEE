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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ribbon.jpa.enteties.*;
import org.ribbon.beans.*;
import javax.ejb.EJB;

/**
 * POST_MESG command class.
 * @author Stanislav Nepochatov
 */
public class ComPostMesg implements Command {
    
    @EJB
    private UserBean usrBean;
    
    @EJB
    private DirectoryBean dirBean;
    
    @EJB
    private MessageBean mesgBean;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message posted = new Message();
        posted.setHeader(request.getParameter("header"));
        Directory findedDIr = dirBean.findByPath(request.getParameter("directory"));
        posted.setDirId(findedDIr);
        posted.setPostDate(new Date());
        User findedUser = usrBean.findByLogin(request.getSession().getAttribute("username").toString());
        posted.setAuthId(findedUser);
        posted.setIsUrgent("urgent".equals(request.getParameter("urgent")));
        posted.setBody(request.getParameter("body"));
        mesgBean.create(posted);
        return "/Ribbon?command=LIST_MESG&dirid=" + request.getSession().getAttribute("last_dir") + "&dirname=" + request.getSession().getAttribute("last_dir_name");
    }

    @Override
    public Boolean isAuthRequired() {
        return true;
    }
    
}
