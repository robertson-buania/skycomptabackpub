package cd.buania.skycompta.shared.erreur;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuaniaErreurService {


    @Autowired

    private  BuaniaErreurRepository buaniaErreurRepository;

    public  BuaniaErreur newErreur(BuaniaErreur buaniaErreur){
        return  buaniaErreurRepository.save(buaniaErreur);
    }
}
