package at.dke.onlinewebshop.sql.services;

import at.dke.onlinewebshop.sql.entities.Article;
import at.dke.onlinewebshop.sql.entities.CorporateCustomer;
import at.dke.onlinewebshop.sql.entities.Customer;
import at.dke.onlinewebshop.sql.entities.PrivateCustomer;
import at.dke.onlinewebshop.sql.repositories.*;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBFiller {
    private static final int USER_LIMIT = 100;
    private static final int ARTICLE_LIMIT = 1000;


    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ArticleRepository articleRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final PrivateCustomerRepository privateCustomerRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final PaymentRepository paymentRepository;
    private final VendorRepository vendorRepository;
    private final WarehouseRepository warehouseRepository;


    public DBFiller(CustomerRepository customerRepository, OrderRepository orderRepository, ArticleRepository articleRepository, CorporateCustomerRepository corporateCustomerRepository, PrivateCustomerRepository privateCustomerRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, PaymentRepository paymentRepository, VendorRepository vendorRepository, WarehouseRepository warehouseRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
        this.corporateCustomerRepository = corporateCustomerRepository;
        this.privateCustomerRepository = privateCustomerRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.paymentRepository = paymentRepository;
        this.vendorRepository = vendorRepository;
        this.warehouseRepository = warehouseRepository;
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

            if (i < USER_LIMIT / 2) {
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
            }
        }

        customerRepository.saveAll(List.of(users));
    }



    /*private void insertArticle() {
        var articles = new Article[USER_LIMIT];

        for (int i = 0; i < USER_LIMIT; i++) {
            var name = Faker.instance().commerce().productName();
            var price = Faker.instance().commerce().price();
            var description = Faker.instance().lorem().sentence();
            var stock = Faker.instance().random().nextInt(0, 1000);

            articles[i] = new Article(
                    name,
                    price,
                    description,
                    stock
            );
        }

        articleRepository.saveAll(List.of(articles));
    }*/
}
