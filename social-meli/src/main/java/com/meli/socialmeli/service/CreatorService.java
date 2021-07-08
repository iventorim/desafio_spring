package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.repository.ClientRepository;
import com.meli.socialmeli.repository.PostRepository;
import com.meli.socialmeli.repository.ProductRepository;
import com.meli.socialmeli.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreatorService {

    private final ClientRepository clientRepository;

    private final PostRepository postRepository;

    private final ProductRepository productRepository;

    private final SellerRepository sellerRepository;

    public CreatorService(ClientRepository clientRepository, PostRepository postRepository, ProductRepository productRepository, SellerRepository sellerRepository) {
        this.clientRepository = clientRepository;
        this.postRepository = postRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        // populateDatabase();
    }

    public void populateDatabase() {
        List<Product> products = this.populateProducts();
        List<Client> clients = this.populateClients();
        List<Seller> sellers = this.populateSellers(clients);
        List<Post> posts = this.populatePosts(products,sellers);
    }

    private List<Product> populateProducts() {
        List<Product> products = List.of(
                new Product("Headset","Gamer","Razer","Green","Sem bateria"),
                new Product("Cadeira", "Gamer", "Racer", "Red black", "Special edition"),
                new Product("PS5", "Gamer", "Sony", "White", "Digital media"),
                new Product("TV", "Eletrônico", "Samsung", "Pink", "Smart"),
                new Product("Carro", "Veículo", "Tesla", "White", "Câmbio automático"),
                new Product("Teclado K55", "Gamer", "Corsair", "RGB", "Com fio"),
                new Product("Mouse Cobra","Gamer", "Redragon", "Preto", "Com fio"),
                new Product("Monitor Nitro XV252Q", "Gamer", "Acer", "Preto", "4K")
        );
        products.forEach(productRepository::save);
        return products;
    }

    private List<Client> populateClients() {
        List<Client> clients = List.of(
                new Client("jose.mane"),
                new Client("jose.roela"),
                new Client("usuario1"),
                new Client("usuario2"),
                new Client("usuario3")
        );
        clients.forEach(clientRepository::save);
        return clients;
    }

    private List<Seller> populateSellers(List<Client> clients) {
        List<Seller> sellers = List.of(
                new Seller("vendedor1",clients.subList(0,2)),
                new Seller("vendedor2",clients.subList(1,2)),
                new Seller("vendedor3",clients.subList(0,4)),
                new Seller("vendedor4",null)
        );

        sellers.forEach(sellerRepository::save);
        return sellers;
    }

    private List<Post> populatePosts(List<Product> products, List<Seller> sellers) {


        List<Post> posts = List.of(
                new Post(LocalDate.of(2021, 7,1), products.get(1), 100, 1550.50, sellers.get(0)),
                new Post(LocalDate.of(2021, 7,1), products.get(0), 100, 400.00, sellers.get(1)),
                new Post(LocalDate.of(2021, 7,2), products.get(2), 100, 4500.00, sellers.get(2)),
                new Post(LocalDate.of(2021, 7,3), products.get(3), 110, 2000.00, sellers.get(0)),
                new Post(LocalDate.of(2021, 6,1), products.get(4), 200,  300000.00, true, 0.1, sellers.get(0)),
                new Post(LocalDate.of(2021, 5,3), products.get(3), 110, 2330.00, true, 0.4, sellers.get(0))
        );
        posts.forEach(postRepository::save);
        return posts;
    }


}
