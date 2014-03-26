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
import org.ribbon.enteties.Group;
import org.ribbon.service.*;
import org.ribbon.dao.*;
import java.sql.*;

/**
 * MySql implementation of Group DAO.
 * @author Stanislav Nepochatov
 * @deprecated use JPA, Luke!
 */
public class MySqlDAOGroup implements IDAOGroup {

    @Override
    public boolean save(Group givenGroup) {
        if (givenGroup.getId() == -1) {
            return insert(givenGroup);
        } else {
            
        }
        return false;
    }

    @Override
    public Group getGroupById(int givenId) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            Group newGroup = new Group();
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("SELECT * FROM Groups WHERE id=?;");
            pstn.setInt(1, givenId);
            res = pstn.executeQuery();
            res.next();
            newGroup.setId(res.getInt("id"));
            newGroup.setName(res.getString("name"));
            newGroup.setDescription(res.getString("description"));
            return newGroup;
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
    public Group getGroupByName(String givenName) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            Group newGroup = new Group();
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("SELECT * FROM Groups WHERE name=?;");
            pstn.setString(1, givenName);
            res = pstn.executeQuery();
            res.next();
            newGroup.setId(res.getInt("id"));
            newGroup.setName(res.getString("name"));
            newGroup.setDescription(res.getString("description"));
            return newGroup;
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
    public List<Group> getAll() {
        Connection conn = null;
        Statement stn = null;
        ResultSet res = null;
        try {
            List<Group> rList = new ArrayList<Group>();
            conn = Utils.getConnection();
            stn = conn.createStatement();
            res = stn.executeQuery("SELECT * FROM Groups;");
            while (res.next()) {
                Group addGroup = new Group();
                addGroup.setId(res.getInt("id"));
                addGroup.setName(res.getString("name"));
                addGroup.setDescription(res.getString("description"));
                rList.add(addGroup);
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
                if (stn != null) {
                    stn.close();
                }
                Utils.closeConnection(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Group> getGroupsByUserId(int givenUserId, Boolean includeDisabled) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * INTERNAL insert implementation;
     * @param givenGroup group to insert.
     * @return result of operation;
     */
    private boolean insert(Group givenGroup) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("INSERT INTO Groups (name,description) VALUES(?,?);", Statement.RETURN_GENERATED_KEYS);
            pstn.setString(1, givenGroup.getName());
            pstn.setString(2, givenGroup.getDescription());
            pstn.executeUpdate();
            res = pstn.getGeneratedKeys();
            res.next();
            givenGroup.setId(res.getInt(1));
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
    
    /**
     * Internal update implementation.
     * @param givenGroup group to update;
     * @return result of operation;
     */
    private boolean update(Group givenGroup) {
        Connection conn = null;
        PreparedStatement pstn = null;
        try {
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("UPDATE Groups SET name=?, description=? WHERE id=?;");
            pstn.setString(1, givenGroup.getName());
            pstn.setString(2, givenGroup.getDescription());
            pstn.setInt(3, givenGroup.getId());
            pstn.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
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
