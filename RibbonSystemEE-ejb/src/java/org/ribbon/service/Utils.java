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

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.*;

/**
 * Final class with useful methods;
 * @author Stanislav Nepochatov
 */
public class Utils {
    
    public static String formatDate(Date toFormat) {
        DateFormatSymbols mySymbols = new DateFormatSymbols() {
            @Override
            public String[] getMonths() {
                return new String[]{"січня", "лютого", "березня", "квітня", "травня", "червеня",
                    "липня", "серпня", "вересня", "жовтня", "листопада", "грудня"};
            }
        };
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM HH:mm:ss", mySymbols);
        return sdf.format(toFormat);
    }
    
    /**
     * Get specified bean by global JNDI name.
     * @param jndi global jndi name;
     * @return finded bean or null;
     */
    public static Object getBean(String jndi) {
        try {
            Context ctx = new InitialContext();
            return ctx.lookup(jndi);
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
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
}
