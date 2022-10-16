package olorini.db;

import javax.persistence.*;

@Entity(name = "deliveries")
@Table(name = "deliveries")
public class DeliveryEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="drone_id")
    private DroneEntity drone;

    @OneToOne
    @JoinColumn(name="medication_id")
    private MedicationEntity medication;

    public DeliveryEntity() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DroneEntity getDrone() {
        return drone;
    }

    public void setDrone(DroneEntity drone) {
        this.drone = drone;
    }

    public MedicationEntity getMedication() {
        return medication;
    }

    public void setMedication(MedicationEntity medication) {
        this.medication = medication;
    }
}
