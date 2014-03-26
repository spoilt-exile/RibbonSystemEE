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

package org.ribbon.dao;

import java.util.*;
import org.ribbon.enteties.*;

/**
 * Ribbon system group DAO interface.
 * @author Stanislav Nepochatov
 * @deprecated use JPA, Luke!
 */
public interface IDAOGroup {
    
    /**
     * Insert or update entity in database.
     * @param givenGroup group entity to save;
     * @return result of operation;
     */
    public boolean save(Group givenGroup);
    
    /**
     * Get group entity by it's id.
     * @param givenId id to search;
     * @return finded group entity or null;
     */
    public Group getGroupById(int givenId);
    
    /**
     * Get group entity by it's login.
     * @param givenName id to search;
     * @return finded group entity or null;
     */
    public Group getGroupByName(String givenName);
    
    /**
     * Get all grouos.
     * @return list of groups sorted by names or null;
     */
    public List<Group> getAll();
    
    /**
     * Get groups of specified user;
     * @param givenUserId user id to search; 
     * @param includeDisabled include in result disabled relations;
     * @return list of groups entries or null;
     */
    public List<Group> getGroupsByUserId(int givenUserId, Boolean includeDisabled);
    
}