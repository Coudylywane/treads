package com.example.scolarite.service;


import java.sql.*;

public class DBHelper {
    private Connection cnx;
    private PreparedStatement pstmt;

    private void openConnection() throws Exception {
        try {
            if (cnx == null || cnx.isClosed()) {
                String url = "jdbc:mysql://localhost:/8888/cours_java";
                cnx = DriverManager.getConnection(url, "root", "root");
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    private void myPreparedQuery(String sql) {
        try {
            pstmt = cnx.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet excuteSelect(String sql, Object[] parameters) throws Exception {
        try {
            openConnection();
            myPreparedQuery(sql);
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject((i + 1), parameters[0]);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
