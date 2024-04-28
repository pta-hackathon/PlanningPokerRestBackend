package pta.MultistagePoker.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pta.MultistagePoker.Service.BrainstormingServiceImpl;
import pta.MultistagePoker.Service.EstimateRelationServiceImpl;
import pta.MultistagePoker.Service.EstimateServiceImpl;
import pta.MultistagePoker.Service.TicketsServiceImpl;
import pta.MultistagePoker.Service.UserServiceImpl;
import pta.MultistagePoker.dbEntities.Brainstorming;
import pta.MultistagePoker.dbEntities.Estimate;
import pta.MultistagePoker.dbEntities.EstimateRelation;
import pta.MultistagePoker.dbEntities.Tickets;
import pta.MultistagePoker.dbEntities.User;


/*

GET
/userliste
/brainstorming
/tickets
/schaetzungen

POST
/anmelden?user=heinz
/abmelden?user=heinz
/kompetenz?user=heinz&kompetenz=rot
/brainstorming?user=waldemar&text=eine idee
/schaetzung?user=waldemar&ticket=1&minval=3.5&maxval=7.8 
/rateschaetzer?user=waldemar&ticket=1&minval=3.5&maxval=7.8 

/stage
/reset

/calcmittelwert?idticket=1
/calckonsistenz?idticket=1


*/




@CrossOrigin
@RestController
public class PlanningPokerController {

	@Autowired
	UserServiceImpl userimpl;

	@Autowired
	BrainstormingServiceImpl brainimpl;

	@Autowired
	TicketsServiceImpl ticketimpl;

	@Autowired
	EstimateServiceImpl estimateimpl;
	
	@Autowired
	EstimateRelationServiceImpl estimaterelateimpl;
	

	
	
	// Requests bzgl. User
	@GetMapping("/userliste")
	public List<User> getUserListe() {
		return userimpl.getAll();
	}

	@PostMapping("/anmelden")
	public  ResponseEntity<StatusMsg> postUserAnmelden(@RequestParam(name="user") String username) {
		User u = userimpl.findByName(username);
		if (u==null) {
			return new ResponseEntity<>(new StatusMsg("username not found"), HttpStatus.NO_CONTENT);
		} else {
			u.setIsSignedIn(1);
			userimpl.updateUser(u);
			return new ResponseEntity<>(new StatusMsg("ok"), HttpStatus.OK);
		}
	}

	@PostMapping("/abmelden")
	public  ResponseEntity<StatusMsg> postUserAbmelden(@RequestParam(name="user") String username) {
		User u = userimpl.findByName(username);
		if (u==null) {
			return new ResponseEntity<>(new StatusMsg("username not found"), HttpStatus.NO_CONTENT);
		} else {
			u.setIsSignedIn(0);
			userimpl.updateUser(u);
			return new ResponseEntity<>(new StatusMsg("ok"), HttpStatus.OK);
		}
	}
	
	@PostMapping("/kompetenz")
	public ResponseEntity<StatusMsg>  postUserAnmelden(@RequestParam(name="user") String username, @RequestParam(name="kompetenz") String competence) {
		
		User u = userimpl.findByName(username);
		if (u==null) {
			return new ResponseEntity<>(new StatusMsg("username not found"), HttpStatus.NO_CONTENT);
		} else {
			u.setCompetence(competence);
			userimpl.updateUser(u);
			return new ResponseEntity<>(new StatusMsg("ok"), HttpStatus.OK);
		}
	}

	// Requests bzgl. Brainstorming
	@GetMapping("/brainstorming")
	public List<Brainstorming> getBrainstorming() {
		return brainimpl.getAll();
	}	
	
	@PostMapping("/brainstorming")
	public ResponseEntity<StatusMsg>  postBrainstorming(
			@RequestParam(name="user") String username, 
			@RequestParam(name="idticket") int idTicket, 
			@RequestParam(name="text") String text) {
		
		User u = userimpl.findByName(username);
		if (u==null) {
			return new ResponseEntity<>(new StatusMsg("username not found"), HttpStatus.NO_CONTENT);
		}
		Brainstorming b=new Brainstorming();
		b.setIdUser(u.getId());
		b.setIdTicket(idTicket);
		b.setText(text);
		brainimpl.postNew(b);
		return new ResponseEntity<>(new StatusMsg("ok"), HttpStatus.OK);
	}
	
	// Requests bzgl. Tickets
	@GetMapping("/tickets")
	public List<Tickets> getTickets() {
		return ticketimpl.getAll();
	}	
	

	// Request Schätzung abgegeben
	@GetMapping("/schaetzungen")
	public List<Estimate> getEstimates() {
		return estimateimpl.getAll();
	}	
	
