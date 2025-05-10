package com.mh.dao;

/*

 */
import java.sql.*;
import java.util.ArrayList;
import com.mh.biz.Venta;

public class VentaDAOImpl implements VentaDAO, AutoCloseable{
    
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

    public VentaDAOImpl() throws Exception {
        con = DriverManager.getConnection(com.mh.utils.Configuration.URL);
    }

    @Override
    public int insertVenta(Venta v) throws Exception {
        String sql = "INSERT INTO venta VALUES(datetime('now', 'localtime'),?,?,?,?,?)";
        int r = 0;
        try (PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, v.getPosicion());
            pstm.setString(2, v.getNombre());
            pstm.setDouble(3, v.getPrecio());
            pstm.setString(4, v.getTipo());
            pstm.setInt(5, v.getCantidad());
            r = pstm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return r;
    }

    @Override
    public ArrayList<Venta> getVentas() throws Exception {
        ArrayList al = new ArrayList();
        Venta v;
        ResultSet rs = null;
        String sql = "select fecha_hora, posicion, nombre, precio, tipo, cantidad from venta";
        try (PreparedStatement stm = con.prepareStatement(sql);) {
            rs = stm.executeQuery();
            while (rs.next()) {
                v = new Venta(rs.getString("fecha_hora"), rs.getString("posicion"), rs.getString("nombre"), rs.getDouble("precio"),
                        rs.getString("tipo"), rs.getInt("cantidad"));
                al.add(v);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
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
