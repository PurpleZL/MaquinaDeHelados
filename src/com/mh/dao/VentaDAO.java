package com.mh.dao;

import com.mh.biz.Venta;
import java.util.List;

/*
Interfaz de VentaDAOImpl
 */
public interface VentaDAO {
    public int insertVenta(Venta v) throws Exception;
    public List<Venta> getVentas() throws Exception;
}
