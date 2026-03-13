package HospitalJDBC;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalController {


    private Connection connection;
    public HospitalController(Connection connection) {
        this.connection = connection;
    }
    Hospital hospital=new Hospital();
    PreparedStatement prepStat;
    Scanner sc1 = new Scanner(System.in); //For integer
    Scanner sc2 = new Scanner(System.in); //For Strings
    public void createTable() throws IOException,SQLException{
        String SERIAL = "P001";
        prepStat=connection.prepareStatement("Create table hospital (pid SERIAL primary key, pname varchar(30) not null, pdisease varchar(20) not null, dname varchar(30) not null, fees double precision not null, pward_no varchar(10), gward_no varchar(10))");
//        prepStat.executeUpdate("Create table hospital (pid SERIAL primary key, pname varchar(30) not null, pdisease varchar(20) not null, dname varchar(30) not null, fees double precision not null, pward_no varchar(10), gward_no varchar(10))");
       prepStat.executeUpdate();

        System.out.println("Table created successfully. ");
    }
    public void createPatient() throws SQLException {
        System.out.println("enter patient name");
        hospital.setPatient_name(sc2.next());
        System.out.println("Disease");
        hospital.setDisease(sc2.next());
        System.out.println("Enter Doctor name");
        hospital.setDoctor_name(sc2.next());
        System.out.println("Enter fess");
        hospital.setFees(sc1.nextDouble());
        System.out.println("Enter private ward number : ");
        hospital.setPrivate_ward(sc2.next());
        System.out.println("Enter General ward number : ");
        hospital.setGeneral_ward(sc1.next());

        prepStat=connection.prepareStatement("insert into hospital (pname, pdisease, dname, fees, pward_no, gward_no) values(?, ?, ?, ?, ?, ?)");

        //prepStat.executeUpdate();
        prepStat.setString(1,hospital.getPatient_name());
        prepStat.setString(2,hospital.getDisease());
        prepStat.setString(3,hospital.getDoctor_name());
        prepStat.setDouble(4,hospital.getFees());
        prepStat.setString(5,hospital.getPrivate_ward());
        prepStat.setString(6,hospital.getGeneral_ward());
        prepStat.executeUpdate();
        System.out.println("Row inserted successfully.");

    }
    public void findDoctorName() throws SQLException{
        prepStat = connection.prepareStatement("Select * from hospital where dname = ?;");
        System.out.println("Enter Doctor name : ");
        String dname = sc2.next();
        prepStat.setString(1,dname);
        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()){
            System.out.println(resultSet.getInt("pid") + " " + resultSet.getString("pname") + " "  + resultSet.getString("pdisease") + " " + resultSet.getDouble("fees") +" " + resultSet.getString("pward_no") + " " + resultSet.getString("gward_no"));
            System.out.println("data retrieved Successfully. ");
        }
        else {
            System.out.println("Invalid Doctor name ! ! !");
        }
    }
    public void ChangeDoctorName() throws SQLException{
        String pname,dname;
        System.out.println("Enter Patient name who want to change his Doctor : ");
        pname = sc2.next();
        System.out.println("Which Doctor you want to Refer : ");
        dname = sc2.next();
        prepStat = connection.prepareStatement("Update hospital set dname = ? where pname = ?;");
        prepStat.setString(1, dname);
        prepStat.setString(2, pname);
        prepStat.executeUpdate();
        System.out.println("New Doctor assigned to the Patient successfully. ");
    }
    public void PrintPatientDetails() throws SQLException{
        prepStat = connection.prepareStatement("Select * from hospital where pname = ?;");
        System.out.println("Enter Patient name whose Details you want : ");
        hospital.setPatient_name(sc2.next());
        prepStat.setString(1, hospital.getPatient_name());
        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()){
            System.out.println(resultSet.getInt("pid") + " " + resultSet.getString("pdisease") + " "+ resultSet.getString("dname") + " " + resultSet.getDouble("fees") + " " + resultSet.getString("pward_no") + " " + resultSet.getString("gward_no"));
            System.out.println("Patient " + hospital.getPatient_name() + "founded Successfully.");
        }
        else {
            System.out.println("Patient not found ! ! " + hospital.getPatient_name());
        }
    }
    public void SameDiseasePatient() throws SQLException{
        System.out.println("Enter Disease : ");
        hospital.setDisease(sc2.next());
        prepStat = connection.prepareStatement("Select * from hospital where pdisease = ?;");
        prepStat.setString(1, hospital.getDisease());
        ResultSet resultSet = prepStat.executeQuery();
        if(true){
            while (resultSet.next()){
                System.out.println(resultSet.getInt("pid") + " " + resultSet.getString("pname") + " " + resultSet.getString("dname") + " " + resultSet.getDouble("fees") + " " + resultSet.getString("pward_no") + " " + resultSet.getString("gward_no"));
            }
            System.out.println("Patient with same Disease ");
        }
        else{
            System.out.println("No patient found of " + hospital.getDisease());
        }
    }
    public void DiscountInFees() throws SQLException{
        System.out.println("Enter Patient Id to whom discount to offer : ");
        int pid = sc1.nextInt();
        System.out.println("Enter the amount to be Discounted : ");
        double discount = sc1.nextDouble();
        prepStat = connection.prepareStatement("Update hospital set fees = fees - ? where pid = ?;");
        prepStat.setDouble(1, discount);
        prepStat.setInt(2, pid);
        prepStat.executeUpdate();
        System.out.println("Discount of Rs. " + discount + " given to the patient " + pid);
    }
    public void PatientDetails() throws SQLException{
        prepStat = connection.prepareStatement("Select * from hospital;");
        ResultSet resultSet = prepStat.executeQuery();
        System.out.println("+-------------+----------------+-----------+---------------+----------+------------------+-------------------+");
        System.out.println("| Patient Id  |  Patient Name  |  Disease  |  Doctor Name  |  fees    |  Private ward_no |  General ward_no  |");
        while (resultSet.next()){
            System.out.println(" \t\t" + resultSet.getInt("pid") + " \t\t\t " + resultSet.getString("pname") + " \t\t\t " + resultSet.getString("pdisease") + " \t\t\t " + resultSet.getString("dname") + " \t\t\t"+ resultSet.getDouble("fees") +"\t\t\t" + resultSet.getString("pward_no") + "\t\t\t " + resultSet.getString("gward_no"));
        }
    }
    public void PatientWardGeneral() throws SQLException{
        prepStat = connection.prepareStatement("Select pid, pname, pdisease, dname, fees, gward_no from hospital;");
        ResultSet resultSet = prepStat.executeQuery();
        System.out.println("+-------------+----------------+-----------+---------------+----------+-------------------+");
        System.out.println("| Patient Id  |  Patient Name  |  Disease  |  Doctor Name  |  fees    |  General ward_no  |");
        while (resultSet.next()){
            System.out.println(" \t\t "+ resultSet.getInt("pid") + " \t\t\t " + resultSet.getString("pname") + " \t\t\t " + resultSet.getString("pdisease") + " \t\t" + resultSet.getString("dname") + " \t\t "+ resultSet.getDouble("fees")  + "\t\t\t " + resultSet.getString("gward_no"));
        }
        System.out.println("General ward Patient Details.");
    }
    public void PatientWardPrivate() throws SQLException{

        prepStat = connection.prepareStatement("Select pid, pname, pdisease, dname, fees, pward_no from hospital;");
        ResultSet resultSet = prepStat.executeQuery();
        System.out.println("+-------------+----------------+-----------+---------------+----------+-------------------+");
        System.out.println("| Patient Id  |  Patient Name  |  Disease  |  Doctor Name  |  fees    |  Private ward_no  |");
        while (resultSet.next()){
            System.out.println(" \t\t "+ resultSet.getInt("pid") + " \t\t\t " + resultSet.getString("pname") + " \t\t\t" + resultSet.getString("pdisease") + "\t\t\t " + resultSet.getString("dname") + " \t\t\t "+ resultSet.getDouble("fees")  + "\t\t\t" + resultSet.getString("pward_no"));
        }
        System.out.println("Private ward Patient Details.");
    }
    public void DischargePatient() throws SQLException{
        String pname;
        System.out.println("Enter Patient Name You Want to Discharge :");
        pname = sc2.next();
        prepStat = connection.prepareStatement("Delete from hospital where pname = ?");
        prepStat.setString(1, pname);
        prepStat.executeUpdate();
        System.out.println("Patient Discharged");


    }

}
