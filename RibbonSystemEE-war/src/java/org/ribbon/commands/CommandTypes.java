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

import org.ribbon.commands.ComLogin;
import org.ribbon.commands.ComLogout;
import org.ribbon.commands.ComPostMesg;
import org.ribbon.commands.ICommand;

/**
 * ICommand type enumeration and class pointer.
 * @author Stanislav Nepochatov
 */
public enum CommandTypes {
    
    /**
     * LOGIN command object.
     */
    LOGIN(new ComLogin()),
    
    /**
     * MAIN command object.
     */
    MAIN(new ComMain()),
    
    /**
     * LOGOUT command object.
     */
    LOGOUT(new ComLogout()),
    
    /**
     * LIST_DIRS command object.
     */
    LIST_DIRS(new ComListDirs()),
    
    /**
     * LIST_MESG command object.
     */
    LIST_MESG(new ComListMesg()),
    
    /**
     * POST_FORM command object.
     */
    POST_FORM(new ComPostForm()),
    
    /**
     * POST_MESG command object.
     */
    POST_MESG(new ComPostMesg()),
    
    /**
     * VIEW_MESG command object.
     */
    VIEW_MESG(new ComViewMesg()),
    
    /**
     * USER_INFO command object.
     */
    USER_INFO(new ComUserInfo());
    
    /**
     * Internal command class.
     */
    private final ICommand innerCommand;
    
    /**
     * Enumeration constructor.
     * @param givenCommand reference of command;
     */
    CommandTypes(ICommand givenCommand) {
        innerCommand = givenCommand;
    }
    
    /**
     * Get command.
     * @return command objectl;
     */
    public ICommand getCommand() {
        return innerCommand;
    }
}