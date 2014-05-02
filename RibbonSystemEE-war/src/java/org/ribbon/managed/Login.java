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

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.ribbon.commands.Router;
import org.ribbon.nwbean.UserFacadeLocal;
import org.ribbon.enteties.User;

/**
 * Login and logout logic of application.
 * @author Stanislav Nepochatov
 */
@Named(value = "login")
@RequestScoped
public class Login {
    
    @Inject
    private UserFacadeLocal usrBean;
    
    private User user = new User();
    
    private String loginError = null;
    
    @ManagedProperty(value="#{ribbonSession}")
    @Inject
    private RibbonSession sesManaged;

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    
    /**
     * Login initiated user.
     * @return redirect address;
     */
    public String login() {
        User findedUser = usrBean.findByLogin(user.getLogin());
        if (findedUser != null && user.getLogin().equals(findedUser.getLogin()) && user.getPassw().equals(findedUser.getPassw())) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", findedUser.getLogin());
            if (findedUser.getIsAdmin()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isAdmin", "true");
            }
            usrBean.performLogin(findedUser);
            sesManaged.setCurrentUser(findedUser);
            return Router.REDIRECT_PAGE;
        } else {
            setLoginError("Користувача не знайдено, чи невірний пароль.");
            return Router.DEFAULT_PAGE;
        }
    }
    
    /**
     * Logout user.
     * @return redirect address;
     */
    public String logout() {
        User logined = sesManaged.getCurrentUser();
        usrBean.performLogout(logined);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return Router.REDIRECT_PAGE;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the loginError
     */
    public String getLoginError() {
        return loginError;
    }

    /**
     * @param loginError the loginError to set
     */
    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }
    
}
