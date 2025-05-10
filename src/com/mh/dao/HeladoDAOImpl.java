package com.mh.dao;

import com.mh.biz.Helado;
import java.sql.*;
import java.util.ArrayList;

/*

 */
public class HeladoDAOImpl implements HeladoDAO, AutoCloseable {
    static {
        try {
            Class.forName(com.mh.utils.Configuration.DRIVER);
        } catch (ClassNotFoundException ex) {
            System.exit(1);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    Connection con = null;

    public HeladoDAOImpl() throws Exception {
        con = DriverManager.getConnection(com.mh.utils.Configuration.URL);
    }

    @Override
    public Helado getHeladoByPosicion(String p) throws Exception {
        Helado h = null;
        String sql = "select posicion, nombre, precio, tipo, cantidad from helado where posicion = ?";
        ResultSet rs = null;
        try (PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, p);
            rs = pstm.executeQuery();
            if (rs.next()) {
                h = new Helado(rs.getString("posicion"), rs.getString("nombre"),rs.getString("tipo"), rs.getDouble("precio"), rs.getInt("cantidad"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return h;
    }

    @Override
    public int updateHelados(Helado h) throws Exception {
        int n = 0;
        String sql = "UPDATE helado SET cantidad = ?, nombre = ?, tipo = ?, precio = ? WHERE posicion = ?";
        try (PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setInt(1, h.getCantidad());
            pstm.setString(2, h.getNombre());
            pstm.setString(3, h.getTipo());
            pstm.setDouble(4, h.getPrecio());
            pstm.setString(5, h.getPosicion());
            n = pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } 
        return n;
    }

    @Override
    public ArrayList<Helado> getAllHelados() throws Exception {
        ArrayList<Helado> al = new ArrayList<Helado>();
        String sql = "select posicion, nombre, precio, tipo, cantidad from helado ";
        try (PreparedStatement pstm = con.prepareStatement(sql); ResultSet rs = pstm.executeQuery()) {
           while(rs.next()){
               al.add(new Helado(rs.getString("posicion"), rs.getString("nombre"),rs.getString("tipo"), rs.getDouble("precio"), rs.getInt("cantidad")));
           }
        } catch (Exception e) {
            throw e;
        } 
        return al;
    }

    @Override
    public void close() throws Exception {
        if (con != null) {
             con.close();
        }
    }


}
