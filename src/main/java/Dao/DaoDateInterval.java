package Dao;

import JDBC.ConnectionToAV6;

import java.util.Date;

public class DaoDateInterval {
    private final Date dateBeginAV6;
    private final Date dateEndAV6;
    private final Date dateBeginAV6Ext;
    private final Date dateEndAV6Ext;

    public DaoDateInterval() {
        ConnectionToAV6 connectionToAV6 = new ConnectionToAV6();
        this.dateBeginAV6 = connectionToAV6.readDateBeginAV6();
        this.dateEndAV6 = connectionToAV6.readDateEndAV6();
        this.dateBeginAV6Ext = connectionToAV6.readDateBeginAV6Ext();
        this.dateEndAV6Ext = connectionToAV6.readDateEndAV6Ext();
        connectionToAV6.closeConnection();
    }

    public Date getDateBeginAV6() {
        return dateBeginAV6;
    }

    public Date getDateEndAV6() {
        return dateEndAV6;
    }

    public Date getDateBeginAV6Ext() {
        return dateBeginAV6Ext;
    }

    public Date getDateEndAV6Ext() {
        return dateEndAV6Ext;
    }
}
