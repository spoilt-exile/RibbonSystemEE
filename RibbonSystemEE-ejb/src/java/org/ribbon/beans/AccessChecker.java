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

package org.ribbon.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.ribbon.jpa.enteties.*;

/**
 * Access check bean. You should use 
 * this bean to check user access rights 
 * @author Stanislav Nepochatov
 */
@Stateless
@LocalBean
public class AccessChecker {
    
    /**
     * Check access for specified user for directory.
     * @param initiator user entity of initiator of check;
     * @param dirBean directory entity bean for tree permission check;
     * @param target name of target directory;
     * @return state of access checking;
     */
    public Permission.ACCESS_STATE check(User initiator, DirectoryBean dirBean, String target) {
        if (initiator.getLogin().equals("root")) {
            return Permission.ACCESS_STATE.MAY_ADMIN;
        }
        List<Directory> chain = dirBean.findChain(target);
        List<Permission> perms = null;
        for (int dirIndex = chain.size() - 1; dirIndex > 0; dirIndex--) {
            List<Permission> tempPerms = chain.get(dirIndex).getPermissionList();
            if (tempPerms.size() > 0) {
                perms = tempPerms;
                break;
            }
        }
        if (perms != null) {
            Permission.ACCESS_STATE resolved = Permission.ACCESS_STATE.NO_ACCESS;
            for (Permission checked: perms) {
                if (!checked.getGroupPerm() && initiator.equals(checked.getUserId())) {
                    resolved = Permission.compare(resolved, Permission.getState(checked));
                } else {
                    for (Groups grp: initiator.getGroupsList()) {
                        if (grp.equals(checked.getGroupId())) {
                            resolved = Permission.compare(resolved, Permission.getState(checked));
                        }
                    }
                }
            }
            return resolved;
        } else {
            return Permission.ACCESS_STATE.MAY_READ;
        }
    }
    
}
