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

package org.ribbon.service;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * Final class with useful methods;
 * @author Stanislav Nepochatov
 */
public class Utils {
    
    /**
     * Get hash sum of given string.
     * @param givenStr given string;
     * @return md5 hash sum representation;
     */
    public static String getHash(String givenStr) {
        StringBuffer hexString = new StringBuffer();
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(givenStr.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (Exception ex) {}
        return hexString.toString();
    }
    
    /**
     * Get new connection from pool.
     * @return connection from pool;
     * @throws NamingException if resource not available;
     * @throws SQLException if connection cann't be established;
     * @deprecated use methods of JPAManager class;
     * @see org.ribbon.jpa.JPAManager
     */
    public static Connection getConnection() throws NamingException, SQLException {
        Connection newcon = null;
        DataSource ds = (DataSource) InitialContext.doLookup("ribbonSource");
        return ds.getConnection();
    }
    
    /**
     * Close used connection and place it to the pool.
     * @param usedConn connection to close;
     * @throws SQLException if something went wrong;
     * @deprecated use methods of JPAManager class;
     * @see org.ribbon.jpa.JPAManager
     */
    public static void closeConnection(Connection usedConn) throws SQLException {
        if (usedConn != null) {
            usedConn.close();
        }
    }
}
