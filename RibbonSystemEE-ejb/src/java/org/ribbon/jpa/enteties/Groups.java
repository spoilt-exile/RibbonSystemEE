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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Group JPA entity class.
 * @author Stanislav Nepochatov
 */
@Entity
@Table(name = "Groups")
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g"),
    @NamedQuery(name = "Groups.findById", query = "SELECT g FROM Groups g WHERE g.id = :id"),
    @NamedQuery(name = "Groups.findByName", query = "SELECT g FROM Groups g WHERE g.name = :name"),
    @NamedQuery(name = "Groups.findByDescription", query = "SELECT g FROM Groups g WHERE g.description = :description")})
public class Groups implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Id of group entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    /**
     * Name of group (unique).
     */
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    
    /**
     * Short text description.
     */
    @Size(max = 300)
    @Column(name = "description")
    private String description;
    
    /**
     * List of users which included in this group.
     * LINK TO: <code>UserGroupsRel</code> table;
     * @see org.ribbon.jpa.enteties.User
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UserGroupsRel", 
      joinColumns={@JoinColumn(name="group_id", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
    private List<User> userList;

    /**
     * Default constructor.
     */
    public Groups() {
    }

    /**
     * Parametric constructor.
     * @param id group id number;
     */
    public Groups(Integer id) {
        this.id = id;
    }

    /**
     * Parametrick constructor (full).
     * @param id group id number;
     * @param name group name;
     */
    public Groups(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
     * Get name of this group.
     * @return the name of group;
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of this group.
     * @param name new name of group;
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get description of this group.
     * @return the description;
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of this group.
     * @param description the description to set;
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get list of the users in this group.
     * @return the list with users;
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Set users which belongs to this group.
     * @param userList the users list to set;
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
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
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ribbon.jpa.enteties.Groups[ id=" + id + " ]";
    }
}
