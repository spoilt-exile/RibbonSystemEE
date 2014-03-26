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

package org.ribbon.dao.mysql;

import org.ribbon.dao.IDAODirectory;
import org.ribbon.dao.IDAOGroup;
import org.ribbon.dao.IDAOMessage;
import org.ribbon.dao.IDAOUser;

/**
 * MySql DAO factory class;
 * @author Stanislav Nepochatov
 * @deprecated use JPA, Luke!
 */
public class MySqlDAOFactory extends org.ribbon.dao.DAOFactory {
    
    /**
     * Private constructor for making singletone.
     */
    private MySqlDAOFactory() {};
    
    /**
     * Singletone instance.
     */
    private static MySqlDAOFactory currInstance;

    @Override
    public IDAOUser getNewIDaoUserInstance() {
        return new MySqlDAOUser();
    }

    @Override
    public IDAOGroup getNewIDaoGroupInstance() {
        return new MySqlDAOGroup();
    }
    
    @Override
    public IDAODirectory getNewDaoDirectoryInstance() {
        return new MySqlDAODirectory();
    }
    
    @Override
    public IDAOMessage getNewDaoMessageInstance() {
        return new MySqlDAOMessage();
    }
    
    /**
     * Get new factory instance;
     * @return factory singletone object;
     */
    public static MySqlDAOFactory getNewInstance() {
        if (currInstance == null) {
            currInstance = new MySqlDAOFactory();
        }
        return currInstance;
    }
}
