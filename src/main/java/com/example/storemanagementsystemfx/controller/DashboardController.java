package com.example.storemanagementsystemfx.controller;

import com.example.storemanagementsystemfx.StoreManagementSystem;
import com.example.storemanagementsystemfx.dao.impl.CustomerDao;
import com.example.storemanagementsystemfx.dao.impl.OrderDao;
import com.example.storemanagementsystemfx.dao.impl.OrderDetailDao;
import com.example.storemanagementsystemfx.dao.impl.ProductDao;
import com.example.storemanagementsystemfx.dao.itface.ICustomerDao;
import com.example.storemanagementsystemfx.dao.itface.IOrderDao;
import com.example.storemanagementsystemfx.dao.itface.IOrderDetailDao;
import com.example.storemanagementsystemfx.dao.itface.IProductDao;
import com.example.storemanagementsystemfx.model.Customer;
import com.example.storemanagementsystemfx.model.Order;
import com.example.storemanagementsystemfx.model.OrderDetail;
import com.example.storemanagementsystemfx.model.Product;
import com.example.storemanagementsystemfx.model.holder.OrderHolder;
import com.example.storemanagementsystemfx.model.holder.UserHolder;
import com.example.storemanagementsystemfx.model.ui.BSPChart;
import com.example.storemanagementsystemfx.model.ui.ICChart;
import com.example.storemanagementsystemfx.model.ui.NOOChart;
import com.example.storemanagementsystemfx.service.CustomerService;
import com.example.storemanagementsystemfx.service.OrderService;
import com.example.storemanagementsystemfx.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class DashboardController implements Initializable {
    @FXML
    private Button closeButton;

    @FXML
    private Button cusButton;

    @FXML
    private AnchorPane cusForm;

    @FXML
    private Rectangle cusRec;

    @FXML
    private Button cus_addButton;

    @FXML
    private Button cus_clearButton;

    @FXML
    private TableColumn<Customer, String> cus_col_customerId;

    @FXML
    private TableColumn<Customer, String> cus_col_customerName;

    @FXML
    private TableColumn<Customer, String> cus_col_customerPhoneNum;

    @FXML
    private TableColumn<Customer, String> cus_col_customerEmail;

    @FXML
    private Label cus_customerId;

    @FXML
    private TextField cus_customerName;

    @FXML
    private TextField cus_customerPhoneNum;

    @FXML
    private TextField cus_customerEmail;

    @FXML
    private Button cus_deleteButton;

    @FXML
    private Button cus_editButton;

    @FXML
    private AnchorPane cus_editPane;

    @FXML
    private TextField cus_searchBar;

    @FXML
    private TableView<Customer> cus_tableView;

    @FXML
    private Button cus_updateButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private AnchorPane dashboardForm;

    @FXML
    private Rectangle dashboardRec;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private Label dashboard_numCustomers;

    @FXML
    private AreaChart<?, ?> dashboard_numOrdersChart;

    @FXML
    private PieChart dashboard_bspChart;

    @FXML
    private Rectangle dashboard_ordersRec;

    @FXML
    private Rectangle dashboard_salesRec;

    @FXML
    private Label dashboard_todayIncome;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button fdButton;

    @FXML
    private AnchorPane fdForm;

    @FXML
    private Rectangle fdRec;

    @FXML
    private Button fd_addButton;

    @FXML
    private Button fd_clearButton;

    @FXML
    private TableColumn<Product, String> fd_col_productId;

    @FXML
    private TableColumn<Product, String> fd_col_productName;

    @FXML
    private TableColumn<Product, Double> fd_col_productPrice;

    @FXML
    private TableColumn<Product, String> fd_col_productStatus;

    @FXML
    private TableColumn<Product, String> fd_col_productType;

    @FXML
    private Button fd_deleteButton;

    @FXML
    private AnchorPane fd_editPane;

    @FXML
    private Label fd_productId;

    @FXML
    private TextField fd_productName;

    @FXML
    private TextField fd_productPrice;

    @FXML
    private ComboBox<String> fd_productStatus;

    @FXML
    private ComboBox<String> fd_productType;

    @FXML
    private TextField fd_searchBar;

    @FXML
    private Button fd_updateButton;

    @FXML
    private TableView<Product> fd_tableView;

    @FXML
    private Button logOutButton;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button minimizeButton;

    @FXML
    private AnchorPane newOrderForm;

    @FXML
    private Button newOrder_addButton;

    @FXML
    private Button newOrder_clearButton;

    @FXML
    private ComboBox<String> newOrder_customerId;

    @FXML
    private ComboBox<String> newOrder_customerName;

    @FXML
    private Label newOrder_orderDate;

    @FXML
    private Label newOrder_orderId;

    @FXML
    private ComboBox<String> newOrder_productId;

    @FXML
    private ComboBox<String> newOrder_productName;

    @FXML
    private Spinner<Integer> newOrder_quantity;

    @FXML
    private Button newOrder_removeButton;

    @FXML
    private Button newOrder_saveButton;

    @FXML
    private TableView<OrderDetail> newOrder_tableView;

    @FXML
    private TableColumn<OrderDetail, String> newOrder_col_productId;

    @FXML
    private TableColumn<OrderDetail, String> newOrder_col_productName;

    @FXML
    private TableColumn<OrderDetail, String> newOrder_col_productPrice;

    @FXML
    private TableColumn<OrderDetail, String> newOrder_col_productQuantity;

    @FXML
    private TableColumn<OrderDetail, String> newOrder_col_productType;

    @FXML
    private Label newOrder_total;

    @FXML
    private Button newOrder_updateButton;

    @FXML
    private ImageView sadIcon;

    @FXML
    private AnchorPane ordListForm;

    @FXML
    private TableColumn<Order, String> ordList_col_cusName;

    @FXML
    private TableColumn<Order, String> ordList_col_delete;

    @FXML
    private TableColumn<Order, String> ordList_col_items;

    @FXML
    private TableColumn<Order, String> ordList_col_orderEdit;

    @FXML
    private TableColumn<Order, String> ordList_col_orderDate;

    @FXML
    private TableColumn<Order, String> ordList_col_orderId;

    @FXML
    private TableColumn<Order, String> ordList_col_total;

    @FXML
    private DatePicker ordList_endDate;

    @FXML
    private TextField ordList_searchBar;

    @FXML
    private Button ordList_newOrdButton;

    @FXML
    private DatePicker ordList_startDate;

    @FXML
    private TableView<Order> ordList_tableView;

    @FXML
    private Button orderButton;

    @FXML
    private AnchorPane orderForm;

    @FXML
    private Spinner<Integer> order_quantity;

    @FXML
    private Rectangle orderRec;

    @FXML
    private Label order_total;

    @FXML
    private Button order_addButton;

    @FXML
    private Button order_clearButton;

    @FXML
    private TableColumn<OrderDetail, String> order_col_productId;

    @FXML
    private TableColumn<OrderDetail, String> order_col_productName;

    @FXML
    private TableColumn<OrderDetail, String> order_col_productPrice;

    @FXML
    private TableColumn<OrderDetail, String> order_col_productQuantity;

    @FXML
    private TableColumn<OrderDetail, String> order_col_productType;

    @FXML
    private Label order_customerName;

    @FXML
    private Button order_editButton;

    @FXML
    private AnchorPane order_editPane;

    @FXML
    private Label order_orderId;

    @FXML
    private ComboBox<String> order_productId;

    @FXML
    private ComboBox<String> order_productName;

    @FXML
    private Button order_editCloseButton;

    @FXML
    private Button order_removeButton;

    @FXML
    private Button order_updateButton;



    @FXML
    private TableView<OrderDetail> order_tableView;

    @FXML
    private Label username;

    //===============================================================================================================
    //---------------------------------------------------------------------------------------------------------------
    //===============================================================================================================

    public Optional<ButtonType> createAlert(String type, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(type.equals("ERROR")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
        } else if(type.equals("INFORMATION")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information message");
            alert.setHeaderText(null);
        } else if(type.equals("CONFIRMATION")) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
        }
        alert.setContentText(message);
        return alert.showAndWait();
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage)mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    public void displayUsername() {
        username.setText(UserHolder.getInstance().getUser().getUserName());
    }

    //Use to set mouse behavior
    private double x = 0;
    private double y = 0;

    public void logOut() throws IOException {
        if(createAlert("CONFIRMATION", "Are you sure you want to logout?").get().equals(ButtonType.OK)) {
            logOutButton.getScene().getWindow().hide();

            //Link to log in form
            FXMLLoader fxmlLoader = new FXMLLoader(StoreManagementSystem.class.getResource("fxml/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            scene.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            }) ;

            scene.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

                stage.setOpacity(.8f);
            });

            scene.setOnMouseReleased(event -> {
                stage.setOpacity(1);
            });

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void switchForm(ActionEvent e) {
        dashboardForm.setVisible(false);
        fdForm.setVisible(false);
        cusForm.setVisible(false);
        ordListForm.setVisible(false);
        orderForm.setVisible(false);
        order_editPane.setVisible(false);
        cus_editPane.setVisible(false);
        fd_editPane.setVisible(false);
        newOrderForm.setVisible(false);
        dashboardRec.setVisible(false);
        fdRec.setVisible(false);
        cusRec.setVisible(false);
        orderRec.setVisible(false);

        if (e.getSource().equals(dashboardButton)) {
            dashboardForm.setVisible(true);
            dashboardRec.setVisible(true);
            dashboardNC();
            dashboardTodayIncome();
            dashboardTotalIncome();
            dashboardNOChart();
            dashboardICChart();
            dashboardBSPChart();
        } else if (e.getSource().equals(fdButton)) {
            fdForm.setVisible(true);
            fdRec.setVisible(true);
            fdShowProducts();
            fd_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            fd_tableView.setPrefWidth(853);
            fd_searchBar.setPrefWidth(742);
            fdSearch();
        } else if(e.getSource().equals(cusButton)) {
            cusForm.setVisible(true);
            cusRec.setVisible(true);
            cusShowCustomers();
            cus_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            cus_tableView.setPrefWidth(853);
            cus_searchBar.setPrefWidth(742);
            cusSearch();
        } else if(e.getSource().equals(orderButton)) {
            ordListForm.setVisible(true);
            orderRec.setVisible(true);
            ordListShowOrders();
            ordListSearch();
        }
    }


    //===============================================================================================================
    //---------------------------------------------------------------------------------------------------------------
    //===============================================================================================================

    //dependencies
    IProductDao productDao = new ProductDao();

    //injects
    ProductService productService = new ProductService(productDao);

    private final String[] status = {"Available", "Not Available"};
    public void initFDStatus() {
        List<String> statusList = new ArrayList<>(Arrays.asList(status));
        ObservableList dataList = FXCollections.observableList(statusList);
        fd_productStatus.setItems(dataList);
    }

    private final String[] categories = {"Meals", "Drinks"};
    public void initFDType() {
        List<String> typeList = new ArrayList<>(Arrays.asList(categories));
        ObservableList dataList = FXCollections.observableList(typeList);
        fd_productType.setItems(dataList);
    }

    private ObservableList<Product> fdList;

    public void fdShowProducts() {
        fdList = FXCollections.observableList(productService.getAll());

        fd_col_productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        fd_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        fd_col_productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        fd_col_productType.setCellValueFactory(new PropertyValueFactory<>("type"));
        fd_col_productStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        fd_tableView.setItems(fdList);
    }

    public void fdEdit() {
        fd_tableView.setPrefWidth(570);
        fd_searchBar.setPrefWidth(566);
        fd_editPane.setVisible(true);
    }

    public void fdEditClose() {
        fd_tableView.setPrefWidth(853);
        fd_searchBar.setPrefWidth(742);
        fd_editPane.setVisible(false);
    }

    public void fdAdd() {
        if(fd_productName.getText().isEmpty()
                || fd_productPrice.getText().isEmpty()
                || fd_productType.getSelectionModel().getSelectedItem() == null
                || fd_productStatus.getSelectionModel().getSelectedItem() == null
        ) {
            createAlert("ERROR", "Please fill all the blank fields");
        } else {
            try {
                //4-args constructing
                Product product = new Product(fd_productName.getText(),
                        Double.parseDouble(fd_productPrice.getText()),
                        fd_productType.getSelectionModel().getSelectedItem(),
                        fd_productStatus.getSelectionModel().getSelectedItem());
                productService.save(product);
                createAlert("INFORMATION", "Successfully Added!");
                fdShowProducts();
                fdClear();
                initOrderDetailProdName();
                initOrderDetailProdId();
            } catch (NumberFormatException e) {
                createAlert("ERROR", "Invalid data type");
            }
        }
    }

    public void fdClear() {
        fd_productId.setText("(Auto-generated)");
        fd_productName.clear();
        fd_productPrice.setText("");
        fd_productType.getSelectionModel().clearSelection();
        fd_productStatus.getSelectionModel().clearSelection();

        fd_addButton.setVisible(true);
        fd_deleteButton.setVisible(false);
        fd_updateButton.setVisible(false);
    }

    public void fdSelect() {
        Product product = fd_tableView.getSelectionModel().getSelectedItem();
        if(fd_tableView.getSelectionModel().getSelectedIndex() < 0) {
            return;
        }

        fd_productId.setText(product.getProductId());
        fd_productName.setText(product.getProductName());
        fd_productPrice.setText(String.valueOf(product.getPrice()));
        fd_productType.getSelectionModel().select(product.getType());
        fd_productStatus.getSelectionModel().select(product.getStatus());

        fd_addButton.setVisible(false);
        fd_deleteButton.setVisible(true);
        fd_updateButton.setVisible(true);
    }

    public void fdUpdate() {
        if(fd_productName.getText().isEmpty()
                || fd_productPrice.getText().isEmpty()
                || fd_productType.getSelectionModel().getSelectedItem() == null
                || fd_productStatus.getSelectionModel().getSelectedItem() == null
        ) {
            createAlert("ERROR", "Please fill all the blank fields");
        } else {
            Product oldProduct = fd_tableView.getSelectionModel().getSelectedItem();
            //5-args constructing
            Product newProduct = new Product(fd_productId.getText(),
                    fd_productName.getText(),
                    Double.parseDouble(fd_productPrice.getText()),
                    fd_productType.getSelectionModel().getSelectedItem(),
                    fd_productStatus.getSelectionModel().getSelectedItem());

            if(oldProduct.compare(newProduct)) {//compare two  products
                createAlert("INFORMATION", "No changes detected");
            } else {
                if(createAlert("CONFIRMATION", "Are you sure?").get().equals(ButtonType.OK)) {
                    createAlert("INFORMATION", "Successfully Updated!");
                    if(productService.update(newProduct) == 1) {
                        for(Order order : orderService.getAllOrders()) {
                            orderService.updateOrder(order);
                        }
                    }
                    fdShowProducts();
                    fdClear();
                } else {
                    createAlert("INFORMATION", "Cancelled.");
                }
            }
        }
    }

    public void fdDelete() {
        Product product = fd_tableView.getSelectionModel().getSelectedItem();
        if(createAlert("CONFIRMATION", "Are you sure you want to delete " + product.getProductId() +" ?").get().equals(ButtonType.OK)) {

            if(productService.delete(product.getProductId()) == 0) {
                createAlert("ERROR", "You can't delete a released product.\nThe current product will be set as Not Available");
                product.setStatus("Not Available");
            } else {
                createAlert("INFORMATION", "Successfully Deleted!");
                fdShowProducts();
                fdClear();
            }

        } else {
            createAlert("INFORMATION", "Cancelled.");
        }
    }

    public void setFdSearchBar() {
        fd_searchBar.textProperty().addListener((observableValue, newValue, oldValue) -> {
            fdSearch();
        });
    }

    public void fdSearch() {
        fdList = FXCollections.observableList(productService.getAll());
        String keyword = fd_searchBar.getText().toLowerCase();
        if (keyword.equals("")) {
            fd_tableView.setItems(fdList);
        } else {
            ObservableList<Product> filteredData = FXCollections.observableArrayList();
            for (Product product : fdList) {
                if(product.getProductName().toLowerCase().contains(keyword)
                    || product.getProductId().toLowerCase().contains(keyword)
                    || String.valueOf(product.getPrice()).contains(keyword)
                    || product.getType().toLowerCase().contains(keyword)
                    || product.getStatus().toLowerCase().contains(keyword))
                    filteredData.add(product);
            }
            fd_tableView.setItems(filteredData);
        }
    }

    //===============================================================================================================
    //---------------------------------------------------------------------------------------------------------------
    //===============================================================================================================

    //Dependencies
    ICustomerDao customerDao = new CustomerDao();

    //Injections
    CustomerService customerService = new CustomerService(customerDao);

    private ObservableList<Customer> cusList;

    public void cusShowCustomers() {
        cusList = FXCollections.observableList(customerService.getAll());

        cus_col_customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        cus_col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        cus_col_customerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cus_col_customerPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        cus_tableView.setItems(cusList);
    }

    public void cusEdit() {
        cus_tableView.setPrefWidth(570);
        cus_searchBar.setPrefWidth(566);
        cus_editPane.setVisible(true);
    }

    public void cusEditClose() {
        cus_tableView.setPrefWidth(853);
        cus_searchBar.setPrefWidth(742);
        cus_editPane.setVisible(false);
    }

    public void cusAdd() {
        if(cus_customerName.getText().isEmpty()
                || (cus_customerEmail.getText().isEmpty() && cus_customerPhoneNum.getText().isEmpty())
        ) {
            createAlert("ERROR", "Please fill all the blank fields");
        } else {
            try {//checking if valid phone number and email
                //3-args constructing
                Customer customer = new Customer(cus_customerName.getText(),//Customer Name
                        cus_customerEmail.getText(),//Customer Email
                        cus_customerPhoneNum.getText());//Customer Phone number
                customerService.save(customer);
                createAlert("INFORMATION", "Successfully Added!");
                cusShowCustomers();
                cusClear();
            } catch (NumberFormatException e) {
                //Validation
            }
        }
    }


    public void cusClear() {
        cus_customerId.setText("(Auto-generated)");
        cus_customerName.setText("");
        cus_customerEmail.setText("");
        cus_customerPhoneNum.setText("");

        cus_addButton.setVisible(true);
        cus_deleteButton.setVisible(false);
        cus_updateButton.setVisible(false);
    }

    public void cusSelect() {
        Customer customer = cus_tableView.getSelectionModel().getSelectedItem();
        if(cus_tableView.getSelectionModel().getSelectedIndex() < 0) {
            return;
        }

        cus_customerId.setText(customer.getCustomerId());
        cus_customerName.setText(customer.getCustomerName());
        cus_customerEmail.setText(customer.getEmail());
        cus_customerPhoneNum.setText(customer.getPhoneNum());

        cus_addButton.setVisible(false);
        cus_deleteButton.setVisible(true);
        cus_updateButton.setVisible(true);
    }

    public void cusUpdate() {
        if(cus_customerName.getText().isEmpty()
                || (cus_customerEmail.getText().isEmpty()
                    && cus_customerPhoneNum.getText().isEmpty())
        ) {
            createAlert("ERROR", "Please fill all the blank fields");
        } else {
            Customer oldCustomer = cus_tableView.getSelectionModel().getSelectedItem();
            //4-args constructing
            Customer newCustomer = new Customer(cus_customerId.getText(),//Customer ID
                                            cus_customerName.getText(),//Customer Name
                                            cus_customerEmail.getText(),//Customer Email
                                            cus_customerPhoneNum.getText());//Customer Phone number

            if(oldCustomer.compare(newCustomer)) {//compare two customers
                createAlert("INFORMATION", "No changes detected");
            } else {
                if(createAlert("CONFIRMATION", "Are you sure?").get().equals(ButtonType.OK)) {
                    createAlert("INFORMATION", "Successfully Updated!");
                    if(customerService.update(newCustomer) == 1) {
                        for(Order order : orderService.getAllOrders()) {
                            orderService.updateOrder(order);
                        }
                    }
                    cusShowCustomers();
                    cusClear();
                } else {
                    createAlert("INFORMATION", "Cancelled.");
                }
            }
        }
    }

    public void cusDelete() {
        Customer customer = cus_tableView.getSelectionModel().getSelectedItem();
        if(createAlert("CONFIRMATION", "Are you sure you want to delete " + customer.getCustomerId() +" ?").get().equals(ButtonType.OK)) {
            createAlert("INFORMATION", "Successfully Deleted!");
            customerService.delete(customer.getCustomerId());
            cusShowCustomers();
            cusClear();
        } else {
            createAlert("INFORMATION", "Cancelled.");
        }
    }

    public void setCusSearchBar() {
        cus_searchBar.textProperty().addListener((observableValue, newValue, oldValue) -> {
            cusSearch();
        });
    }

    public void cusSearch() {
        cusList = FXCollections.observableList(customerService.getAll());
        String keyword = cus_searchBar.getText().toLowerCase();
        if (keyword.equals("")) {
            cus_tableView.setItems(cusList);
        } else {
            ObservableList<Customer> filteredData = FXCollections.observableArrayList();
            for (Customer customer : cusList) {
                if(customer.getCustomerId().toLowerCase().contains(keyword)
                        || customer.getCustomerName().toLowerCase().contains(keyword)
                        || customer.getEmail().toLowerCase().contains(keyword)
                        || customer.getPhoneNum().toLowerCase().contains(keyword))
                    filteredData.add(customer);
            }
            cus_tableView.setItems(filteredData);
        }
    }

    //===============================================================================================================
    //---------------------------------------------------------------------------------------------------------------
    //===============================================================================================================
    //dependencies
    private IOrderDao orderDao = new OrderDao();
    private IOrderDetailDao orderDetailsDao = new OrderDetailDao();

    //injects
    private OrderService orderService = new OrderService(orderDao, orderDetailsDao, customerService, productService);

    private ObservableList<Order> orderList;

    public void ordListShowOrders() {
        orderList = FXCollections.observableList(orderService.getAllOrders());

        ordList_col_orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        ordList_col_orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDateView"));
        ordList_col_cusName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        ordList_col_items.setCellValueFactory(new PropertyValueFactory<>("items"));
        ordList_col_total.setCellValueFactory(new PropertyValueFactory<>("amount"));

        Callback<TableColumn<Order, String>, TableCell<Order, String>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Order, String> call(TableColumn<Order, String> orderStringTableColumn) {
                return new TableCell<>() {

                    final Button detailButton = new Button();

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            ImageView detailImage = new ImageView(String.valueOf(StoreManagementSystem.class.getResource("icons/detail.png")));
                            detailImage.setFitWidth(19);
                            detailImage.setFitHeight(19);
                            detailButton.setGraphic(detailImage);
                            detailButton.setId("orderDetailButton");
                            detailButton.setOnAction(event -> {
                                Order order = getTableView().getItems().get(getIndex());
                                orderForm.setVisible(true);
                                ordListForm.setVisible(false);
                                orderDetailShowForm(order);
                            });
                            setGraphic(detailButton);
                            setText(null);
                        }
                    }
                };
            }
        };
        ordList_col_orderEdit.setCellFactory(cellFactory);
        Callback<TableColumn<Order, String>, TableCell<Order, String>> cellFactory2 = new Callback<>() {
            @Override
            public TableCell<Order, String> call(TableColumn<Order, String> orderStringTableColumn) {
                return new TableCell<>() {

                    final Button deleteButton = new Button();

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            ImageView deleteImage = new ImageView(String.valueOf(StoreManagementSystem.class.getResource("icons/close.png")));
                            deleteImage.setFitWidth(12);
                            deleteImage.setFitHeight(12);
                            deleteButton.setGraphic(deleteImage);
                            deleteButton.setId("close");
                            deleteButton.setOnAction(event -> {
                                Order order = getTableView().getItems().get(getIndex());
                                ordListDelete(order);
                            });
                            setGraphic(deleteButton);
                            setText(null);
                        }
                    }
                };
            }
        };
        ordList_col_delete.setCellFactory(cellFactory2);
        ordList_tableView.setItems(orderList);
    }

    public void ordListSearch() {
        Date startDate;
        Date endDate;
        Calendar history = Calendar.getInstance();
        history.set(2000, Calendar.JANUARY, 1);
        if(ordList_startDate.getValue() == null) {
            startDate = history.getTime();
        } else {
            startDate = Date.from(ordList_startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if(ordList_endDate.getValue() == null) {
            endDate = Calendar.getInstance().getTime();
        } else {
            Calendar endOfDay = Calendar.getInstance();
            endOfDay.setTime(Date.from(ordList_endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            endOfDay.set(Calendar.HOUR_OF_DAY, 23);
            endOfDay.set(Calendar.MINUTE, 59);
            endOfDay.set(Calendar.MINUTE, 59);
            endDate = endOfDay.getTime();
        }
        orderList = FXCollections.observableList(orderService.getByOrderDate(startDate, endDate));

        String keyword = ordList_searchBar.getText().toLowerCase();
        if (keyword.equals("")) {
            ordList_tableView.setItems(orderList);
        } else {
            ObservableList<Order> filteredData = FXCollections.observableArrayList();
            for (Order order : orderList) {
                if(order.getOrderId().toLowerCase().contains(keyword)
                        || order.getOrderDateView().toLowerCase().contains(keyword)
                        || order.getCustomerName().toLowerCase().contains(keyword)
                        || String.valueOf(order.getItems()).contains(keyword)
                        || String.valueOf(order.getAmount()).contains(keyword))
                    filteredData.add(order);
            }
            ordList_tableView.setItems(filteredData);
        }
    }

    public void ordListDelete(Order order) {
        if(createAlert("CONFIRMATION", "Are you sure?").get().equals(ButtonType.OK)) {
            OrderHolder.getInstance().setOrder(order);
            createAlert("INFORMATION", "Successfully Deleted!");
            for(OrderDetail orderDetail : orderService.getOrderDetailsByOrder()) {
                orderService.deleteOrderDetails(orderDetail.getProductId());
            }
            orderService.deleteOrder(order.getOrderId());
            ordListShowOrders();
        } else {
            createAlert("INFORMATION", "Cancelled.");
        }
    }

    //===============================================================================================================
    //---------------------------------------------------------------------------------------------------------------
    //===============================================================================================================

    private List<OrderDetail> newOrderDetailTempList = new ArrayList<>();

    public void ordListNewOrd() {
        newOrderForm.setVisible(true);
        ordListForm.setVisible(false);

        OrderHolder.getInstance().setOrder(new Order());
        Order currentOrder = OrderHolder.getInstance().getOrder();

        newOrder_orderId.setText(currentOrder.getOrderId());
        newOrder_orderDate.setText(currentOrder.getOrderDateView());

        newOrderDetailTempList.clear();

        newOrderShowTable();

        initNewOrdCusName();
        initNewOrdCusId();
        initNewOrdProdName();
        initNewOrdProdId();
        initNewOrdQuantity();
    }

    public void initNewOrdCusName() {
        Set<String> cusNameSet = new HashSet<>();
        for(Customer customer : customerService.getAll()) {
            cusNameSet.add(customer.getCustomerName());
        }
        List<String> cusNameList = cusNameSet.stream().toList();
        ObservableList dataList = FXCollections.observableList(cusNameList);
        newOrder_customerName.setItems(dataList);
    }

    public void initNewOrdCusId() {
        List<String> cusIdList = new ArrayList<>();
        for(Customer customer : customerService.getAll()) {
            if (customer.getCustomerName().equals(newOrder_customerName.getSelectionModel().getSelectedItem())) {
                cusIdList.add(customer.getCustomerId());
            }
        }
        ObservableList dataList = FXCollections.observableList(cusIdList);
        newOrder_customerId.setItems(dataList);
    }

    public void initNewOrdProdName() {
        Set<String> prodNameSet = new HashSet<>();
        for(Product product : productService.getAll()) {
            if(product.getStatus().equals("Available")) {
                prodNameSet.add(product.getProductName());
            }
        }
        List<String> prodNameList = prodNameSet.stream().toList();
        ObservableList dataList = FXCollections.observableList(prodNameList);
        newOrder_productName.setItems(dataList);
    }

    public void initNewOrdProdId() {
        List<String> prodIdList = new ArrayList<>();
        for(Product product : productService.getAll()) {
            if (product.getProductName().equals(newOrder_productName.getSelectionModel().getSelectedItem()) && product.getStatus().equals("Available")) {
                prodIdList.add(product.getProductId());
            }
        }
        ObservableList dataList = FXCollections.observableList(prodIdList);
        newOrder_productId.setItems(dataList);
    }

    public void initNewOrderTotalAmount() {
        newOrder_total.setText(orderService.setOrderData(OrderHolder.getInstance().getOrder(), newOrderDetailTempList).getAmount().toString());
    }

    public void initNewOrdQuantity() {
        newOrder_quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0));
    }

    private ObservableList<OrderDetail> newOrderDetailList;

    public void newOrderShowTable() {
        newOrderDetailList = FXCollections.observableList(orderService.setDetail(newOrderDetailTempList));

        newOrder_col_productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        newOrder_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        newOrder_col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        newOrder_col_productPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        newOrder_col_productQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        newOrder_tableView.setItems(newOrderDetailList);
        initNewOrderTotalAmount();
    }

    public void newOrderProductClear() {
        newOrder_productId.getSelectionModel().clearSelection();
        newOrder_productName.getSelectionModel().clearSelection();
        newOrder_quantity.getValueFactory().setValue(0);

        newOrder_updateButton.setVisible(false);
        newOrder_addButton.setVisible(true);
        newOrder_removeButton.setVisible(false);

    }

    public void newOrderAllClear() {
        newOrderProductClear();
        newOrder_customerId.getSelectionModel().clearSelection();
        newOrder_customerName.getSelectionModel().clearSelection();
    }

    public void newOrderAdd() {
        if(newOrder_productId.getSelectionModel().getSelectedItem() == null
                || newOrder_productName.getSelectionModel().getSelectedItem() == null
        ) {
            createAlert("ERROR", "Please fill all the blank fields");
        } else if(newOrder_quantity.getValue() == 0) {
            createAlert("ERROR", "Quantity cannot be zero");
        } else {
            OrderDetail newOrderDetail = new OrderDetail(OrderHolder.getInstance().getOrder().getOrderId(),
                    newOrder_productId.getValue(),
                    newOrder_quantity.getValue());

            newOrderDetailTempList.add(newOrderDetail);


            createAlert("INFORMATION", "Successfully Added!");
            newOrderShowTable();
            newOrderProductClear();
        }
    }

    public void newOrderSelect() {
        OrderDetail orderDetail = newOrder_tableView.getSelectionModel().getSelectedItem();

        if(newOrder_tableView.getSelectionModel().getSelectedIndex() < 0) {
            return;
        }
        initNewOrdProdName();
        newOrder_productName.getSelectionModel().select(orderDetail.getProductName());
        initNewOrdProdId();
        newOrder_productId.getSelectionModel().select(orderDetail.getProductId());
        newOrder_quantity.getValueFactory().setValue(orderDetail.getQuantity());

        newOrder_updateButton.setVisible(true);
        newOrder_addButton.setVisible(false);
        newOrder_removeButton.setVisible(true);
    }

    public void newOrderUpdate() {
        if(newOrder_productId.getSelectionModel().getSelectedItem() == null
                || newOrder_productName.getSelectionModel().getSelectedItem() == null
        ) {
            createAlert("ERROR", "Please fill all the blank fields");
        } else if(newOrder_quantity.getValue() == 0) {
            createAlert("ERROR", "Quantity cannot be zero");
        } else {
            OrderDetail oldOrderDetail = newOrder_tableView.getSelectionModel().getSelectedItem();
            OrderDetail newOrderDetail = new OrderDetail(OrderHolder.getInstance().getOrder().getOrderId(),
                    newOrder_productId.getValue(),
                    newOrder_quantity.getValue());

            if(oldOrderDetail.compare(newOrderDetail)) {//compare two orders
                createAlert("INFORMATION", "No changes detected");
            } else {
                if(createAlert("CONFIRMATION", "Are you sure?").get().equals(ButtonType.OK)) {
                    createAlert("INFORMATION", "Successfully Updated!");
                    newOrderDetailTempList.remove(oldOrderDetail);
                    newOrderDetailTempList.add(newOrderDetail);
                    newOrderShowTable();
                    newOrderProductClear();
                } else {
                    createAlert("INFORMATION", "Cancelled.");
                }
            }
        }
    }

    public void newOrderRemove() {
        OrderDetail orderDetail = newOrder_tableView.getSelectionModel().getSelectedItem();
        if(createAlert("CONFIRMATION", "Are you sure you want to remove " + orderDetail.getProductId() +" ?").get().equals(ButtonType.OK)) {
            createAlert("INFORMATION", "Successfully Deleted!");
            newOrderDetailTempList.remove(orderDetail);
            newOrderShowTable();
            newOrderProductClear();
        } else {
            createAlert("INFORMATION", "Cancelled.");
        }
    }

    public void newOrderSave() {
        if(newOrder_customerId.getSelectionModel().getSelectedItem() == null
                || newOrder_customerName.getSelectionModel().getSelectedItem() == null
        ) {
            createAlert("ERROR", "Please fill all the blank customer fields");
        } else if(newOrder_tableView.getItems().isEmpty()) {
            createAlert("ERROR", "Product list cannot be blanked");
        } else {
            if(createAlert("CONFIRMATION", "Are you sure?").get().equals(ButtonType.OK)) {
                Order order = OrderHolder.getInstance().getOrder();
                order.setCustomerId(newOrder_customerId.getSelectionModel().getSelectedItem());
                orderService.saveOrder(order);
                int stuckIndex = -1;
                boolean success = true;
                for(OrderDetail orderDetail : newOrderDetailTempList) {
                    stuckIndex++;
                    if(orderService.saveOrderDetails(orderDetail) == 0) {
                        createAlert("ERROR", "Duplicate Product");
                        success = false;
                        break;
                    }
                }
                if(success) {
                    createAlert("INFORMATION", "Successfully Added!");
                    newOrderAllClear();
                    newOrderForm.setVisible(false);
                    ordListForm.setVisible(true);
                    ordListShowOrders();
                } else {
                    for(OrderDetail orderDetail : newOrderDetailTempList) {
                        stuckIndex--;
                        orderService.deleteOrderDetails(orderDetail.getProductId());
                        if(stuckIndex == -1) {
                            break;
                        }
                    }
                    orderService.deleteOrder(order.getOrderId());
                }
            } else {
                createAlert("INFORMATION", "Cancelled.");
            }
        }
    }

    //===============================================================================================================
    //---------------------------------------------------------------------------------------------------------------
    //===============================================================================================================

    private ObservableList<OrderDetail> orderDetailList;

    public void orderDetailShowForm(Order order) {
        OrderHolder.getInstance().setOrder(order);  //Set selected order

        orderDetailShowTable();
        order_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        order_tableView.setPrefWidth(850);

        order_orderId.setText(order.getOrderId());
        order_customerName.setText(order.getCustomerName());

        initOrderDetailQuantity();
        initOrderDetailProdId();
        initOrderDetailProdName();
    }

    public void orderDetailShowTable() {
        orderDetailList = FXCollections.observableList(orderService.getOrderDetailsByOrder());

        order_col_productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        order_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        order_col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        order_col_productPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        order_col_productQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        order_tableView.setItems(orderDetailList);
        initOrderTotalAmount();
    }

    public void orderDetailEdit() {
        order_tableView.setPrefWidth(610);
        order_editPane.setVisible(true);
    }

    public void orderDetailEditClose() {
        order_editPane.setVisible(false);
        order_tableView.setPrefWidth(850);
    }

    public void initOrderDetailProdId() {
        List<String> prodIdList = new ArrayList<>();
        for(Product product : productService.getAll()) {
            if (product.getProductName().equals(order_productName.getSelectionModel().getSelectedItem())) {
                prodIdList.add(product.getProductId());
            }
        }
        ObservableList dataList = FXCollections.observableList(prodIdList);
        order_productId.setItems(dataList);
    }


    public void initOrderDetailQuantity() {
        order_quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0));
    }

    public void initOrderDetailProdName() {
        Set<String> prodNameSet = new HashSet<>();
        for(Product product : productService.getAll()) {
            prodNameSet.add(product.getProductName());
        }
        List<String> prodNameList = prodNameSet.stream().toList();
        ObservableList dataList = FXCollections.observableList(prodNameList);
        order_productName.setItems(dataList);
    }

    public void initOrderTotalAmount() {
        order_total.setText(OrderHolder.getInstance().getOrder().getAmount().toString());
    }

    public void orderDetailSelect() {
        OrderDetail orderDetail = order_tableView.getSelectionModel().getSelectedItem();

        if(order_tableView.getSelectionModel().getSelectedIndex() < 0) {
            return;
        }
        initOrderDetailProdName();
        order_productName.getSelectionModel().select(orderDetail.getProductName());
        initOrderDetailProdId();
        order_productId.getSelectionModel().select(orderDetail.getProductId());
        order_quantity.getValueFactory().setValue(orderDetail.getQuantity());

        order_updateButton.setVisible(true);
        order_addButton.setVisible(false);
        order_removeButton.setVisible(true);
    }

    public void orderDetailClear() {
        order_productId.getSelectionModel().clearSelection();
        order_productName.getSelectionModel().clearSelection();
        order_quantity.getValueFactory().setValue(0);

        order_updateButton.setVisible(false);
        order_addButton.setVisible(true);
        order_removeButton.setVisible(false);

        order_tableView.getSelectionModel().select(-1);
    }

    public void orderDetailAdd() {
        if(order_productId.getSelectionModel().getSelectedItem() == null
                || order_productName.getSelectionModel().getSelectedItem() == null
        ) {
            createAlert("ERROR", "Please fill all the blank fields");
        } else if(order_quantity.getValue() == 0) {
            createAlert("ERROR", "Quantity cannot be zero");
        } else {
            OrderDetail orderDetail = new OrderDetail(OrderHolder.getInstance().getOrder().getOrderId(),
                                                    order_productId.getValue(),
                                                    order_quantity.getValue());
            if(orderService.saveOrderDetails(orderDetail) != 0) {
                createAlert("INFORMATION", "Successfully Added!");
                orderDetailShowTable();
                orderDetailClear();
            } else {
                createAlert("ERROR", "Duplicate Product");
            }
        }
    }

    public void orderDetailUpdate() {
        if(order_productId.getSelectionModel().getSelectedItem() == null
                || order_productName.getSelectionModel().getSelectedItem() == null
        ) {
            createAlert("ERROR", "Please fill all the blank fields");
        } else if(order_quantity.getValue() == 0) {
            createAlert("ERROR", "Quantity cannot be zero");
        } else {
            OrderDetail oldOrderDetail = order_tableView.getSelectionModel().getSelectedItem();
            OrderDetail newOrderDetail = new OrderDetail(OrderHolder.getInstance().getOrder().getOrderId(),
                    order_productId.getValue(),
                    order_quantity.getValue());

            if(oldOrderDetail.compare(newOrderDetail)) {//compare two orders
                createAlert("INFORMATION", "No changes detected");
            } else {
                if(createAlert("CONFIRMATION", "Are you sure?").get().equals(ButtonType.OK)) {
                    if(orderService.updateOrderDetails(newOrderDetail, oldOrderDetail.getProductId()) != 0) {
                        createAlert("INFORMATION", "Successfully Updated!");
                        orderDetailShowTable();
                        orderDetailClear();
                    } else {
                        createAlert("ERROR", "Duplicate Product");
                        orderDetailSelect();
                    }
                } else {
                    createAlert("INFORMATION", "Cancelled.");
                }
            }
        }
    }

    public void orderDetailRemove() {
        OrderDetail orderDetail = order_tableView.getSelectionModel().getSelectedItem();
        if(createAlert("CONFIRMATION", "Are you sure you want to remove " + orderDetail.getProductId() +" ?").get().equals(ButtonType.OK)) {
            createAlert("INFORMATION", "Successfully Deleted!");
            orderService.deleteOrderDetails(orderDetail.getProductId());
            orderDetailShowTable();
            orderDetailClear();
        } else {
            createAlert("INFORMATION", "Cancelled.");
        }
    }

    //===============================================================================================================
    //---------------------------------------------------------------------------------------------------------------
    //===============================================================================================================

    public void dashboardNC() {
        int num = 0;
        for(Customer customer : customerService.getAll()) {
            num++;
        }
        dashboard_numCustomers.setText(String.valueOf(num));
    }

    public void dashboardTodayIncome() {
        Double income = 0.0;
        Calendar beginOfDay = Calendar.getInstance();
        beginOfDay.set(Calendar.HOUR_OF_DAY, 0);
        beginOfDay.set(Calendar.MINUTE, 0);
        beginOfDay.set(Calendar.MINUTE, 0);

        Calendar endOfDay = Calendar.getInstance();
        endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        endOfDay.set(Calendar.MINUTE, 59);
        endOfDay.set(Calendar.MINUTE, 59);
        for(Order order : orderService.getByOrderDate(beginOfDay.getTime(), endOfDay.getTime())) {
            income += order.getAmount();
        }
        dashboard_todayIncome.setText(income.toString());
    }

    public void dashboardTotalIncome() {
        Double income = 0.0;
        for(Order order : orderService.getAllOrders()) {
            income += order.getAmount();
        }
        dashboard_totalIncome.setText(income.toString());
    }

    public void dashboardNOChart() {
        dashboard_numOrdersChart.getData().clear();
        XYChart.Series chart = new XYChart.Series();
        for(NOOChart newData : orderService.numbersOfOrdersByDate()) {
            chart.getData().add(new XYChart.Data(newData.getDate(), newData.getQuantity()));
        }
        dashboard_numOrdersChart.getData().add(chart);
    }

    public void dashboardICChart() {
        dashboard_incomeChart.getData().clear();
        XYChart.Series chart = new XYChart.Series();
        for(ICChart newData : orderService.incomesByDate()) {
            chart.getData().add(new XYChart.Data(newData.getDate(), newData.getTotal()));
        }
        dashboard_incomeChart.getData().add(chart);
    }

    public void dashboardBSPChart() {
        dashboard_bspChart.getData().clear();
        List<PieChart.Data> pieDataList = new ArrayList<>();
        for(BSPChart newData : orderService.getProductTotal()) {
            pieDataList.add(new PieChart.Data(newData.getProductName() + " " + newData.getQuantity() + " items", newData.getQuantity()));
        }
        sadIcon.setVisible(orderService.getProductTotal().isEmpty());
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(pieDataList);
        dashboard_bspChart.setData(pieData);
    }

    public void dashboardSales() {
        dashboard_salesRec.setVisible(true);
        dashboard_incomeChart.setVisible(true);
        dashboard_ordersRec.setVisible(false);
        dashboard_numOrdersChart.setVisible(false);
    }

    public void dashboardOrders() {
        dashboard_salesRec.setVisible(false);
        dashboard_incomeChart.setVisible(false);
        dashboard_ordersRec.setVisible(true);
        dashboard_numOrdersChart.setVisible(true);
    }

    //===============================================================================================================
    //---------------------------------------------------------------------------------------------------------------
    //===============================================================================================================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        initFDStatus();
        initFDType();
        fdShowProducts();
        setFdSearchBar();
        setCusSearchBar();
        dashboardNC();
        dashboardTodayIncome();
        dashboardTotalIncome();
        dashboardNOChart();
        dashboardICChart();
        dashboardBSPChart();
    }
}
