package com.mh.dao;

import com.mh.biz.Helado;
import java.util.ArrayList;

/*
Interfaz de HeladoDAOImpl
 */
public interface HeladoDAO {
    public ArrayList<Helado> getAllHelados() throws Exception;
    public Helado getHeladoByPosicion(String posicion) throws Exception;
    public int updateHelados(Helado h) throws Exception;
}
                  