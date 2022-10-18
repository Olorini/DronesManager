package olorini.db;

import javax.persistence.*;

@Entity(name = "loads")
@Table(name = "loads")
public class LoadEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="drone_id")
    private DroneEntity drone;

    @OneToOne
    @JoinColumn(name="medication_id")
    private MedicationEntity medication;

    public LoadEntity() { }

    public LoadEntity(DroneEntity drone, MedicationEntity medication) {
        this.drone = drone;
        this.medication = medication;
    }

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
