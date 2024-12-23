package org.example.Classes;

public class Pedidos {
    private static int geradorID = 1;
    private int id;
    private int mesaId;
    private int funcionarioID;
    private String data;
    private String status;
    private int estoqueID;

    public Pedidos() {
    }

    public Pedidos(int mesaId, int funcionarioID, String data, String status, int estoqueID) {

        this.id = geradorID++;
        this.mesaId = mesaId;
        this.funcionarioID = funcionarioID;
        this.data = data;
        this.status = status;
        this.estoqueID = estoqueID;
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

    public int getEstoqueID() {
        return estoqueID;
    }

    public void setEstoqueID(int estoqueID) {
        this.estoqueID = estoqueID;
    }

    @Override
    public String toString() {
            return String.format("\nid: %d, mesa: %d, funcionario: %d, data: %s, status %s, estoque: %d", getId(), mesaId, funcionarioID,data, status, estoqueID);
    }
}
