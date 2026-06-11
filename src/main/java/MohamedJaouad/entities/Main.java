package MohamedJaouad.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        List<Product> products = new ArrayList<>();
        products.add(new Product("Libro Java", "Books", 120.0));
        products.add(new Product("Pannolini Pampers", "Baby", 30.0));
        products.add(new Product("Macchinina", "Boys", 50.0));
        products.add(new Product("Romanzo Fantasy", "Books", 80.0));
        products.add(new Product("Felpa Ragazzi", "Boys", 40.0));



        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Mario Rossi", 1));
        customers.add(new Customer("Luca Bianchi", 2));
        customers.add(new Customer("Anna Verdi", 2));


        List<Order> orders = new ArrayList<>();

        orders.add(new Order(
                "DELIVERED",
                LocalDate.of(2021, 2, 10),
                LocalDate.of(2021, 2, 15),
                List.of(products.get(0), products.get(2)),
                customers.get(1)
        ));

        orders.add(new Order(
                "PENDING",
                LocalDate.of(2021, 3, 5),
                LocalDate.of(2021, 3, 12),
                List.of(products.get(1)),
                customers.get(0)
        ));

        orders.add(new Order(
                "DELIVERED",
                LocalDate.of(2021, 1, 20),
                LocalDate.of(2021, 1, 25),
                List.of(products.get(3), products.get(4)),
                customers.get(2)
        ));
        Map<Customer,List<Order>> orderByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
        orderByCustomer.forEach((customer, customerOrders) ->
                System.out.println("\nCliente: " + customer.getName() + " \nOrdini: " + customerOrders));

        Map<Customer, Double> totCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomer,
                        Collectors.summingDouble(Order::calcTotal)
                ));
        totCustomer.forEach((customer, total) ->
                System.out.println("Cliente: " + customer.getName() + "  Totale: " + total));

        List<Product> mostExProduct = products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(3)
                .toList();
        mostExProduct.forEach(System.out::println);

        double averageOrderAmount = orders.stream()
                .collect(Collectors.averagingDouble(Order::calcTotal));

        System.out.println("\nMedia importi ordini: " + averageOrderAmount);

    }

}