	@PostMapping("/schaetzung")
	public ResponseEntity<StatusMsg> postEstimate(
			@RequestParam(name="user") String username, 
			@RequestParam(name="ticket") int idTicket,
			@RequestParam(name="minval") double minval ,
			@RequestParam(name="maxval") double maxval) 
	{
		User u = userimpl.findByName(username);
		if (u==null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		Estimate e=new Estimate();
		e.setId(u.getId());
		e.setIdTicket(idTicket);
		e.setMinVal(minval);
		e.setMaxVal(maxval);
		
		estimateimpl.postNew(e);
		return new ResponseEntity<>(new StatusMsg("ok"), HttpStatus.OK);
	}	

	
	// Request welcher User hat welche Schätzung abgegeben
	@PostMapping("/rateschaetzer")
	public ResponseEntity<Object>  postRateEstimator(
			@RequestParam(name="user") String username,
			@RequestParam(name="tipuser") String tipUsername, 
			@RequestParam(name="idticket") int idTicket,
			@RequestParam(name="idschaetzung") int idschaetzung) 
	{
		User u = userimpl.findByName(username);
		if (u==null) {
			return new ResponseEntity<>(new StatusMsg("username not found"), HttpStatus.NO_CONTENT);
		}
		User tipUser = userimpl.findByName(tipUsername);
		if (tipUser==null) {
			return new ResponseEntity<>(new StatusMsg("tip-username not found"), HttpStatus.NO_CONTENT);
		}
		EstimateRelation er=new EstimateRelation(); 
		er.setIdUser(u.getId());
		er.setIdUserEsteemed(tipUser.getId());
		er.setIdTicket(idTicket);
		er.setIdEstimate(idschaetzung);
		estimaterelateimpl.postNew(er);
		return new ResponseEntity<>(new StatusMsg("ok"), HttpStatus.OK);
	}	
	
	
	// Request: reset! User, Feedback loeschen, ...
	@PostMapping("/reset")
	public ResponseEntity<StatusMsg> reset() {
		List<User> usr=userimpl.getAll();
		for (User e: usr) {
			e.setIsSignedIn(0);
			e.setCompetence(null);
			userimpl.updateUser(e);
		}
		
		brainimpl.deleteAll();
		return new ResponseEntity<>(new StatusMsg("ok"), HttpStatus.OK);
	}

	
	@GetMapping("/stage")
	public ResponseEntity<StatusMsg> getStage() {

		String msg=null;
		List<User> usr=userimpl.getAll();
		for (User e: usr) {
			if (e.getIsSignedIn()==0) msg="warte_logon";
		}
		if (msg==null) {
			for (User e: usr) {
				if (e.getCompetence()==null || e.getCompetence().length()==0) msg="warte_kompetenz";
			}		
		}

		if (msg==null) {
			List<Brainstorming> bsr=brainimpl.getAll();
			List<Integer> ids=new ArrayList<>();
			for (Brainstorming b: bsr) {
				if (ids.indexOf(b.getIdUser())==-1) {
					ids.add(b.getIdUser());
				}
			}
			if (ids.size()<usr.size()) {
				msg="warte_brainstorming";
			}
		}

		if (msg==null) {
			if (estimateimpl.calcKonsistenz(0)<10.0) {
				msg="ende_schaetzung";
			}
		}
		
		if (msg==null) {
			msg="ende";
		}
		return new ResponseEntity<>(new StatusMsg(msg), HttpStatus.OK);
	}	
	
	

	@GetMapping("/calcmittelwert")
	public double getCalcMittelwert(@RequestParam(name="ticket") int idTicket) {
		return estimateimpl.calcMittelwert(idTicket);
	}

	@GetMapping("/calckonsistenz")
	public double getCalcKonsistenz(@RequestParam(name="ticket") int idTicket) {
		return estimateimpl.calcKonsistenz(idTicket);
	}
	
	@GetMapping("/calctabelle")
	public List<TabellenEintrag> getCalcTabelle() {
		List<TabellenEintrag> erg=new ArrayList<>();

		for (Tickets tckt: ticketimpl.getAll()) {
			if (tckt.getActualEffort()>0) {
				for (Estimate est: estimateimpl.getAll()) {
					if (est.getIdTicket()==tckt.getId()) {
						double diff=(est.getMaxVal()+est.getMinVal())/2.0;
						diff = Math.abs(tckt.getActualEffort()-diff);
						double pkt=diff>10 ? 0.0 : 10-diff;

						for (TabellenEintrag te: erg) {
							if (te.getIdUser()==est.getIdUser()) {
								te.addPunkte(pkt);
							}
						}
					}
				}
			}
		}

		for (Brainstorming be: brainimpl.getAll()) {
			for (TabellenEintrag te: erg) {
				if (te.getIdUser()==be.getIdUser()) {
					te.addPunkte(1.0);
				}
			}
		}

		// TODO: Extrapunkte für gut geratene Schätzungen
		
		Collections.sort(erg);
		return erg;
	}
	
}
