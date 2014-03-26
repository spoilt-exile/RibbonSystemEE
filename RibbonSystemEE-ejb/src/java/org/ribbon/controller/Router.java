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

package org.ribbon.controller;

/**
 * Router table class.
 * @author Stanislav Nepochatov
 */
public final class Router {
    
    /**
     * Generic route for ordinary user pages.
     */
    public static final String GENERIC_PAGE_DIR = "/WEB-INF/pages/";
    
    /**
     * Generic route for administrator pages.
     */
    public static final String ADMIN_PAGE_DIR = "/WEB-INF/admin-pages/";
    
    /**
     * Default route (should be used if login failed).
     */
    public static final String DEFAULT_PAGE = "/login.jsp";
    
    /**
     * Main user UI route (use this to redirect logined users).
     */
    public static final String MAIN_PAGE = GENERIC_PAGE_DIR + "main.jsp";
    
    /**
     * Generic error page route.
     */
    public static final String ERROR_PAGE = GENERIC_PAGE_DIR + "error.jsp";
    
    /**
     * LIST_DIR command page.
     */
    public static final String COM_LIST_DIRS = GENERIC_PAGE_DIR + "listdirs.jsp";
    
    /**
     * HEADER command page.
     */
    public static final String COM_HEADER = GENERIC_PAGE_DIR + "header.jsp";
    
    /**
     * LIST_MESG command page.
     */
    public static final String COM_LIST_MESG = GENERIC_PAGE_DIR + "listmesg.jsp";
    
    /**
     * POST_FORM command page.
     */
    public static final String COM_POST_FORM = GENERIC_PAGE_DIR + "postform.jsp";
    
    /**
     * VIEW_MESG command page.
     */
    public static final String COM_VIEW_MESG = GENERIC_PAGE_DIR + "viewmesg.jsp";
    
}
