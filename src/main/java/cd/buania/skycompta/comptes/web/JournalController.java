package cd.buania.skycompta.comptes.web;

import cd.buania.skycompta.comptes.dto.JournalRequest;
import cd.buania.skycompta.comptes.dto.JournalResponse;
import cd.buania.skycompta.comptes.model.Classe;
import cd.buania.skycompta.comptes.model.Journal;
import cd.buania.skycompta.comptes.service.ClasseService;
import cd.buania.skycompta.comptes.service.JournalService;
import cd.buania.skycompta.shared.info.INFO_SHARED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(INFO_SHARED.REQUEST_END+ "journaux/")
@CrossOrigin(INFO_SHARED.AUTHORIZE_ORIGIN_FROM)
public class JournalController {
    @Autowired
    private JournalService journalService;

    @PostMapping("new")
    public boolean createJournal(@RequestBody JournalRequest journal) {


        return journalService.createJournal(journal);
    }


    @GetMapping("all")
    public List<JournalResponse> getAllJournauxs() {
        return journalService.getAllJournaux();
    }
    // Other endpoints for update, delete, etc.
}