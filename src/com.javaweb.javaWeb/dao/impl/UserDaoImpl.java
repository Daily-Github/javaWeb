package com.javaweb.javaWeb.dao.impl;

import com.javaweb.javaWeb.dao.UserDao;
import com.javaweb.javaWeb.entity.User;
import com.javaweb.javaWeb.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String name, String pwd) {
        boolean flag = false;
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from user where name='"+name+"' and pwd='"+pwd+"'");
            while(rs.next()){
                if(rs.getString("name").equals(name) && rs.getString("pwd").equals(pwd)){
                    flag = true;
                }
            }
            DBconn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;

    }

    @Override
    public boolean register(User user) {
        boolean flag = false;
        DBconn.init();
        int i =DBconn.addUpdDel("insert into user(name,pwd,sex,home,info) " +
                "values('"+user.getName()+"','"+user.getPwd()+"','"+user.getSex()+"','"+user.getHome()+"','"+user.getInfo()+"')");
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;

    }

    @Override
    public List<User> getUserAll() {
        List<User> list = new ArrayList<User>();
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from user");
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setSex(rs.getString("sex"));
                user.setHome(rs.getString("home"));
                user.setInfo(rs.getString("info"));
                list.add(user);
            }
            DBconn.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        DBconn.init();
        String sql = "delete  from user where id="+id;
        int i =DBconn.addUpdDel(sql);
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;

    }

    @Override
    public boolean update(int id, String name, String pwd, String sex, String home, String info) {
        boolean flag = false;
        DBconn.init();
        String sql ="update user set name ='"+name
                +"' , pwd ='"+pwd
                +"' , sex ='"+sex
                +"' , home ='"+home
                +"' , info ='"+info+"' where id = "+id;
        int i =DBconn.addUpdDel(sql);
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;

    }
}
