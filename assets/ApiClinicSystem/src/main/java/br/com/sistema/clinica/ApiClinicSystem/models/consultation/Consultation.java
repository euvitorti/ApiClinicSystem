package br.com.sistema.clinica.ApiClinicSystem.models.consultation;

import br.com.sistema.clinica.ApiClinicSystem.models.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.models.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Doctor medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    public Consultation(Long id, Doctor doctor, Paciente paciente, LocalDateTime date) {
        this.medico= doctor;
        this.paciente = paciente;
        this.data = date;
    }

    public void cancel(CancellationReason cancellationReason) {
        this.cancellationReason = cancellationReason;
    }


    public Long getId() {
        return id;
    }

    public Doctor getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public CancellationReason getCancellationReason() {
        return cancellationReason;
    }
}
