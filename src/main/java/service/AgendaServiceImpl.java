package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AgendaDao;
import model.Contacto;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaDao agendaDao;


	@Override
	public void agregarContacto(Contacto contacto) throws Exception {
		//añade el contacto si no existe otro con el mismo email
		//si ya existe uno, provoca una excepción
		if(agendaDao.recuperarContacto(contacto.getEmail())==null) {
			agendaDao.agregarContacto(contacto);	
			return;
		}
		throw new Exception("Contacto repetido");
	}
	@Override
	public List<Contacto> recuperarContactos() {
//		try {
//			Thread.sleep(8000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return agendaDao.devolverContactos();
	}

	@Override
	public void actualizarContacto(Contacto contacto) {
		// Si existe, lo actualizamos
		if (agendaDao.recuperarContacto(contacto.getIdContacto()) != null)
			agendaDao.actualizarContacto(contacto);

	}

	@Override
	public boolean eliminarContacto(int idContacto) {
		// Si existe, lo eliminamos.
		if (agendaDao.recuperarContacto(idContacto) != null) {
			agendaDao.eliminarContacto(idContacto);
			return true;
		}
		return true;

	}

	@Override
	public Contacto buscarContacto(int idContacto) {
		return agendaDao.recuperarContacto(idContacto);
	}

}
