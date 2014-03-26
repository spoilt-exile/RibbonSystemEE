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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User JPA entity class;
 * @author Stanislav Nepochatov
 */
@Entity
@Table(name = "User")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByDescription", query = "SELECT u FROM User u WHERE u.description = :description"),
    @NamedQuery(name = "User.findByPassw", query = "SELECT u FROM User u WHERE u.passw = :passw"),
    @NamedQuery(name = "User.findByCrtDate", query = "SELECT u FROM User u WHERE u.crtDate = :crtDate"),
    @NamedQuery(name = "User.findByLogDate", query = "SELECT u FROM User u WHERE u.logDate = :logDate"),
    @NamedQuery(name = "User.findByIsAdmin", query = "SELECT u FROM User u WHERE u.isAdmin = :isAdmin"),
    @NamedQuery(name = "User.findByIsEnabled", query = "SELECT u FROM User u WHERE u.isEnabled = :isEnabled"),
    @NamedQuery(name = "User.findByIsActive", query = "SELECT u FROM User u WHERE u.isActive = :isActive")})
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Id of user entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    /**
     * Login of user (unique).
     */
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "login")
    private String login;
    
    /**
     * Short text description.
     */
    @Size(max = 300)
    @Column(name = "description")
    private String description;
    
    /**
     * Hash of user password (MD5).
     */
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "passw")
    private String passw;
    
    /**
     * Creation date of this entry.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "crt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtDate;
    
    /**
     * Last access date of this entry.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "log_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDate;
    
    /**
     * System administrator flag (all access check will pass).
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_admin")
    private boolean isAdmin;
    
    /**
     * User activation flag (inactive user wouldn't login).
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;
    
    /**
     * Flag which display is user online.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private boolean isActive;
    
    /**
     * List of groups of this user.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UserGroupsRel", 
      joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="group_id", referencedColumnName="id")})
    private List<Groups> groupsList;
    
    /**
     * List of referenced messages.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authId", fetch = FetchType.LAZY)
    private List<Message> messageList;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Parametric constructor.
     * @param id user id number;
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * Parametric constructor.
     * @param id user id number;
     * @param login user name for login;
     * @param passw user password hash string (MD5);
     * @param crtDate date of user creation;
     * @param logDate date of last login of user;
     * @param isAdmin administrator privilegies flag;
     * @param isEnabled activation flag;
     * @param isActive user current activity flag;
     */
    public User(Integer id, String login, String passw, Date crtDate, Date logDate, boolean isAdmin, boolean isEnabled, boolean isActive) {
        this.id = id;
        this.login = login;
        this.passw = passw;
        this.crtDate = crtDate;
        this.logDate = logDate;
        this.isAdmin = isAdmin;
        this.isEnabled = isEnabled;
        this.isActive = isActive;
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
     * Get login of user.
     * @return the login;
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set user login.
     * @param login the login to set;
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get user decription string.
     * @return the description;
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set user description string.
     * @param description the description to set;
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get hash string of user password.
     * @return the hash of password;
     */
    public String getPassw() {
        return passw;
    }

    /**
     * Set hash string of user password.
     * @param passw the hash of password to set
     */
    public void setPassw(String passw) {
        this.passw = passw;
    }

    /**
     * Get date of user creation.
     * @return the date of creation
     */
    public Date getCrtDate() {
        return crtDate;
    }

    /**
     * Set date of user creation.
     * @param create_date the date to set
     */
    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    /**
     * Get date of user last login.
     * @return the date of last login
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * Set date of user last login.
     * @param log_date the log_date to set
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /**
     * Get system admin flag of user.
     * @return the flag of admin rights of user
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Set system admin flag of user.
     * @param is_admin flag of admin rights of user
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Get activation flag for user.
     * @return the activation flag
     */
    public boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * Set activation flag for user,
     * @param is_enabled the flag of user activation to set
     */
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * Find out if user online.
     * @return the flag of user active in the system right now
     */
    public boolean getIsActive() {
        return isActive;
    }
    
    /**
     * Set user activity status.
     * @param is_active the activity flag to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Get messages of this user.
     * @return message list;
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * Set message list of this user.<br/>
     * <b>WARNING!</b> You shouln't use this method!
     * @param messageList 
     */
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
    
    /**
     * Get group list of this user.
     * @return the list of groups;
     */
    public List<Groups> getGroupsList() {
        return groupsList;
    }

    /**
     * Set list of groups of this user;
     * @param groupsList the list of groups to set;
     */
    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ribbon.jpa.enteties.User[ id=" + id + " ]";
    }
}
