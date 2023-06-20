package at.dke.onlinewebshop.sql.services;

import at.dke.onlinewebshop.sql.entities.Employee;
import at.dke.onlinewebshop.sql.entities.Order;
import at.dke.onlinewebshop.sql.entities.Vendor;
import at.dke.onlinewebshop.sql.repositories.EmployeeRepository;
import at.dke.onlinewebshop.sql.repositories.OrderRepository;
import at.dke.onlinewebshop.sql.repositories.VendorRepository;
import at.dke.onlinewebshop.sql.services.exceptions.StockException;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFulfillmentService {

    private final OrderRepository orderRepository;
    private final StockService stockService;
    private final VendorRepository vendorRepository;
    private final EmployeeRepository employeeRepository;

    public OrderFulfillmentService(OrderRepository orderRepository, StockService stockService, VendorRepository vendorRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
        this.vendorRepository = vendorRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Order fulfillOrder(int orderId) throws StockException {
        // Check if the order exists
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        Vendor vendor = new Vendor();
        vendor.setName(Faker.instance().company().name());
        vendor.setAddress(Faker.instance().address().fullAddress());
        vendor.setSvnr(Faker.instance().idNumber().valid());
        vendor = vendorRepository.save(vendor);
        // Save the vendor to the database and get the saved entity

        Employee employee = new Employee();
        employee.setLocation(Faker.instance().address().fullAddress());
        employee.setName(Faker.instance().name().fullName());
        employee = employeeRepository.save(employee);
        // Save the employee to the database and get the saved entity

        // Check if all articles in the order are available in stock
        for (var article : order.getArticles()) {
            int quantity = article.getQuantity();

            // Check if there is enough stock of the article
            if (!stockService.hasEnoughStock(article, quantity)) {
                throw new StockException("Not enough stock for article " + article.getId());
            }

            // Reduce the stock of the article
            stockService.reduceStock(article, quantity);
        }

        // Assign the vendor and the employee to the order
        order.setVendor(vendor);
        order.setEmployee(employee);

        // Mark the order as fulfilled
        order.setFulfilled(true);
        return orderRepository.save(order);
    }

}
