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
 * System user entity for DAO.
 * @author Stanislav Nepochatov
 * @deprecated use JPA entity!
 * @see org.ribbon.jpa.enteties.Directory
 */
public class Directory {
    
    /**
     * Default constructor.
     */
    public Directory() {};
    
    /**
     * Parametric constructor.
     * @param givenPath full path of directory;
     * @param givenDescription description string for directory;
     * @param givenHideFlag visibility flag for ordinary user;
     */
    public Directory(String givenPath, String givenDescription, Boolean givenHideFlag) {
        this.path = givenPath;
        this.description = givenDescription;
        this.isHidden = givenHideFlag;
    }
    
    /**
     * Id of user entry.
     */
    private int id = -1;
    
    /**
     * Path of this dir from root.<br/>
     * FORMAT: <code>DIR1.DIR2.DIR3</code>;
     */
    private String path;
    
    /**
     * Description of current dir.
     */
    private String description;
    
    /**
     * Flag of dir visibility for ordinary user. Admin can see all dirs.
     */
    private Boolean isHidden;
    
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
     * Get path of current dir.
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set path to current dir.
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Get description text of dir.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set desctiption to dir.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get visibility flag for current dir.
     * @return visibility flag of dir;
     */
    public Boolean getIsHidden() {
        return isHidden;
    }

    /**
     * Set visibility flag for current dir.
     * @param isHidden new visibility flag;
     */
    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }
    
    /**
     * 
     * @return 
     */
    public String getChain() {
        String[] chunks = this.path.split("\\.");
        String chain = "";
        for (int index = 0; index < chunks.length; index++) {
            if (index == chunks.length - 1) {
                chain += chunks[index];
            } else {
                chain += "---";
            }
        }
        return chain;
    }
}
