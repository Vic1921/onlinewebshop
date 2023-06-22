package at.dke.onlinewebshop.nosql.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.dke.onlinewebshop.nosql.entities.CustomerNoSQL;

@Repository
public interface CustomerRepositoryNoSQL extends MongoRepository<CustomerNoSQL, String> {

    @Aggregation(pipeline = {
            "{ $match: { _class: { $eq: \"com.imse.onlineshop.nosql.entities.CustomerNoSQL\" }, } }",
            "{$unwind: \"$returnedOrders\"}",
            "{ $match: { $expr: { $eq: [{$subtract: [{$year: \"$$NOW\"}, {$year: \"$returnedOrders.date\"}]}, 1] } } }",
            "{$addFields: {\"returnedOrders.date\": {$year: \"$returnedOrders.date\"}}}",
            "{$unwind: \"$returnedOrders.products\"}",
            "{ $lookup: { from: \"users\", let: {product: \"$returnedOrders.products.product\"}, pipeline: [ {$unwind: \"$products\"}, { $match: { $expr: { $eq: [\"$products.productNumber\", \"$$product\"] } } }, { $project: { _id: 0, \"products.productNumber\": 1, \"products.productName\": 1 } } ], as: \"returnedProductProducer\" } }",
            "{$unwind: \"$returnedProductProducer\"}",
            "{ $group: { _id: { name: \"$name\", surname: \"$surname\", productName: \"$returnedProductProducer.products.productName\", year: \"$returnedOrders.date\" }, totalReturned: {$sum: \"$returnedOrders.products.amount\"} } }",
            "{$sort: {\"totalReturned\": -1,}}",
            "{ $group: { _id: { name: \"$_id.name\", surname: \"$_id.surname\", year: \"$_id.year\", }, products: { $push: { productName: \"$_id.productName\", totalReturned: \"$totalReturned\" } } } }",
            "{ $project: { _id: 0, name: \"$_id.name\", surname: \"$_id.surname\", year: \"$_id.year\", products: { $slice: [\"$products\", 0, 5] } } }",
            "{$sort: {\"name\": 1, \"surname\": 1}}"})
    List<CustomerReportNoSQL> report();

    @Aggregation(
            pipeline = {"{$match: {_class: {$eq: \"com.imse.onlineshop.nosql.entities.CustomerNoSQL\"}}}",
                    "{$project: {_id: 0, returnedOrdersLen: {$size: \"$returnedOrders\"}}}",
                    "{$group: {_id: null, total: {$sum: \"$returnedOrdersLen\"}}}",
                    "{$project: {_id: 0, total: {$add: [\"$total\", 1]}}}",})
    Long nextReturnOrderId();

}

