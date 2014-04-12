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

import org.ribbon.enteties.Message;
import org.ribbon.enteties.User;
import org.ribbon.enteties.Directory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ribbon.beans.*;
import org.ribbon.service.Utils;

/**
 * POST_MESG command class.
 * @author Stanislav Nepochatov
 * @deprecated Use managed beans instead. Will be removed soon.
 */
public class ComPostMesg implements ICommand {
    
    private UserBean usrBean;
    
    private DirectoryBean dirBean;
    
    private MessageBean mesgBean;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        usrBean = (UserBean) Utils.getBean("java:global/RibbonSystemEE/RibbonSystemEE-ejb/UserBean!org.ribbon.beans.UserBean");
        dirBean = (DirectoryBean) Utils.getBean("java:global/RibbonSystemEE/RibbonSystemEE-ejb/DirectoryBean!org.ribbon.beans.DirectoryBean");
        mesgBean = (MessageBean) Utils.getBean("java:global/RibbonSystemEE/RibbonSystemEE-ejb/MessageBean!org.ribbon.beans.MessageBean");
        Message posted = new Message();
        posted.setHeader(request.getParameter("header"));
        Directory findedDIr = dirBean.findByPath(request.getParameter("directory"));
        List<Directory> dirList = new ArrayList<Directory>();
        dirList.add(findedDIr);
        posted.setDirId(dirList);
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
