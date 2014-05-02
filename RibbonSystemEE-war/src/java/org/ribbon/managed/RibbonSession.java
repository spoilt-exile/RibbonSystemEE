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

package org.ribbon.managed;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.ribbon.enteties.Directory;
import org.ribbon.enteties.User;

/**
 * Ribbon specific session handler.
 * @author Stanislav Nepochatov
 */
@Named(value = "ribbonSession")
@SessionScoped
public class RibbonSession implements Serializable {
    
    /**
     * Current user in this session.
     */
    private User currentUser;
    
    /**
     * Directory which current user veiw.
     */
    private Directory currentDir;

    /**
     * Creates a new instance of RibbonSession
     */
    public RibbonSession() {
    }

    /**
     * @return the currentUser
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the currentDir
     */
    public Directory getCurrentDir() {
        return currentDir;
    }

    /**
     * @param currentDir the currentDir to set
     */
    public void setCurrentDir(Directory currentDir) {
        this.currentDir = currentDir;
    }
    
}
