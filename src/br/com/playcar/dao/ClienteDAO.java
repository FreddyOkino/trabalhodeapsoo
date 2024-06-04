/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.playcar.dao;

import java.sql.*;
import br.com.playcar.jdbc.ConnectionFactory;
import br.com.playcar.model.Cliente;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author freddy
 */
public class ClienteDAO {

    private final Connection con;

    public ClienteDAO() {
        this.con = new ConnectionFactory().getConection();
    }

    //Metodo cadastrar cliente
    public void cadastrarCliente(Cliente obj) {
        try {
            String sql = "insert into clientes(nome,cpf,telefone) values(?,?,?); ";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getCpf());
                stmt.setString(3, obj.getTelefone());
                stmt.execute();
                stmt.close();
            }
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);

        }
    }

    public List<Cliente> buscarTodosClientes() {
        try {
            List<Cliente> lista = new ArrayList<>();
            String sql = "select * from clientes;";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setTelefone(rs.getString("telefone"));
                lista.add(obj);

            }
            return lista;

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
            return null;
        }
    }

    public List<Cliente> buscarporClienteNome(String nome) {
        try {
            List<Cliente> lista = new ArrayList<>();

            String sql = "select * from clientes where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setTelefone(rs.getString("telefone"));
                
                lista.add(obj);
                        
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }

    }

}
