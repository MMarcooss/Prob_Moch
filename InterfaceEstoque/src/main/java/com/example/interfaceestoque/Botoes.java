package com.example.interfaceestoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class Botoes {
        public static void removeProdutoFromDatabase(Produto produto) {
            String query = "DELETE FROM Produtos WHERE nome = ?";  // Ou pode ser usado um ID único para a exclusão

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, produto.getNome());  // Supondo que o 'nome' seja único, mas o ideal é usar um ID único
                stmt.executeUpdate();  // Executa a remoção no banco de dados

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

