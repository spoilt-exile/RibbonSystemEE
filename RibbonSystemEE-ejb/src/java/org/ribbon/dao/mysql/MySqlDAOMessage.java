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

package org.ribbon.dao.mysql;

import java.util.*;
import java.sql.*;
import org.ribbon.dao.IDAOMessage;
import org.ribbon.enteties.Message;
import org.ribbon.service.Utils;

/**
 * MySql implementation of Message DAO.
 * @author Stanislav Nepochatov
 * @deprecated use JPA, Luke!
 */
public class MySqlDAOMessage implements IDAOMessage {

    @Override
    public boolean save(Message givenMessage) {
        if (givenMessage.getId() == -1) {
            return insert(givenMessage);
        } else {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Message getMessageById(int givenId) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            Message fMessage = new Message();
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("SELECT * FROM Message WHERE id=?;");
            pstn.setInt(1, givenId);
            res = pstn.executeQuery();
            res.next();
            fMessage.setId(res.getInt("id"));
            fMessage.setHeader(res.getString("header"));
            fMessage.setDirId(res.getInt("dir_id"));
            fMessage.setPostDate(res.getDate("post_date"));
            fMessage.setAuthId(res.getInt("auth_id"));
            fMessage.setIsUrgent(res.getBoolean("is_urgent"));
            fMessage.setBody(res.getString("body"));
            return fMessage;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (pstn != null) {
                    pstn.close();
                }
                Utils.closeConnection(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Message> getMessagesByDirId(int givenDirId) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            List<Message> rList = new ArrayList<Message>();
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("SELECT Message.* FROM Message JOIN Directory WHERE Directory.id=dir_id AND dir_id=? ORDER BY Message.id DESC;");
            pstn.setInt(1, givenDirId);
            res = pstn.executeQuery();
            while (res.next()) {
                Message addMesg = new Message();
                addMesg.setId(res.getInt("id"));
                addMesg.setHeader(res.getString("header"));
                addMesg.setDirId(res.getInt("dir_id"));
                addMesg.setPostDate(res.getDate("post_date"));
                addMesg.setAuthId(res.getInt("auth_id"));
                addMesg.setIsUrgent(res.getBoolean("is_urgent"));
                addMesg.setBody(res.getString("body"));
                rList.add(addMesg);
            }
            return rList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (pstn != null) {
                    pstn.close();
                }
                Utils.closeConnection(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private Boolean insert(Message givenMessage) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("INSERT INTO Message (header, dir_id, post_date, auth_id, is_urgent, body) VALUES(?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstn.setString(1, givenMessage.getHeader());
            pstn.setInt(2, givenMessage.getDirId());
            pstn.setDate(3, new java.sql.Date(givenMessage.getPostDate().getTime()));
            pstn.setInt(4, givenMessage.getAuthId());
            pstn.setBoolean(5, givenMessage.getIsUrgent());
            pstn.setString(6, givenMessage.getBody());
            pstn.executeUpdate();
            res = pstn.getGeneratedKeys();
            res.next();
            givenMessage.setId(res.getInt(1));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (pstn != null) {
                    pstn.close();
                }
                Utils.closeConnection(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
