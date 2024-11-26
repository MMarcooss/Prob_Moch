package com.example.interfaceestoque;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class EstoqueController {

    @FXML
    private Button addButton;
    @FXML
    private Button otimizarButton;
    @FXML
    private Button attButton;
    @FXML
    private Button removeButton;
    @FXML
    private TableView<Produto> table;
    @FXML
    private TableColumn<Produto, String> nomeColumn;
    @FXML
    private TableColumn<Produto, Double> valorColumn;
    @FXML
    private TableColumn<Produto, Integer> pesoColumn;
    @FXML
    private TableColumn<Produto, Integer> qntColumn;
    @FXML
    private TableColumn<Produto, String> descColumn;
    @FXML
    private TextField nomeInput;
    @FXML
    private TextField valorInput;
    @FXML
    private TextField pesoInput;
    @FXML
    private TextField qntInput;
    @FXML
    private TextField descInput;

    private ObservableList<Produto> produtosData = FXCollections.observableArrayList();
    private Estoque estoque = new Estoque(50);
    private ProdutoDAO produtoDAO = new ProdutoDAO(); // DAO para interação com o banco

    @FXML
    public void initialize() {
        // Configuração das colunas da TableView
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        valorColumn.setCellValueFactory(cellData -> cellData.getValue().valorProperty().asObject());
        pesoColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
        qntColumn.setCellValueFactory(cellData -> cellData.getValue().qntProperty().asObject());
        table.setItems(produtosData);

        // Carregar produtos do banco de dados ao iniciar
        carregarProdutosDoBanco();

        // Ação do botão "Adicionar"
        addButton.setOnAction(e -> {
            String nome = nomeInput.getText();
            double valor = Double.parseDouble(valorInput.getText());
            int peso = Integer.parseInt(pesoInput.getText());
            int qnt = Integer.parseInt(qntInput.getText());

            Produto produto = new Produto(nome, valor, peso, qnt);
            produtosData.add(produto);
            estoque.addEstoque(produto);

            // Salvar no banco de dados
            produtoDAO.adicionarProduto(produto);

            limparCampos();
        });

        // Ação do botão "Otimizar"
        otimizarButton.setOnAction(e -> {
            List<Produto> produtosSelecionados = AlgoritimoAEstrela.executar(produtosData, estoque.getCapMax());
            estoque.otimizarEstoque(produtosSelecionados);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Otimização com A* Completa");
            alert.setHeaderText(null);
            alert.setContentText("Produtos otimizados:\n" + estoque.toString());
            alert.showAndWait();
        });

        // Ação do botão "Atualizar"
        attButton.setOnAction(e -> {
            Produto selectedProduto = table.getSelectionModel().getSelectedItem();
            if (selectedProduto != null) {
                selectedProduto.setNome(nomeInput.getText());
                selectedProduto.setValor(Double.parseDouble(valorInput.getText()));
                selectedProduto.setPeso(Integer.parseInt(pesoInput.getText()));
                selectedProduto.setQnt(Integer.parseInt(qntInput.getText()));

                // Atualizar no banco de dados
                produtoDAO.atualizarProduto(selectedProduto);

                table.refresh();
                limparCampos();
            } else {
                mostrarAlerta("Nenhum produto selecionado", "Por favor, selecione um produto para atualizar.");
            }
        });

        // Ação do botão "Remover"
        removeButton.setOnAction(e -> {
            Produto selectedProduto = table.getSelectionModel().getSelectedItem();
            if (selectedProduto != null) {
                produtosData.remove(selectedProduto);
                estoque.removeEstoque(selectedProduto);

                // Remover do banco de dados
                produtoDAO.removerProduto(selectedProduto.getNome());
            } else {
                mostrarAlerta("Nenhum produto selecionado", "Por favor, selecione um produto para remover.");
            }
        });
    }

    private void carregarProdutosDoBanco() {
        produtosData.clear();
        produtosData.addAll(produtoDAO.buscarTodosProdutos());
        table.setItems(produtosData);
    }

    private void limparCampos() {
        nomeInput.clear();
        valorInput.clear();
        pesoInput.clear();
        qntInput.clear();
        descInput.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
