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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Message JPA entity class;
 * @author Stanislav Nepochatov
 */
@Entity
@Table(name = "Message")
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findByDirIdSortId", query = "SELECT m FROM Message m WHERE m.dirId = :dirId ORDER BY m.id DESC"),
    @NamedQuery(name = "Message.findByPostDate", query = "SELECT m FROM Message m WHERE m.postDate = :postDate"),
    @NamedQuery(name = "Message.findByIsUrgent", query = "SELECT m FROM Message m WHERE m.isUrgent = :isUrgent")})
public class Message implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Id of message entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    /**
     * Header of this message.
     */
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "header")
    private String header;
    
    /**
     * Date of posting this message.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "post_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    
    /**
     * Urgency flag of message.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_urgent")
    private boolean isUrgent;
    
    /**
     * Main text of this message.
     */
    @Basic(optional = false, fetch=FetchType.LAZY)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "body")
    private String body;
    
    /**
     * Directory object which contains this message.<br/>
     * LINK TO: <code>Directory</code> table;
     */
    @JoinColumn(name = "dir_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Directory dirId;
    
    /**
     * Entity of author which post this message..<br/>
     * LINK TO: <code>User</code> table;
     */
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User authId;

    /**
     * Default constructor.
     */
    public Message() {
    }

    /**
     * Parametric constructor.
     * @param id message id number;
     */
    public Message(Integer id) {
        this.id = id;
    }

    /**
     * Parametric constructor;
     * @param id message id number;
     * @param header message header;
     * @param postDate date of message post;
     * @param isUrgent urgency flag of message;
     * @param body main text of message;
     * @param author author entety reference;
     * @param directory directory entity reference;
     */
    public Message(Integer id, String header, Date postDate, boolean isUrgent, String body, User author, Directory directory) {
        this.id = id;
        this.header = header;
        this.postDate = postDate;
        this.isUrgent = isUrgent;
        this.body = body;
        this.authId = author;
        this.dirId = directory;
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
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get header of this message.
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Set header of this message.
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Get date of message posting.
     * @return the date of posting;
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * Set date of message posting.
     * @param postDate new date to set;
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * Get urgent flag of this message.
     * @return the flag of urgeness of this message;
     */
    public boolean getIsUrgent() {
        return isUrgent;
    }

    /**
     * Set urgent flag of this message.
     * @param isUrgent flag to set;
     */
    public void setIsUrgent(boolean isUrgent) {
        this.isUrgent = isUrgent;
    }

    /**
     * Get body text of this message.
     * @return the body;
     */
    public String getBody() {
        return body;
    }

    /**
     * Set new body of message text.
     * @param body the text to set;
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Get referenced directory.
     * @return the directory;
     */
    public Directory getDirId() {
        return dirId;
    }

    /**
     * Set referenced directory (equal to move message).
     * @param dirId the new dir to set;
     */
    public void setDirId(Directory dirId) {
        this.dirId = dirId;
    }

    /**
     * Get author.
     * @return the author User object;
     */
    public User getAuthId() {
        return authId;
    }

    /**
     * Set author.
     * @param authId the User object of author to set;
     */
    public void setAuthId(User authId) {
        this.authId = authId;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ribbon.jpa.enteties.Message[ id=" + id + " ]";
    }
}
