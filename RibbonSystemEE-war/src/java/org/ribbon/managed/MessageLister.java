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

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import org.ribbon.nwbean.MessageFacadeLocal;
import org.ribbon.nwbean.UserFacadeLocal;
import org.ribbon.beans.AccessChecker;
import org.ribbon.commands.Router;
import org.ribbon.enteties.Message;
import org.ribbon.enteties.Permission;
import org.ribbon.nwbean.DirectoryFacadeLocal;

/**
 * Message lister bean.
 * @author Stanislav Nepochatov
 */
@Named(value = "messageLister")
@RequestScoped
public class MessageLister {

    /**
     * Creates a new instance of MessageLister
     */
    public MessageLister() {
    }
    
    @Inject
    private MessageFacadeLocal mesgBean;
    
    @Inject
    private UserFacadeLocal usrBean;
    
    @Inject
    private AccessChecker accBean;
    
    @Inject
    private DirectoryFacadeLocal dirBean;
    
    @ManagedProperty(value="#{ribbonSession}")
    @Inject
    private RibbonSession sesManaged;
    
    /**
     * Access mode for directory.
     */
    private int accessMode;
    
    /**
     * Message list.
     */
    private List<Message> messages;
    
    public String list(String dirId, String dirName) {
        Permission.ACCESS_STATE resolved;
        resolved = accBean.check(usrBean.findByLogin(sesManaged.getCurrentUser().getLogin()), dirBean, dirName);
        if (resolved.ordinal() >= Permission.ACCESS_STATE.MAY_READ.ordinal()) {
            sesManaged.setCurrentDir(dirBean.findByPath(dirName));
            setMessages(mesgBean.findByDirIdSortId(dirBean.findByPath(dirName)));
            setAccessMode(resolved.ordinal());
        }
        return Router.COM_LIST_MESG;
    }

    /**
     * Get access mode integer.
     * @return the accessMode
     */
    public int getAccessMode() {
        return accessMode;
    }

    /**
     * Set access mode integer.
     * @param accessMode the mode to set;
     */
    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }

    /**
     * Get messages list.
     * @return the messagesl
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Set messages list.
     * @param messages the messages to set
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
