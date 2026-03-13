package HospitalJDBC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class HospitalMenuJDBC {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        String str = "y";
        int private_ward, general_ward ,choice;
        String patient_id, patient_name, disease, doctor_name;
        double fees;

        Properties prop = new Properties();
        prop.load(new FileInputStream("connection.properties"));
        Connection conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        HospitalController hospitalController = new HospitalController(conn);
        PreparedStatement prepStat;
//        hospitalController.createTable();
        do{
            System.out.println("Enter your choice : ");
            System.out.println("Enter 1 for create patient : ");
            System.out.println("Enter 2 for Doctor Name : ");
            System.out.println("Enter 3 for for changing Doctor name : ");
            System.out.println("Enter 4 for Print patient Details : ");
            System.out.println("Enter 5 for Patient Details with same Disease : ");
            System.out.println("Enter 6 for Discount in fess : ");
            System.out.println("Enter 7 for Patient Discharge : ");
            System.out.println("Enter 8 to view General Ward List : ");
            System.out.println("Enter 9 to view Private Ward List : ");
            System.out.println("Enter 10 to Display all patient details : ");
            choice = sc2.nextInt();


            switch (choice){
                case 1 -> {
                   hospitalController.createPatient();
                }
                case 2 -> {
                    hospitalController.findDoctorName();
                }
                case 3 -> {
                    hospitalController.ChangeDoctorName();
                }
                case 4 -> {
                    hospitalController.PrintPatientDetails();
                }
                case 5 -> {
                    hospitalController.SameDiseasePatient();
                }
                case 6 -> {
                    hospitalController.DiscountInFees();
                }
                case 7 -> {
                    hospitalController.DischargePatient();
                }
                case 8 -> {
                    hospitalController.PatientWardGeneral();
                }
                case 9 -> {
                    hospitalController.PatientWardPrivate();
                }
                case 10 -> {
                    hospitalController.PatientDetails();
                }
            }
            System.out.println("Do you want to continue ! ! !");
            str = sc1.next();

        }while (Objects.equals(str,"y") || Objects.equals(str,"Y"));

    }
}
