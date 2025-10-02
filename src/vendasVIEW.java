import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {

    private final JTable listaVendas;
    private final JScrollPane scrollPane;
    private final JButton btnVoltar;
    private final JLabel titulo;

    public vendasVIEW() {
        setTitle("Lista de Produtos Vendidos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        titulo = new JLabel("Produtos Vendidos");
        titulo.setBounds(180, 10, 200, 30);
        add(titulo);

        // tabela
        listaVendas = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nome", "Valor", "Status"}
        ));
        scrollPane = new JScrollPane(listaVendas);
        scrollPane.setBounds(30, 50, 430, 250);
        add(scrollPane);

        // botão voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 320, 100, 30);
        btnVoltar.addActionListener(e -> dispose());
        add(btnVoltar);

        // carrega a lista de vendidos
        listarVendas();
    }

    private void listarVendas() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            DefaultTableModel model = (DefaultTableModel) listaVendas.getModel();
            model.setNumRows(0);

            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutosVendidos();

            for (ProdutosDTO produto : listagem) {
                model.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getValor(),
                    produto.getStatus()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
