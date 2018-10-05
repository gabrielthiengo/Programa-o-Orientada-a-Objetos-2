package edu.pitagoras.funcionarioDAO;

import edu.pitagoras.config.Conexao;
import edu.pitagoras.modelodados.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class FuncionarioDAO {
    
    public static void adicionarfuncionario(Funcionarios funcionarios) throws SQLException {
        Connection con = Conexao.getConnection();
        System.out.println("Conectado!");
        String sql = "insert into funcionarios (nome,cpf) values(?,?)";
        PreparedStatement stmt =  con.prepareStatement(sql);
        stmt.setString(1, funcionarios.getNome());
        stmt.setString(2, funcionarios.getCpf());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public static List<Funcionarios> buscarFuncionarios() throws SQLException {
        Connection con = Conexao.getConnection();
        System.out.println("Conectado!");
        String sql = "select * from funcionarios";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Funcionarios> funcionarios = new ArrayList<Funcionarios>();
        while(rs.next()){
            Funcionarios funcionario = new Funcionarios();
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            
            funcionarios.add(funcionario);  
        }
        rs.close();
        stmt.close();
        con.close();
        return funcionarios;
    }    
}
