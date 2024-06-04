/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.playcar.dao;

import br.com.playcar.jdbc.ConnectionFactory;
import br.com.playcar.model.Endereco;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author freddy
 */
public class EnderecoDAO {

    private final Connection con;

    public EnderecoDAO() {
        this.con = new ConnectionFactory().getConection();
    }

    //metodo cadastrar endereços
    public void cadastrarEnderecos(Endereco obj) {
        try {
            String sql = "insert into enderecos(cliente_id,tipo_endereco,rua,numero,bairro,complemento,cep) values(?,?,?,?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
           
            stmt.setInt(1, obj.getCliente_id().getId());
            stmt.setString(2, obj.getTipo_endereco());
            stmt.setString(3, obj.getRua());
            stmt.setString(4, obj.getNumero());
            stmt.setString(5, obj.getBairro());
            stmt.setString(6, obj.getComplemento());
            stmt.setString(7, obj.getCep());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);

        }
    }
    
    public List<Endereco> buscarPorCliente(int id){
        try {
            List<Endereco> lista = new ArrayList<>();
            String sql = "select * from enderecos where cliente_id = ?;";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Endereco obj = new Endereco();
                obj.setTipo_endereco(rs.getString("tipo_endereco"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setCep(rs.getString("cep"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
        
    }

}
