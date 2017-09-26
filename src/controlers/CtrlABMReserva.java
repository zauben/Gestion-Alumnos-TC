package controlers;

import java.util.ArrayList;

import data.DataElemento;
import data.DataPersona;
import data.DataReserva;
import data.DataTipoElemento;
import entity.Elemento;
import entity.Persona;
import entity.Reserva;
import entity.TipoElemento;
import ui.AMBCReserva;

public class CtrlABMReserva {

	private DataReserva dataRes;
	private DataPersona dataPer;
	private DataElemento dataElem;
	private DataTipoElemento dataTipo;
	private ArrayList<Reserva> res;
	private AMBCReserva iuRe;
	

	
	
public CtrlABMReserva() {
	dataRes = new DataReserva();
	dataElem= new DataElemento();
	dataPer= new DataPersona();
	dataTipo= new DataTipoElemento();
}

public void add(Reserva r) {
	dataRes.add(r);;
}

public void delete(Reserva r){
	dataRes.delete(r);
}

public void update(Reserva r){
	dataRes.update(r);
}


public ArrayList<Reserva> getAll(){
return dataRes.getAll();}

public ArrayList<Persona> getPersonas(){
return dataPer.getAll();}

public void DialogoReserva() {
	iuRe = new AMBCReserva();
	iuRe.start();
	// TODO Auto-generated method stub
	
}



}	