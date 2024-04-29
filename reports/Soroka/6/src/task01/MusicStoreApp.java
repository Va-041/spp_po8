import java.util.Arrays;
import java.util.List;

// Интерфейс Strategy
interface MusicStoreStrategy
{
    void processOrder(Customer customer, Order order);
}

// Реализация стратегий
class StandardStrategy implements MusicStoreStrategy
{
    @Override
    public void processOrder(Customer customer, Order order)
    {
        // Обработка стандартного заказа
        System.out.println("\nОбрабатывается стандартный заказ для " + customer.getName() + ".");
        System.out.println("Товары:");
        for (Product product : order.getProducts()) {
            System.out.println("- " + product.getName() + " (" + String.format("%.0f р.", product.getPrice()) + ")");
        }
        order.displayTotalPrice();
    }
}

class DiscountStrategy implements MusicStoreStrategy
{
    // Константы для скидок
    private static final double DISCOUNT_THRESHOLD = 1000.0; // Порог суммы заказа для скидки
    private static final double DISCOUNT_RATE = 0.1; // Ставка скидки (10%)
    private static final int MINIMUM_PRODUCTS_FOR_DISCOUNT = 3; // Минимальное количество товаров для скидки

    @Override
    public void processOrder(Customer customer, Order order)
    {
        // Обработка заказа с применением скидок
        System.out.println("\nОбрабатывается заказ с применением скидок для " + customer.getName() + ".");
        System.out.println("Товары:");
        for (Product product : order.getProducts()) {
            System.out.println("- " + product.getName() + " (" + String.format("%.0f р.", product.getPrice()) + ")");
        }

        // Проверяем сумму заказа для возможной скидки
        double totalPrice = order.getTotalPrice();
        if (totalPrice >= DISCOUNT_THRESHOLD || order.getProducts().size() >= MINIMUM_PRODUCTS_FOR_DISCOUNT) {
            applyDiscount(order);
        }
        // Выводим общую сумму
        order.displayTotalPrice();
    }

    // Применение скидки
    private void applyDiscount(Order order)
    {
        System.out.println("\nПрименяется скидка в размере " + DISCOUNT_RATE * 100 + "%");
        List<Product> products = order.getProducts();
        double totalDiscount = 0.0; // Инициализируем переменную для хранения общей суммы скидки
        for (Product product : products) {
            double discountAmount = product.getOriginalPrice() * DISCOUNT_RATE;
            double discountedPrice = product.getOriginalPrice() - discountAmount;
            product.applyDiscount(discountedPrice);
            totalDiscount += discountAmount; // Обновляем общую сумму скидки
        }
        System.out.println("Общая скидка: " + totalDiscount + " руб."); // Выводим общую сумму скидки
    }

}


class VIPStrategy implements MusicStoreStrategy
{
    @Override
    public void processOrder(Customer customer, Order order)
    {
        // Обработка VIP-заказа
        System.out.println("\nОбрабатывается VIP-заказ для " + customer.getName() + ".");
        System.out.println("Товары:");
        for (Product product : order.getProducts()) {
            System.out.println("- " + product.getName() + " (" + String.format("%.0f р.", product.getPrice()) + ")");
        }
        order.displayTotalPrice();
    }
}

// Класс MusicStore
class MusicStore
{
    private MusicStoreStrategy strategy;

    public MusicStore(MusicStoreStrategy strategy) {
        this.strategy = strategy;
    }

    public void processOrder(Customer customer, Order order) {
        strategy.processOrder(customer, order);
    }

    public void setStrategy(MusicStoreStrategy strategy) {
        this.strategy = strategy;
    }
}

// Класс Customer
class Customer
{
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Класс Order
class Order
{
    private final Customer customer;
    private final List<Product> products;

    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice()
    {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public void displayTotalPrice()
    {
        double totalPrice = getTotalPrice();
        System.out.println("Общая сумма: " + String.format("%.0f р.", totalPrice));
    }
}

// Класс Product
class Product
{
    private final String name;
    private double price;
    private final double originalPrice; // Добавляем поле для хранения оригинальной цены

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.originalPrice = price; // Сохраняем оригинальную цену при создании продукта
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    // Метод для применения скидки к цене продукта
    public void applyDiscount(double discountedPrice)
    {
        double discountAmount = this.originalPrice - discountedPrice; // Вычисляем сумму скидки
        this.price = discountedPrice;
        System.out.println("- Скидка на: " + name + " = "+ discountAmount + " руб."); // Выводим сумму скидки для каждого продукта
    }
}

// Класс MusicStoreApp
public class MusicStoreApp
{
    public static void main(String[] args)
    {
        // Создание магазина с базовой стратегией
        MusicStore store = new MusicStore(new StandardStrategy());
        // Создание магазина с discount стратегией
        MusicStore storeDiscount = new MusicStore(new DiscountStrategy());

        // Создание покупателей и заказов
        Customer customer1 = new Customer("Иванов Иван");
        Order order1 = new Order(customer1, Arrays.asList(new Product("Гитара пятиструночная", 1000), new Product("Струны гитарные", 50)));
        Customer customer2 = new Customer("Сергеев Сергей");
        Order order2 = new Order(customer2, Arrays.asList(new Product("Виолончель", 800), new Product("Балалайка", 400)));
        // Обработка заказов
        store.processOrder(customer1, order1);
        store.processOrder(customer2, order2);

        // Переключение на VIP-стратегию
        store.setStrategy(new VIPStrategy());
        // VIP-заказ
        Customer vipCustomer = new Customer("Алексеев Алексей");
        Order vipOrder = new Order(vipCustomer, Arrays.asList(new Product("Пианино Леруаль", 5000), new Product("Гитара ДиКалино", 25000)));
        store.processOrder(vipCustomer, vipOrder);

        // Переключение на Discount-стратегию
        store.setStrategy(new DiscountStrategy());
        // Создание покупателей и заказов
        Customer customer3 = new Customer("Мариевич Мария");
        Order order3 = new Order(customer3, Arrays.asList(new Product("Гитара четырёхструночная", 800), new Product("Струны гитарные", 70), new Product("Набор инструментов для настройки гитары", 250)));
        store.processOrder(customer3, order3);
    }
}
