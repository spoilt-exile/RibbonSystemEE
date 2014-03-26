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

import java.util.List;
import org.ribbon.enteties.Message;

/**
 * RibbonSystem message DAO interface;
 * @author Stanislav Nepochatov
 * @deprecated use JPA, Luke!
 */
public interface IDAOMessage {
    
    /**
     * Insert or update entity in database.
     * @param givenMessage message entity to save;
     * @return result of operation;
     */
    public boolean save(Message givenMessage);
    
    /**
     * Get directory entity by it's id.
     * @param givenId id to search;
     * @return finded idr entity or null;
     */
    public Message getMessageById(int givenId);
    
    /**
     * Get messages by directory id.
     * @return list of messages sorted by id (desc) or null;
     */
    public List<Message> getMessagesByDirId(int givenDirId);
    
}
