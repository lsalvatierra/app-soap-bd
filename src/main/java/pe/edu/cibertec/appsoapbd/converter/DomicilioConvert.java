package pe.edu.cibertec.appsoapbd.converter;

import org.springframework.stereotype.Component;
import pe.edu.cibertec.appsoapbd.model.Domicilio;
import pe.edu.cibertec.ws.objects.Domiciliows;

import java.util.ArrayList;
import java.util.List;

@Component
public class DomicilioConvert {

    public Domicilio convertDomicilioWsToDomicilio(Domiciliows domiciliows) {
        Domicilio domicilio = new Domicilio();
        domicilio.setIddomicilio(domiciliows.getIddomicilio());
        domicilio.setDescdomicilio(domiciliows.getDescdomicilio());
        domicilio.setNrodomicilio(domiciliows.getNrodomicilio());
        domicilio.setRefdomicilio(domiciliows.getRefdomicilio());
        return domicilio;
    }

    public Domiciliows convertDomicilioToDomicilioWs(Domicilio domicilio) {
        Domiciliows domiciliows = new Domiciliows();
        domiciliows.setIddomicilio(domicilio.getIddomicilio());
        domiciliows.setDescdomicilio(domicilio.getDescdomicilio());
        domiciliows.setNrodomicilio(domicilio.getNrodomicilio());
        domiciliows.setRefdomicilio(domicilio.getRefdomicilio());
        return domiciliows;
    }


    public List<Domicilio> convertDomicilioWsToDomicilio(List<Domiciliows> domiciliowsList) {
        List<Domicilio> domicilioList = new ArrayList<Domicilio>();
        for (Domiciliows domiciliows : domiciliowsList) {
            domicilioList.add(convertDomicilioWsToDomicilio(domiciliows));
        }
        return domicilioList;
    }

    public List<Domiciliows> convertDomicilioToDomicilioWs(List<Domicilio> domicilioList) {
        List<Domiciliows> domiciliowsList = new ArrayList<Domiciliows>();
        for (Domicilio domicilio : domicilioList) {
            domiciliowsList.add(convertDomicilioToDomicilioWs(domicilio));
        }
        return domiciliowsList;
    }

}
