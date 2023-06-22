package at.dke.onlinewebshop.sql.services;

import at.dke.onlinewebshop.sql.repositories.CountryOrderCount;
import at.dke.onlinewebshop.sql.repositories.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ReportService {

    private final OrderRepository orderRepository;

    public ReportService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<CountryOrderCount> generateReport(int articleId) {
       // return orderRepository.findTopCountriesByArticle(articleId, (Pageable) PageRequest.of(0, 5));
        return null;
    }
}

