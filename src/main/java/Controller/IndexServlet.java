package Controller;

import Dao.DaoDateInterval;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class IndexServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DaoDateInterval dao = new DaoDateInterval();
        Date dateBeginAV6 = dao.getDateBeginAV6();
        Date dateEndAV6 = dao.getDateEndAV6();
        Date dateBeginAV6Ext = dao.getDateBeginAV6Ext();
        Date dateEndAV6Ext = dao.getDateEndAV6Ext();
        if (dateBeginAV6 == null || dateEndAV6 == null || dateBeginAV6Ext == null || dateEndAV6Ext == null) {
            request.getRequestDispatcher("upload.jsp").forward(request, response);
        } else {
            long dateBegin = dateBeginAV6.getTime() > dateBeginAV6Ext.getTime() ? dateBeginAV6.getTime() : dateBeginAV6Ext.getTime();
            long dateEnd = dateEndAV6.getTime() < dateEndAV6Ext.getTime() ? dateEndAV6.getTime() : dateEndAV6Ext.getTime();
            request.setAttribute("DateBegin", dateBegin);
            request.setAttribute("DateEnd", dateEnd);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
