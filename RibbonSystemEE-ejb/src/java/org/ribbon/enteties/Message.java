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
 * Message entity class for DAO;
 * @author Stanislav Nepochatov
 * @deprecated use JPA entity!
 * @see org.ribbon.jpa.enteties.Message
 */
public class Message {
    
    /**
     * Default constructor.
     */
    public Message() {};
    
    /**
     * Parametric constructor.
     * @param givenHeader header of message;
     * @param givenAuthId id of author;
     * @param givenUrgent urgent flag;
     * @param givenBody main text of message;
     */
    public Message(String givenHeader, int givenAuthId, Boolean givenUrgent, String givenBody) {
        this.header = givenHeader;
        this.postDate = new Date();
        this.authId = givenAuthId;
        this.isUrgent = givenUrgent;
        this.body = givenBody;
    }
    
    /**
     * Id of user entry.
     */
    private int id = -1;
    
    /**
     * Header of this message.
     */
    private String header;
    
    /**
     * Id of directory which contains this message.
     */
    private int dirId;
    
    /**
     * Date of posting this message.
     */
    private Date postDate;
    
    /**
     * Referenced id of author.
     */
    private int authId;
    
    /**
     * Urgeness flag of message.
     */
    private Boolean isUrgent;
    
    /**
     * Main text of this message.
     */
    private String body;
    
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
     * Get id of referenced directory.
     * @return the integer id of directory;
     */
    public int getDirId() {
        return dirId;
    }

    /**
     * Set id of referenced directory (equal to move message).
     * @param dirId the new id to set;
     */
    public void setDirId(int dirId) {
        this.dirId = dirId;
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
     * Get author id.
     * @return the id of this message author;
     */
    public int getAuthId() {
        return authId;
    }

    /**
     * Set author id.
     * @param authId the id to set;
     */
    public void setAuthId(int authId) {
        this.authId = authId;
    }

    /**
     * Get urgent flag of this message.
     * @return the flag of urgeness of this message;
     */
    public Boolean getIsUrgent() {
        return isUrgent;
    }

    /**
     * Set urgent flag of this message.
     * @param isUrgent flag to set;
     */
    public void setIsUrgent(Boolean isUrgent) {
        this.isUrgent = isUrgent;
    }

    /**
     * Get body text of this message.
     * @return the body;
     */
    public String getBody() {
        return body;
    }

    /**Set new body of message text.
     * @param body the text to set;
     */
    public void setBody(String body) {
        this.body = body;
    }
    
    public String getText() {
        return this.body.replaceAll("\n", "<br/>");
    }
}
