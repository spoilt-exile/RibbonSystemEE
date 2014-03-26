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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Directory JPA entity class;
 * @author Stanislav Nepochatov
 */
@Entity
@Table(name = "Directory")
@NamedQueries({
    @NamedQuery(name = "Directory.findAll", query = "SELECT d FROM Directory d"),
    @NamedQuery(name = "Directory.findAllSortPath", query = "SELECT d FROM Directory d ORDER BY d.path"),
    @NamedQuery(name = "Directory.findById", query = "SELECT d FROM Directory d WHERE d.id = :id"),
    @NamedQuery(name = "Directory.findByPath", query = "SELECT d FROM Directory d WHERE d.path = :path"),
    @NamedQuery(name = "Directory.findByDescription", query = "SELECT d FROM Directory d WHERE d.description = :description"),
    @NamedQuery(name = "Directory.findByIsHidden", query = "SELECT d FROM Directory d WHERE d.isHidden = :isHidden")})
public class Directory implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    /**
     * Path of directory.
     */
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "path")
    private String path;

    /**
     * Desription of directory.
     */
    @Size(max = 300)
    @Column(name = "description")
    private String description;
    
    /**
     * Hidden flag for directory.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_hidden")
    private boolean isHidden;
    
    /**
     * List of messages in this directory.<br/>
     * LINK TO: <code>Message</code> table;
     * @see org.ribbon.jpa.enteties.Message
     */
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dirId")
    private List<Message> messageList;
    
    /**
     * List of messages in this directory.<br/>
     * LINK TO: <code>Permission</code> table;
     * @see org.ribbon.jpa.enteties.Permission
     */
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dirId")
    private List<Permission> permissionList;

    /**
     * Default constructor.
     */
    public Directory() {
    }

    /**
     * Parametric cosntructor.
     * @param id directory id number;
     */
    public Directory(Integer id) {
        this.id = id;
    }

    /**
     * Parametrick constructor (full).
     * @param id directory id number;
     * @param path path of directory;
     * @param isHidden directory visibility flag (for ordinary users);
     */
    public Directory(Integer id, String path, boolean isHidden) {
        this.id = id;
        this.path = path;
        this.isHidden = isHidden;
    }
    
    /**
     * Get id of this entry
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
     * Get path of current dir.
     * @return the path;
     */
    public String getPath() {
        return path;
    }

    /**
     * Set path to current dir.
     * @param path the path to set;
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Get description text of dir.
     * @return the description;
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set desctiption to dir.
     * @param description the description to set;
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get visibility flag for current dir.
     * @return visibility flag of dir;
     */
    public boolean getIsHidden() {
        return isHidden;
    }

    /**
     * Set visibility flag for current dir.
     * @param isHidden new visibility flag;
     */
    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * Get list of linked messages;
     * @return list of messages in this directory;
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * Set list of linked messages;<br/>
     * <b>WARNING!</b> You shouldn't use this method.
     * @param messageList new message list to set;
     */
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
    
    /**
     * Get all permission for this directory.
     * @return the permissionList list of permission object;
     */
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    /**
     * Set permission list.
     * @param permissionList the list of permission to set;
     */
    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
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
        if (!(object instanceof Directory)) {
            return false;
        }
        Directory other = (Directory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ribbon.jpa.enteties.Directory[ id=" + id + " ]";
    }
    
    /**
     * Get formatted last chain of path.
     * @return formatted string for displaing;
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
