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

package org.ribbon.enteties;

/**
 * Group entity class for DAO.
 * @author Stanislav Nepochatov
 * @deprecated use JPA entity!
 * @see org.ribbon.jpa.enteties.Groups
 */
public class Group {
    
    /**
     * Default constructor.
     */
    public Group() {}
    
    /**
     * Parametric constructor for creating groups via application.
     * @param givenName name for new group;
     * @param givenDesc description of group;
     */
    public Group(String givenName, String givenDesc) {
        name = givenName;
        description = givenDesc;
    } 
    
    /**
     * Id of group entry.
     */
    private int id;
    
    /**
     * Name of group (unique).
     */
    private String name;
    
    /**
     * Short text description.
     */
    private String description;

    /**
     * Get id of this entry. Returns -1 if entity wasn't saved.
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Set new id for this entry. This will work only for one time.
     * @param id the id to set
     */
    public void setId(int id) {
        if (this.id == -1) {
            this.id = id;
        }
    }    

    /**
     * Get name of this group.
     * @return the name of group
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of this group
     * @param name new name of group
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get description of this group
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of this group
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
