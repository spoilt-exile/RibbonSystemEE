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

package org.ribbon.jpa.enteties;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Permission JPA entity class;
 * @author Stanislav Nepochatov
 */
@Entity
@Table(name = "Permission")
@NamedQueries({
    @NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p"),
    @NamedQuery(name = "Permission.findById", query = "SELECT p FROM Permission p WHERE p.id = :id"),
    @NamedQuery(name = "Permission.findByDirId", query = "SELECT p FROM Permission p WHERE p.dirId = :dirId"),
    @NamedQuery(name = "Permission.findByGroupPerm", query = "SELECT p FROM Permission p WHERE p.groupPerm = :groupPerm"),
    @NamedQuery(name = "Permission.findByAllPerm", query = "SELECT p FROM Permission p WHERE p.allPerm = :allPerm"),
    @NamedQuery(name = "Permission.findByMayRead", query = "SELECT p FROM Permission p WHERE p.mayRead = :mayRead"),
    @NamedQuery(name = "Permission.findByMayPost", query = "SELECT p FROM Permission p WHERE p.mayPost = :mayPost"),
    @NamedQuery(name = "Permission.findByMayAdmin", query = "SELECT p FROM Permission p WHERE p.mayAdmin = :mayAdmin")})
public class Permission implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Id of permission entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    /**
     * Referenced directory entity.
     */
    @JoinColumn(name = "dir_id", referencedColumnName = "id")
    @NotNull
    @ManyToOne
    private Directory dirId;
    
    /**
     * Group permission flag.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "group_perm")
    private boolean groupPerm;
    
    /**
     * ALL service group permission flag. 
     * Should be used for wide access specification. 
     * <b>WARNING:</b> may be <code>null</code>.
     * <b>WARNING:</b> <code>groupPerm</code> must be enabled to use this flag.
     */
    @Column(name = "all_perm")
    private Boolean allPerm;
    
    /**
     * Allow to specified user/group/all to read messages in referenced directory.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "may_read")
    private boolean mayRead;
    
    /**
     * Allow to specified user/group/all to post messages to referenced directory.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "may_post")
    private boolean mayPost;
    
    /**
     * Allow to specified user/group/all to admin messages in referenced directory. 
     * User or group with admin permission can edit or delete messages of other users.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "may_admin")
    private boolean mayAdmin;
    
    /**
     * Group reference. 
     * Should be used in condition to <code>groupPerm</code>
     * <b>WARNING:</b> may be <code>null</code>.
     * @see #groupPerm
     */
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne
    private Groups groupId;
    
    /**
     * User reference. 
     * Should be used in condition to <code>groupPerm</code>
     * <b>WARNING:</b> may be <code>null</code>.
     * @see #groupPerm
     */
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    /**
     * Default constructor.
     */
    public Permission() {
    }

    /**
     * Parametric constructor.
     * @param id permission id number;
     */
    public Permission(Integer id) {
        this.id = id;
    }

    /**
     * Parametric constructor (full).
     * @param id permission id number;
     * @param dirId directory reference;
     * @param groupPerm group permission flag;
     * @param mayRead read flag;
     * @param mayPost post flag;
     * @param mayAdmin admin flag;
     */
    public Permission(Integer id, Directory dirId, boolean groupPerm, boolean mayRead, boolean mayPost, boolean mayAdmin) {
        this.id = id;
        this.dirId = dirId;
        this.groupPerm = groupPerm;
        this.mayRead = mayRead;
        this.mayPost = mayPost;
        this.mayAdmin = mayAdmin;
    }

    /**
     * Get id of this entry.
     * @return the id;
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Set new id for this entry.
     * @param id the id to set;
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get referenced dir.
     * @return directory;
     */
    public Directory getDirId() {
        return dirId;
    }

    /**
     * Set referenced directory.
     * @param dirId the directory to set
     */
    public void setDirId(Directory dirId) {
        this.dirId = dirId;
    }

    /**
     * Get group permission flag. This flag 
     * displays which field should be used during access
     * checking.
     * @return the groupPerm flag;
     */
    public boolean getGroupPerm() {
        return groupPerm;
    }

    /**
     * Set group permission flag.
     * @param groupPerm the groupPerm to set
     */
    public void setGroupPerm(boolean groupPerm) {
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
     * Get read permission for specified user/group on current dir.
     * @return read permission flag;
     */
    public boolean getMayRead() {
        return mayRead;
    }

    /**
     * Set read permission flag.
     * @param mayRead the flag to set;
     */
    public void setMayRead(boolean mayRead) {
        this.mayRead = mayRead;
    }

    /**
     * Get post permission for specified user/group on current dir.
     * @return post permission flag;
     */
    public boolean getMayPost() {
        return mayPost;
    }

    /**
     * Set post permission flag.
     * @param mayPost the flag to set;
     */
    public void setMayPost(boolean mayPost) {
        this.mayPost = mayPost;
    }

    /**
     * Get admin permission for specified user/group on current dir.
     * @return admin permission flag;
     */
    public boolean getMayAdmin() {
        return mayAdmin;
    }

    /**
     * Set admin permission flag.
     * @param mayAdmin the flag to set;
     */
    public void setMayAdmin(boolean mayAdmin) {
        this.mayAdmin = mayAdmin;
    }

    /**
     * Get referenced group.
     * @return group or null if permission for user;
     */
    public Groups getGroupId() {
        return groupId;
    }

    /**
     * Set referenced group.
     * @param groupId the group to set;
     */
    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }

    /**
     * Get referenced user.
     * @return user or null if permission for group;
     */
    public User getUserId() {
        return userId;
    }

    /**
     * Set referenced user.
     * @param userId the user to set;
     */
    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ribbon.jpa.enteties.Permission[ id=" + id + " ]";
    }
    
}
