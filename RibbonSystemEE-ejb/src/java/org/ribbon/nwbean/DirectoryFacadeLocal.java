/*
 * Copyright (C) 2014 spoilt
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

package org.ribbon.nwbean;

import java.util.List;
import javax.ejb.Local;
import org.ribbon.enteties.Directory;

/**
 *
 * @author spoilt
 */
@Local
public interface DirectoryFacadeLocal {

    void create(Directory directory);

    void edit(Directory directory);

    void remove(Directory directory);

    Directory find(Object id);

    List<Directory> findAll();

    List<Directory> findRange(int[] range);

    int count();
    
}
