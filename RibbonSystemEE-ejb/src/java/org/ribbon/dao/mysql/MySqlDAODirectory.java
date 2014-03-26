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
import org.ribbon.dao.*;
import org.ribbon.enteties.Directory;
import org.ribbon.service.Utils;

/**
 * MySql implementation of Directory DAO.
 * @author Stanislav Nepochatov
 * @deprecated use JPA, Luke!
 */
public class MySqlDAODirectory implements IDAODirectory {

    @Override
    public boolean save(Directory givenDir) {
        if (givenDir.getId() == -1) {
            return insert(givenDir);
        } else {
            return update(givenDir);
        }
    }

    @Override
    public Directory getDirById(int givenId) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("SELECT * FROM Directory WHERE id=?;");
            pstn.setInt(1, givenId);
            res = pstn.executeQuery();
            res.next();
            Directory addDir = new Directory();
            addDir.setId(res.getInt("id"));
            addDir.setPath(res.getString("path"));
            addDir.setDescription(res.getString("description"));
            addDir.setIsHidden(res.getBoolean("is_hidden"));
            return addDir;
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
    public Directory getDirByPath(String givenPath) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("SELECT * FROM Directory WHERE path=?;");
            pstn.setString(1, givenPath);
            res = pstn.executeQuery();
            res.next();
            Directory addDir = new Directory();
            addDir.setId(res.getInt("id"));
            addDir.setPath(res.getString("path"));
            addDir.setDescription(res.getString("description"));
            addDir.setIsHidden(res.getBoolean("is_hidden"));
            return addDir;
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
    public List<Directory> getAll() {
        Connection conn = null;
        Statement stn = null;
        ResultSet res = null;
        try {
            List<Directory> rList = new ArrayList<Directory>();
            conn = Utils.getConnection();
            stn = conn.createStatement();
            res = stn.executeQuery("SELECT * FROM Directory ORDER BY path ASC;");
            while (res.next()) {
                Directory addDir = new Directory();
                addDir.setId(res.getInt("id"));
                addDir.setPath(res.getString("path"));
                addDir.setDescription(res.getString("description"));
                addDir.setIsHidden(res.getBoolean("is_hidden"));
                rList.add(addDir);
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
    
    /**
     * INTERNAL insert implementation.
     * @param givenDir dir to insert;
     * @return result of operation;
     */
    private boolean insert(Directory givenDir) {
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet res = null;
        try {
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("INSERT INTO Directory (path,description,is_hidden) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstn.setString(1, givenDir.getPath());
            pstn.setString(2, givenDir.getDescription());
            pstn.setBoolean(3, givenDir.getIsHidden());
            pstn.executeUpdate();
            res = pstn.getGeneratedKeys();
            res.next();
            givenDir.setId(res.getInt(1));
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
     * INTERNAL update implementation.
     * @param givenDir dir to update;
     * @return result of operation;
     */
    private boolean update(Directory givenDir) {
        Connection conn = null;
        PreparedStatement pstn = null;
        try {
            conn = Utils.getConnection();
            pstn = conn.prepareStatement("UPDATE Directory SET path=?,description=?,is_hidden=? WHERE id=?;");
            pstn.setString(1, givenDir.getPath());
            pstn.setString(2, givenDir.getDescription());
            pstn.setBoolean(3, givenDir.getIsHidden());
            pstn.setInt(4, givenDir.getId());
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
