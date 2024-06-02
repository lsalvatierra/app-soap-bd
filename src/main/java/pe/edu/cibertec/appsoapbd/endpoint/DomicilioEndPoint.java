package pe.edu.cibertec.appsoapbd.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pe.edu.cibertec.appsoapbd.converter.DomicilioConvert;
import pe.edu.cibertec.appsoapbd.exception.NotFoundException;
import pe.edu.cibertec.appsoapbd.model.Domicilio;
import pe.edu.cibertec.appsoapbd.repository.DomicilioRepository;
import pe.edu.cibertec.ws.objects.*;

import java.util.List;
import java.util.Optional;

@Endpoint
public class DomicilioEndPoint {

    private static final String NAMESPACE_URI = "http://www.cibertec.edu.pe/ws/objects";

    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private DomicilioConvert domicilioConvert;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDomiciliosRequest")
    @ResponsePayload
    public GetDomiciliosResponse getDomicilios(@RequestPayload GetDomiciliosRequest request) {
        GetDomiciliosResponse response = new GetDomiciliosResponse();
        List<Domicilio> domicilios = domicilioRepository.findAll();
        List<Domiciliows> domiciliowsList = domicilioConvert.convertDomicilioToDomicilioWs(domicilios);
        response.getDomicilios().addAll(domiciliowsList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDomicilioRequest")
    @ResponsePayload
    public GetDomicilioResponse getDomicilio(@RequestPayload GetDomicilioRequest request) {
        GetDomicilioResponse response = new GetDomicilioResponse();
        Domicilio domicilio = domicilioRepository.findById(request.getId()).orElse(null);
        if(domicilio == null){
            throw new NotFoundException("El domicilio con el ID" + request.getId() +" no existe!");        }
        Domiciliows domiciliows = domicilioConvert.convertDomicilioToDomicilioWs(domicilio);
        response.setDomicilio(domiciliows);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postDomicilioRequest")
    @ResponsePayload
    public PostDomicilioResponse saveDomicilio(@RequestPayload PostDomicilioRequest request) {
        PostDomicilioResponse response = new PostDomicilioResponse();
        Domicilio nuevoDomicilio = domicilioConvert.convertDomicilioWsToDomicilio(request.getDomicilio());
        Domicilio domicilio = domicilioRepository.save(nuevoDomicilio);
        if(domicilio == null){
            throw new NotFoundException("No se pudo crear el domicilio.");        }
        Domiciliows domiciliows = domicilioConvert.convertDomicilioToDomicilioWs(domicilio);
        response.setDomicilio(domiciliows);
        return response;
    }
}
