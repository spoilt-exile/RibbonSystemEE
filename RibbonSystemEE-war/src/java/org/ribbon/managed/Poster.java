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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import org.ribbon.enteties.Directory;
import org.ribbon.enteties.Message;
import org.ribbon.nwbean.DirectoryFacadeLocal;
import org.ribbon.nwbean.MessageFacadeLocal;

/**
 * Post message bean.
 * @author Stanislav Nepochatov
 */
@Named(value = "poster")
@RequestScoped
public class Poster {
    
    /**
     * Message to post.
     */
    private Message postMessage;
    
    /**
     * Raw directories from form.
     */
    private String[] dirs;
    
    @Inject
    private DirectoryFacadeLocal dirBean;
    
    @Inject
    private MessageFacadeLocal mesgBean;
    
    @ManagedProperty(value="#{ribbonSession}")
    @Inject
    private RibbonSession sesManaged;

    /**
     * Creates a new instance of Poster
     */
    public Poster() {
        postMessage = new Message();
    }
    
    /**
     * Perform message posting.
     * @return redirect adress;
     */
    public String post() {
        List<Directory> dirList = new ArrayList<Directory>(dirs.length);
        for (String dirName: dirs) {
            Directory finded = dirBean.findByPath(dirName);
            if (finded != null) {
                dirList.add(finded);
            }
        }
        postMessage.setDirId(dirList);
        postMessage.setPostDate(new Date());
        postMessage.setAuthId(sesManaged.getCurrentUser());
        mesgBean.create(postMessage);
        return "/pages/poststatus.jsf";
    }

    /**
     * Get message for post.
     * @return the message;
     */
    public Message getPostMessage() {
        return postMessage;
    }

    /**
     * Set message for post.
     * @param postMessage the message which will be posted;
     */
    public void setPostMessage(Message postMessage) {
        this.postMessage = postMessage;
    }

    /**
     * Get directory array.
     * @return the string array with directories;
     */
    public String[] getDirs() {
        return dirs;
    }

    /**
     * Set directory array.
     * @param dirs the string array with directories;
     */
    public void setDirs(String[] dirs) {
        this.dirs = dirs;
    }
    
}
