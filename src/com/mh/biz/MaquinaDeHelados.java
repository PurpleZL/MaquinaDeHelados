package com.mh.biz;

import com.mh.exceptions.QuantityExceededException;
import com.mh.exceptions.NotValidPositionException;
import com.mh.exceptions.NotEnoughMoneyException;
import com.mh.dao.VentaDAOImpl;
import com.mh.dao.HeladoDAOImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import com.mh.utils.Utils;

public class MaquinaDeHelados {
    private double monedero = 0;
    private double ingresos = 0;

    public Helado pedirHelado(String posicion) throws Exception {
        Helado h;
        Venta v;
        try (HeladoDAOImpl heladoImpl = new HeladoDAOImpl(); VentaDAOImpl ventaImpl = new VentaDAOImpl();) {
            h = heladoImpl.getHeladoByPosicion(posicion);
            if (h == null) {
                throw new NotValidPositionException();
            } else if (h.getCantidad() <= 0) {
                throw new QuantityExceededException();
            } else if (h.getPrecio() > monedero) {
                throw new NotEnoughMoneyException();
            } else {
                h.setCantidad(h.getCantidad() - 1);
                this.setMonedero(Utils.redondeoDosDecimales(this.getMonedero() - h.getPrecio()));
                this.setIngresos(Utils.redondeoDosDecimales(this.getIngresos() + h.getPrecio()));
                heladoImpl.updateHelados(h);
                v = new Venta(LocalDate.now().toString(), posicion, h.getNombre(), h.getPrecio(), h.getTipo(), 1);
                ventaImpl.insertVenta(v);
            }
        } catch (Exception e) {
            throw e;
        }
        return h;
    }

    @Override
    public String toString() {
        String s = "";
        ArrayList<Helado> helados;
        try (HeladoDAOImpl he = new HeladoDAOImpl()) {
            helados = he.getAllHelados();
            for (Helado value : helados) {
                s += value.toString() + "\n";
            }
        } catch (Exception e) {
        }
        return s;
    }

    public ArrayList<Helado> getHelados() throws Exception {
        try (HeladoDAOImpl h = new HeladoDAOImpl();) {
            return h.getAllHelados();
        } catch (Exception e) {
            throw e;
        }
    }
     public ArrayList<Venta> getVentas() throws Exception {
        try (VentaDAOImpl v = new VentaDAOImpl();) {
            return v.getVentas();
        } catch (Exception e) {
            throw e;
        }
    }
    public double getPrecio(String posicion) throws Exception{
        double p = 0;
        Helado h;
          try (HeladoDAOImpl heladoImpl = new HeladoDAOImpl();) {
            h = heladoImpl.getHeladoByPosicion(posicion);
            p = h.getPrecio();
          } catch (Exception e){
               throw e;
          }
        return p;
    }
    public String getTipo(String posicion) throws Exception{
        String s;
        Helado h;
          try (HeladoDAOImpl heladoImpl = new HeladoDAOImpl();) {
            h = heladoImpl.getHeladoByPosicion(posicion);
           s = h.getNombre();
          } catch (Exception e){
               throw e;
          }
        return s;
    }

    public double getMonedero() {
        return Utils.redondeoDosDecimales(monedero);
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setMonedero(double monedero) {
        this.monedero = Utils.redondeoDosDecimales(monedero);
    }

    public void setIngresos(double ingresos) {
        this.ingresos = Utils.redondeoDosDecimales(ingresos);
    }

}
