package pe.edu.cibertec.appsoapbd.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pe.edu.cibertec.appsoapbd.converter.DomicilioConvert;
import pe.edu.cibertec.appsoapbd.model.Domicilio;
import pe.edu.cibertec.appsoapbd.repository.DomicilioRepository;
import pe.edu.cibertec.ws.objects.*;

import java.util.List;

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

}
