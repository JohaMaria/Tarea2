/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Domain.Vehicle;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class VehicleFile {

    public RandomAccessFile random;
    private int regsQuantity;// cantidad de resgistros en el archivo
    private int regSize;//tama;o del registro
    private String myFilePath;//ruta

    public VehicleFile(File file) throws IOException {

        myFilePath = file.getPath();

        this.regSize = 140;

        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + "is an invalid file");
        } else {
            random = new RandomAccessFile(file, "rw");
            // necesitamos indicar cuantos registros hay
            this.regsQuantity = (int) Math.ceil((double) random.length() / (double) regSize);
        }
    }//muy importante cerrar nuestros archivos

    public void close() throws IOException {
        random.close();
    }//indicar la cantidad de registro de nuestros archivos

    public int fileSize() {
        return this.regsQuantity;
    }

    //insertar un nuevo registro en una posición específica
    public boolean putValues(int position, Vehicle vehicle) throws IOException {
        //primero verificar que sea valida la inserción

        if (position < 0 && position > this.regsQuantity) {
            System.err.println("1001- Record position is out of bounds");
            return false;
        } else {
            if (vehicle.sizeInBytes() > this.regSize) {
                System.err.println("1002- Record size is out of bounds");
                return false;
            } else {
                //bingo
                random.seek(position * this.regSize);
                random.writeUTF(vehicle.getName());
                random.writeInt(vehicle.getYear());
                random.writeFloat(vehicle.getMileage());
                random.writeBoolean(vehicle.isAmerican());
                random.writeInt(vehicle.getSeries());
                return true;
            }
        }
    }// end method
    // insertar al final del archivo

    public boolean addEndRecord(Vehicle vehicle) throws IOException {
        boolean success = putValues(this.regsQuantity, vehicle);
        if (success) {
            ++this.regsQuantity;
        }//if
        return success;
    }//addEndRecord

    //obtener un estudiante
    public Vehicle getVehicle(int position) throws IOException {
        //validar
        int i = 0;
        Vehicle vehicleTemp = new Vehicle();
        for (i = 0; i < this.regsQuantity; i++) {
            // colocamos el brazo en el brazo en lugar
            for (i = 0; i < this.regsQuantity; i++) {
                random.seek(position * this.regSize);
            }
            //hacemos la lectura

            vehicleTemp.setName(random.readUTF());
            vehicleTemp.setYear(random.readInt());
            vehicleTemp.setMileage(random.readFloat());
            vehicleTemp.setAmerican(random.readBoolean());
            vehicleTemp.setSeries(random.readInt());
            if (vehicleTemp.getName().equalsIgnoreCase("deleted")) {

                return vehicleTemp;

            } else {
                return vehicleTemp;
            }//else if
        }//for

        return vehicleTemp;
    }//getVehicle

    public boolean deleteVehicle(int serie) throws IOException {
        Vehicle myAuto;
        //buscar
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myAuto = this.getVehicle(i);
            //preguntar si es el estudiante que deseo eliminar
            if (myAuto.getSeries() == (serie)) {
                // marcar como eliminado

                myAuto.setName("deleted");
               
                return this.putValues(i, myAuto);
            }//if

        }//for
        return false;

    }//delete

    //retornar una lista de estudiante
    public ArrayList<Vehicle> getAllCar() throws IOException {
        ArrayList<Vehicle> vehicleArray = new ArrayList<Vehicle>();
        for (int i = 0; i < this.regsQuantity; i++) {
            Vehicle sTemp = this.getVehicle(i);

            if (sTemp != null) {
                if (!sTemp.getName().equalsIgnoreCase("deleted")) {
                    vehicleArray.add(sTemp);
                }//if interno
            }//if externo
        }//end for
        return vehicleArray;

    }//getAllCar

    public boolean devolver(Vehicle vehicle) throws IOException {
        return this.putValues(this.regsQuantity, vehicle);
    }//devolver

    //método verifica que la serie se encuentre en el archivo para poder actualizar
    public boolean upDate(int serie, String name, int year, boolean american) throws IOException {
        Vehicle myAuto;
        //buscar
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myAuto = this.getVehicle(i);
            //preguntar si es el estudiante que deseo eliminar
            if (myAuto.getSeries() == (serie)) {
                // marcar como eliminado

                myAuto.setName(name);
                myAuto.setYear(year);
                myAuto.setAmerican(american);
                return this.putValues(i, myAuto);
            }//if

        }//for
        System.err.print("serie no encontrada");
        return false;

    }//update

    //Metodo que al ingresar una serie verifica si la serie ya está en el archivo y si está
    //manda un mensaje indicando que la serie ya se encuentra en el archivo
    public boolean check(int serie) throws IOException {
        Vehicle myAuto;
        //buscar
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            myAuto = this.getVehicle(i);
            //preguntar si es el estudiante que deseo eliminar
            if (myAuto.getSeries() == (serie)) {
                JOptionPane.showMessageDialog(null, "serie repetida");
                return false;
            }//if

        }//for
        return true;
    }//verificar
    
    public boolean compress() throws IOException{
         Vehicle vehicle;
         Vehicle vehicle2;
         VehicleFile vehicleFile;
         
        //buscar
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posicion actual
            vehicle = this.getVehicle(i);
            //preguntar si es el estudiante que deseo eliminar
            if (!vehicle.getName().equalsIgnoreCase("deleted")) {
                // marcar como eliminado
                System.out.print("NP");
                File fileAutomovil2 = new File("./Automovil2.dat");
                vehicleFile= new VehicleFile(fileAutomovil2);
                vehicle2= new Vehicle(vehicle.getName(),vehicle.getYear(),
                        vehicle.getMileage(),vehicle.isAmerican(),vehicle.getSeries());
                vehicleFile.devolver(vehicle2);
                addEndRecord(vehicle2);
            }//if

        }//for
      return true;
    }
}
