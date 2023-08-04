package gestione_dispositivi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import gestione_dispositivi.model.Utente;
import gestione_dispositivi.repository.DispositivoDaoRepository;
import gestione_dispositivi.repository.UtenteDaoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UtenteService {

	@Autowired
	private UtenteDaoRepository utenteRepo;

	@Autowired
	private DispositivoDaoRepository dispositivoRepo;

	public Utente salvaUtente(Utente u) {
		if (utenteRepo.existsByEmail(u.getEmail()) || utenteRepo.existsByUsername(u.getUsername())) {
			throw new EntityExistsException("Username o Email già esistenti!");
		}
		utenteRepo.save(u);
		return u;
	}

	public void salvaFakeUtente() {
		salvaUtente(new Utente());
	}

	public String rimuoviUtente(Long id) {
		if (!utenteRepo.existsById(id)) {
			throw new EntityNotFoundException("L'UTENTE cercato è inesistente!");
		}
		if (!dispositivoRepo.findByUtente(utenteRepo.findById(id).get()).isEmpty()) {
			throw new EntityExistsException("L'UTENTE non può essere eliminato dal DB in quanto ha un dispositivo assegnato!");
		}
		utenteRepo.deleteById(id);
		return "UTENTE eliminato dal DB!";
	}

	public Object findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object aggiornaUtente(Utente u) {
		// TODO Auto-generated method stub
		return null;
	}


}
