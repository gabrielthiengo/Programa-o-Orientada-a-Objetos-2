package edu.pitagoras.clienteDAO;

import edu.pitagoras.config.Conexao;
import edu.pitagoras.modelodados.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ClienteDAO {
    
    public static void adicionarCliente(Cliente cliente) throws SQLException {
        Connection con = Conexao.getConnection();
        System.out.println("Conectado!");
        String sql = "insert into clientes" + "(nome,cpf)" + "values(?,?)";
        PreparedStatement stmt =  con.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static List<Cliente> buscarClientes() throws SQLException{
        Connection con = Conexao.getConnection();
        System.out.println("Conectado!");
        String sql = "select * from clientes ";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Cliente> clientes = new ArrayList<Cliente>();
        while (rs.next()) {
        	Cliente cliente = new Cliente();
        	cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            
            clientes.add(cliente);
        }
        rs.close();
        stmt.close();
        con.close();
        return clientes;
    }
    
}
