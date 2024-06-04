/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.playcar.dao;

import br.com.playcar.jdbc.ConnectionFactory;
import br.com.playcar.model.Carros;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author freddy
 */
public class CarrosDAO {
    
    private final Connection con;

    public CarrosDAO() {
        this.con = new ConnectionFactory().getConection();
    }
    
    //metodo cadastrar carro
    public void cadastrarCarro(Carros obj){
        try {
            String sql = "insert into carros(cliente_id,marca,modelo,cor,placa) values(?,?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCliente_id().getId());
            stmt.setString(2, obj.getMarca());
            stmt.setString(3, obj.getModelo());
            stmt.setString(4, obj.getCor());
            stmt.setString(5, obj.getPlaca());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Carro cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro: " + e);
        }
    }
    
    public List<Carros> buscarPorCarros(int t){
        try {
            List<Carros> lista = new ArrayList<>();
            String sql = "select * from carros where cliente_id = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, t);
                   
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Carros obj = new Carros();
                obj.setMarca(rs.getString("marca"));
                obj.setModelo(rs.getString("modelo"));
                obj.setCor(rs.getString("cor"));
                obj.setPlaca(rs.getString("placa"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }
}
