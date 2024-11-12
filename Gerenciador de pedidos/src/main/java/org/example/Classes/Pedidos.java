package org.example.Classes;

public class Pedidos {
    private static int geradorID = 1;
    private int id;
    private int mesaId;
    private int funcionarioID;
    private String data;
    private String status;

    public Pedidos() {
    }




    public Pedidos(int mesaId, int funcionarioID, String data, String status) {

        this.id = geradorID++;
        this.mesaId = mesaId;
        this.funcionarioID = funcionarioID;
        this.data = data;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMesaId() {
        return mesaId;
    }

    public void setMesaId(int mesaId) {
        this.mesaId = mesaId;
    }

//    public Mesa getMesa() {
//        return mesa;
//    }
//
//    public void setMesa(Mesa mesa) {
//        this.mesa = mesa;
//        this.mesaId = mesa.getId();
//    }

    public int getFuncionarioID() {
        return funcionarioID;
    }

    public void setFuncionarioID(int funcionarioID) {
        this.funcionarioID = funcionarioID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        {
            return String.format("\nId: %d MesaID: %d FuncionarioID: %d Data: %s Status do pedido: %s", getId(), mesaId, funcionarioID,data, status);
        }
    }
}
