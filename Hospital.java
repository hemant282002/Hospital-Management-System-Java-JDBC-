package HospitalJDBC;

public class Hospital {
    private String patient_id;
/*
    public Hospital(String patient_id, String patient_name, String disease, String doctor_name, Double fees, String private_ward, String general_ward) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.disease = disease;
        this.doctor_name = doctor_name;
        this.fees = fees;
        this.private_ward = private_ward;
        this.general_ward = general_ward;
    }*/
    public Hospital(){}
    private String patient_name;
    private String disease;
    private String doctor_name;
    private Double fees;
    private String private_ward;
    private String general_ward;

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public String getPrivate_ward() {
        return private_ward;
    }

    public void setPrivate_ward(String private_ward) {
        this.private_ward = private_ward;
    }

    public String getGeneral_ward() {
        return general_ward;
    }

    public void setGeneral_ward(String general_ward) {
        this.general_ward = general_ward;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "patient_id='" + patient_id + '\'' +
                ", patient_name='" + patient_name + '\'' +
                ", disease='" + disease + '\'' +
                ", doctor_name='" + doctor_name + '\'' +
                ", fees=" + fees +
                ", private_ward='" + private_ward + '\'' +
                ", general_ward='" + general_ward + '\'' +
                '}';
    }
}
