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
import javax.inject.Inject;
import org.ribbon.enteties.Directory;
import org.ribbon.nwbean.DirectoryFacadeLocal;

/**
 * Directory listing and other operation bean.
 * @author Stanislav Nepochatov
 */
@Named(value = "directoryLister")
@RequestScoped
public class DirectoryLister {
    
    @Inject
    private DirectoryFacadeLocal dirBean;

    /**
     * Creates a new instance of DirectoryLister
     */
    public DirectoryLister() {
    }

    /**
     * @return the dirs
     */
    public List<Directory> getDirs() {
        return dirBean.findAllSortByPath();
    }
}