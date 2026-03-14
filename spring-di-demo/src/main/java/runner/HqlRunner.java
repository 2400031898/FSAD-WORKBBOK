package com.klu.springdidemo.runner;

import com.klu.springdidemo.entity.Product;
import com.klu.springdidemo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class HqlRunner implements CommandLineRunner {

    private final ProductRepository repo;

    public HqlRunner(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String[] args) {

        // Task 2: Insert products
        repo.save(new Product("Laptop","Electronics",65000,10));
        repo.save(new Product("Mobile","Electronics",25000,15));
        repo.save(new Product("Mouse","Accessories",800,30));
        repo.save(new Product("Keyboard","Accessories",1500,25));
        repo.save(new Product("Chair","Furniture",4000,5));
        repo.save(new Product("Table","Furniture",7000,3));
        repo.save(new Product("Pen","Stationery",20,100));

        // Task 3
        repo.sortByPriceAsc().forEach(p -> System.out.println("ASC: "+p.getName()));
        repo.sortByPriceDesc().forEach(p -> System.out.println("DESC: "+p.getName()));

        // Task 4
        repo.sortByQuantityDesc().forEach(p -> System.out.println("QTY: "+p.getName()));

        // Task 5 Pagination
        repo.paginate(PageRequest.of(0,3)).forEach(p -> System.out.println("Page1: "+p.getName()));
        repo.paginate(PageRequest.of(1,3)).forEach(p -> System.out.println("Page2: "+p.getName()));

        // Task 6 Aggregates
        System.out.println("Total: "+repo.totalCount());
        System.out.println("Available: "+repo.availableCount());

        repo.countByDescription()
            .forEach(o -> System.out.println(o[0]+" = "+o[1]));

        Object[] mm = repo.minMaxPrice();
        System.out.println("Min: "+mm[0]+" Max: "+mm[1]);

        // Task 7
        repo.groupByDescription()
            .forEach(o -> System.out.println(o[0]+" Qty:"+o[1]));

        // Task 8
        repo.priceRange(1000,30000)
            .forEach(p -> System.out.println("Range: "+p.getName()));

        // Task 9 LIKE
        repo.nameLike("M%").forEach(p -> System.out.println("Starts M: "+p.getName()));
        repo.nameLike("%p").forEach(p -> System.out.println("Ends p: "+p.getName()));
        repo.nameLike("%ou%").forEach(p -> System.out.println("Contains ou: "+p.getName()));
        repo.nameLength(5).forEach(p -> System.out.println("Length 5: "+p.getName()));
    }
}
