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

/**
 * Simple command interface.
 * @author Stanislav Nepochatov
 */
public interface Command {
    
    /**
     * Process command from controller.
     * @param request http request object;
     * @param response response object
     * @return redirect address;
     * @throws ServletException if some servlet functionality went wrong;
     * @throws IOException if response object broken;
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException;
    
    /**
     * Find out if auth required for execution this command;
     * @return true if user must be logined or false if anonymous can execute this command;
     */
    public Boolean isAuthRequired();
}
