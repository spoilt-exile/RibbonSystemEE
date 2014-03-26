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
 * Directory permission class for DAO.
 * @author Stanislav Nepochatov
 * @deprecated use JPA entity!
 * @see org.ribbon.jpa.enteties.Permission
 */
public class Permission {
    
    /**
     * Default constructor.
     */
    public Permission() {};
    
    /**
     * Full parametric constructor.
     * @param givenDirId referenced id of directory;
     * @param givenGroupPerm flag for group permission;
     * @param givenAllPerm flag for ALL service group permission;
     * @param givenUserId referenced id of user (may be null);
     * @param givenGroupId referenced id of group (may be null);
     * @param givenReadFlag permission read access flag;
     * @param givenPostFlag permission post access flag;
     * @param givenAdminFlag permission admin access flag;
     */
    public Permission(int givenDirId, Boolean givenGroupPerm, Boolean givenAllPerm,
            int givenUserId, int givenGroupId, Boolean givenReadFlag, Boolean givenPostFlag, Boolean givenAdminFlag) {
        this.dirId = givenDirId;
        this.groupPerm = givenGroupPerm;
        this.allPerm = givenAllPerm;
        this.userId = givenUserId;
        this.groupId = givenGroupId;
        this.mayRead = givenReadFlag;
        this.mayPost = givenPostFlag;
        this.mayAdmin = givenAdminFlag;
    }
    
    /**
     * Id of user entry.
     */
    private int id = -1;
    
    /**
     * ID of referenced directory.
     */
    private int dirId;
    
    /**
     * Group permission flag.
     */
    private Boolean groupPerm;
    
    /**
     * ALL service group permission flag. 
     * Should be used for wide access specification. 
     * <b>WARNING:</b> may be <code>null</code>.
     * <b>WARNING:</b> <code>groupPerm</code> must be enabled to use this flag.
     */
    private Boolean allPerm;
    
    /**
     * User ID reference. 
     * Should be used in condition to <code>groupPerm</code>
     * <b>WARNING:</b> may be <code>null</code>.
     * @see #groupPerm
     */
    private int userId;
    
    /**
     * Group ID reference. 
     * Should be used in condition to <code>groupPerm</code>
     * <b>WARNING:</b> may be <code>null</code>.
     * @see #groupPerm
     */
    private int groupId;
    
    /**
     * Allow to specified user/group/all to read messages in referenced directory.
     */
    private Boolean mayRead;
    
    /**
     * Allow to specified user/group/all to post messages to referenced directory.
     */
    private Boolean mayPost;
    
    /**
     * Allow to specified user/group/all to admin messages in referenced directory. 
     * User or group with admin permission can edit or delete messages of other users.
     */
    private Boolean mayAdmin;
    
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
     * Get id of referenced dir.
     * @return id of directory;
     */
    public int getDirId() {
        return dirId;
    }

    /**
     * Set id of referenced directory.
     * @param dirId the dirId to set
     */
    public void setDirId(int dirId) {
        this.dirId = dirId;
    }

    /**
     * Get group permission flag. This flag 
     * displays which field should be used during access
     * checking.
     * @return the groupPerm flag;
     */
    public Boolean getGroupPerm() {
        return groupPerm;
    }

    /**
     * Set group permission flag.
     * @param groupPerm the groupPerm to set
     */
    public void setGroupPerm(Boolean groupPerm) {
        this.groupPerm = groupPerm;
    }

    /**
     * Get flag for ALL service group permission.
     * @return the flag which displays if permission is for ALL group;
     */
    public Boolean getAllPerm() {
        return allPerm;
    }

    /**
     * Get flag for ALL service group permission.
     * @param allPerm the allPerm to set
     */
    public void setAllPerm(Boolean allPerm) {
        this.allPerm = allPerm;
    }

    /**
     * Get referenced user id.
     * @return id or null if permission for group;
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set referenced user id.
     * @param userId the id to set;
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Get referenced group id.
     * @return id or null if permission for user;
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Set referenced group id.
     * @param groupId the id to set;
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * Get read permission for specified user/group on current dir.
     * @return read permission flag;
     */
    public Boolean getMayRead() {
        return mayRead;
    }

    /**
     * Set read permission flag.
     * @param mayRead the flag to set;
     */
    public void setMayRead(Boolean mayRead) {
        this.mayRead = mayRead;
    }

    /**
     * Get post permission for specified user/group on current dir.
     * @return post permission flag;
     */
    public Boolean getMayPost() {
        return mayPost;
    }

    /**
     * Set post permission flag.
     * @param mayPost the flag to set;
     */
    public void setMayPost(Boolean mayPost) {
        this.mayPost = mayPost;
    }

    /**
     * Get admin permission for specified user/group on current dir.
     * @return admin permission flag;
     */
    public Boolean getMayAdmin() {
        return mayAdmin;
    }

    /**
     * Set admin permission flag.
     * @param mayAdmin the flag to set;
     */
    public void setMayAdmin(Boolean mayAdmin) {
        this.mayAdmin = mayAdmin;
    }
    
}
