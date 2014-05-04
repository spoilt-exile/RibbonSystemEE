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

package org.ribbon.managed;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.ribbon.enteties.Message;
import org.ribbon.nwbean.MessageFacadeLocal;

/**
 * Message viewver.
 * @author Stanislav Nepochatov
 */
@Named(value = "viewver")
@RequestScoped
public class Viewver {
    
    @Inject
    private MessageFacadeLocal mesgBean;
    
    /**
     * Message to view.
     */
    private Message viewMessage;
    
    /**
     * Message ID to view.
     */
    private String id;

    /**
     * Creates a new instance of Viewver
     */
    public Viewver() {
    }
    
    /**
     * Load message in bean.
     */
    public void loadMessage() {
        viewMessage = mesgBean.find(new Integer(id));
    }

    /**
     * Get viewed message.
     * @return the message with specified id;
     */
    public Message getViewMessage() {
        return viewMessage;
    }

    /**
     * Set viewved message.
     * @param viewMessage the message to set;
     */
    public void setViewMessage(Message viewMessage) {
        this.viewMessage = viewMessage;
    }

    /**
     * Get id of message.
     * @return the string version of id;
     */
    public String getId() {
        return id;
    }

    /**
     * Set id of message.
     * @param id the id to set;
     */
    public void setId(String id) {
        this.id = id;
    }
}
