package com.mv.schelokov.car_rent.controller.actions;

import com.mv.schelokov.car_rent.controller.consts.SessionAttr;
import com.mv.schelokov.car_rent.controller.exceptions.ActionException;
import com.mv.schelokov.car_rent.model.entities.User;
import com.mv.schelokov.car_rent.model.services.UserService;
import com.mv.schelokov.car_rent.model.services.exceptions.ServiceException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Maxim Chshelokov <schelokov.mv@gmail.com>
 */
public class Login extends AbstractAction {
    
    public static final Logger LOG = Logger.getLogger(Login.class);

    @Override
    public JspForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        UserService userService = new UserService();
        String login = req.getParameter("email");
        String password = req.getParameter("pass");
        try {
            List userList = userService.getUserByCredentials(login, password);
            if (userList.size() == 1) {
                User user = (User) userList.get(0);
                HttpSession session = req.getSession();
                session.setAttribute(SessionAttr.USER, user);
                if (isAdmin(req))
                    return new JspForward("action/admin_actions", true);
                else
                    return new JspForward("action/home", true);
            } else {
                req.setAttribute("errorLogin", 1);
                req.setAttribute("logn", true);
                return new JspForward("action/home", true);
            }
        } catch (ServiceException ex) {
            LOG.error("Failed to login", ex);
            throw new ActionException("Failed to login", ex);
        }
    }
    
}
