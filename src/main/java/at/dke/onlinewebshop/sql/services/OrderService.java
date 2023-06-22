package at.dke.onlinewebshop.sql.services;
import at.dke.onlinewebshop.entities.Article;
import at.dke.onlinewebshop.entities.Customer;
import at.dke.onlinewebshop.entities.Order;
import at.dke.onlinewebshop.entities.Payment;
import at.dke.onlinewebshop.entities.*;
import at.dke.onlinewebshop.sql.repositories.*;
import at.dke.onlinewebshop.sql.services.exceptions.InvalidPaymentInfoException;
import at.dke.onlinewebshop.sql.services.exceptions.MissingCustomerIdException;
import at.dke.onlinewebshop.sql.services.exceptions.MissingProductException;
import com.github.javafaker.Faker;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ArticleRepository articleRepository;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;

    public OrderService(OrderRepository orderRepository, ArticleRepository articleRepository, CustomerRepository customerRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public void saveOrder(int customerId, @NonNull String creditCardNr, @NonNull String creditCardName,
                          @NonNull String orderDate, double sum, @NonNull List<Article> articleList)
            throws MissingProductException, InvalidPaymentInfoException, MissingCustomerIdException {

        var customerOpt = customerRepository.findById(customerId).get();
        var faker = Faker.instance();
        checkCredentials(creditCardNr, creditCardName, orderDate, sum, articleList, customerOpt);

        // creating the order
        var newOrder = new Order(0, sum, orderDate, false, customerOpt, articleList, null, null, null);

        // create and validate PaymentInfoObject
        Payment paymentInfo = new Payment(0, sum, orderDate, new Date(System.currentTimeMillis()).toString(), newOrder);
        paymentRepository.save(paymentInfo);

        // setting PaymentInfo in order
        newOrder.setPayment(paymentInfo);

        // setting orderProducts in the order class
        List<Article> orderProducts = newOrder.getArticles();
        for (var prod : articleList) {
            var article = articleRepository.findById(prod.getId()).get();
            orderProducts.add(article);
        }

        // saving the order in database
        newOrder.setArticles(orderProducts);

        // save the order
        orderRepository.save(newOrder);

    }

    private void checkCredentials(String creditCardNr, String creditCardName,
                                  String orderDate, double sum, List<Article> articleList, Customer customerOpt)
            throws MissingProductException, InvalidPaymentInfoException, MissingCustomerIdException {

        if (creditCardNr == null) {
            throw new InvalidPaymentInfoException("credit card nr is null");
        }

        if (creditCardName == null) {
            throw new InvalidPaymentInfoException("credit card name is null");
        }

        if (orderDate == null) {
            throw new InvalidPaymentInfoException("expiration date is null");
        }

        if (sum == 0) {
            throw new MissingProductException("Total amount of ordered product prices is null");
        }

        if (articleList == null) {
            throw new MissingProductException("Missing products");
        }

        if (customerOpt == null) {
            throw new MissingCustomerIdException(String.format("user is not found"));
        }

    }

    public List<CountryOrderCount> getOrdersByCustomerId(int articleId) {
        return orderRepository.findTopCountriesByArticle(articleId,(Pageable) PageRequest.of(0, 5));
    }

}
