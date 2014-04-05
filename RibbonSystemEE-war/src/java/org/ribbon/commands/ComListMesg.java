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

import org.ribbon.enteties.Permission;
import org.ribbon.enteties.Directory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ribbon.beans.*;
import org.ribbon.service.Utils;

/**
 * LIST_MESG command class.
 * @author Stanislav Nepochatov
 */
public class ComListMesg implements ICommand {
    
    private UserBean usrBean;
    
    private DirectoryBean dirBean;
    
    private MessageBean mesgBean;
    
    private AccessChecker accBean;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accBean = (AccessChecker) Utils.getBean("java:global/RibbonSystemEE/RibbonSystemEE-ejb/AccessChecker!org.ribbon.beans.AccessChecker");
        usrBean = (UserBean) Utils.getBean("java:global/RibbonSystemEE/RibbonSystemEE-ejb/UserBean!org.ribbon.beans.UserBean");
        dirBean = (DirectoryBean) Utils.getBean("java:global/RibbonSystemEE/RibbonSystemEE-ejb/DirectoryBean!org.ribbon.beans.DirectoryBean");
        mesgBean = (MessageBean) Utils.getBean("java:global/RibbonSystemEE/RibbonSystemEE-ejb/MessageBean!org.ribbon.beans.MessageBean");
        if (request.getParameter("dirid") != null && request.getParameter("dirname") != null) {
            Permission.ACCESS_STATE resolved = accBean.check(usrBean.findByLogin(request.getSession().getAttribute("username").toString()), dirBean, request.getParameter("dirname"));
            if (resolved.ordinal() >= Permission.ACCESS_STATE.MAY_READ.ordinal()) {
                request.getSession().setAttribute("last_dir_name", request.getParameter("dirname"));
                request.getSession().setAttribute("last_dir", request.getParameter("dirid"));
                Directory dirId = dirBean.find(new Integer(request.getParameter("dirid")));
                request.setAttribute("mlist", mesgBean.findByDirIdSortId(dirId));
            }
            request.setAttribute("acc_mode", resolved.ordinal());
        }
        return Router.COM_LIST_MESG;
    }

    @Override
    public Boolean isAuthRequired() {
        return true;
    }
    
}
