

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;



public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
   
      // Método para cadastrar produto
  public boolean cadastrarProduto(ProdutosDTO produto) {
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setDouble(2, produto.getValor());  
        prep.setString(3, produto.getStatus());

        prep.executeUpdate();
        return true; // deu certo

    } catch (SQLException e) {
        e.printStackTrace();
        return false; // deu erro
    } finally {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    // Método para listar todos os produtos
    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listagem;
    }
    
    
    public boolean venderProduto(int idProduto) {
    String sql = "UPDATE produtos SET status = ? WHERE id = ?";
    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        prep.setString(1, "Vendido");
        prep.setInt(2, idProduto);

        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getDouble("valor"));
            produto.setStatus(resultset.getString("status"));

            listagem.add(produto);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return listagem;
}


    
    
    
        
}

