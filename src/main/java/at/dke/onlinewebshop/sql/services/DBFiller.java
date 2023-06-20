package at.dke.onlinewebshop.sql.services;

import at.dke.onlinewebshop.sql.entities.*;
import at.dke.onlinewebshop.sql.repositories.*;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBFiller {
    private static final int USER_LIMIT = 100;
    private static final int ARTICLE_LIMIT = 1000;
    private static final int WAREHOUSE_LIMIT = 5;


    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ArticleRepository articleRepository;
    //private final CorporateCustomerRepository corporateCustomerRepository;
    //private final PrivateCustomerRepository privateCustomerRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final PaymentRepository paymentRepository;
    private final VendorRepository vendorRepository;
    private final WarehouseRepository warehouseRepository;


    public DBFiller(CustomerRepository customerRepository, OrderRepository orderRepository, ArticleRepository articleRepository, /*CorporateCustomerRepository corporateCustomerRepository, PrivateCustomerRepository privateCustomerRepository,*/ DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, PaymentRepository paymentRepository, VendorRepository vendorRepository, WarehouseRepository warehouseRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
        //this.corporateCustomerRepository = corporateCustomerRepository;
        //this.privateCustomerRepository = privateCustomerRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.paymentRepository = paymentRepository;
        this.vendorRepository = vendorRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public void fillDB() {
        insertCustomers();
        insertArticle();
        insertEmployees();
        insertWarehouse();
    }

    private void insertCustomers() {
        var domains = new String[]{
                "gmail.com",
                "protonmail.com",
                "yahoo.com"
        };

        var users = new Customer[USER_LIMIT];

        for (int i = 0; i < USER_LIMIT; i++) {
            var n = Faker.instance().name();
            var firstName = n.firstName();
            var lastName = n.lastName();
            var address = Faker.instance().address();
            var password = Faker.instance().random().hex();
            var email = firstName + "." + lastName + "@" + domains[i % domains.length];

            /*if (i < USER_LIMIT / 2) {
                var age = Faker.instance().random().nextInt(20, 55);
                var phone = Faker.instance().phoneNumber();

                users[i] = new PrivateCustomer(
                        email,
                        lastName,
                        firstName,
                        address.fullAddress(),
                        password);
            } else {
                var taxNumber = Faker.instance().number().digits(5);

                users[i] = new CorporateCustomer(
                        email,
                        lastName,
                        firstName,
                        address.fullAddress(),
                        password,
                        taxNumber
                );
            }*/
            users[i] = new Customer(
                    email,
                    lastName,
                    firstName,
                    address.fullAddress(),
                    password);
        }


        customerRepository.saveAll(List.of(users));
    }

    private void insertArticle() {
        var articles = new Article[ARTICLE_LIMIT];

        for (int i = 0; i < ARTICLE_LIMIT; i++) {
            var name = Faker.instance().commerce().productName();
            var price = Faker.instance().commerce().price(1, 10000).length();

            articles[i] = new Article(
                    name,
                    price
            );
        }

        articleRepository.saveAll(List.of(articles));
    }

    private void insertEmployees() {

        var employees = new Employee[USER_LIMIT];

        for (int i = 0; i < USER_LIMIT; i++) {
            var n = Faker.instance().name();
            var firstName = n.firstName();
            var lastName = n.lastName();
            var address = Faker.instance().address();
            var location = address.fullAddress();

            employees[i] = new Employee(
                    location,
                    firstName + " " + lastName
            );
        }

        employeeRepository.saveAll(List.of(employees));
    }

    private void insertWarehouse() {
        var warehouses = new Warehouse[WAREHOUSE_LIMIT];

        for (int i = 0; i < WAREHOUSE_LIMIT; i++) {
            var n = Faker.instance().name();
            var firstName = n.firstName();
            var lastName = n.lastName();
            var address = Faker.instance().address();
            var location = address.fullAddress();

            warehouses[i] = new Warehouse(
                    location,
                    firstName + " " + lastName
            );
        }

        warehouseRepository.saveAll(List.of(warehouses));
    }


}
