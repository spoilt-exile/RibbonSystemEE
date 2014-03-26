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

import java.util.Date;

/**
 * User entity class for DAO.
 * @author Stanislav Nepochatov
 * @deprecated use JPA entity!
 * @see org.ribbon.jpa.enteties.User
 */
public class User {
    
    /**
     * Default constructor;
     */
    public User(){};
    
    /**
     * Parametric constructor for creating new user via application.
     * @param givenLogin login for new user;
     * @param givenDesc description for user;
     * @param givenRawPassw raw unhashed password;
     * @param givenIsAdmin admin flag for user;
     * @param givenIsEnabled activation flag for user;
     */
    public User(String givenLogin, String givenDesc, String givenRawPassw, 
            Boolean givenIsAdmin, Boolean givenIsEnabled) {
        login = givenLogin;
        description = givenDesc;
        passw = org.ribbon.service.Utils.getHash(givenRawPassw);
        crtDate = new Date();
        logDate = new Date();
        isAdmin = givenIsAdmin;
        isEnabled = givenIsEnabled;
    }
    
    /**
     * Id of user entry.
     */
    private int id = -1;
    
    /**
     * Login of user (unique).
     */
    private String login;
    
    /**
     * Short text description.
     */
    private String description;
    
    /**
     * Hash of user password (MD5).
     */
    private String passw;
    
    /**
     * Creation date of this entry.
     */
    private Date crtDate;
    
    /**
     * Last access date of this entry.
     */
    private Date logDate;
    
    /**
     * System administrator flag (all access check will pass).
     */
    private Boolean isAdmin;
    
    /**
     * User activation flag (inactive user wouldn't login).
     */
    private Boolean isEnabled;
    
    /**
     * Flag which display is user online.
     */
    private Boolean isActive;

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
     * Get login of user.
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set user login.
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get user decription string.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set user description string.
     * @param description the descriptionl to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get hash string of user password.
     * @return the hash of password
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
    public void setCrtDate(Date create_date) {
        this.crtDate = create_date;
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
    public void setLogDate(Date log_date) {
        this.logDate = log_date;
    }

    /**
     * Get system admin flag of user.
     * @return the flag of admin rights of user
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Set system admin flag of user.
     * @param is_admin flag of admin rights of user
     */
    public void setIsAdmin(Boolean is_admin) {
        this.isAdmin = is_admin;
    }

    /**
     * Get activation flag for user.
     * @return the activation flag
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * Set activation flag for user,
     * @param is_enabled the flag of user activation to set
     */
    public void setIsEnabled(Boolean is_enabled) {
        this.isEnabled = is_enabled;
    }

    /**
     * Find out if user online.
     * @return the flag of user active in the system right now
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Set user activity status.
     * @param is_active the activity flag to set
     */
    public void setIsActive(Boolean is_active) {
        this.isActive = is_active;
    }
}
