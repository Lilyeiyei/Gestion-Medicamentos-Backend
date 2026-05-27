package co.vinni.Medicamento.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import co.vinni.Medicamento.model.Medicamento;
import co.vinni.Medicamento.repository.MedicamentoRepository;
import java.time.LocalDate;

@ApplicationScoped
public class MedicamentoService {

    @Inject
    MedicamentoRepository repository;

    @Transactional
    public Medicamento registrar(Medicamento medicamento){

        // VALIDACIONES

        if(medicamento.id == null){
            throw new WebApplicationException("ID obligatorio", 400);
        }

        if(medicamento.id <= 0){
            throw new WebApplicationException("El ID debe ser mayor a 0", 400);
        }

        Medicamento existeId = repository.findById(medicamento.id);

        if(existeId != null){
            throw new WebApplicationException("El ID ya existe", 400);
        }

        if(medicamento.nombreMedicamento == null || medicamento.nombreMedicamento.isEmpty()){
            throw new WebApplicationException("Nombre obligatorio", 400);
        }

        if(medicamento.miligramos == null || medicamento.miligramos.isEmpty()){
            throw new WebApplicationException("Miligramos obligatorios", 400);
        }

        if(medicamento.dosis == null || medicamento.dosis.isEmpty()){
            throw new WebApplicationException("Dosis obligatoria", 400);
        }

        if(medicamento.frecuencia == null || medicamento.frecuencia.isEmpty()){
            throw new WebApplicationException("Frecuencia obligatoria", 400);
        }

        if(medicamento.horario == null || medicamento.horario.isEmpty()){
            throw new WebApplicationException("Horario obligatorio", 400);
        }

        if(medicamento.fechaVencimiento == null){
            throw new WebApplicationException("Fecha obligatoria", 400);
        }

        // VALIDAR QUE NO ESTÉ VENCIDO
        if(medicamento.fechaVencimiento.isBefore(LocalDate.now())){
            throw new WebApplicationException("Medicamento vencido", 400);
        }

        // VALIDAR DUPLICADO
        Medicamento existe = repository.find("nombreMedicamento", medicamento.nombreMedicamento).firstResult();

        if(existe != null){
            throw new WebApplicationException("Medicamento ya se encuentra registrado", 400);
        }

        repository.persist(medicamento);

        return medicamento;
    }
}