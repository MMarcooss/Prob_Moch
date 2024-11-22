package com.example.interfaceestoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class Botoes {
        public static void removeProdutoFromDatabase(Produto produto) {
            String query = "DELETE FROM Produtos WHERE nome = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, produto.getNome());
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


