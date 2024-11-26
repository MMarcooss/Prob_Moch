

//uso dos botões de atualizar e excluir
//addButton.setOnAction(e -> {
//    String nome = nomeInput.getText();
//    double valor = Double.parseDouble(valorInput.getText());
//    int peso = Integer.parseInt(pesoInput.getText());
//    int qnt = Integer.parseInt(qntInput.getText());
//
//    Produto produto = new Produto(nome, valor, peso, qnt);
//    produtosData.add(produto);
//    estoque.addEstoque(produto);
//    nomeInput.clear();
//    valorInput.clear();
//    pesoInput.clear();
//    qntInput.clear();
//    descInput.clear();
//});
//
//attButton.setOnAction(e -> AtualizarProduto.atualizarProduto(table, nomeInput, valorInput, pesoInput, qntInput, descInput));
//
//removeButton.setOnAction(e -> RemoverProduto.removerProduto(table, estoque));


package com.example.interfaceestoque;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
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

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        valorColumn.setCellValueFactory(cellData -> cellData.getValue().valorProperty().asObject());
        pesoColumn.setCellValueFactory(cellData -> cellData.getValue().pesoProperty().asObject());
        qntColumn.setCellValueFactory(cellData -> cellData.getValue().qntProperty().asObject());
        table.setItems(produtosData);

        addButton.setOnAction(e -> {
            String nome = nomeInput.getText();
            double valor = Double.parseDouble(valorInput.getText());
            int peso = Integer.parseInt(pesoInput.getText());
            int qnt = Integer.parseInt(qntInput.getText());

            Produto produto = new Produto(nome, valor, peso, qnt);
            produtosData.add(produto);
            estoque.addEstoque(produto);
            nomeInput.clear();
            valorInput.clear();
            pesoInput.clear();
            qntInput.clear();
            descInput.clear();
        });

        otimizarButton.setOnAction(e -> {
            List<Produto> produtosSelecionados = AlgoritimoAEstrela.executar(new ArrayList<>(produtosData), estoque.getCapMax());
            estoque.otimizarEstoque(produtosSelecionados);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Otimização com A* Completa");
            alert.setHeaderText(null);
            alert.setContentText("Produtos otimizados:\n" + estoque.toString());
            alert.showAndWait();
        });
        attButton.setOnAction(e -> {
            Produto selectedProduto = table.getSelectionModel().getSelectedItem();
            if (selectedProduto != null) {
                selectedProduto.setNome(nomeInput.getText());
                selectedProduto.setValor(Double.parseDouble(valorInput.getText()));
                selectedProduto.setPeso(Integer.parseInt(pesoInput.getText()));
                selectedProduto.setQnt(Integer.parseInt(qntInput.getText()));

                table.refresh();
                nomeInput.clear();
                valorInput.clear();
                pesoInput.clear();
                qntInput.clear();
                descInput.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nenhum produto selecionado");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, selecione um produto para atualizar.");
                alert.showAndWait();
            }
        });

        removeButton.setOnAction(e -> {
            Produto selectedProduto = table.getSelectionModel().getSelectedItem();
            if (selectedProduto != null) {
                produtosData.remove(selectedProduto);
                estoque.removeEstoque(selectedProduto);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nenhum produto selecionado");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, selecione um produto para remover.");
                alert.showAndWait();
            }
        });
    }
}
